/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.microservice.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 *  Author : tungtt         
 * Sep 15, 2016
 */
public class RestClientTest {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://localhost:8080/list/users");
		
		URLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Accept", "Application/Json");
		
		InputStream inputStream = connection.getInputStream();
		byte[] bytes = new byte[4*1024];
		int read = -1;
		try {
			while((read = inputStream.read(bytes)) != -1) {
				System.out.println(new String(bytes, 0, read));
			}
		} finally {
			inputStream.close();
		}
	}
}
