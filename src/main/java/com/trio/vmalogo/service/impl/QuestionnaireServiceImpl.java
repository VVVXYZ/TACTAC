package com.trio.vmalogo.service.impl;

import com.trio.vmalogo.dao.QuestionnaireDao;
import com.trio.vmalogo.model.Questionnaire;
import com.trio.vmalogo.service.QuestionnaireService;
import com.trio.vmalogo.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author loser
 * @ClassName BlackServiceImpl
 * @Description
 * @Date 2016/07/03 1:29
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
   @Autowired
   private QuestionnaireDao questionnaireDao;

    @Override
    public Questionnaire get(Integer id) {
        return ServiceHelper.get(questionnaireDao, Questionnaire.class, id);
    }

    @Override
    public int create(Questionnaire questionnaire) {
        return ServiceHelper.create(questionnaireDao, Questionnaire.class, questionnaire);
    }

    @Override
    public void update(Questionnaire questionnaire) {
        ServiceHelper.update(questionnaireDao, Questionnaire.class, questionnaire);
    }
}
