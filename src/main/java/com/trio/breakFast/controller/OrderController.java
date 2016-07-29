package com.trio.breakFast.controller;

import com.trio.breakFast.enums.LogoStyle;
import com.trio.breakFast.enums.OrderStatus;
import com.trio.breakFast.enums.Role;
import com.trio.breakFast.model.BusinessOrder;
import com.trio.breakFast.model.Questionnaire;
import com.trio.breakFast.pageModel.*;
import com.trio.breakFast.service.BusinessOrderService;
import com.trio.breakFast.service.QuestionnaireService;
import com.trio.breakFast.sys.bind.annotation.CurrentUser;
import com.trio.breakFast.sys.entity.Account;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.sys.service.AccountService;
import com.trio.breakFast.util.HqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;

//import com.trio.breakFast.service.OrderService;

/**
 * @author loser
 * @ClassName OrderController
 * @Description
 * @Date 2016/07/03 14:40
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private BusinessOrderService businessOrderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private QuestionnaireService questionnaireService;
//    @Autowired
//    private OrderService orderService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("navMenu", "order");
        String url = "/jsp/order/index.jsp";
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Role.saleman.name())) {
            model.addAttribute("designerList", accountService.findDesigner());
            url = "/jsp/order/sale.jsp";
        } else if (subject.hasRole(Role.designer.name())) {
            model.addAttribute("designerList", accountService.findDesigner());
            url = "/jsp/order/design.jsp";
        }
        return url;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageHelper<OrderVO> list(@CurrentUser Account account, String filters, Integer page, Integer rows, String sort,@RequestParam(defaultValue = HqlUtil.DESC) String order) {
        FilterGroup filterGroup = FilterGroup.newFilterGroup(filters);
//        FilterGroup filterGroup1=new FilterGroup();
//        filterGroup1.addRules(new FilterRule("asd",HqlUtil.EQUAL,1));
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Role.saleman.name())) {
            filterGroup.addRules(new FilterRule("saleman.accountId", HqlUtil.EQUAL, account.getAccountId()));
        } else if (subject.hasRole(Role.designer.name())) {
            filterGroup.addRules(new FilterRule("designer.accountId", HqlUtil.EQUAL, account.getAccountId()));
            filterGroup.addRules(new FilterRule("status", HqlUtil.NO_EQUAL, OrderStatus.cancel.name()));
        }
        return businessOrderService.findOrderVoByPage(filterGroup, page, rows, sort, order);
    }

    @RequiresRoles("saleman")
    @ResponseBody
    @RequestMapping(value = "/perfect", method = RequestMethod.POST)
    public MessageHelper perfect(@CurrentUser Account account, Integer businessOrderId, String zbj, String logoName, String remark) {
        MessageHelper messageHelper = new MessageHelper();
        try {
            BusinessOrder businessOrder = businessOrderService.get(businessOrderId);
            businessOrderService.checkSaleman(businessOrder, account);
            businessOrderService.perfectOrder(businessOrder, zbj, remark);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("录入成功");
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }

    @RequiresRoles("saleman")
    @ResponseBody
    @RequestMapping(value = "/dispatch", method = RequestMethod.POST)
    public MessageHelper dispatch(@CurrentUser Account account, Integer businessOrderId, Integer designerId) {
        MessageHelper messageHelper = new MessageHelper();
        try {
            BusinessOrder businessOrder = businessOrderService.get(businessOrderId);
            Account designer = accountService.get(designerId);
            businessOrderService.checkSaleman(businessOrder, account);
            businessOrderService.dispatch(businessOrder, designer);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("派单成功");
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }

    @RequiresRoles("saleman")
    @ResponseBody
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public MessageHelper changeStatus(@CurrentUser Account account, Integer businessOrderId, OrderStatus status) {
        MessageHelper messageHelper = new MessageHelper();
        try {
            BusinessOrder businessOrder = businessOrderService.get(businessOrderId);
            businessOrderService.checkSaleman(businessOrder, account);
            businessOrderService.changeStatus(businessOrder, status);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("变更成功");
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }

    @RequiresRoles("designer")
    @ResponseBody
    @RequestMapping(value = "/changeDesignStatus", method = RequestMethod.POST)
    public MessageHelper changeDesignStatus(@CurrentUser Account account, Integer businessOrderId, OrderStatus status) {
        MessageHelper messageHelper = new MessageHelper();
        try {
            System.out.print(status);
            BusinessOrder businessOrder = businessOrderService.get(businessOrderId);
            businessOrderService.checkDesigner(businessOrder, account);
            businessOrderService.changeStatus(businessOrder, status);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("变更成功");
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }

    @RequiresRoles("designer")
    @ResponseBody
    @RequestMapping(value = "/designPerfect", method = RequestMethod.POST)
    public MessageHelper designPerfect(@CurrentUser Account account, Integer businessOrderId, @RequestParam MultipartFile designFile) {
        MessageHelper messageHelper = new MessageHelper();
        try {
            BusinessOrder businessOrder = businessOrderService.get(businessOrderId);
            businessOrderService.checkDesigner(businessOrder, account);
            businessOrderService.designPerfect(businessOrder, designFile);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("录入成功");
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }


    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    public String saleDetail(@PathVariable Integer orderId, Model model, HttpServletRequest request) throws Exception{
        String url = "/jsp/order/";
        Subject subject = SecurityUtils.getSubject();
        BusinessOrder businessOrder = businessOrderService.get(orderId);
        if(businessOrder==null){
            throw  new NoSuchRequestHandlingMethodException(request);
        }
        model.addAttribute("businessOrder",businessOrder);
        if(subject.hasRole(Role.saleman.name())){
            url += "saleDetail.jsp";
        }else if(subject.hasRole(Role.designer.name())){
            url += "designDetail.jsp";
        }else{
            throw  new NoSuchRequestHandlingMethodException(request);
        }
        model.addAttribute("navMenu", "order");
        return url;
    }

    @RequiresRoles(value = {"saleman","designer"}, logical = Logical.OR)
    @RequestMapping(value = "/questionnaireDetail/{questionnaireId}", method = RequestMethod.GET)
    public String questionnaireForOne(@PathVariable Integer questionnaireId,HttpServletRequest request, Model model) throws Exception{
        Questionnaire questionnaire = questionnaireService.get(questionnaireId);
        if(questionnaire==null){
            throw  new NoSuchRequestHandlingMethodException(request);
        }
        model.addAttribute("questionnaire", questionnaire);
        if(questionnaire.getAttachmentUrls()!=null) {
            model.addAttribute("pics", questionnaire.getAttachmentUrls().split(","));
        }
        if(questionnaire.getLogoStyles()!=null){
            model.addAttribute("styles", questionnaire.getLogoStyles().split(","));
        }
        return "/jsp/order/questionnaire.jsp";
    }

    @RequiresRoles("saleman")
    @ResponseBody
    @RequestMapping(value = "/updateQuestionnaire", method = RequestMethod.POST)
    public MessageHelper updateQuestionnaire(Questionnaire questionnaire,LogoStyle[] logoStyle,String[] attachmentUrl){
        MessageHelper messageHelper = new MessageHelper();
        try {
            messageHelper.setSuccess(true);
            if(logoStyle!=null&&logoStyle.length!=0){
                questionnaire.setLogoStyles(StringUtils.join(logoStyle, ","));
            }
            if(attachmentUrl!=null&&attachmentUrl.length!=0){
                questionnaire.setAttachmentUrls(StringUtils.join(attachmentUrl,","));
            }
            questionnaireService.update(questionnaire);
            messageHelper.setMessage("更新成功");
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MessageHelper login(String id,String psw){
        MessageHelper messageHelper= new MessageHelper();

        return messageHelper;
    }

}
