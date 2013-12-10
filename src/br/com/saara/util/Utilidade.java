package br.com.saara.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import beans.Categorias;
import br.com.saara.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
		if(lojas != null && lojas.length() > 0){
			String[] ids = lojas.split(",");
			for(int i=0; i < ids.length;i++){
				if(!ids[i].equalsIgnoreCase(""+id_loja)){
					editor.putString("lojas", lojas+id_loja+",");
					editor.commit();
				}
			}
		}else{
			editor.putString("lojas", id_loja+",");
			editor.commit();
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
		if(lojas != null && lojas.length() > 0){
			String[] ids = lojas.split(",");
			for(int i=0; i < ids.length;i++){
				if(!ids[i].equalsIgnoreCase(""+id_loja)){
					editor.putString("likes", lojas+id_loja+",");
					editor.commit();
				}
			}
		}else{
			editor.putString("likes", id_loja+",");
			editor.commit();
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
		if(lojas != null && lojas.length() > 0){
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
	
	public static boolean checkInternet(Context ctx) {
		
		ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

		return activeNetworkInfo.isConnected();

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
	
	public static ArrayList<Categorias> montaListaDeCategorias(Context ctx) {
		
		ArrayList<Categorias> arrayCategorias = new ArrayList<Categorias>();
		
		arrayCategorias.add(new Categorias(R.drawable.alimentacao, ctx.getString(R.string.alimentacao)       	 ,1, 20, new int[]{78,205,196} , new int[]{66,191,183} ));
		arrayCategorias.add(new Categorias(R.drawable.armarinhos, ctx.getString(R.string.armarinhos)         	 ,2, 3 , new int[]{255,162,0}  , new int[]{235,149,2}  ));
		arrayCategorias.add(new Categorias(R.drawable.artesanato, ctx.getString(R.string.artesanato)        	 ,3, 9 , new int[]{82,221,92}  , new int[]{68,207,78}  ));
		arrayCategorias.add(new Categorias(R.drawable.art_festas, ctx.getString(R.string.festas)             	 ,4, 12, new int[]{242,29,65}  , new int[]{228,22,58}  ));
		arrayCategorias.add(new Categorias(R.drawable.art_esportivos, ctx.getString(R.string.esportivos)     	 ,5, 1 , new int[]{255,198,0}  , new int[]{242,188,0}  ));
		arrayCategorias.add(new Categorias(R.drawable.bancos, ctx.getString(R.string.bancos)                 	 ,6, 5 , new int[]{96,2,72}    , new int[]{76,15,56}   ));
		arrayCategorias.add(new Categorias(R.drawable.bazares, ctx.getString(R.string.bazares)               	 ,7, 5 , new int[]{189,235,7}  , new int[]{180,224,3}  ));
		arrayCategorias.add(new Categorias(R.drawable.bijouterias, ctx.getString(R.string.bijuterias)       	 ,8, 17, new int[]{255,107,107}, new int[]{241,97,97}  ));
		arrayCategorias.add(new Categorias(R.drawable.brinquedos, ctx.getString(R.string.brinquedos)         	 ,9, 4 , new int[]{63,176,148} , new int[]{62,166,141} ));
		arrayCategorias.add(new Categorias(R.drawable.calcados_bolsas_malas, ctx.getString(R.string.calcados)	 ,10,8 , new int[]{242,100,61} , new int[]{227,88,49}  ));
		
		arrayCategorias.add(new Categorias(R.drawable.cama_mesa_banho, ctx.getString(R.string.cama)              ,11, 8 , new int[]{78,205,196} , new int[]{66,191,183}));
		arrayCategorias.add(new Categorias(R.drawable.carnaval, ctx.getString(R.string.carnaval)                 ,12, 6 , new int[]{255,162,0}  , new int[]{235,149,2}));
		arrayCategorias.add(new Categorias(R.drawable.confeccao_tecidos, ctx.getString(R.string.confeccao)       ,13, 10, new int[]{82,221,92}  , new int[]{68,207,78}));
		arrayCategorias.add(new Categorias(R.drawable.decoracao, ctx.getString(R.string.decoracao)               ,14, 5 , new int[]{242,29,65}  , new int[]{228,22,58}));
		arrayCategorias.add(new Categorias(R.drawable.descartavais_embalagens, ctx.getString(R.string.embalagens),15, 3 , new int[]{255,198,0}  , new int[]{242,188,0}));
		arrayCategorias.add(new Categorias(R.drawable.drogarias, ctx.getString(R.string.drogaria)                ,16, 2 , new int[]{96,2,72}    , new int[]{76,15,56}));
		arrayCategorias.add(new Categorias(R.drawable.emporio, ctx.getString(R.string.emporio)                   ,17, 2 , new int[]{189,235,7}  , new int[]{180,224,3}));
		arrayCategorias.add(new Categorias(R.drawable.estacionamento, ctx.getString(R.string.estacionamento)     ,18, 2 , new int[]{255,107,107}, new int[]{241,97,97}));
		arrayCategorias.add(new Categorias(R.drawable.ferragens, ctx.getString(R.string.ferragens)               ,19, 5 , new int[]{63,176,148} , new int[]{62,166,141}));
		arrayCategorias.add(new Categorias(R.drawable.flores, ctx.getString(R.string.flor)                       ,20, 2 , new int[]{242,100,61} , new int[]{227,88,49}));
		
		arrayCategorias.add(new Categorias(R.drawable.relogios, ctx.getString(R.string.joias)              		 ,21, 17 , new int[]{78,205,196} , new int[]{66,191,183} ));
		arrayCategorias.add(new Categorias(R.drawable.lingerie_erotica, ctx.getString(R.string.lingerie)   		 ,22, 3 , new int[]{255,162,0}  , new int[]{235,149,2}));
		arrayCategorias.add(new Categorias(R.drawable.malhas_praia, ctx.getString(R.string.malhas)         		 ,23, 8 , new int[]{82,221,92}  , new int[]{68,207,78}));
		arrayCategorias.add(new Categorias(R.drawable.moda_indiana, ctx.getString(R.string.moda)           		 ,24, 3 , new int[]{242,29,65}  , new int[]{228,22,58}));
		arrayCategorias.add(new Categorias(R.drawable.outros, ctx.getString(R.string.outros)               		 ,25, 29 , new int[]{255,198,0}  , new int[]{242,188,0}));
		arrayCategorias.add(new Categorias(R.drawable.palhas, ctx.getString(R.string.palhas)         		 	 ,26, 2 , new int[]{96,2,72}    , new int[]{76,15,56}));
		arrayCategorias.add(new Categorias(R.drawable.papelaria, ctx.getString(R.string.papelaria)		         ,27, 2 , new int[]{189,235,7}  , new int[]{180,224,3}));
		arrayCategorias.add(new Categorias(R.drawable.perfumes_cosmeticos, ctx.getString(R.string.perfumes)      ,28, 4 , new int[]{255,107,107}, new int[]{241,97,97}));
		arrayCategorias.add(new Categorias(R.drawable.plasticos, ctx.getString(R.string.plasticos)         		 ,29, 4 , new int[]{63,176,148} , new int[]{62,166,141}));
		arrayCategorias.add(new Categorias(R.drawable.presentes, ctx.getString(R.string.presentes)     		     ,30, 6 , new int[]{242,100,61} , new int[]{227,88,49}));
		
		arrayCategorias.add(new Categorias(R.drawable.roupa_feminina, ctx.getString(R.string.feminina)     		 ,31, 14, new int[]{78,205,196} , new int[]{66,191,183} ));
		arrayCategorias.add(new Categorias(R.drawable.roupa_infantil, ctx.getString(R.string.infantil)           ,32, 7 , new int[]{255,162,0}  , new int[]{235,149,2}  ));
		arrayCategorias.add(new Categorias(R.drawable.silki, ctx.getString(R.string.silk)   		 			 ,33, 1 , new int[]{82,221,92}  , new int[]{68,207,78}  ));
		arrayCategorias.add(new Categorias(R.drawable.vestuario_geral, ctx.getString(R.string.vestuario)         ,34, 15, new int[]{242,29,65}  , new int[]{228,22,58}  ));

		return arrayCategorias;
		
	}
	
	public Categorias getColorsAndIconCategory(Categorias categoria, Context ctx) {
		
		int id = categoria.getIdCategoria();
		
		switch (id) {
		case 1:
			categoria.setIcon(R.drawable.alimentacao);
			categoria.setRgbColor(new int[]{78,205,196});
			categoria.setRgbColorListaLojas(new int[]{66,191,183});
			categoria.setText(ctx.getString(R.string.alimentacao));
			break;
		
		case 2:
			categoria.setIcon(R.drawable.armarinhos);
			categoria.setRgbColor(new int[]{255,162,0});
			categoria.setRgbColorListaLojas( new int[]{235,149,2});
			categoria.setText(ctx.getString(R.string.armarinhos));
			
		case 3:
			categoria.setIcon(R.drawable.artesanato);
			categoria.setRgbColor(new int[]{82,221,92} );
			categoria.setRgbColorListaLojas( new int[]{68,207,78});
			categoria.setText(ctx.getString(R.string.artesanato));
		
		case 4:
			categoria.setIcon(R.drawable.art_festas);
			categoria.setRgbColor(new int[]{242,29,65});
			categoria.setRgbColorListaLojas(new int[]{228,22,58});
			categoria.setText(ctx.getString(R.string.festas));
		
		case 5:
			categoria.setIcon(R.drawable.art_esportivos);
			categoria.setRgbColor(new int[]{255,198,0});
			categoria.setRgbColorListaLojas(new int[]{242,188,0});
			categoria.setText(ctx.getString(R.string.esportivos));		
		
		case 6:
			categoria.setIcon(R.drawable.bancos);
			categoria.setRgbColor(new int[]{96,2,72});
			categoria.setRgbColorListaLojas(new int[]{76,15,56});
			categoria.setText(ctx.getString(R.string.bancos));
			
		case 7:
			categoria.setIcon(R.drawable.bazares);
			categoria.setRgbColor(new int[]{189,235,7});
			categoria.setRgbColorListaLojas(new int[]{180,224,3});
			categoria.setText(ctx.getString(R.string.bazares));
			
		case 8:
			categoria.setIcon(R.drawable.bijouterias);
			categoria.setRgbColor( new int[]{255,107,107});
			categoria.setRgbColorListaLojas(new int[]{241,97,97});
			categoria.setText(ctx.getString(R.string.bijuterias));
			
		case 9:
			categoria.setIcon(R.drawable.brinquedos);
			categoria.setRgbColor(new int[]{63,176,148});
			categoria.setRgbColorListaLojas(new int[]{62,166,141});
			categoria.setText(ctx.getString(R.string.brinquedos));
			
		case 10:
			categoria.setIcon(R.drawable.calcados_bolsas_malas);
			categoria.setRgbColor(new int[]{242,100,61});
			categoria.setRgbColorListaLojas(new int[]{227,88,49});
			categoria.setText(ctx.getString(R.string.calcados));
			
		case 11:
			categoria.setIcon(R.drawable.cama_mesa_banho);
			categoria.setRgbColor(new int[]{78,205,196});
			categoria.setRgbColorListaLojas(new int[]{66,191,183});
			categoria.setText(ctx.getString(R.string.cama));
			
		case 12:
			categoria.setIcon(R.drawable.carnaval);
			categoria.setRgbColor(new int[]{255,162,0});
			categoria.setRgbColorListaLojas(new int[]{235,149,2});
			categoria.setText(ctx.getString(R.string.carnaval));
			
		case 13:
			categoria.setIcon(R.drawable.confeccao_tecidos);
			categoria.setRgbColor(new int[]{82,221,92} );
			categoria.setRgbColorListaLojas(new int[]{68,207,78});
			categoria.setText(ctx.getString(R.string.confeccao));
			
		case 14:
			categoria.setIcon(R.drawable.decoracao);
			categoria.setRgbColor(new int[]{242,29,65});
			categoria.setRgbColorListaLojas(new int[]{68,207,78});
			categoria.setText(ctx.getString(R.string.confeccao));
			
		case 15:
			categoria.setIcon(R.drawable.confeccao_tecidos);
			categoria.setRgbColor(new int[]{82,221,92} );
			categoria.setRgbColorListaLojas(new int[]{68,207,78});
			categoria.setText(ctx.getString(R.string.confeccao));
			
		case 16:
			categoria.setIcon(R.drawable.confeccao_tecidos);
			categoria.setRgbColor(new int[]{82,221,92} );
			categoria.setRgbColorListaLojas(new int[]{68,207,78});
			categoria.setText(ctx.getString(R.string.confeccao));
			
		case 17:
			categoria.setIcon(R.drawable.confeccao_tecidos);
			categoria.setRgbColor(new int[]{82,221,92} );
			categoria.setRgbColorListaLojas(new int[]{68,207,78});
			categoria.setText(ctx.getString(R.string.confeccao));
			
		default:
			break;
		}
		
		return categoria;
	}

}
