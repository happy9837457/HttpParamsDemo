package com.palm.http.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * get请求
 * 
 * @author weixiang.qin
 * 
 */
public class HttpGet {
	/**
	 * get请求
	 * 
	 * @param urlPath
	 * @return
	 */
	public static String sendGet(String urlPath) {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		try {
			URL url = new URL(urlPath);
			connection = (HttpURLConnection) url.openConnection();
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
			connection.setRequestMethod("GET");
			connection.connect();
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
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
