package com.zy.common.utils;

import com.sun.org.apache.regexp.internal.RE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 各种id生成策略
 */
public class IDUtils {

	/**
	 * 图片名生成
	 */
	public static String genImageName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		
		return str;
	}
	
	/**
	 * 商品id生成
	 */
	public static long genItemId() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		//如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	
	public static void main(String[] args) {
		for(int i=0;i< 100;i++)
		System.out.println(genItemId());
	}

	public static String genOrderId(){

//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String time = simpleDateFormat.format(new Date());
//		String rand = UUID.randomUUID().toString().substring(0,4);
//		return time+"--"+rand;

		return System.currentTimeMillis()+"";
	}

	public static String genOrderItemId(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = simpleDateFormat.format(new Date());
		String rand = UUID.randomUUID().toString().substring(0,4);
		return System.currentTimeMillis()+"";
	}

}
