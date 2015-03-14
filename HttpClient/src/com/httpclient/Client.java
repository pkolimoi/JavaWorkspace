package com.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Client {

	private final static String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) {
		
		System.out.println("Executing Post---");
		try {
			executePost();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done.");

	}

	private static void executePost() throws ClientProtocolException,IOException {

		String url = "http://localhost:8082/RestWSPoc/restfulServices/myServices/subtractValues";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("a", "5"));
		params.add(new BasicNameValuePair("b", "2"));

		post.setEntity(new UrlEncodedFormEntity(params));

		HttpResponse response = client.execute(post);
		
		//get details for status
		System.out.println(response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase() + " " + response.getStatusLine().getProtocolVersion());
		
		//get header info
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() +" : "+ header.getValue());
	 
		}

		//get content details
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
	}

}
