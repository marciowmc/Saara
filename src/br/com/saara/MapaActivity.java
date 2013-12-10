package br.com.saara;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.loopj.android.http.AsyncHttpResponseHandler;

import beans.Categorias;
import beans.Lojas;
import br.com.saara.util.ClientHttp;
import br.com.saara.util.RestClientGet;
import br.com.saara.util.S;
import br.com.saara.util.Utilidade;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MapaActivity extends MapActivity {

	private double latitude,longitude;
	private Lojas loja;
	private Categorias categoria;
	private boolean isFavorite = false;
	private ImageView imgFavoritos;
	private ImageView imgLike;
	private String IMEI;
	private String hash;
	private TextView txtLike;
	protected ProgressDialog progress;
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.mapa_loja);
		
		Bundle params = getIntent().getExtras();
		
		loja = (Lojas) params.getSerializable("loja");
		categoria = (Categorias) params.getSerializable("categoria");
		
		latitude  = loja.getLatitude();
		longitude = loja.getLongitude();
		
		imgFavoritos = (ImageView) findViewById(R.id.imgFavorito);
		imgLike = (ImageView) findViewById(R.id.imgCurti);
		
		imgLike.setOnClickListener(new View.OnClickListener() {
			

			@Override
			public void onClick(View v) {
				String like = txtLike.getText().toString();
				int likes = Integer.parseInt(like)+1;
				txtLike.setText(""+likes);
				likeLoja();
			}
		});
		
		TextView txtEnd       = (TextView) findViewById(R.id.txtEnd);
		TextView txtCategoria = (TextView) findViewById(R.id.txtCategoria);
		TextView txtTitle     = (TextView) findViewById(R.id.txtTitle);
		txtLike               = (TextView) findViewById(R.id.txtLike);
		txtLike.setText(""+loja.getLikes());
		
		Button btVoltar       = (Button) findViewById(R.id.btVoltar);
		Button btFavoritoTopo = (Button) findViewById(R.id.btFavoritos);
		Button btSegmentos    = (Button) findViewById(R.id.btSegmentos);
		Button btInfo         = (Button) findViewById(R.id.btInformacoes);
		Button btNavigator    = (Button) findViewById(R.id.btNavigator);
		Button btCall         = (Button) findViewById(R.id.btCall);
		
		if(loja.getTelefone().trim().length() < 4){
			btCall.setEnabled(false);
		}
		
		btCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri callUri = Uri.parse("tel:" + loja.getTelefone());
				Intent i = new Intent(Intent.ACTION_CALL, callUri);
				startActivity(i);
			}
		});
		
		btFavoritoTopo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setClass(MapaActivity.this, FavoritosActivity.class));
				finish();
			}
		});
		
		btInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(MapaActivity.this,android.R.style.Theme_InputMethod);
				dialog.setContentView(R.layout.popup_info);
				Button close = (Button) dialog.findViewById(R.id.btClose);
			    WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();

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
		
		RelativeLayout curtidas = (RelativeLayout) findViewById(R.id.curtidas);
		final ImageView imgFavarito  = (ImageView) findViewById(R.id.imgFavorito);
		
		txtEnd.setText(loja.getEndereco());
		txtCategoria.setText(categoria.getText());
		txtTitle.setText(loja.getNome());
		
		int[] rgb =  categoria.getRgbColorListaLojas();
		
		txtTitle.setBackgroundColor(Color.argb(215,rgb[0], rgb[1],rgb[2] ));
		curtidas.setBackgroundColor(Color.rgb(rgb[0], rgb[1],rgb[2]));
		imgFavarito.setBackgroundColor(Color.argb(125,rgb[0], rgb[1],rgb[2]));
		
		btNavigator.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("google.navigation:q=" + latitude + ","
								+ longitude + "&mode=w"));
				startActivity(intent);
			}
		});
		
		btVoltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
				finish();
			}
		});
		
		btSegmentos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setClass(MapaActivity.this, CategoriasActivity.class));
				finish();
			}
		});

		String lojasFavoritas = Utilidade.getFavorite(MapaActivity.this);
		if(lojasFavoritas != null){
			String[] lojas = lojasFavoritas.split(",");
			for(int i=0;i<lojas.length;i++){
				if(lojas[i].equalsIgnoreCase(""+loja.getIdLoja())){
					imgFavarito.setImageResource(R.drawable.favoritos_pressed);
					isFavorite = true;	
				}
			}
		}
		
		String likeLoja = Utilidade.getLike(MapaActivity.this);
		if(likeLoja != null){
			String[] likes = likeLoja.split(",");
			for(int i=0;i<likes.length;i++){
				if(likes[i].equalsIgnoreCase(""+loja.getIdLoja())){
					imgLike.setImageResource(R.drawable.curti_ativo);
					imgLike.setEnabled(false);
				}
			}
		}
		
		
		imgFavoritos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isFavorite){
					Toast.makeText(MapaActivity.this, getString(R.string.mapa_msg_remove_favoritos), Toast.LENGTH_LONG).show();
					Utilidade.removeFavorite(MapaActivity.this, loja.getIdLoja());
					imgFavoritos.setImageResource(R.drawable.menu_favoritos);
					isFavorite = false;
				}else{
					Utilidade.saveFavorite(MapaActivity.this, loja.getIdLoja());
					imgFavarito.setImageResource(R.drawable.favoritos_pressed);
					Toast.makeText(MapaActivity.this, getString(R.string.mapa_msg_add_favoritos), Toast.LENGTH_LONG).show();
					isFavorite = true;
				}
			}
		});
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.marker_map);
		
		GeoPoint gp = new GeoPoint((int)(loja.getLatitude()*1E6), (int)(loja.getLongitude()*1E6));
		
		MapView map = (MapView) findViewById(R.id.map);
		map.setClickable(true);
		map.setBuiltInZoomControls(true);
		
		map.getController().setCenter(gp);
		map.getController().setZoom(18);
		
		map.getOverlays().add(new SitesOverlay(drawable));
		
		IMEI = Utilidade.getIMEI(MapaActivity.this);
		try {
			hash = Utilidade.SHA1(getPackageName());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		if(!Utilidade.getShowPopup(MapaActivity.this) && metrics.heightPixels > 300){
			final Dialog dialog = new Dialog(MapaActivity.this,android.R.style.Theme_InputMethod);
			dialog.setContentView(R.layout.popup_curti);
			Button close = (Button) dialog.findViewById(R.id.btClose);
			final CheckBox check = (CheckBox) dialog.findViewById(R.id.checkBox1);
			WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();
	
			WMLP.gravity = Gravity.CENTER;
			dialog.getWindow().setAttributes(WMLP);
	
			close.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					Utilidade.saveShowPopup(MapaActivity.this, check.isChecked());
								
				}
			});
	
			dialog.setCancelable(false);
			dialog.show();
		}
	}
	
	private class SitesOverlay extends ItemizedOverlay<OverlayItem> {
		private List<OverlayItem> items = new ArrayList<OverlayItem>();

		public SitesOverlay(Drawable marker) {
			super(marker);

			boundCenterBottom(marker);

			items.add(new OverlayItem(new GeoPoint((int) (latitude * 1E6),
					(int) (longitude * 1E6)), "Cliente", "Cliente"));
			populate();
		}

		@Override
		protected OverlayItem createItem(int i) {
			return (items.get(i));
		}

		@Override
		protected boolean onTap(int i) {
			return (true);
		}

		@Override
		public int size() {
			return (items.size());
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
	
	public void likeLoja() {
		
		ClientHttp.get(S.URL_LIKE_LOJA+loja.getIdLoja()+"&imei="+IMEI+"&hash="+hash, null , new AsyncHttpResponseHandler(){
			
			@Override
			public void onStart() {
				
				progress = new ProgressDialog(MapaActivity.this);
				progress.setMessage(getString(R.string.load_like));
				progress.show();

			}
			
			@Override
			public void onSuccess(int statusCode, String content) {
				
				if (statusCode != 200) {
					Toast.makeText(MapaActivity.this, getString(R.string.error_conexao_internet), Toast.LENGTH_LONG).show();
				} else {
					imgLike.setImageResource(R.drawable.curti_ativo);
					imgLike.setEnabled(false);
					Utilidade.saveLike(MapaActivity.this, loja.getIdLoja());
				}
			}
			
			@Override
			public void onFailure(Throwable error, String content) {
				Toast.makeText(MapaActivity.this, getString(R.string.error_conexao_internet), Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onFinish() {
				progress.dismiss();
			}
			
		}, null);
	}
	
	public void saveLike(){
		
		new Thread("saveLike"){
			@Override
			public void run() {
				Looper.prepare();
				
				String url = "http://50.97.119.31/~i9app968/saara/postLike.php?id_loja="+loja.getIdLoja()+"&imei="+IMEI+"&hash="+hash;
				RestClientGet get = new RestClientGet(url);
				String[] retorno = get.restGet();
				if(retorno==null){
					
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText(MapaActivity.this, getString(R.string.error_conexao_internet), Toast.LENGTH_LONG).show();
						}
					});
					
				}else{
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							imgLike.setImageResource(R.drawable.curti_ativo);
							imgLike.setEnabled(false);
							Utilidade.saveLike(MapaActivity.this, loja.getIdLoja());
						}
					});
				}
				
				progress.dismiss();
				
				Looper.loop();
			}
		}.start();
	}
}
