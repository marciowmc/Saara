package br.com.saara;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bugsense.trace.BugSenseHandler;

import beans.Categorias;
import beans.Lojas;
import br.com.saara.util.RestClientGet;
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
	private static String url = "http://50.97.119.31/~i9app968/saara/getlojasbyidcategoria.php?id_categoria=";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		BugSenseHandler.initAndStartSession(LojasActivity.this, "c8c053dd");
		
		setContentView(R.layout.lojas);

		Bundle params = getIntent().getExtras();
		categoria = (Categorias) params.getSerializable("categoria");
		
		Button btVoltar = (Button) findViewById(R.id.btVoltar);
		Button btFavoritos = (Button) findViewById(R.id.btFavoritos);
		Button btInfo      = (Button) findViewById(R.id.btInformacoes);
		Button btSegmento  = (Button) findViewById(R.id.btSegmentos);
		
		btInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(LojasActivity.this,android.R.style.Theme_InputMethod);
				dialog.setContentView(R.layout.popup_info);
				Button btclose = (Button) dialog.findViewById(R.id.btClose);
			    WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();

			    WMLP.gravity = Gravity.TOP | Gravity.RIGHT;
			    WMLP.y = 100;   //y position
			    dialog.getWindow().setAttributes(WMLP);
			    
			    btclose.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
		
		btSegmento.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
		
		btFavoritos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent().setClass(LojasActivity.this, FavoritosActivity.class));
				finish();
			}
		});
		
		myHandler = new Handler();
		
		listView = (ListView) findViewById(R.id.listLoja);
		
		listLojas = new ArrayList<Lojas>();

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
			alert.setTitle(getString(R.string.app_name));
			alert.setIcon(R.drawable.icon);
			alert.show();

		}else{
		
			progress = new ProgressDialog(LojasActivity.this);
			progress.setTitle(getString(R.string.app_name));
			progress.setMessage(getString(R.string.load_lojas));
			progress.show();
			
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
		       
		       carregaLojas();
		}
	}
	
	public void carregaLojas(){
		new Thread(){
			
			public void run(){
				
				RestClientGet get = new RestClientGet(url+""+categoria.getIdCategoria());
				String[] retorno = get.restGet();
				if(retorno!=null){
					if(Integer.parseInt(retorno[0]) == 200 ){
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
								listLojas.add(loja);
							}
							
							myHandler.post(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									adapter.setData(listLojas);
									progress.dismiss();
								}
							});
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
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
	
	public void erroConexao(){
		myHandler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				final AlertDialog.Builder alert = new AlertDialog.Builder(LojasActivity.this);
				alert.setTitle(getString(R.string.app_name));
				alert.setMessage(getString(R.string.error_load_lojas));
				alert.setNeutralButton(getString(R.string.bt_dialogo_ok), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
						progress.dismiss();
						finish();
					}
				});
				alert.create();
				alert.show();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		BugSenseHandler.closeSession(LojasActivity.this);
	}
}
