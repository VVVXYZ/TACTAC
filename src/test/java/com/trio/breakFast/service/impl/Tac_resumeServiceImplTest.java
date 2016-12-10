package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_resumeDao;
import com.trio.breakFast.model.Tac_atoocomment;
import com.trio.breakFast.model.Tac_resume;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.service.Tac_userService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Tac_resumeServiceImplTest {

    @InjectMocks
    Tac_resumeServiceImpl tac_resumeService=new Tac_resumeServiceImpl();

    @Mock
    Tac_resumeDao tac_resumeDao;
    @Mock
    Tac_userService tac_userService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateResume() throws Exception {

        try
        {
            when(tac_userService.get(anyString())).thenReturn(new Tac_user());
            tac_resumeService.createResume(anyString(), anyString(), anyString (),anyString (),
                    anyString (), anyString ());
            verify(tac_userService.get(anyString()));
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetResumeBuID() throws Exception {

        try
        {
            when(tac_resumeDao.get(anyString(),anyMap())).thenReturn(new Tac_resume());
            assertThat(tac_resumeService.getResumeBuID(anyInt()),is(new Tac_resume()) );
            verify(tac_resumeDao.get(anyString(),anyMap()));
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testUpdateResume() throws Exception {

        try
        {
            tac_resumeService.updateResume(anyInt(), anyString(), anyString(), anyString(),
                    anyString(), anyString(), anyString());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetResume() throws Exception {

        try
        {

            when(tac_resumeService.getResume(anyInt())).thenReturn(new Tac_resume());
            assertThat(tac_resumeService.getResume(anyInt()),is(new Tac_resume()) );
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetResumeByName() throws Exception {

        try
        {

            when(tac_resumeDao.get(anyString(),anyMap())).thenReturn(new Tac_resume());
            assertThat(tac_resumeService.getResumeByName(anyString()),is(new Tac_resume()) );
            verify(tac_resumeDao.get(anyString(),anyMap()));

        }
        catch (Exception e)
        {

        }
    }
}