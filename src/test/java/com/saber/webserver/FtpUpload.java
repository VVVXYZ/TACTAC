package com.saber.webserver;

import com.trio.breakFast.model.BusinessOrder;
import com.trio.breakFast.sys.entity.Account;
import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by xiaozhi on 2015/8/17.
 */
public class FtpUpload {
    @Test
    public void terset(){
        ExpressionParser expressionParser = new SpelExpressionParser();
        BusinessOrder businessOrder = new BusinessOrder();
        businessOrder.setSaleman(new Account());
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(businessOrder);
        Class clazz = expressionParser.parseExpression("saleman.username").getValueType(standardEvaluationContext);
        System.out.print(clazz);
    }
}
