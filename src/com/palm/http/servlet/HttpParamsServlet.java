package com.palm.http.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 输出http请求
 * 
 * @author weixiang.qin
 * 
 */
public class HttpParamsServlet extends HttpServlet {
	private static final long serialVersionUID = -781719809558791819L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		// 是否get请求
		boolean isGet = false;
		if (request.getMethod().equalsIgnoreCase("GET")) {
			isGet = true;
		}
		// 请求行
		sb.append(request.getMethod()).append(" ")
				.append(request.getServletPath());
		if (isGet) {
			sb.append("?").append(request.getQueryString());
		}
		sb.append(" ").append(request.getProtocol()).append("\r\n");
		// 请求头
		Enumeration<String> headerNames = request.getHeaderNames();
		String headerName = null;
		while (headerNames.hasMoreElements()) {
			headerName = headerNames.nextElement();
			sb.append(headerName).append(":")
					.append(request.getHeader(headerName)).append("\r\n");
		}
		// 空行
		sb.append("\r\n");
		// 请求体(Get没有)
		if (!isGet) {
			byte[] bytes = new byte[request.getContentLength()];
			request.getInputStream().read(bytes);
			sb.append(new String(bytes));
		}
		System.out.println(sb.toString());
		response.getWriter().print(sb.toString());
	}

}
