package net.wenz.service.fsnode.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
	//将key转化为int型
		public static int getInt(HttpServletRequest request, String key) {
			try {
				return Integer.decode(request.getParameter(key));
			}catch(Exception e) {
				return -1;
			}
		}
		//将key转化为Long型
		public static long getLong(HttpServletRequest request, String key) {
			try {
				return Long.valueOf(request.getParameter(key));
			}catch(Exception e) {
				return -1;
			}
		}
		//将key转化为Double型
		public static double getDouble(HttpServletRequest request, String key) {
			try {
				return Double.valueOf(request.getParameter(key));
			}catch(Exception e) {
				return -1d;
			}
		}
		//将key转化为Boolean型
		public static boolean getBoolean(HttpServletRequest request, String key) {
			try {
				return Boolean.valueOf(request.getParameter(key));
			}catch(Exception e) {
				return false;
			}
		}
		//将key转化为String型
		public static String getString(HttpServletRequest request, String key) {
			try {
				String result=request.getParameter(key);
				if(result!=null) {
					result=result.trim();
				}
				if("".equals(result)) {
					result=null;
				}
				return result;
			}catch(Exception e) {
				return null;
			}
		}
}
