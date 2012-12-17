package br.com.saara;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import beans.Lojas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MapaActivity extends MapActivity {

	private double latitude,longitude;
	
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
		
		Lojas loja = (Lojas) params.getSerializable("loja");
		
		latitude  = loja.getLatitude();
		longitude = loja.getLongitude();
		
		TextView txtLoja = (TextView) findViewById(R.id.txtLoja);
		TextView txtEnd  = (TextView) findViewById(R.id.txtEndereco);
		
		txtLoja.setText(loja.getNome());
		txtEnd.setText(loja.getEndereco());
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.icon_pin_cliente);
		
		GeoPoint gp = new GeoPoint((int)(loja.getLatitude()*1E6), (int)(loja.getLongitude()*1E6));
		
		MapView map = (MapView) findViewById(R.id.map);
		map.setClickable(true);
		map.setBuiltInZoomControls(true);
		
		map.getController().setCenter(gp);
		map.getController().setZoom(17);
		
		map.getOverlays().add(new SitesOverlay(drawable));
		
		Log.i("Local",loja.getLatitude()+ " - "+ loja.getLongitude());
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
}

