package com.trio.breakFast.service;

import com.trio.breakFast.model.Questionnaire;

public interface QuestionnaireService {
    Questionnaire get(Integer id);
    int create(Questionnaire questionnaire);
    void update(Questionnaire questionnaire);
}
