package br.com.saara;

import java.util.ArrayList;
import java.util.HashMap;

import beans.Categorias;
import br.com.saara.util.ClientHttp;
import br.com.saara.util.S;

import android.app.Application;

public class App extends Application {
	
	private HashMap<String, String> qtdCategoria;
	public ArrayList<Categorias> allCategorias = new ArrayList<Categorias>();

	@Override
	public void onCreate() {
		super.onCreate();
		
		qtdCategoria = new HashMap<String, String>();
		
		ClientHttp.setUserAgent(S.USER_AGENT);
	}

}
