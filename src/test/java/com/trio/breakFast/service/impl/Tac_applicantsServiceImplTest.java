package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_applicantsDao;
import com.trio.breakFast.dao.Tac_recruitDao;
import com.trio.breakFast.model.*;
import com.trio.breakFast.service.Tac_recruitService;
import com.trio.breakFast.service.Tac_resumeService;
import com.trio.breakFast.service.Tac_userService;
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

public class Tac_applicantsServiceImplTest {

    @InjectMocks
    Tac_applicantsServiceImpl tac_applicantsService=new Tac_applicantsServiceImpl();

    @Mock
    Tac_applicantsDao tac_applicantsDao;
    @Mock
    Tac_resumeService tac_resumeService;
    @Mock
    Tac_userService tac_userService;
    @Mock
    Tac_recruitService tac_recruitService;
    @Mock
    Tac_recruitDao tac_recruitDao;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetApplicant() throws Exception {

        try
        {
            List<Tac_applicants> list=new ArrayList<>();
            when(tac_applicantsDao.find(anyString(),anyMap(), anyInt(), anyInt() ) ).thenReturn(list) ;
            assertThat(tac_applicantsService.getApplicant(anyInt(), anyInt(), anyInt()),is( list )  );
            verify(tac_applicantsDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetChoosenApplicant() throws Exception {

        try
        {

            List<Tac_applicants> list=new ArrayList<>();
            when(tac_applicantsDao.find(anyString(),anyMap(), anyInt(), anyInt() ) ).thenReturn(list) ;
            assertThat(tac_applicantsService.getChoosenApplicant(anyInt(), anyInt(), anyInt()),is( list )  );
            verify(tac_applicantsDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testChooseApplicant() throws Exception {

        try
        {

            when(tac_applicantsDao.get(anyString(),anyMap())).thenReturn(null);
            tac_applicantsService.chooseApplicant(anyInt(),anyInt());
            //verify(tac_atoocommentDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testCancelChooseApplicant() throws Exception {

        try
        {

            when(tac_applicantsDao.get(anyString(),anyMap())).thenReturn(null);
            tac_applicantsService.CancelChooseApplicant(anyInt(), anyInt());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testCheckChoosen() throws Exception {

        try
        {
            when(tac_applicantsDao.get(anyString(),anyMap())).thenReturn(null);
            tac_applicantsService.checkChoosen(anyInt());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetApplicantForApplicant() throws Exception {

        try
        {

            List<Tac_applicants> list=new ArrayList<>();
            when(tac_applicantsDao.find(anyString(),anyMap(), anyInt(), anyInt() ) ).thenReturn(list) ;
            assertThat(tac_applicantsService.getApplicantForApplicant(anyInt(), anyInt(), anyInt()),is( list )  );
            verify(tac_applicantsDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetApplicantsByID() throws Exception {

        try
        {

            when(tac_applicantsDao.get(anyString(),anyMap())).thenReturn(new Tac_applicants());
            assertThat(tac_applicantsService.getApplicantsByID(anyInt()),is(new Tac_applicants()) );
            verify(tac_applicantsDao,times(1)).get(anyString(), anyMap());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetApplicants() throws Exception {

        try
        {

            when(tac_applicantsDao.get(anyString(),anyMap())).thenReturn(new Tac_applicants());
            assertThat(tac_applicantsService.getApplicants(anyInt(), anyInt()),is(new Tac_applicants()) );
            verify(tac_applicantsDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testCreateApplicants() throws Exception {

        try
        {

            when(tac_resumeService.getResume(anyInt())).thenReturn(new Tac_resume());
            when(tac_userService.getUserByID(anyInt())).thenReturn(new Tac_user());
            when(tac_recruitService.getRecruitByID(anyInt())).thenReturn(new Tac_recruit());
            tac_applicantsService.createApplicants(anyInt(),anyString(),anyString(),anyInt());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testChangeApplicants() throws Exception {

        try
        {
            tac_applicantsService.changeApplicants(anyInt(),anyInt());
        }
        catch (Exception e)
        {

        }
    }
}