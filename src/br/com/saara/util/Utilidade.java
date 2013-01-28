package br.com.saara.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.saara.MapaActivity;
import br.com.saara.R;

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
	
	public static String getFavorite(Context ctx){
		SharedPreferences pref = ctx.getSharedPreferences("APP__MEU_SAARA", Context.MODE_PRIVATE);
		String lojas = pref.getString("lojas", null);
		return lojas;
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
	
}
