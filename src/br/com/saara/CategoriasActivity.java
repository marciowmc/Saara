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
import android.widget.LinearLayout;
import android.widget.Toast;
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
		
		arrayCategorias.add(new Categorias(R.drawable.alimentacao, getString(R.string.alimentacao),1, 10 , new int[]{78,205,196}));
		arrayCategorias.add(new Categorias(R.drawable.armarinhos, getString(R.string.armarinhos),1, 10 , new int[]{255,162,0}));
		arrayCategorias.add(new Categorias(R.drawable.art_esportivos, getString(R.string.esportivos),1, 23 , new int[]{82,221,92}));
		arrayCategorias.add(new Categorias(R.drawable.art_festas, getString(R.string.festas),1, 17 , new int[]{242,29,65}));
		arrayCategorias.add(new Categorias(R.drawable.artesanato, getString(R.string.artesanato),1, 17 ,new int[]{255,198,0}));
		arrayCategorias.add(new Categorias(R.drawable.bancos, getString(R.string.bancos),1, 10 , new int[]{96,2,72}));
		arrayCategorias.add(new Categorias(R.drawable.bazares, getString(R.string.bazares),1, 23 , new int[]{189,235,7}));
		arrayCategorias.add(new Categorias(R.drawable.bijouterias, getString(R.string.bijuterias),1, 17 , new int[]{255,107,107 }));
		arrayCategorias.add(new Categorias(R.drawable.brinquedos, getString(R.string.brinquedos),1, 17 , new int[]{63,176,148}));
		arrayCategorias.add(new Categorias(R.drawable.calcados_bolsas_malas, getString(R.string.calcados),1, 17 , new int[]{242,100,61}));
		
		arrayCategorias.add(new Categorias(R.drawable.cama_mesa_banho, getString(R.string.cama),1, 10 , new int[]{78,205,196}));
		arrayCategorias.add(new Categorias(R.drawable.carnaval, getString(R.string.carnaval),1, 10 , new int[]{255,162,0}));
		arrayCategorias.add(new Categorias(R.drawable.confeccao_tecidos, getString(R.string.confeccao),1, 23 , new int[]{82,221,92}));
		arrayCategorias.add(new Categorias(R.drawable.decoracao, getString(R.string.decoracao),1, 17 , new int[]{242,29,65}));
		arrayCategorias.add(new Categorias(R.drawable.descartavais_embalagens, getString(R.string.embalagens),1, 17 ,new int[]{255,198,0}));
		arrayCategorias.add(new Categorias(R.drawable.drogarias, getString(R.string.drogaria),1, 10 , new int[]{96,2,72}));
		arrayCategorias.add(new Categorias(R.drawable.emporio, getString(R.string.emporio),1, 23 , new int[]{189,235,7}));
		arrayCategorias.add(new Categorias(R.drawable.estacionamento, getString(R.string.estacionamento),1, 17 , new int[]{255,107,107 }));
		arrayCategorias.add(new Categorias(R.drawable.ferragens, getString(R.string.ferragens),1, 17 , new int[]{63,176,148}));
		arrayCategorias.add(new Categorias(R.drawable.flores, getString(R.string.flor),1, 17 , new int[]{242,100,61}));
		
		arrayCategorias.add(new Categorias(R.drawable.relogios, getString(R.string.joias),1, 10 , new int[]{78,205,196}));
		arrayCategorias.add(new Categorias(R.drawable.lingerie_erotica, getString(R.string.lingerie),1, 10 , new int[]{255,162,0}));
		arrayCategorias.add(new Categorias(R.drawable.malhas_praia, getString(R.string.malhas),1, 23 , new int[]{82,221,92}));
		arrayCategorias.add(new Categorias(R.drawable.moda_indiana, getString(R.string.moda),1, 17 , new int[]{242,29,65}));
		arrayCategorias.add(new Categorias(R.drawable.palhas, getString(R.string.palhas),1, 17 ,new int[]{255,198,0}));
		arrayCategorias.add(new Categorias(R.drawable.papelaria, getString(R.string.papelaria),1, 10 , new int[]{96,2,72}));
		arrayCategorias.add(new Categorias(R.drawable.perfumes_cosmeticos, getString(R.string.perfumes),1, 23 , new int[]{189,235,7}));
		arrayCategorias.add(new Categorias(R.drawable.plasticos, getString(R.string.plasticos),1, 17 , new int[]{255,107,107 }));
		arrayCategorias.add(new Categorias(R.drawable.presentes, getString(R.string.presentes),1, 17 , new int[]{63,176,148}));
		
		arrayCategorias.add(new Categorias(R.drawable.roupa_infantil, getString(R.string.infantil),1, 10 , new int[]{78,205,196}));
		arrayCategorias.add(new Categorias(R.drawable.silki, getString(R.string.silk),1, 10 , new int[]{255,162,0}));
		arrayCategorias.add(new Categorias(R.drawable.vestuario_geral,getString(R.string.vestuario),1, 23 , new int[]{82,221,92}));
		arrayCategorias.add(new Categorias(R.drawable.outros, getString(R.string.outros),1, 17 , new int[]{242,29,65}));
		


		CategoriaAdapter adapter = new CategoriaAdapter(this,arrayCategorias,R.layout.categoria_itens);
		   
		   listCategorias = (ListView)findViewById(R.id.listCategoria);
		  
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
