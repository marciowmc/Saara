package br.com.saara;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beans.Categorias;
import beans.Favoritos;
import beans.Lojas;
import br.com.saara.util.ColorRGB;
import br.com.saara.util.RestClientGet;
import br.com.saara.util.Utilidade;
import adapters.FavoritoAdapter;
import adapters.LojaAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
	private Categorias categoria;
	private ProgressDialog progress;
	private FavoritoAdapter adapter;
	private static String url = "http://50.97.119.31/~i9app968/saara/getlojasbyid.php?id_lojas=";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
				   
				   ColorRGB rgb = new ColorRGB();
				   
				   Categorias cat = new Categorias(rgb.getDrawableCategoria(fav.getId_categoria()), 
						   fav.getNome_categoria(), 
						   fav.getId_categoria(), 
						   0,
						   new int[]{0,0,0}, 
						   new int[]{0,0,0} );
				   
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
				// TODO Auto-generated method stub
				Dialog dialog = new Dialog(FavoritosActivity.this,android.R.style.Theme_InputMethod);
				dialog.setContentView(R.layout.popup_info);
			    WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();
			    Button close = (Button) dialog.findViewById(R.id.btClose);
			    WMLP.gravity = Gravity.TOP | Gravity.RIGHT;
			    WMLP.y = 120;   //y position
			    dialog.getWindow().setAttributes(WMLP);
			    dialog.show();
			}
		});
		
		btSegmento.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setClass(FavoritosActivity.this, CategoriasActivity.class));
				finish();
			}
		});
		String favoritos = Utilidade.getFavorite(FavoritosActivity.this);
		if( favoritos == null){
			AlertDialog.Builder alert = new AlertDialog.Builder(FavoritosActivity.this);
			alert.setMessage("Você ainda não escolheu nenhuma loja como favorita.");
			alert.setTitle("Meu Saara");
			alert.setIcon(R.drawable.ic_launcher);
			alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startActivity(new Intent().setClass(FavoritosActivity.this, CategoriasActivity.class));
					finish();
				}
			});
					
		}else{
			url = url+favoritos;
			carregaFavoritos();
		}
	}
	
	public void carregaFavoritos(){
		new Thread(){
			public void run(){
				
				RestClientGet get = new RestClientGet(url);
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
								fav.setRgbColor(new ColorRGB().getColorList(fav.getId_categoria()));
								fav.setDrawable_categoria(new ColorRGB().getDrawableCategoria(fav.getId_categoria()));
								listFavoritos.add(fav);
							}
							
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									adapter.setData(listFavoritos);
									progress.dismiss();
								}
							});
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		progress = new ProgressDialog(FavoritosActivity.this);
		progress.setMessage("Carregando favoritos...");
		progress.setTitle("Meu Saara");
		progress.setIcon(R.drawable.ic_launcher);
		progress.show();

	}
}
