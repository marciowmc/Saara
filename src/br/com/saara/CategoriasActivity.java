package br.com.saara;

import java.util.ArrayList;
import beans.Categorias;

import adapters.CategoriaAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CategoriasActivity extends Activity  {

	
	ArrayList<Categorias> arrayCategorias;
	private ListView listCategorias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categorias);
		arrayCategorias = new ArrayList<Categorias>();
		
		arrayCategorias.add(new Categorias(R.drawable.seta_botao, "Bijuterias",1));
		arrayCategorias.add(new Categorias(R.drawable.seta_botao, "Brinquedos",2));
		arrayCategorias.add(new Categorias(R.drawable.seta_botao, "Carnaval",3));
		arrayCategorias.add(new Categorias(R.drawable.seta_botao, "Roupas",4));
		
		   CategoriaAdapter adapter = new CategoriaAdapter(this,arrayCategorias,R.layout.categoria_itens);
		   
		   listCategorias = (ListView)findViewById(R.id.listCategorias);
		  
	       listCategorias.setAdapter(adapter);
	       
	       listCategorias.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
				   Intent intent = new Intent();
				   intent.setClass(CategoriasActivity.this, LojasActivity.class);
				   Log.i("Local", "Categorias id : "+ arrayCategorias.get(position).getIdCategoria());
				   intent.putExtra("id_categoria", arrayCategorias.get(position).getIdCategoria());
				   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				   startActivity(intent);
				}
			});
		
	}
	
}
