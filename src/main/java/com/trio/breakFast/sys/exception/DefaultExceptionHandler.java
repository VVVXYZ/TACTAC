package com.trio.breakFast.sys.exception;

import com.trio.breakFast.pageModel.MessageHelper;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("/jsp/error/unauthorized.jsp");
        return mv;
    }

    @ExceptionHandler({UnknownAccountException.class})
    public ModelAndView processUnknownAccountException(NativeWebRequest request, UnknownAccountException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("/jsp/system/loginNew.jsp");
        return mv;
    }

    @ExceptionHandler({Exception.class})
    public MessageHelper processException(NativeWebRequest request, Exception e) {
        MessageHelper messageHelper = new MessageHelper();
        Logger.getLogger(this.getClass()).error(e,e);
        messageHelper.setSuccess(false);
        messageHelper.setMessage("操作失败,未知错误，请联系管理员");
        return messageHelper;
    }
}
