package com.trio.vmalogo.controller;

import com.google.common.base.Strings;
import com.trio.vmalogo.enums.OrderStatus;
import com.trio.vmalogo.enums.Role;
import com.trio.vmalogo.model.BusinessOrder;
import com.trio.vmalogo.pageModel.FilterGroup;
import com.trio.vmalogo.pageModel.FilterRule;
import com.trio.vmalogo.service.BusinessOrderService;
import com.trio.vmalogo.sys.bind.annotation.CurrentUser;
import com.trio.vmalogo.sys.entity.Account;
import com.trio.vmalogo.sys.views.ExcelView;
import com.trio.vmalogo.util.DateUtil;
import com.trio.vmalogo.util.HqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loser
 * @ClassName ExcelController
 * @Description
 * @Date 2016/06/02 18:25
 */
@Controller
@RequestMapping
public class ExcelController extends BaseController {
    @Autowired
    private BusinessOrderService businessOrderService;

    @RequestMapping("/demo/default.xsl")
    public ModelAndView excel(ModelAndView modelAndView) {
        modelAndView.setView(new ExcelView());
        modelAndView.addObject(ExcelView.COLUMN_NAME_KEY, new ArrayList<String>() {{
            add("头1");
            add("头2");
            add("头3");
            add("头4");
            add("头5");
            add("头6");
        }});
        modelAndView.addObject(ExcelView.COLUMN_DATA_KEY, new ArrayList<ArrayList<String>>() {{
            add(new ArrayList<String>() {{
                add("11梵蒂冈梵蒂冈的");
                add("1豆腐干豆腐干2");
                add("1豆腐干豆腐干梵蒂冈3");
                add("1反对广泛的豆腐干豆腐干豆腐干是豆腐干豆腐干反对4");
                add("1豆腐干豆腐干豆腐干豆腐干5");
                add("豆腐干豆腐干豆腐干反对16");
            }});
            add(new ArrayList<String>() {{
                add("21231231231");
                add("22123123121");
                add("223213213第三方斯蒂芬3");
                add("241");
                add("25");
                add("2斯蒂芬斯蒂芬斯蒂芬16");
            }});
            add(new ArrayList<String>() {{
                add("331211");
                add("32");
                add("33");
                add("34");
                add("35");
                add("36");
            }});
            add(new ArrayList<String>() {{
                add("4斯蒂芬森答复211");
                add("42");
                add("43");
                add("44");
                add("45");
                add("46");
            }});
            add(new ArrayList<String>() {{
                add("斯蒂芬森答复51");
                add("52");
                add("53");
                add("5斯蒂芬斯蒂芬森的4");
                add("55");
                add("56");
            }});
            add(new ArrayList<String>() {{
                add("61");
                add("6斯蒂芬森答复2");
                add("63");
                add("64");
                add("65");
                add("66");
            }});
            add(new ArrayList<String>() {{
                add("71");
                add("72");
                add("7斯蒂芬森答复3");
                add("74");
                add("7斯蒂芬森答复5");
                add("76");
            }});
            add(new ArrayList<String>() {{
                add("81");
                add("82");
                add("83");
                add("84");
                add("85");
                add("8送达方式的6");
            }});
        }});
        return modelAndView;
    }

    @RequestMapping("/order.xsl")
    public ModelAndView orderExcel(@CurrentUser Account account, ModelAndView modelAndView) {
        modelAndView.setView(new ExcelView());

        FilterGroup filterGroup = new FilterGroup();
        switch (account.getRole()) {
            case saleman:
                filterGroup.addRules(new FilterRule("saleman.accountId", HqlUtil.EQUAL, account.getAccountId()));
                break;
            case designer:
                filterGroup.addRules(new FilterRule("designer.accountId", HqlUtil.EQUAL, account.getAccountId()));
                filterGroup.addRules(new FilterRule("status", HqlUtil.NO_EQUAL, OrderStatus.cancel));
                break;
        }

        List<BusinessOrder> businessOrderList = businessOrderService.find(filterGroup);
        ArrayList<String> header = new ArrayList<String>() {{
            add("编号");
            add("logo名");
            add("业务员");
            add("设计师");
            add("客户qq");
            add("客户手机号");
            add("订单状态");
            add("提交时间");
            add("付款时间");
            add("派单时间");
            add("操作时间");
        }};
        modelAndView.addObject(ExcelView.COLUMN_NAME_KEY, header);
        if(account.getRole()== Role.saleman){
            header.add("备注");
        }
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        for (BusinessOrder businessOrder : businessOrderList) {
            ArrayList<String> rowData = new ArrayList<String>();
            rowData.add(businessOrder.getBusinessOrderId().toString());
            rowData.add(Strings.nullToEmpty(businessOrder.getQuestionnaire().getChinese()));
            rowData.add(Strings.nullToEmpty(businessOrder.getSaleman().getName()));
            if (businessOrder.getDesigner() != null) {
                rowData.add(Strings.nullToEmpty(businessOrder.getDesigner().getName()));
            } else {
                rowData.add("");
            }
            rowData.add(Strings.nullToEmpty(businessOrder.getQuestionnaire().getQq()));
            rowData.add(Strings.nullToEmpty(businessOrder.getQuestionnaire().getCellphone()));
            rowData.add(businessOrder.getStatus().getName());
            rowData.add(DateUtil.date2String(businessOrder.getSubmitTime(), DateUtil.DEFAULT_SECOND_FORMAT));

            if (businessOrder.getPayedTime() != null) {
                rowData.add(DateUtil.date2String(businessOrder.getPayedTime(), DateUtil.DEFAULT_SECOND_FORMAT));
            } else {
                rowData.add("");
            }
            if (businessOrder.getDispatchTime() != null) {
                rowData.add(DateUtil.date2String(businessOrder.getDispatchTime(), DateUtil.DEFAULT_SECOND_FORMAT));
            } else {
                rowData.add("");
            }
            rowData.add(DateUtil.date2String(businessOrder.getOperateTime(), DateUtil.DEFAULT_SECOND_FORMAT));

            if(account.getRole()== Role.saleman){
                rowData.add(Strings.nullToEmpty(businessOrder.getRemark()));
            }
            data.add(rowData);
        }
        modelAndView.addObject(ExcelView.COLUMN_DATA_KEY, data);
        return modelAndView;
    }
}
