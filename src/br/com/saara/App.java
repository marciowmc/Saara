package br.com.saara;

import java.util.ArrayList;
import java.util.HashMap;

import beans.Categorias;
import br.com.saara.util.ClientHttp;
import br.com.saara.util.S;

import android.app.Application;

public class App extends Application {
	
	public ArrayList<Categorias> allCategorias = new ArrayList<Categorias>();

	@Override
	public void onCreate() {
		super.onCreate();
		
		ClientHttp.setUserAgent(S.USER_AGENT);
	}

}
