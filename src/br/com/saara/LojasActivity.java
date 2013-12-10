package br.com.saara;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;

import beans.Categorias;
import beans.Lojas;
import br.com.saara.util.ClientHttp;
import br.com.saara.util.RestClientGet;
import br.com.saara.util.S;
import adapters.LojaAdapter;
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
import android.widget.TextView;

public class LojasActivity extends Activity {
	
	private Handler myHandler;
	private ArrayList<Lojas> listLojas;
	private ListView listView;
	private Categorias categoria;
	private ProgressDialog progress;
	private LojaAdapter adapter;
	private int indexCategoria = -1;
	private App app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lojas);

		Bundle params = getIntent().getExtras();
		categoria = (Categorias) params.getSerializable("categoria");
		indexCategoria = (int) params.getInt("index");
		
		Button btVoltar = (Button) findViewById(R.id.btVoltar);
		Button btFavoritos = (Button) findViewById(R.id.btFavoritos);
		Button btInfo      = (Button) findViewById(R.id.btInformacoes);
		Button btSegmento  = (Button) findViewById(R.id.btSegmentos);
		
		btInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(LojasActivity.this,android.R.style.Theme_InputMethod);
				dialog.setContentView(R.layout.popup_info);
				Button btclose = (Button) dialog.findViewById(R.id.btClose);
			    WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();

			    WMLP.gravity = Gravity.CENTER;
			    dialog.getWindow().setAttributes(WMLP);
			    
			    btclose.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			    
			    dialog.show();
			}
		});
		
		TextView txtCategoria = (TextView) findViewById(R.id.txtCategoria);

		txtCategoria.setText(categoria.getText());
		
		btVoltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
				finish();
			}
		});
		
		btSegmento.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
				finish();
			}
		});
		
		btFavoritos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent().setClass(LojasActivity.this, FavoritosActivity.class));
				finish();
			}
		});
		
		myHandler = new Handler();
		
		listView = (ListView) findViewById(R.id.listLoja);
		
		listLojas = new ArrayList<Lojas>();
	
		app = (App) getApplication();
		
		carregaLojas();
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		app = (App) getApplication();
	}
	
	
	public void getLojasFromServer() {
		
		ClientHttp.get(S.URL_LOJAS_BY_CATEGORY+categoria.getIdCategoria(), null, new AsyncHttpResponseHandler() {
			
			@Override
			public void onStart() {
				
				progress = new ProgressDialog(LojasActivity.this);
				progress.setMessage(getString(R.string.load_lojas));
				progress.show();
				
			}
			
			@Override
			public void onSuccess(int statusCode, String content) {
				
				if (statusCode == 200) {
					
					try {
						JSONObject json = new JSONObject(content);
						JSONArray jsonArray = json.getJSONArray("results");
						for(int i= 0; i< jsonArray.length();i++){
						
							Lojas loja = new Lojas();
							loja.setEndereco(jsonArray.getJSONObject(i).getString("end_loja"));
							loja.setNome(jsonArray.getJSONObject(i).getString("nome"));
							loja.setTelefone(jsonArray.getJSONObject(i).getString("telefone"));
							loja.setLatitude(Double.parseDouble(jsonArray.getJSONObject(i).getString("latitude")));
							loja.setLongitude(Double.parseDouble(jsonArray.getJSONObject(i).getString("longitude")));
							loja.setIdLoja(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")));

							try{
								loja.setLikes(Integer.parseInt(jsonArray.getJSONObject(i).getString("likes")));
							}catch(Exception e){
								loja.setLikes(0);
							}
							
							listLojas.add(loja);
						}
						
						adapter.setData(listLojas);
						progress.dismiss();
						
						app = (App) getApplication();
						
						if (indexCategoria > 0) {
							app.allCategorias.get(indexCategoria).setLojas(listLojas);
						} else {
							for (Categorias categoriaApp : app.allCategorias) {
								if (categoriaApp.getIdCategoria() == categoria.getIdCategoria()) {
									categoria.setLojas(listLojas);
								}
							}
						}
						
					} catch (JSONException e) {
						erroConexao();
					}
				} else {
					erroConexao();
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
	
	/*public void carregaLojas(){
		new Thread(){
			
			@Override
			public void run(){
				
				RestClientGet get = new RestClientGet(S.URL_LOJAS_BY_CATEGORY+""+categoria.getIdCategoria());
				String[] retorno = get.restGet();
				if(retorno!=null){
					if (Integer.parseInt(retorno[0]) == 200 ) {
						try {
							JSONObject json = new JSONObject(retorno[1]);
							JSONArray jsonArray = json.getJSONArray("results");
							for(int i= 0; i< jsonArray.length();i++){
							
								Lojas loja = new Lojas();
								loja.setEndereco(jsonArray.getJSONObject(i).getString("end_loja"));
								loja.setNome(jsonArray.getJSONObject(i).getString("nome"));
								loja.setTelefone(jsonArray.getJSONObject(i).getString("telefone"));
								loja.setLatitude(Double.parseDouble(jsonArray.getJSONObject(i).getString("latitude")));
								loja.setLongitude(Double.parseDouble(jsonArray.getJSONObject(i).getString("longitude")));
								loja.setIdLoja(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")));
								try{
									loja.setLikes(Integer.parseInt(jsonArray.getJSONObject(i).getString("likes")));
								}catch(Exception e){
									loja.setLikes(0);
								}
								listLojas.add(loja);
							}
							
							myHandler.post(new Runnable() {
								
								@Override
								public void run() {
									adapter.setData(listLojas);
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
	}*/
	
	public void erroConexao() {
			
				final AlertDialog.Builder alert = new AlertDialog.Builder(LojasActivity.this);
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	
	public void carregaLojas() {
		
		if (!app.allCategorias.get(indexCategoria).getLojas().isEmpty()) {
			listLojas = app.allCategorias.get(indexCategoria).getLojas();
			adapter.setData(listLojas);
		} else {
		
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	
			if (activeNetworkInfo == null) {
	
				AlertDialog.Builder builder = new AlertDialog.Builder(LojasActivity.this);
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
	
			}else{
				adapter = new LojaAdapter(this,listLojas,R.layout.loja_itens, categoria.getIcon(), categoria.getRgbColor() , categoria.getRgbColorListaLojas());
			    listView.setAdapter(adapter);
			    listView.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
						   Intent intent = new Intent();
						   intent.setClass(LojasActivity.this, MapaActivity.class);
						   intent.putExtra("loja", listLojas.get(position));
						   intent.putExtra("categoria", categoria);
						   startActivity(intent);
						}
					});
			    getLojasFromServer();
			}
		}
	}
	
}
