package br.com.saara;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PinLocation extends ItemizedOverlay<OverlayItem> {

	private Context context;
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
	public PinLocation(Drawable defaultMarker) {
		super(defaultMarker);
		// TODO Auto-generated constructor stub
	}
	
	public PinLocation(Drawable defaultMarker ,Context ctx ) {
		super(defaultMarker);
		context = ctx;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addOverlay(OverlayItem overlay) {
        mOverlays.add(overlay);
        populate();
    }
}
