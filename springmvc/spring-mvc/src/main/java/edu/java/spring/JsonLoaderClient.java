/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *  Author : tungtt         
 * Aug 26, 2016
 */
public class JsonLoaderClient {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/clazz/json");
		URLConnection conn = url.openConnection();
		conn.addRequestProperty("Accept", "Application/json");
		InputStream stream = conn.getInputStream();
		
		int read;
		byte[] bytes = new byte[4*1024];
		while((read = stream.read(bytes)) != -1) {
			System.out.println(new String(bytes, 0, read));
		}
	}
}
