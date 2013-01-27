package br.com.saara.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Utilidade {

	public static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is),8192);
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static void saveFavorite(Context ctx, int id_loja){
		
		SharedPreferences pref = ctx.getSharedPreferences("SAARA_APP", Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString("lojas", ""+id_loja+",");
		editor.commit();
		
	}
	
	public static String getFavorite(Context ctx){
		SharedPreferences pref = ctx.getSharedPreferences("SAARA_APP", Context.MODE_PRIVATE);
		String lojas = pref.getString("lojas", null);
		return lojas;
	}
	
	
}
