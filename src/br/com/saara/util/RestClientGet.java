package br.com.saara.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class RestClientGet {

	private String URL = "";

	private ArrayList<NameValuePair> header;

	public RestClientGet(String URL) {
		this.URL = URL;
		header = new ArrayList<NameValuePair>();
	}

	public void addHeader(String name, String value) {
		header.add(new BasicNameValuePair(name, value));
	}

	public String[] restGet() {
		String[] retorno = new String[2];

		
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 10000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		int timeoutSocket = 10000;

		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		HttpClient httpclient = new DefaultHttpClient();

		httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		HttpGet httpget = new HttpGet(URL);
		
		httpget.setHeader("User-Agent", "Saara");
		
		HashMap<String, String> extras = new HashMap<String, String>();
		try {

			// Add Header
			for (NameValuePair h : header) {
				httpget.addHeader(h.getName(), h.getValue());
				extras.put(h.getName(), h.getValue());
			}

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httpget);

			retorno[0] = "" + response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				String responseText = Utilidade.convertStreamToString(entity.getContent());
				retorno[1] = responseText;
			} else {
				retorno[1] = "";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			retorno = null;
		}

		return retorno;
	}
}
