package com.trio.vmalogo.service;


import com.trio.vmalogo.enums.OrderStatus;
import com.trio.vmalogo.model.BusinessOrder;
import com.trio.vmalogo.model.Questionnaire;
import com.trio.vmalogo.pageModel.FilterGroup;
import com.trio.vmalogo.pageModel.OrderVO;
import com.trio.vmalogo.pageModel.PageHelper;
import com.trio.vmalogo.sys.entity.Account;
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
