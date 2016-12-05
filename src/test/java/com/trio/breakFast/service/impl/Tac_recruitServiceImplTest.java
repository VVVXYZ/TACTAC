package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_recruitDao;
import com.trio.breakFast.dao.impl.Tac_recruitDaoImpl;
import com.trio.breakFast.service.Tac_recruitService;
import com.trio.breakFast.service.Tac_userService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


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





    @BeforeClass
    public static void beforeClass() throws Exception {


    }

    @Test
    public void testCreateRecruit() throws Exception {

    }

    @Test
    public void testGetRecruitByID() throws Exception {

        Tac_recruitServiceImpl tac_recruitService=new Tac_recruitServiceImpl();
        tac_recruitService.getRecruitByID(2);
    }

    @Test
    public void testGetRecruiting() throws Exception {

    }

    @Test
    public void testGetRecruited() throws Exception {

    }

    @Test
    public void testGetRecruitForRecruitor() throws Exception {

    }

    @Test
    public void testChangeStatusOfRecruit() throws Exception {

    }

    @Test
    public void testGetRecruit() throws Exception {

    }

    @Test
    public void testGetRecruitForManager() throws Exception {

    }

    @Test
    public void testGetRecruitForManagerAndApplicator() throws Exception {

    }
}