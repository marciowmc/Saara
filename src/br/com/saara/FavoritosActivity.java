package br.com.saara;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;

import beans.Categorias;
import beans.Favoritos;
import beans.Lojas;
import br.com.saara.util.ClientHttp;
import br.com.saara.util.ColorRGB;
import br.com.saara.util.RestClientGet;
import br.com.saara.util.S;
import br.com.saara.util.Utilidade;
import adapters.FavoritoAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FavoritosActivity extends Activity {

	private Handler myHandler;
	private ArrayList<Favoritos> listFavoritos;
	private ListView listView;
	private ProgressDialog progress;
	private FavoritoAdapter adapter;
	private String getURL = "";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favoritos);

		myHandler = new Handler();
		
		listFavoritos = new ArrayList<Favoritos>();
		listView = (ListView) findViewById(R.id.listFavoritos);
		
		adapter = new FavoritoAdapter(this,listFavoritos,R.layout.loja_itens);
		   
	    listView.setAdapter(adapter);
		
		
	    listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
				   Intent intent = new Intent();
				   intent.setClass(FavoritosActivity.this, MapaActivity.class);
				   
				   Favoritos fav = listFavoritos.get(position);
				   Lojas loja = new Lojas();
				   loja.setLatitude(fav.getLatitude_loja());
				   loja.setLongitude(fav.getLongitude_loja());
				   loja.setIdLoja(fav.getId_loja());
				   loja.setEndereco(fav.getEnd_loja());
				   loja.setNome(fav.getNome_loja());
				   loja.setTelefone(fav.getTelefone());
				   loja.setLikes(fav.getLikes());
				   
				   ColorRGB rgb = new ColorRGB();
				   int[] rgbListaLojas = fav.getRgbColor();
				   Categorias cat = new Categorias(rgb.getDrawableCategoria(fav.getId_categoria()), 
						   fav.getNome_categoria(), 
						   fav.getId_categoria(), 
						   0,
						   new int[]{rgbListaLojas[0],rgbListaLojas[1],rgbListaLojas[2]}, 
						   new int[]{rgbListaLojas[3],rgbListaLojas[4],rgbListaLojas[5]} );
				   
				   intent.putExtra("categoria", cat);
				   intent.putExtra("loja", loja);
				   startActivity(intent);
				}
			});

		
		Button btInfo      = (Button) findViewById(R.id.btInformacoes);
		Button btSegmento  = (Button) findViewById(R.id.btSegmentos);
		
		btInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(FavoritosActivity.this,android.R.style.Theme_InputMethod);
				dialog.setContentView(R.layout.popup_info);
			    WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();
			    Button close = (Button) dialog.findViewById(R.id.btClose);
			    WMLP.gravity = Gravity.CENTER;
			    dialog.getWindow().setAttributes(WMLP);
			    
			    close.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			    
			    dialog.show();
			}
		});
		
		btSegmento.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setClass(FavoritosActivity.this, CategoriasActivity.class));
				finish();
			}
		});
	}
	
	public void carregarFavoritos() {
		
		ClientHttp.get(getURL, null, new AsyncHttpResponseHandler() {
			
			@Override
			public void onStart() {
				progress = new ProgressDialog(FavoritosActivity.this);
				progress.setMessage(getString(R.string.load_favoritos));
				progress.show();
			}
			
			@Override
			public void onSuccess(int statusCode, String content) {

				if (statusCode != 200) {
					erroConexao();
				} else {
					
					try {
						JSONObject json = new JSONObject(content);
						JSONArray jsonArray = json.getJSONArray("results");
						for(int i= 0; i< jsonArray.length();i++){
						
							Favoritos fav = new Favoritos();
							fav.setEnd_loja(jsonArray.getJSONObject(i).getString("end_loja"));
							fav.setNome_loja(jsonArray.getJSONObject(i).getString("nome"));
							fav.setLatitude_loja(Double.parseDouble(jsonArray.getJSONObject(i).getString("latitude")));
							fav.setLongitude_loja(Double.parseDouble(jsonArray.getJSONObject(i).getString("longitude")));
							fav.setId_loja(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")));
							fav.setId_categoria(Integer.parseInt(jsonArray.getJSONObject(i).getString("id_categoria")));
							fav.setNome_categoria(jsonArray.getJSONObject(i).getString("nome_categoria"));
							fav.setTelefone(jsonArray.getJSONObject(i).getString("telefone"));
							fav.setRgbColor(new ColorRGB().getColorList(Integer.parseInt(jsonArray.getJSONObject(i).getString("id_categoria"))));
							fav.setDrawable_categoria(new ColorRGB().getDrawableCategoria(Integer.parseInt(jsonArray.getJSONObject(i).getString("id_categoria"))));
							try{
								fav.setLikes(Integer.parseInt(jsonArray.getJSONObject(i).getString("likes")));
							}catch(Exception e){
								fav.setLikes(0);
							}
							listFavoritos.add(fav);
							
						}
						
						adapter.setData(listFavoritos);
						progress.dismiss();
						
					} catch (JSONException e) {
						erroConexao();
					}
				}
			}
			
			@Override
			public void onFailure(Throwable error, String content) {
				erroConexao();
			}
			
			@Override
			public void onFinish() {
				progress.dismiss();
			}
			
		}, null);
		
	}
	
	public void carregaFavoritos(){
		new Thread(){
			@Override
			public void run(){
				
				RestClientGet get = new RestClientGet(getURL);
				String[] resposta = get.restGet();
				if(resposta != null){
					if(Integer.parseInt(resposta[0]) == 200){
						
						try {
							JSONObject json = new JSONObject(resposta[1]);
							JSONArray jsonArray = json.getJSONArray("results");
							for(int i= 0; i< jsonArray.length();i++){
							
								Favoritos fav = new Favoritos();
								fav.setEnd_loja(jsonArray.getJSONObject(i).getString("end_loja"));
								fav.setNome_loja(jsonArray.getJSONObject(i).getString("nome"));
								fav.setLatitude_loja(Double.parseDouble(jsonArray.getJSONObject(i).getString("latitude")));
								fav.setLongitude_loja(Double.parseDouble(jsonArray.getJSONObject(i).getString("longitude")));
								fav.setId_loja(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")));
								fav.setId_categoria(Integer.parseInt(jsonArray.getJSONObject(i).getString("id_categoria")));
								fav.setNome_categoria(jsonArray.getJSONObject(i).getString("nome_categoria"));
								fav.setTelefone(jsonArray.getJSONObject(i).getString("telefone"));
								fav.setRgbColor(new ColorRGB().getColorList(Integer.parseInt(jsonArray.getJSONObject(i).getString("id_categoria"))));
								fav.setDrawable_categoria(new ColorRGB().getDrawableCategoria(Integer.parseInt(jsonArray.getJSONObject(i).getString("id_categoria"))));
								try{
									fav.setLikes(Integer.parseInt(jsonArray.getJSONObject(i).getString("likes")));
								}catch(Exception e){
									fav.setLikes(0);
								}
								listFavoritos.add(fav);
								
							}
							
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									adapter.setData(listFavoritos);
									progress.dismiss();
								}
							});
							
						} catch (JSONException e) {
							erroConexao();
						}
					}else{
						erroConexao();
					}
				}else{
					erroConexao();
				}
			}
		}.start();
	}

	
	@Override
	protected void onResume() {
		super.onResume();

		listFavoritos.clear();
		
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

		String favoritos = Utilidade.getFavorite(FavoritosActivity.this);

		if( favoritos == null || favoritos.trim().length() == 0){
			
			AlertDialog.Builder alert = new AlertDialog.Builder(FavoritosActivity.this);
			alert.setMessage(getString(R.string.favoritos_vazio));
			alert.setNeutralButton(getString(R.string.bt_dialogo_ok), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					startActivity(new Intent().setClass(FavoritosActivity.this, CategoriasActivity.class));
					finish();
				}
			});
			alert.show();
					
		}else{
			
			if (activeNetworkInfo == null) { // verifica se tem conexao com a internet

				AlertDialog.Builder builder = new AlertDialog.Builder(FavoritosActivity.this);
				builder.setMessage(getString(R.string.error_conexao_internet))
						.setCancelable(false)
						.setPositiveButton(getString(R.string.bt_dialogo_ok),
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {
										finish();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();

				
			} else {
				getURL = S.URL_LOJAS_BY_ID+favoritos;
				carregarFavoritos();
			}
		}
		
	}
	
	public void erroConexao(){
		
				final AlertDialog.Builder alert = new AlertDialog.Builder(FavoritosActivity.this);
				alert.setMessage(getString(R.string.error_load_lojas));
				alert.setNeutralButton(getString(R.string.bt_dialogo_ok), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
						progress.dismiss();
						finish();
						
					}
				});
				alert.create();
				alert.show();
			}
}
	