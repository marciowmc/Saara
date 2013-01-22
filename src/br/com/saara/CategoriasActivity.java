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
		
		arrayCategorias.add(new Categorias(R.drawable.armarinhos, getString(R.string.armarinhos),1, 10 , new int[]{78,205,196}));
		arrayCategorias.add(new Categorias(R.drawable.brinquedos, getString(R.string.brinquedos),1, 23 , new int[]{255,162,0}));
		arrayCategorias.add(new Categorias(R.drawable.carnaval, getString(R.string.carnaval),1, 17 , new int[]{82,221,92}));
		arrayCategorias.add(new Categorias(R.drawable.decoracao, getString(R.string.decoracao),1, 17 , new int[]{242,29,65}));
		arrayCategorias.add(new Categorias(R.drawable.estacionamento, getString(R.string.estacionamento),1, 10 , new int[]{255,198,0}));
		arrayCategorias.add(new Categorias(R.drawable.bancos, getString(R.string.bancos),1, 23 , new int[]{96,2,72}));
		arrayCategorias.add(new Categorias(R.drawable.bazares, getString(R.string.bazares),1, 17 , new int[]{189,235,7}));
		arrayCategorias.add(new Categorias(R.drawable.bijouterias, getString(R.string.bijuterias),1, 17 , new int[]{255,107,107 }));
		
		//arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.esportivos),2 , 10 , 0xFFA200));
		/*arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.festas),3,  10 , "52DD5C"));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.artesanato),4 , 10 , "F21D41"));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.bancos),5 , 15, "FFC600"));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.bazares),6 ,19 , "601848"));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.bijuterias),7, 23, "BDEB07"));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.brinquedos),8, 9,"FF6B6B"));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.calcados),9));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.cama),10));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.carnaval),11));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.confeccao),12));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.decoracao),13));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.embalagens),14));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.drogaria),15));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.emporio),16));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.estacionamento),17));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.ferragens),18));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.flor),19));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.joias),20));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.bares),21));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.lingerie),22));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.malhas),23));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.moda),24));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.palhas),25));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.papelaria),26));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.perfumes),27));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.plasticos),28));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.presentes),29));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.feminina),30));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.infantil),31));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.silk),32));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.tecidos),33));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.vestuario),34));
		arrayCategorias.add(new Categorias(R.drawable.bijuterias, getString(R.string.outros),35	));*/

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
