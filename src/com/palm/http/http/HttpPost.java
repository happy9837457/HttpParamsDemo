package com.palm.http.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * post请求
 * 
 * @author weixiang.qin
 * 
 */
public class HttpPost {
	/**
	 * post请求
	 * 
	 * @param urlPath
	 * @param params
	 * @return
	 */
	public static String sendPost(String urlPath, String params) {
		HttpURLConnection connection = null;
		DataOutputStream out = null;
		BufferedReader reader = null;
		try {
			URL url = new URL(urlPath);
			connection = (HttpURLConnection) url.openConnection();
			/*
			 * connection.setRequestProperty("User-Agent",CommonValues.User_Agent
			 * ); connection.setRequestProperty("Accept",CommonValues.Accept);
			 * connection
			 * .setRequestProperty("Accept-Charset",CommonValues.Accept_Charset
			 * ); connection.setRequestProperty("Accept-Language",CommonValues.
			 * Accept_Language);
			 * connection.setRequestProperty("Connection",CommonValues
			 * .Connection);
			 * connection.setRequestProperty("Keep-Alive",CommonValues
			 * .Keep_Alive);
			 */
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			// 设定传送的内容类型是可序列化的java对象
			// connection.setRequestProperty("Content-type",
			// "application/x-java-serialized-object");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			connection.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			connection.setDoInput(true);
			// Post 请求不能使用缓存
			connection.setUseCaches(false);
			// 设置连接主机超时（单位：毫秒）
			connection.setConnectTimeout(30000);
			// 设置从主机读取数据超时（单位：毫秒）
			connection.setReadTimeout(30000);
			// 设定请求的方法为"POST"，默认是GET
			connection.setRequestMethod("POST");
			connection.connect();
			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(params);
			out.flush();
			StringBuffer result = new StringBuffer();
			if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
				reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					result.append(inputLine);
				}
			}
			return result.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
