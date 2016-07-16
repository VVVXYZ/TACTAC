package com.trio.breakFast.service.impl;

import com.google.common.base.Strings;
import com.trio.breakFast.dao.BusinessOrderDao;
import com.trio.breakFast.dao.QuestionnaireDao;
import com.trio.breakFast.enums.OrderStatus;
import com.trio.breakFast.model.BusinessOrder;
import com.trio.breakFast.model.Questionnaire;
import com.trio.breakFast.pageModel.FilterGroup;
import com.trio.breakFast.pageModel.OrderVO;
import com.trio.breakFast.pageModel.PageHelper;
import com.trio.breakFast.service.BlackService;
import com.trio.breakFast.service.BusinessOrderService;
import com.trio.breakFast.service.FileUploadService;
import com.trio.breakFast.sys.entity.Account;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.HqlUtil;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class BusinessOrderServiceImpl implements BusinessOrderService {
    @Autowired
    private BusinessOrderDao businessOrderDao;
    @Autowired
    private BlackService blackService;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private QuestionnaireDao questionnaireDao;

    void checkOrderOperate(BusinessOrder businessOrder) {
        if (businessOrder.getStatus() == OrderStatus.finished) {
            throw new ServiceException("该订单已结束！");
        }
        if (businessOrder.getStatus() == OrderStatus.cancel) {
            throw new ServiceException("该订单已取消！");
        }
    }

    @Override
    public BusinessOrder get(Integer id) {
        return ServiceHelper.get(businessOrderDao, BusinessOrder.class, id);
    }

    @Override
    public void changeStatus(BusinessOrder businessOrder, OrderStatus status) {
        checkOrderOperate(businessOrder);
        businessOrder.setStatus(status);
        businessOrder.setOperateTime(Calendar.getInstance().getTime());
        if(status==OrderStatus.paid){
            businessOrder.setPayedTime(businessOrder.getOperateTime());
        }
        if(status==OrderStatus.cancel){
            if(Strings.isNullOrEmpty(businessOrder.getQuestionnaire().getQq()) == false){
                blackService.put(businessOrder.getQuestionnaire().getQq());
            }
            if(Strings.isNullOrEmpty(businessOrder.getQuestionnaire().getCellphone()) == false){
                blackService.put(businessOrder.getQuestionnaire().getCellphone());
            }
        }
        ServiceHelper.update(businessOrderDao, BusinessOrder.class, businessOrder);
    }

    @Override
    public void dispatch(BusinessOrder businessOrder, Account designer) {
        checkOrderOperate(businessOrder);
        businessOrder.setDesigner(designer);
        businessOrder.setOperateTime(Calendar.getInstance().getTime());
        businessOrder.setDispatchTime(businessOrder.getOperateTime());
        ServiceHelper.update(businessOrderDao, BusinessOrder.class, businessOrder);
    }

    @Override
    public void perfectOrder(BusinessOrder businessOrder, String zbj, String remark) {
        checkOrderOperate(businessOrder);
        businessOrder.setZbj(zbj);
        businessOrder.setRemark(remark);
        businessOrder.setOperateTime(Calendar.getInstance().getTime());
        ServiceHelper.update(businessOrderDao, BusinessOrder.class, businessOrder);
    }

    @Override
    public void designPerfect(BusinessOrder businessOrder, MultipartFile designFile) {
        String url = fileUploadService.upload(designFile, "design").getValue();
        businessOrder.setAttachmentUrl(url);
        businessOrder.setOperateTime(Calendar.getInstance().getTime());
        ServiceHelper.update(businessOrderDao, BusinessOrder.class, businessOrder);
    }

    @Override
    public void updateDesign(BusinessOrder businessOrder, String url) {
        checkOrderOperate(businessOrder);
        businessOrder.setAttachmentUrl(url);
        businessOrder.setOperateTime(Calendar.getInstance().getTime());
        ServiceHelper.update(businessOrderDao, BusinessOrder.class, businessOrder);
    }

    @Override
    public void checkSaleman(BusinessOrder businessOrder, Account saleman) {
        if (businessOrder.getSaleman().getAccountId().equals(saleman.getAccountId()) == false) {
            throw new ServiceException("不是你的业务单!无法操作");
        }
    }

    @Override
    public void checkDesigner(BusinessOrder businessOrder, Account designer) {
        if (businessOrder.getDesigner().getAccountId().equals(designer.getAccountId()) == false) {
            throw new ServiceException("不是你的设计单!无法操作");
        }
    }

    @Override
    public PageHelper<OrderVO> findOrderVoByPage(FilterGroup filterGroup, Integer page, Integer rows, String sort, String order) {
        PageHelper<OrderVO> pageHelper = new PageHelper<OrderVO>();

        page = page != null && page > 0 ? page : 1;
        pageHelper.setPage(page);
        rows = rows != null && rows > 0 ? rows : 10;
        pageHelper.setRows(rows);
        pageHelper.setOrder(sort);
        pageHelper.setSort(order);

//        Map<String, Object> params = new HashMap<String, Object>();
//        String hql = HqlUtil.getQueryHql("a", BusinessOrder.class, filterGroup, params, new HqlUtil.Sorter(sort, order));
//        hql = "select new " + OrderVO.class.getName()
//                + "(a.businessOrderId,a.saleman.name,a.designer.name,a.questionnaire.qq,a.questionnaire.cellphone," +
//                "a.zbj,a.status,a.submitTime,a.payedTime,a.dispatchTime,a.operateTime,a.attachmentUrl,a.remark) "
//                + hql;
        List<BusinessOrder> businessOrderList = ServiceHelper.find(businessOrderDao,BusinessOrder.class,filterGroup,page,rows,new HqlUtil.Sorter(sort,order));
        List<OrderVO> orderVOList = new ArrayList<OrderVO>();
        for (BusinessOrder businessOrder : businessOrderList) {
            OrderVO orderVO = new OrderVO();
            orderVO.setBusinessOrderId(businessOrder.getBusinessOrderId());
            orderVO.setSalemanName(businessOrder.getSaleman().getName());
            if(businessOrder.getDesigner()!=null){
                orderVO.setDesignerName(businessOrder.getDesigner().getName());
            }
            orderVO.setQq(businessOrder.getQuestionnaire().getQq());
            orderVO.setCellphone(businessOrder.getQuestionnaire().getCellphone());


            if (blackService.get(businessOrder.getQuestionnaire().getQq()) != null) {
                orderVO.setQqBlack(true);
            }
            if (blackService.get(businessOrder.getQuestionnaire().getCellphone()) != null) {
                orderVO.setCellphoneBlack(true);
            }

            orderVO.setZbj(businessOrder.getZbj());
            orderVO.setStatus(businessOrder.getStatus());
            orderVO.setSubmitTime(businessOrder.getSubmitTime());
            orderVO.setPayedTime(businessOrder.getPayedTime());
            orderVO.setDispatchTime(businessOrder.getDispatchTime());
            orderVO.setOperateTime(businessOrder.getOperateTime());
            orderVO.setAttachmentUrl(businessOrder.getAttachmentUrl());
            orderVO.setRemark(businessOrder.getRemark());
            orderVO.setChinese(businessOrder.getQuestionnaire().getChinese());
            orderVOList.add(orderVO);
        }
        pageHelper.setDataList(orderVOList);
        pageHelper.setTotalRows(ServiceHelper.count(businessOrderDao, BusinessOrder.class, filterGroup).intValue());
        pageHelper.countTotalPages();
        pageHelper.setMessage("查询成功");

        return pageHelper;
    }

    @Override
    public int count(FilterGroup filterGroup) {
        return ServiceHelper.count(businessOrderDao, BusinessOrder.class, filterGroup).intValue();
    }

    @Override
    public List<BusinessOrder> find(FilterGroup filterGroup) {
        return ServiceHelper.find(businessOrderDao, BusinessOrder.class,filterGroup);
    }

    @Override
    public void generalOrder(Questionnaire questionnaire, Account saleman) {
        questionnaire.setQuestionnaireId(ServiceHelper.create(questionnaireDao,Questionnaire.class, questionnaire));
        BusinessOrder businessOrder = new BusinessOrder();
        businessOrder.setQuestionnaire(questionnaire);
        businessOrder.setOperateTime(Calendar.getInstance().getTime());
        businessOrder.setSubmitTime(businessOrder.getOperateTime());
        businessOrder.setSaleman(saleman);
        businessOrder.setStatus(OrderStatus.unpaid);
        ServiceHelper.create(businessOrderDao, BusinessOrder.class, businessOrder);
    }
}
