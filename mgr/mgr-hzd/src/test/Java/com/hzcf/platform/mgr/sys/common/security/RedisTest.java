package com.hzcf.platform.mgr.sys.common.security;

import java.util.Date;

import org.apache.shiro.session.mgt.SimpleSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hzcf.platform.common.cache.ICache;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:cache.xml")
public class RedisTest {
    @Autowired
    private ICache cache;

    @Test
    public void test() {
    	SimpleSession session = new SimpleSession();
    	session.setId("123");
    	session.setHost("127.0.0.1");
    	session.setLastAccessTime(new Date());
    	session.setStartTimestamp(new Date());
    	cache.save("1rwty8pwbhoz5", session);
    	SimpleSession value = (SimpleSession)cache.load("1rwty8pwbhoz5");
//    	cache.delete("1rwty8pwbhoz5");
    	System.out.println("id=" + value.getId());
    	System.out.println("Host=" + value.getHost());
    	System.out.println("LastAccessTime=" + value.getLastAccessTime());
    	System.out.println("StartTimestamp=" + value.getStartTimestamp());
    }
}
