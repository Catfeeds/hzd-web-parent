package com.hzcf.platform.deploy.user; /**
 * 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
 * @author sunyx
 *
 */
public class DeployCoreUser {

private static final String CORE_WORK_XML = "classpath:root-bean.xml";
	
	private static final Logger logger = LoggerFactory.getLogger(DeployCoreUser.class);
	
	public static void main(String[] args) {
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CORE_WORK_XML)){
			context.registerShutdownHook();
			context.start();
			
			logger.info("core-user 容器启动成功! ");
			
			Object lock = new Object();
			synchronized (lock) {
				try {
					while (true)
						lock.wait();
				} catch (InterruptedException e) {
					logger.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			logger.error("core-user 容器启动错误", e);
		}
		
	}

}
