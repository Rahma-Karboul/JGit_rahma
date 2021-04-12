package com.focuscorp.DOFAN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DofanApplication {

	public static void main(String[] args)  throws IOException {

		SpringApplication.run(DofanApplication.class, args);
		/*URL url = new URL("http://10.5.14.122/createItem?name=HelloJob");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");
		httpConn.setRequestProperty("Jenkinsfile-Crumb", "1f28637d27dcd7d1e9548c791317174775fa0694500aee564d6edf10f3a90707baf1230fb7609cc3568f7a5338d48cd06a0d044bd6841f3e9b5f0e4cc431dd12");
		httpConn.setRequestProperty("Content-Type", "text/xml");

		byte[] message = ("admin:RDrd123++").getBytes("UTF-8");
		String basicAuth = DatatypeConverter.printBase64Binary(message);
		System.out.print(basicAuth);
		httpConn.setRequestProperty("Authorization", "Basic " + basicAuth);

		httpConn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write(".pipeline/config.xml");
		writer.flush();
		writer.close();
		httpConn.getOutputStream().close();

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);*/
	}

}
