package br.com.saara;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beans.Categorias;
import br.com.saara.util.ClientHttp;
import br.com.saara.util.S;
import br.com.saara.util.Utilidade;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import adapters.CategoriaAdapter;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class CategoriasActivity extends Activity {

	ArrayList<Categorias> arrayCategorias;
	private ListView listCategorias;
	private Button btInfo;
	private Button btFavoritos;
	private Handler myHandler;
	private int tryConnection = 0;

	private ProgressDialog progress;
	private CategoriaAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.categorias);
		btFavoritos = (Button) findViewById(R.id.btFavoritos);
		btInfo = (Button) findViewById(R.id.btInformacoes);

		btInfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(CategoriasActivity.this,
						android.R.style.Theme_InputMethod);
				dialog.setContentView(R.layout.popup_info);
				Button btClose = (Button) dialog.findViewById(R.id.btClose);

				WindowManager.LayoutParams WMLP = dialog.getWindow()
						.getAttributes();

				WMLP.gravity = Gravity.CENTER;
				dialog.getWindow().setAttributes(WMLP);

				btClose.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});

		btFavoritos.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent().setClass(CategoriasActivity.this,
						FavoritosActivity.class));
			}
		});
		arrayCategorias = new ArrayList<Categorias>();

		adapter = new CategoriaAdapter(this, arrayCategorias,
				R.layout.categoria_itens);

		listCategorias = (ListView) findViewById(R.id.listCategoria);

		listCategorias.setAdapter(adapter);

		listCategorias.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(CategoriasActivity.this, LojasActivity.class);
				intent.putExtra("categoria", arrayCategorias.get(position));
				intent.putExtra("index", position);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});

		myHandler = new Handler();
		App app = (App) getApplication();
		if(app.allCategorias.size() > 1) {
			adapter.setData(app.allCategorias);
		} else {
			getAllCategories();
		}
	}

	/*
	 * @Override public void onBackPressed() { // TODO Auto-generated method
	 * stub AlertDialog.Builder alert = new
	 * AlertDialog.Builder(CategoriasActivity.this);
	 * alert.setTitle(getString(R.string.app_name));
	 * alert.setMessage(getString(R.string.dialogo_sair_app));
	 * alert.setPositiveButton(getString(R.string.bt_dialogo_sim), new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) { //
	 * TODO Auto-generated method stub dialog.dismiss(); finish(); } });
	 * 
	 * alert.setNegativeButton(getString(R.string.bt_dialogo_nao), new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) { //
	 * TODO Auto-generated method stub dialog.dismiss(); } });
	 * 
	 * alert.create().show(); }
	 */

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public void getAllCategories() {

		ClientHttp.get(S.URL_ALL_CATEGORIES, new RequestParams(),
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						progress = new ProgressDialog(CategoriasActivity.this);
						progress.setMessage(getString(R.string.load_categorias));
						progress.setCancelable(false);
						progress.show();
					}

					@Override
					public void onSuccess(int statusCode, String content) {

						if (statusCode == 200) {
							try {

								JSONObject json = new JSONObject(content);
								if ("ok".equalsIgnoreCase(json
										.optString("status"))) {

									JSONArray jsonCategories = json
											.optJSONArray("results");
									arrayCategorias = Utilidade
											.montaListaDeCategorias(CategoriasActivity.this);
									for (int i = 0; i < jsonCategories.length(); i++) {
										for (Categorias categorias : arrayCategorias) {
											if (categorias.getIdCategoria() == jsonCategories
													.getJSONObject(i).optInt(
															"id")) {
												categorias
														.setQtdLojas(jsonCategories
																.getJSONObject(
																		i)
																.optInt("qtdlojas"));
											}
										}
									}
									
									App app = (App) getApplication();
									app.allCategorias = arrayCategorias;
									adapter.setData(arrayCategorias);
								} else {
									erroConexao();
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
	
	public void erroConexao() {
		
		final AlertDialog.Builder alert = new AlertDialog.Builder(CategoriasActivity.this);
		alert.setMessage(getString(R.string.error_load_categorias));
		
		alert.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				getAllCategories();
			}
		});
		
		alert.setNegativeButton(getString(R.string.bt_dialogo_ok), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				finish();
			}
		});
		alert.create();
		alert.show();
}


}
