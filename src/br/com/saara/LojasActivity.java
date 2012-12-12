package br.com.saara;

import java.util.ArrayList;

import beans.Lojas;
import br.com.saara.R.id;

import adapters.CategoriaAdapter;
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
		int id_categoria = params.getInt("id_categoria");
		
		Log.i("Local","Categoria : " +id_categoria);
		
		switch (id_categoria) {
		case 2:
			listLojas.add(new Lojas("ARMARINHO DELMAR","Loja Tradicional de Brinquedos, com mais de 100 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,1));
			listLojas.add(new Lojas("BRINK MANIA","Loja Tradicional de Brinquedos, com mais de 200 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,2));
			listLojas.add(new Lojas("LOJAS SIMÕES","Loja Tradicional de Brinquedos, com mais de 300 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,3));
			listLojas.add(new Lojas("FESTA DA CIDADE - PRESENTES","Loja Tradicional de Brinquedos, com mais de 400 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,4));
			break;

		case 1 :
			
			listLojas.add(new Lojas("ALFA 167","Loja Tradicional de Brinquedos, com mais de 100 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,1));
			listLojas.add(new Lojas("HOT FASHION 373","Loja Tradicional de Brinquedos, com mais de 200 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,2));
			listLojas.add(new Lojas("268 BIJUTERIAS","Loja Tradicional de Brinquedos, com mais de 300 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,3));
			listLojas.add(new Lojas("CAÇULA BIJUTERIAS","Loja Tradicional de Brinquedos, com mais de 400 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,4));
			break;
			
		case 3 :
			
			listLojas.add(new Lojas("BABADO DA FOLIA","Tudo para o seu carnaval 1 ","R. Sr. dos Passos, 279/283",-43.22,-23.22,1));
			listLojas.add(new Lojas("CAÇULA CARNAVAL","Tudo para o seu carnaval 2 ","R. Sr. dos Passos, 279/283",-43.22,-23.22,2));
			listLojas.add(new Lojas("CASA TURUNA","Tudo para o seu carnaval 3","R. Sr. dos Passos, 279/283",-43.22,-23.22,3));
			listLojas.add(new Lojas("LOJAS E BIJ. SILMER","Tudo para o seu carnaval 4","R. Sr. dos Passos, 279/283",-43.22,-23.22,4));
			break;
			
		case 4 :
			
			listLojas.add(new Lojas("DEDECO MODAS","Loja Tradicional de Brinquedos, com mais de 100 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,1));
			listLojas.add(new Lojas("ALDEIA DOS VENTOS","Loja Tradicional de Brinquedos, com mais de 200 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,2));
			listLojas.add(new Lojas("PRAIA DOS BAIXINHOS","Loja Tradicional de Brinquedos, com mais de 300 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,3));
			listLojas.add(new Lojas("CBK FASHION","Loja Tradicional de Brinquedos, com mais de 400 anos.","R. Sr. dos Passos, 279/283",-43.22,-23.22,4));
			break;
		default:
			break;
		}
		
		 LojaAdapter adapter = new LojaAdapter(this,listLojas,R.layout.loja_itens);
		   
	       listView.setAdapter(adapter);
	       
	       listView.setOnItemClickListener(new OnItemClickListener() {
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
