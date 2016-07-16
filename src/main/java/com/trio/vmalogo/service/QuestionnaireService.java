package com.trio.vmalogo.service;

import com.trio.vmalogo.model.Questionnaire;

public interface QuestionnaireService {
    Questionnaire get(Integer id);
    int create(Questionnaire questionnaire);
    void update(Questionnaire questionnaire);
}
