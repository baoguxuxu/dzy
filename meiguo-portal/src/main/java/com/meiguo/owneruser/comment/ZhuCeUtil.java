package com.meiguo.owneruser.comment;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
/*
 * 根据uuid生成唯一标识
 *
 */
public class ZhuCeUtil {
	
	public static String getNewUserId() {
		    String ipAddress = "";
		      try {		    	  
		          //获取服务器IP地址
		          ipAddress = InetAddress.getLocalHost().getHostAddress();
		      }catch (Exception e) {
		    	  e.printStackTrace();
		      }		     
		      //获取UUID
		      String uuid = ipAddress + "$" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		      //生成后缀
		      long suffix = Math.abs(uuid.hashCode() % 100000000);
		      SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		      String time = sdf.format(new Date(System.currentTimeMillis()));
		      //生成前缀
		      long prefix = Long.parseLong(time) * 100000000;
		      String userId = String.valueOf(prefix + suffix);
		      return userId;
		        //System.out.println(userId.substring(8,14));
		     }


}