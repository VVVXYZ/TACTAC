package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_resumeDao;
import com.trio.breakFast.dao.Tac_userDao;
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




//@RunWith(PowerMockRunner.class)
public class Tac_userServiceImplTest {

    @InjectMocks
    Tac_userService tac_userService=new Tac_userServiceImpl();



    @Mock
    Tac_userDao tac_userDao;

    @Mock
    Tac_resumeServiceImpl tac_resumeService;

    @Mock
    Tac_resumeDao tac_resumeDao;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        //when(tac_userDao.get(any(Tac_user.class))).thenReturn(1);
        //when(userMapper.insertSelective(any(User.class))).thenReturn(1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCheckEmail() throws Exception {


        tac_userService.checkEmail("995625851@qq.com");
    }

    @Test
    public void testGet() throws Exception {

        tac_userService.get("vv");
    }

    @Test
    public void testGetUserByID() throws Exception {

        try
        {
            tac_userService.getUserByID(anyInt());

        }
        catch (Exception e)
        {

        }

    }

    @Test
    public void testCreate() throws Exception {

        try
        {
            tac_userService.create("vv", "vv", "vv", "vv") ;
        }
        catch (Exception e)
        {

        }

    }

    @Test
    public void testUpdate() throws Exception {
        try
        {
        tac_userService.update(anyInt(),anyString(), anyString(),anyString(),anyString());

        }
        catch (Exception e)
        {

        }
    }



    @Test
    public void testUpdatePassword() throws Exception {

        try
        {
            tac_userService.updatePassword("vv","vv");
        }
        catch (Exception e)
        {

        }

    }
}