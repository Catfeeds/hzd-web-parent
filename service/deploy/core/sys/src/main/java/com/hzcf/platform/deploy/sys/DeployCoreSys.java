/**
 * 
 */
package com.hzcf.platform.deploy.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author allen.shen
 *
 */
public class DeployCoreSys {

	private static final String CORE_WORK_XML = "classpath:root-bean.xml";
	
	private static final Logger logger = LoggerFactory.getLogger(DeployCoreSys.class);	
	
	public static void main(String[] args) {
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CORE_WORK_XML)){
			context.registerShutdownHook();
			context.start();
			
			logger.info("core-sys 容器启动成功! ");
			
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
			logger.error("core-sys 容器启动错误", e);
		}
		
	}

}
