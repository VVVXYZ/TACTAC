package com.trio.breakFast.service;


import com.trio.breakFast.enums.OrderStatus;
import com.trio.breakFast.model.BusinessOrder;
import com.trio.breakFast.model.Questionnaire;
import com.trio.breakFast.pageModel.FilterGroup;
import com.trio.breakFast.pageModel.OrderVO;
import com.trio.breakFast.pageModel.PageHelper;
import com.trio.breakFast.sys.entity.Account;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BusinessOrderService {
    BusinessOrder get(Integer id);
    void changeStatus(BusinessOrder businessOrder, OrderStatus status);
    void dispatch(BusinessOrder businessOrder, Account designer);
    void perfectOrder(BusinessOrder businessOrder, String zbj, String remark);
    void designPerfect(BusinessOrder businessOrder, MultipartFile designFile);
    void updateDesign(BusinessOrder businessOrder, String url);
    void checkSaleman(BusinessOrder businessOrder, Account saleman);
    void checkDesigner(BusinessOrder businessOrder, Account designer);
    PageHelper<OrderVO> findOrderVoByPage(FilterGroup filterGroup, Integer page, Integer rows, String sort, String order);
    int count(FilterGroup filterGroup);
    void generalOrder(Questionnaire questionnaire, Account saleman);
    List<BusinessOrder> find(FilterGroup filterGroup);
}
