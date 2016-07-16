package com.trio.breakFast.controller;

import com.google.common.base.Strings;
import com.trio.breakFast.enums.LogoStyle;
import com.trio.breakFast.model.Questionnaire;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.BusinessOrderService;
import com.trio.breakFast.service.FileUploadService;
import com.trio.breakFast.sys.entity.Account;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.sys.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author loser
 * @ClassName QuestionnaireController
 * @Description
 * @Date 2016/07/04 2:06
 */
@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    private BusinessOrderService businessOrderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String result(HttpServletRequest request,@RequestHeader("Referer") String refer) throws Exception{
        refer = Strings.emptyToNull(refer);
        String requestUrl = request.getRequestURL().toString();
        requestUrl = requestUrl.substring(0, requestUrl.indexOf("result"));

        if(refer.startsWith(requestUrl) == false){
            throw  new NoSuchRequestHandlingMethodException(request);
        }

        return "/jsp/questionnaire/result.jsp";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String questionnaire(@PathVariable String username,HttpServletRequest request, Model model) throws Exception{
        Account account = accountService.getSalemanByUsername(username);
        if(account == null){
            throw  new NoSuchRequestHandlingMethodException(request);
        }
        return "/jsp/questionnaire/index.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/pictureUpload", method = RequestMethod.POST)
    public MessageHelper pictureUpload(@RequestParam MultipartFile picture) {
        MessageHelper messageHelper = new MessageHelper();
        try {
            messageHelper.setSuccess(true);
            messageHelper.setMessage(fileUploadService.upload(picture, "questionnaire").getValue());
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }

    @ResponseBody
    @RequestMapping(value = "/{username}/generateOrder", method = RequestMethod.POST)
    public MessageHelper generateOrder(@PathVariable String username,Questionnaire questionnaire,LogoStyle[] logoStyle,String[] attachmentUrl){
        MessageHelper messageHelper = new MessageHelper();
        try {
            Account account = accountService.getSalemanByUsername(username);
            if(account == null){
                throw new ServiceException("没有找到相应的业务员");
            }
            messageHelper.setSuccess(true);
            if(logoStyle!=null&&logoStyle.length!=0){
                questionnaire.setLogoStyles(StringUtils.join(logoStyle,","));
            }
            if(attachmentUrl!=null&&attachmentUrl.length!=0){
                questionnaire.setAttachmentUrls(StringUtils.join(attachmentUrl,","));
            }

            businessOrderService.generalOrder(questionnaire, account);
            messageHelper.setMessage("生成成功");
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }
}
