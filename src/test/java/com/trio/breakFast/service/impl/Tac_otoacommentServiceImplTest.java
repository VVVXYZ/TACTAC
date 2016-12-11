package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_otoacommentDao;
import com.trio.breakFast.dao.Tac_userDao;
import com.trio.breakFast.model.Tac_applicants;
import com.trio.breakFast.model.Tac_atoocomment;
import com.trio.breakFast.model.Tac_otoacomment;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.service.Tac_applicantsService;
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
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Tac_otoacommentServiceImplTest {

    @InjectMocks
    Tac_otoacommentServiceImpl tac_otoacommentService=new Tac_otoacommentServiceImpl();

    @Mock
    Tac_otoacommentDao tac_otoacommentDao;
    @Mock
    Tac_userDao tac_userDao;
    @Mock
    Tac_userService tac_userService;
    @Mock
    Tac_applicantsService tac_applicantsService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTac_otoacomment() throws Exception {

        try
        {

            when(tac_otoacommentDao.get(anyString(),anyMap())).thenReturn(new Tac_otoacomment());
            assertThat(tac_otoacommentService.getTac_otoacomment(anyInt()),is(new Tac_otoacomment()) );
            verify(tac_otoacommentDao,times(1)).get(anyString(), anyMap());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetTac_otoacommentByID() throws Exception {

        try
        {

            when(tac_otoacommentDao.get(anyString(),anyMap())).thenReturn(new Tac_otoacomment());
            assertThat(tac_otoacommentService.getTac_otoacommentByID(anyInt()),is(new Tac_otoacomment()) );
            verify(tac_otoacommentDao,times(1)).get(anyString(), anyMap());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testCreateComment() throws Exception {
        try
        {

            when(tac_userService.getUserByID(anyInt())).thenReturn(new Tac_user());
            when(tac_applicantsService.getApplicantsByID(anyInt())).thenReturn(new Tac_applicants() );
            tac_otoacommentService.createComment(anyInt(),anyInt(),anyInt(),anyInt(),
                    anyString(),anyString(),anyFloat(), anyString() );

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetComment() throws Exception {

        try
        {

            List<Tac_otoacomment> list = new ArrayList<Tac_otoacomment>();
            when(tac_otoacommentDao.find(anyString(), anyMap(), anyInt(), anyInt()) ).thenReturn(list) ;
            assertThat(tac_otoacommentService.getComment(anyInt(), anyInt(), anyInt()), is(list));
            verify(tac_otoacommentDao,times(1)).get(anyString(), anyMap());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetCommentForManager() throws Exception {
        try
        {

            List<Tac_otoacomment> list = new ArrayList<Tac_otoacomment>();
            when(tac_otoacommentDao.find(anyString(), anyMap(), anyInt(), anyInt()) ).thenReturn(list) ;
            assertThat(tac_otoacommentService.getCommentForManager(anyInt(), anyInt()), is(list));
            verify(tac_otoacommentDao,times(1)).get(anyString(), anyMap());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testTipComment() throws Exception {
        try
        {
            tac_otoacommentService.tipComment(anyInt());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testCheckComment() throws Exception {
        try
        {
            tac_otoacommentService.checkComment(anyInt(), anyString(), anyInt());
        }
        catch (Exception e)
        {

        }
    }
}