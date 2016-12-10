package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_atoocommentDao;
import com.trio.breakFast.dao.Tac_userDao;
import com.trio.breakFast.model.Tac_applicants;
import com.trio.breakFast.model.Tac_atoocomment;
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
import static org.mockito.Mockito.*;

public class Tac_atoocommentServiceImplTest {


    @InjectMocks
    Tac_atoocommentServiceImpl tac_atoocommentService=new Tac_atoocommentServiceImpl();

    @Mock
    Tac_atoocommentDao tac_atoocommentDao;
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
    public void testGetTac_atoocomment() throws Exception {

        try
        {
            when(tac_atoocommentDao.get(anyString(),anyMap())).thenReturn(new Tac_atoocomment());
            assertThat(tac_atoocommentService.getTac_atoocomment(anyInt()),is(new Tac_atoocomment()) );
            verify(tac_atoocommentDao.get(anyString(),anyMap()));
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetTac_atoocommentByID() throws Exception {

        try
        {
            when(tac_atoocommentDao.get(anyString(),anyMap())).thenReturn(new Tac_atoocomment());
            assertThat(tac_atoocommentService.getTac_atoocommentByID(anyInt()),is(new Tac_atoocomment()) );
            verify(tac_atoocommentDao,times(1)).get(anyString(), anyMap());

        }
        catch (Exception e)
        {
          //System.out.println(e.getMessage());
        }

    }

    @Test
    public void testCreateComment() throws Exception {

        try
        {
            when(tac_userService.getUserByID(anyInt())).thenReturn(new Tac_user());
            when(tac_applicantsService.getApplicantsByID(anyInt())).thenReturn(new Tac_applicants());
            tac_atoocommentService.createComment(anyInt(), anyInt(), anyInt(), anyInt(),
                    anyString(), anyString(), anyFloat(), anyString());
            doReturn(null).when(tac_atoocommentService).createComment(anyInt(), anyInt(), anyInt(), anyInt(),
                    anyString(), anyString(), anyFloat(), anyString());
            verify(tac_userService, times(2)).getUserByID(anyInt());
            verify(tac_applicantsService,times(1)).getApplicantsByID(anyInt());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetComment() throws Exception {

        try
        {
            List<Tac_atoocomment> list = new ArrayList<Tac_atoocomment>();
            when(tac_atoocommentDao.find(anyString(),anyMap(), anyInt(), anyInt() ) ).thenReturn(list) ;
            assertThat(tac_atoocommentService.getComment(anyInt(), anyInt(),anyInt() ),is( list )  );
            verify(tac_atoocommentDao,times(1)).get(anyString(), anyMap());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetCommentForManager() throws Exception {

        try
        {
            List<Tac_atoocomment> list = new ArrayList<Tac_atoocomment>();
            when(tac_atoocommentDao.find(anyString(),anyMap(), anyInt(), anyInt() ) ).thenReturn(list) ;
            assertThat(tac_atoocommentService.getCommentForManager(anyInt(), anyInt()),is( list )  );
            verify(tac_atoocommentDao,times(1)).get(anyString(), anyMap());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testTipComment() throws Exception {

        try
        {
            doReturn(null).when(tac_atoocommentService).tipComment(anyInt());
            //verify(tac_userService, times(2)).getUserByID(anyInt());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testCheckComment() throws Exception {

        try
        {
            doReturn(null).when(tac_atoocommentService).checkComment(anyInt(),anyString() , anyInt());
        }
        catch (Exception e)
        {

        }
    }
}