package br.com.saara;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import beans.Categorias;
import beans.Lojas;
import br.com.saara.util.Utilidade;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MapaActivity extends MapActivity {

	private double latitude,longitude;
	private Lojas loja;
	private Categorias categoria;
	private boolean isFavorite = false;
	private ImageView imgFavoritos;
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.mapa_loja);
		
		Bundle params = getIntent().getExtras();
		
		loja = (Lojas) params.getSerializable("loja");
		categoria = (Categorias) params.getSerializable("categoria");
		
		latitude  = loja.getLatitude();
		longitude = loja.getLongitude();
		
		imgFavoritos = (ImageView) findViewById(R.id.imgFavorito);
		
		TextView txtEnd       = (TextView) findViewById(R.id.txtEnd);
		TextView txtCategoria = (TextView) findViewById(R.id.txtCategoria);
		TextView txtTitle     = (TextView) findViewById(R.id.txtTitle);
		
		Button btVoltar       = (Button) findViewById(R.id.btVoltar);
		Button btFavoritos    = (Button) findViewById(R.id.btFavoritoRodape);
		Button btFavoritoTopo = (Button) findViewById(R.id.btFavoritos);
		Button btSegmentos    = (Button) findViewById(R.id.btSegmentos);
		Button btInfo         = (Button) findViewById(R.id.btInformacoes);
		
		
		btFavoritoTopo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setClass(MapaActivity.this, FavoritosActivity.class));
				finish();
			}
		});
		
		btInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(MapaActivity.this,android.R.style.Theme_InputMethod);
				dialog.setContentView(R.layout.popup_info);
				Button close = (Button) dialog.findViewById(R.id.btClose);
			    WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();

			    WMLP.gravity = Gravity.TOP | Gravity.RIGHT;
			    WMLP.y = 120;   //y position
			    dialog.getWindow().setAttributes(WMLP);
			    
			    close.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
			    
			    dialog.show();
			}
		});
		
		ImageView imgCategoria = (ImageView) findViewById(R.id.imgCategoria);
		final ImageView imgFavarito  = (ImageView) findViewById(R.id.imgFavorito);
		
		imgCategoria.setImageResource(categoria.getIcon());
		
		txtEnd.setText(loja.getEndereco());
		txtCategoria.setText(categoria.getText());
		txtTitle.setText(loja.getNome());
		
		int[] rgb =  categoria.getRgbColorListaLojas();
		
		txtTitle.setBackgroundColor(Color.argb(215,rgb[0], rgb[1],rgb[2] ));
		imgCategoria.setBackgroundColor(Color.rgb(rgb[0], rgb[1],rgb[2]));
		imgFavarito.setBackgroundColor(Color.argb(125,rgb[0], rgb[1],rgb[2]));
		
		btFavoritos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isFavorite){
					Toast.makeText(MapaActivity.this, "Essa loja j� foi adicionada aos favoritos.", Toast.LENGTH_LONG).show();
				}else{
					Utilidade.saveFavorite(MapaActivity.this, loja.getIdLoja());
					imgFavarito.setImageResource(R.drawable.favoritos_pressed);
					Toast.makeText(MapaActivity.this, "Loja adicionada aos favoritos.", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		btVoltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
		
		btSegmentos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
		
		imgFavoritos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isFavorite){
					AlertDialog.Builder dialog = new AlertDialog.Builder(MapaActivity.this);
					dialog.setTitle("Meu Saara");
					dialog.setMessage("Deseja remover dos favoritos ?");
					dialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							Toast.makeText(MapaActivity.this, "Loja removida dos favoritos.", Toast.LENGTH_LONG).show();
							Utilidade.removeFavorite(MapaActivity.this, loja.getIdLoja());
							imgFavoritos.setImageResource(R.drawable.favoritos_lojas);
							isFavorite = false;
							
						}
					});
					
					dialog.setNegativeButton("N�O", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					
				}else{
					Utilidade.saveFavorite(MapaActivity.this, loja.getIdLoja());
					imgFavarito.setImageResource(R.drawable.favoritos_pressed);
					Toast.makeText(MapaActivity.this, "Loja adicionada aos favoritos.", Toast.LENGTH_LONG).show();
					isFavorite = true;
				}
			}
		});
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.icon_pin_cliente);
		
		GeoPoint gp = new GeoPoint((int)(loja.getLatitude()*1E6), (int)(loja.getLongitude()*1E6));
		
		MapView map = (MapView) findViewById(R.id.map);
		map.setClickable(true);
		map.setBuiltInZoomControls(true);
		
		map.getController().setCenter(gp);
		map.getController().setZoom(18);
		
		map.getOverlays().add(new SitesOverlay(drawable));
		
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
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
}
