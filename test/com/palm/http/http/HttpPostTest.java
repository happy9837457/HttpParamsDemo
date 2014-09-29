package com.palm.http.http;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HttpPostTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSendPost() {
		String urlPath = "http://localhost:8080/http.cl";
		String params = "username=name?password=pwd";
		HttpPost.sendPost(urlPath, params);
	}

	@After
	public void tearDown() throws Exception {
	}

}
