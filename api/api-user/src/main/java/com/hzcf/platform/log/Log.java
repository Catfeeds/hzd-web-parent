package com.hzcf.platform.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	private Logger logger;
	
	private Log(Class<?> paramClass) {
		logger = LoggerFactory.getLogger(paramClass);
	}

	/**
	 * 返回新的Log对象
	 * TODO 需要优化构建方法
	 * @param paramClass 参数类
	 * @return 日志服务对象
	 */
	public static Log getLogger(Class<?> paramClass) {
		return new Log(paramClass);
	}

	/**
	 * Log debug message.
	 * 
	 * @param message
	 */
	public void d(String message) {
		// 添加对DebugEnabled的判定会减少执行时间
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	/**
	 * Log infomation message
	 * 
	 * @param message
	 */
	public void i(String message) {
		// 添加对DebugEnabled的判定会减少执行时间
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	/**
	 * Log warning message
	 * 
	 * @param message
	 */
	public void w(String message) {
		logger.warn(message);
	}

	/**
	 * Log error message
	 * 
	 * @param message
	 */
	public void e(String message) {
		logger.error(message);
	}

	/**
	 * Log error message with the exception
	 * 
	 * @param message
	 * @param e
	 */
	public void e(String message, Throwable e) {
		logger.error(message, e);
	}
}
