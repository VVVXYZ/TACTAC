package com.trio.vmalogo.controller;

import com.trio.vmalogo.model.BusinessOrder;
import com.trio.vmalogo.service.BusinessOrderService;
import com.trio.vmalogo.util.PdfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author loser
 * @ClassName PdfController
 * @Description
 * @Date 2016/05/31 17:25
 */
@Controller
@RequestMapping("/pdf")
public class PdfController extends BaseController {

    @Autowired
    private BusinessOrderService businessOrderService;

//    @RequestMapping(value = "/questionnaire.pdf", method = RequestMethod.POST)
//    public ModelAndView questionnaire(ModelAndView modelAndView,HttpServletRequest request,@RequestParam Integer businessOrderId){
//        modelAndView.setView(new PdfView());
//        BusinessOrder businessOrder = businessOrderService.get(businessOrderId);
//        modelAndView.getModelMap().addAttribute(PdfUtil.QUESTIONNAIRE_KEY, businessOrder.getQuestionnaire());
//        modelAndView.getModelMap().addAttribute(PdfUtil.QUESTIONNAIRE_PICTURE_PATH, request.getSession().getServletContext().getRealPath("/"));
//        modelAndView.getModelMap().addAttribute(PdfUtil.QUESTIONNAIRE_FILE_NAME, businessOrder.getQuestionnaire().getChinese());
//        return modelAndView;
//    }

    @RequestMapping(value = "/questionnaire.pdf", method = RequestMethod.POST)
    public String questionnaire(Model model,HttpServletRequest request,@RequestParam Integer businessOrderId){
        BusinessOrder businessOrder = businessOrderService.get(businessOrderId);
        model.addAttribute(PdfUtil.QUESTIONNAIRE_KEY, businessOrder.getQuestionnaire());
        model.addAttribute(PdfUtil.QUESTIONNAIRE_PICTURE_PATH, request.getSession().getServletContext().getRealPath("/"));
        model.addAttribute(PdfUtil.QUESTIONNAIRE_FILE_NAME, businessOrder.getQuestionnaire().getChinese());
        return "pdfView";
    }
}
