package br.com.saara.util;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ClientHttp {


	  private static AsyncHttpClient client = new AsyncHttpClient();

	  public static void get(String url, RequestParams params, BinaryHttpResponseHandler responseHandler, ArrayList<NameValuePair> header) {
	      client.get(null, url, null, params, responseHandler, header);
	  }
	  
	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler, ArrayList<NameValuePair> header) {
	      client.get(null, url, null, params, responseHandler, header);
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler , ArrayList<NameValuePair> header) {
	      client.post(url, params, responseHandler, header);
	  }

	  public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler , ArrayList<NameValuePair> header) {
		  client.put(url, params, responseHandler, header);
	  }

	  public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler , ArrayList<NameValuePair> header) {
		  client.delete(null, url, null, params, responseHandler, header);
	  }

	  public static void setUserAgent(String userAgent){
		  client.setUserAgent(userAgent);
	  }
}
