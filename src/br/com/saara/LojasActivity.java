package br.com.saara;

import java.util.ArrayList;

import beans.Categorias;
import beans.Lojas;
import adapters.LojaAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LojasActivity extends Activity {
	
	private ArrayList<Lojas> listLojas;
	private ListView listView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lojas);
		
		listView = (ListView) findViewById(R.id.listLojas);
		
		listLojas = new ArrayList<Lojas>();
		
		Bundle params = getIntent().getExtras();
		Categorias categoria = (Categorias) params.getSerializable("categoria");
		
		Log.i("Local","Categoria : " +categoria.getIcon());
		
			
		 LojaAdapter adapter = new LojaAdapter(this,listLojas,R.layout.loja_itens);
		   
	       listView.setAdapter(adapter);
	       
	       listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
				   Intent intent = new Intent();
				   intent.setClass(LojasActivity.this, MapaActivity.class);
				   intent.putExtra("loja", listLojas.get(position));
				   startActivity(intent);
				}
			});
	}

}
