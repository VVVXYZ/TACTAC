package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_recruitDao;
import com.trio.breakFast.dao.impl.Tac_recruitDaoImpl;
import com.trio.breakFast.model.Tac_atoocomment;
import com.trio.breakFast.model.Tac_recruit;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.service.Tac_recruitService;
import com.trio.breakFast.service.Tac_userService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:F:/Work/TAC/TACTAC/src/main/resources/spring.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-cache.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-druid.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-mvc.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-mvc-shiro.xml",
        "file:F:/Work/TAC/TACTAC/src/main/resources/spring-shiro.xml"

})//启动Spring容器
@Transactional

public class Tac_recruitServiceImplTest {

    @InjectMocks
    Tac_recruitServiceImpl tac_recruitService = new Tac_recruitServiceImpl();

    @Mock
    Tac_userService tac_userService;
    @Mock
    Tac_recruitDao tac_recruitDao;


    @BeforeClass
    public static void beforeClass() throws Exception {


    }

    @Test
    public void testCreateRecruit() throws Exception {

        try
        {
            when(tac_userService.get(anyString())).thenReturn(new Tac_user());
            tac_recruitService.createRecruit(anyInt(),anyString(),
                    anyString(),anyString() ,anyString(),
                    anyString(),anyString() ,anyString(),
                    anyInt() );
            verify(tac_userService.get(anyString()));
        }
        catch (Exception e)
        {

        }

    }

    @Test
    public void testGetRecruitByID() throws Exception {

        try
        {
            when(tac_recruitDao.get(anyString(),anyMap())).thenReturn(new Tac_recruit());
            assertThat(tac_recruitService.getRecruitByID(anyInt()),is(new Tac_recruit()) );
            verify(tac_recruitDao.get(anyString(),anyMap()));
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetRecruiting() throws Exception {

        try
        {
            List<Tac_recruit> list = new ArrayList<Tac_recruit>();
            when(tac_recruitDao.find(anyString(), anyMap(), anyInt(), anyInt()) ).thenReturn(list) ;
            assertThat(tac_recruitService.getRecruiting(anyInt(), anyInt(), anyInt()),is( list )  );
            verify(tac_recruitDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetRecruited() throws Exception {

        try
        {
            List<Tac_recruit> list = new ArrayList<Tac_recruit>();
            when(tac_recruitDao.find(anyString(), anyMap(), anyInt(), anyInt()) ).thenReturn(list) ;
            assertThat(tac_recruitService.getRecruited(anyInt(), anyInt(), anyInt()),is( list )  );
            verify(tac_recruitDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }



    @Test
    public void testChangeStatusOfRecruit() throws Exception {
        try
        {

            tac_recruitService.changeStatusOfRecruit(anyInt(), anyInt());

        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetRecruit() throws Exception {
        try
        {
            List<Tac_recruit> list = new ArrayList<Tac_recruit>();
            when(tac_recruitDao.find(anyString(), anyMap(), anyInt(), anyInt()) ).thenReturn(list) ;
            assertThat(tac_recruitService.getRecruit(anyInt(), anyInt()),is( list )  );
            verify(tac_recruitDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetRecruitForManager() throws Exception {
        try
        {
            List<Tac_recruit> list = new ArrayList<Tac_recruit>();
            when(tac_recruitDao.find(anyString(), anyMap(), anyInt(), anyInt()) ).thenReturn(list) ;
            assertThat(tac_recruitService.getRecruitForManager(anyInt(), anyInt()),is( list )  );
            verify(tac_recruitDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void testGetRecruitForManagerAndApplicator() throws Exception {
        try
        {
            List<Tac_recruit> list = new ArrayList<Tac_recruit>();
            when(tac_recruitDao.find(anyString(), anyMap(), anyInt(), anyInt()) ).thenReturn(list) ;
            assertThat(tac_recruitService.getRecruitForManagerAndApplicator(anyInt(), anyInt(), anyInt()),is( list )  );
            verify(tac_recruitDao,times(1)).get(anyString(), anyMap());
        }
        catch (Exception e)
        {

        }
    }
}