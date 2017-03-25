package com.thinkman.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;

public class NetUtil {

	private static Logger logger = LoggerFactory.getLogger(NetUtil.class);

	public static String sendPost(String url, String params) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			URL connURL = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			httpConn.setRequestProperty("Content-Length", "" + params.getBytes("UTF8").length);

			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			out = new PrintWriter(httpConn.getOutputStream());
			out.write(params);
			out.flush();
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * zhexin,
	 * send to real server by proxy
	 */ 
	public static String sendPostProxy(Map<String, String> header,String params,String realURL) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			//if(header.get("host")==null){
			//	logger.error("realURL is null!!");
			//	return result;
			//}
			logger.info("realURL: "+realURL);
			URL connURL = new URL(realURL);
			HttpURLConnection httpConn =null;
			@SuppressWarnings("static-access")
            Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress("120.26.119.149", 14201));
			httpConn = (HttpURLConnection) connURL.openConnection(proxy);
			
			System.setProperty("sun.net.http.allowRestrictedHeaders","true");
			
			for(Map.Entry<String, String> entry:header.entrySet()){
			     logger.info(entry.getKey()+"--->"+entry.getValue());
			     httpConn.setRequestProperty(entry.getKey(), entry.getValue());
			}
			/**
			logger.info("Connection="+header.get("connection"));
			logger.info("user-agent="+header.get("user-agent"));
			logger.info("content-type="+header.get("content-type"));
			logger.info("content-length="+header.get("content-length"));
			logger.info("Proxy-Authorization="+header.get("Proxy-Authorization"));
			*/
			
			//httpConn.setRequestProperty("Accept", "*/*");
			/*httpConn.setRequestProperty("Connection", header.get("connection"));
			if(header.get("user-agent")!=null) httpConn.setRequestProperty("User-Agent", header.get("user-agent"));
			httpConn.setRequestProperty("Content-Type", header.get("content-type"));
			if(header.get("Content-Length")!=null) httpConn.setRequestProperty("Content-Length", header.get("content-length"));
			*/
			
			httpConn.setRequestProperty("Proxy-Authorization", header.get("Proxy-Authorization"));
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
						
			out = new PrintWriter(httpConn.getOutputStream());
			out.write(params);
			out.flush();
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line="";
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	
	public static String getBody(HttpServletRequest request) throws IOException {

		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			BufferedReader inputStream = request.getReader();
			if (inputStream != null) {
				while ((line = inputStream.readLine()) != null)
					stringBuilder.append(line);
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		logger.info("js: getBody:" + stringBuilder.toString());
		return stringBuilder.toString();
	}

	public static byte[] getBody2(HttpServletRequest request) throws IOException {
		byte[] buffer = null;
		InputStream in = null;
		try {
			int len = request.getContentLength();
			if (len <= 0) {
				return null;
			}
			in = request.getInputStream();
			buffer = new byte[len];
			in.read(buffer, 0, len);
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		return buffer;
	}
public static byte[] getBody3(HttpServletRequest request) throws IOException {
	InputStream is = null;
        String notifyMessage = null;
        try {
            is = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String buffer = null;
            StringBuffer sb = new StringBuffer();
            while ((buffer = br.readLine()) != null) {
                sb.append(buffer);
            }
            notifyMessage = sb.toString();
            logger.info("wooShopSms notify json2", notifyMessage);
	    return notifyMessage.getBytes();
        } catch (IOException e) {
            logger.error("checkOrNotify", e);
        }
	return null;
}


}
