package com.palm.http.http;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HttpGetTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSendGet() {
		String urlPath = "http://localhost:8080/http.cl?username=name";
		HttpGet.sendGet(urlPath);
	}

	@After
	public void tearDown() throws Exception {
	}

}
