package com.hzcf.platform.mgr.sys.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringNumFormat {

	//不存在舍去
	private final static String[] FORMATS_N = { "0", "0.0", "0.00", "0.000",
			"0.0000", "0.00000", "0.000000", "0.0000000", "0.00000000" };
	//不存在显示为0
	private final static String[] FORMATS_Y = { "#", "#.#", "#.##", "#.###",
			"#.####", "#.#####", "#.######", "#.#######", "#.########" };
	
	/**
	 * 将double转换为字符串,如果不存在相应的位数，则省去
	 * @param d double数值
	 * @param num 指定的格式化
	 * @return
	 */
	public static double parseDoubleN(double d,int num) {
		DecimalFormat fmt = getFormat(FORMATS_N[num]);
		String s = fmt.format(d);
		return Double.parseDouble(s);
	}
	
	/**
	 * 将double转换为字符串,如果不存在相应的位数,则显示为0
	 * @param d double数值
	 * @param num 指定的格式化
	 * @return
	 */
	public static double parseDoubleY(double d,int num) {
		DecimalFormat fmt = getFormat(FORMATS_Y[num]);
		String s = fmt.format(d);
		return Double.parseDouble(s);
	}
	
	/**
	 * 返回相应的格式化类型
	 * @param format 格式化字符串
	 * @return
	 */
	public static DecimalFormat getFormat(String format) {
		DecimalFormat fmt = new DecimalFormat(format);
		fmt.setDecimalSeparatorAlwaysShown(false);
		return fmt;
	}
	
	/**
	 * 将数字字符串转为指定精度的大数据
	 * 如果不存在相应的位数，则省去
	 * @param d 数字字符串
	 * @param num 指定的格式化
	 * @return
	 */
	public static BigDecimal getBigDecimalN(String d,int num){
		BigDecimal dci=new BigDecimal(d);
		DecimalFormat fmt = getFormat(FORMATS_N[num]);
		return new BigDecimal(fmt.format(dci.doubleValue()));
	}
	
	/**
	 * 将数字字符串转为指定精度的大数据
	 * 如果不存在相应的位数,则显示为0
	 * @param d 数字字符串
	 * @param num 指定的格式化
	 * @return
	 */
	public static BigDecimal getBigDecimalY(String d,int num){
		BigDecimal dci = new BigDecimal(d);
		DecimalFormat fmt = getFormat(FORMATS_Y[num]);
		return new BigDecimal(fmt.format(dci.doubleValue()));
	}
	
	/**
	 * 将数字字符串转为指定精度的浮点数
	 * 如果不存在相应的位数，则省去
	 * @param d 数字字符串
	 * @param num 指定的格式化
	 * @return
	 */
	public static Double getDoubleN(String d,int num){
		BigDecimal dci = getBigDecimalN(d, num);
		double value = dci.doubleValue();
		return value;
	}
	
	/**
	 * 将数字字符串转为指定精度的浮点数
	 * 如果不存在相应的位数,则显示为0
	 * @param d 数字字符串
	 * @param num 指定的格式化
	 * @return
	 */
	public static Double getDoubleY(String d,int num){
		BigDecimal dci = getBigDecimalY(d, num);
		double value = dci.doubleValue();
		return value;
	}
	
	/**
	 * 将数字字符串转为指定精度的浮点数
	 * 如果不存在相应的位数，则省去
	 * @param d 数字字符串
	 * @param num 指定的格式化
	 * @return
	 */
	public static Float getFloatN(String d,int num){
		BigDecimal dci = getBigDecimalN(d, num);
		float value = dci.floatValue();
		return value;
	}
	
	/**
	 * 将数字字符串转为指定精度的浮点数
	 * 如果不存在相应的位数,则显示为0
	 * @param d 数字字符串
	 * @param num 指定的格式化
	 * @return
	 */
	public static Float getFloatY(String d,int num){
		BigDecimal dci = getBigDecimalY(d, num);
		float value = dci.floatValue();
		return value;
	}
	
	public static int getIntValue(String d){
		
		int value = 0;
		try {
			value = Integer.parseInt(d);
		} catch (Exception e) {
			value = 0;
		}
		return value;
	}
	
	public static Integer getIntegerValue(String d){
		
		Integer value = new Integer(getIntValue(d));
		return value;
	}
	
	public static BigDecimal getBigDecimalValue(String s) {
		BigDecimal value = new BigDecimal(s);
		return value;
	}
	
	public static String getFmtToString(String d){
		try {
			Double.valueOf(d);
		} catch (Exception e) {
			// TODO: handle exception
			return "0";
		}
		return d;
	}
	
}
