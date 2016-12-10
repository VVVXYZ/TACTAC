package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_resumeDao;
import com.trio.breakFast.dao.Tac_userDao;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.service.Tac_userService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:F:/Work/TAC/TACTAC/src/main/resources/spring.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-cache.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-druid.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-mvc.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-mvc-shiro.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-shiro.xml"

})//启动Spring容器


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
            tac_userService.getUserByID(1);

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
    public void testJudgeLogin() throws Exception {

        try
        {
            when(tac_userDao.get(anyString(),anyMap())).thenReturn(new Tac_user());
            //assertEquals(true, tac_userService.judgeLogin(anyString(), anyString()));
            assertThat(tac_userService.judgeLogin(anyString(), anyString()),is(new Tac_user()) );
            verify(tac_userDao.get(anyString(),anyMap()));
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