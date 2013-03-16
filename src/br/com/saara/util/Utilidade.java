package br.com.saara.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.saara.MapaActivity;
import br.com.saara.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;

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
	
	public static void saveShowPopup(Context ctx, boolean isShow){
		
		SharedPreferences pref = ctx.getSharedPreferences("APP__MEU_SAARA", Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putBoolean("popup_curtir", isShow);
		editor.commit();
		
	}
	
	public static boolean getShowPopup(Context ctx){
		boolean isShow = false;
		SharedPreferences pref = ctx.getSharedPreferences("APP__MEU_SAARA", Context.MODE_PRIVATE);
		isShow = pref.getBoolean("popup_curtir", false);
		return isShow;
		
	}

	public static void saveFavorite(Context ctx, int id_loja){
		
		SharedPreferences pref = ctx.getSharedPreferences("APP__MEU_SAARA", Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		String lojas = pref.getString("lojas", "");
		if(lojas != null || lojas.length() > 0){
			String[] ids = lojas.split(",");
			for(int i=0; i < ids.length;i++){
				if(!ids[i].equalsIgnoreCase(""+id_loja)){
					editor.putString("lojas", lojas+id_loja+",");
					editor.commit();			
				}
			}
		}
	}
	
	 private static String convertToHex(byte[] data) {
	        StringBuilder buf = new StringBuilder();
	        for (byte b : data) {
	            int halfbyte = (b >>> 4) & 0x0F;
	            int two_halfs = 0;
	            do {
	                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
	                halfbyte = b & 0x0F;
	            } while (two_halfs++ < 1);
	        }
	        return buf.toString();
	    }
	
	 public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
	        md.update(text.getBytes("iso-8859-1"), 0, text.length());
	        byte[] sha1hash = md.digest();
	        return convertToHex(sha1hash);
	    }
	
	public static void saveLike(Context ctx, int id_loja){
		
		SharedPreferences pref = ctx.getSharedPreferences("APP__MEU_SAARA", Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		String lojas = pref.getString("likes", "");
		if(lojas != null || lojas.length() > 0){
			String[] ids = lojas.split(",");
			for(int i=0; i < ids.length;i++){
				if(!ids[i].equalsIgnoreCase(""+id_loja)){
					editor.putString("likes", lojas+id_loja+",");
					editor.commit();			
				}
			}
		}
	}
	
	public static String getLike(Context ctx){
		SharedPreferences pref = ctx.getSharedPreferences("APP__MEU_SAARA", Context.MODE_PRIVATE);
		String lojas = pref.getString("likes", null);
		return lojas;
	}
	
	public static String getFavorite(Context ctx){
		SharedPreferences pref = ctx.getSharedPreferences("APP__MEU_SAARA", Context.MODE_PRIVATE);
		String lojas = pref.getString("lojas", null);
		return lojas;
	}
	
	public static boolean checkLojaLike(Context ctx , int id_loja){
		
		boolean isLike = false;
		
		String lojasLikes = Utilidade.getFavorite(ctx);
		if(lojasLikes != null){
			String[] likes = lojasLikes.split(",");
			for(int i=0;i<likes.length;i++){
				if(likes[i].equalsIgnoreCase(""+id_loja)){
					isLike = true;	
				}
			}
		}
		
		return isLike;
	}
	
	public static void removeFavorite(Context ctx, int id_loja){

		String updateFavoritos = "";
		SharedPreferences pref = ctx.getSharedPreferences("APP__MEU_SAARA", Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		String lojas = pref.getString("lojas", "");
		if(lojas != null || lojas.length() > 0){
			String[] ids = lojas.split(",");
			for(int i=0; i < ids.length;i++){
				if(!ids[i].equalsIgnoreCase(""+id_loja)){
					updateFavoritos += ids[i]+",";
				}
			}
			editor.remove("lojas");
			editor.commit();
			editor.putString("lojas",updateFavoritos);
			editor.commit();
		}

	}

	public static boolean checkLojaFavorita(Context ctx , int id_loja){
		
		boolean isFavorite = false;
		
		String lojasFavoritas = Utilidade.getFavorite(ctx);
		if(lojasFavoritas != null){
			String[] lojas = lojasFavoritas.split(",");
			for(int i=0;i<lojas.length;i++){
				if(lojas[i].equalsIgnoreCase(""+id_loja)){
					isFavorite = true;	
				}
			}
		}
		
		return isFavorite;
	}
	
	public static String getIMEI(Context ctx){
	 	String IMEI = "";
	 	TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
	 	try{
	 		IMEI = telephonyManager.getDeviceId();
	 	}catch(Exception e){
	 		IMEI = telephonyManager.getNetworkOperator();
	 	}
	 	return IMEI;
	}
	
}
