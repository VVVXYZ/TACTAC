package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_adviceDao;
import com.trio.breakFast.model.Tac_advice;
import com.trio.breakFast.model.Tac_atoocomment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Tac_adviceServiceImplTest {

    @InjectMocks
    Tac_adviceServiceImpl tac_adviceService=new Tac_adviceServiceImpl();

    @Mock
    Tac_adviceDao tac_adviceDao;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateAdvice() throws Exception {

        try
        {
            tac_adviceService.createAdvice(anyInt(),anyString(),anyString(),anyString(),anyString());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetAdvice() throws Exception {

        try
        {
            List<Tac_advice> list = new ArrayList<Tac_advice>();
            when(tac_adviceDao.find(anyString(),anyMap(), anyInt(), anyInt() ) ).thenReturn(list) ;
            assertThat(tac_adviceService.getAdvice(anyInt(), anyInt()),is( list )  );
            verify(tac_adviceDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }
}