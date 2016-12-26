package com.exiao.platform.deploy.supplier.test;



import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hzcf.platform.core.sys.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
    	Long[]  ids= new Long[]{(long) 1,(long) 3};
    	
    	System.out.println(userDao.getUserListByIds(ids).size());
    }
    
   
    
    public void testUpdate(){
    	
    }
    
    public void testGetById(){
    	
    }
}
