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
import android.widget.Button;
import android.widget.ListView;

public class CategoriasActivity extends Activity  {

	
	ArrayList<Categorias> arrayCategorias;
	private ListView listCategorias;
	private Button btInfo;
	private Button btFavoritos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categorias);

		btInfo      = (Button) findViewById(R.id.btInformacoes);
		btFavoritos = (Button) findViewById(R.id.btFavoritos);
		
		
		arrayCategorias = new ArrayList<Categorias>();
		
		// Monta lista
		arrayCategorias.add(new Categorias(R.drawable.alimentacao, getString(R.string.alimentacao)       ,1, 17, new int[]{78,205,196} , new int[]{66,191,183} ));
		arrayCategorias.add(new Categorias(R.drawable.armarinhos, getString(R.string.armarinhos)         ,2, 3 , new int[]{255,162,0}  , new int[]{235,149,2}  ));
		arrayCategorias.add(new Categorias(R.drawable.artesanato, getString(R.string.artesanato)         ,3, 8 , new int[]{82,221,92}  , new int[]{68,207,78}  ));
		arrayCategorias.add(new Categorias(R.drawable.art_festas, getString(R.string.festas)             ,4, 12, new int[]{242,29,65}  , new int[]{228,22,58}  ));
		arrayCategorias.add(new Categorias(R.drawable.art_esportivos, getString(R.string.esportivos)     ,5, 1 , new int[]{255,198,0}  , new int[]{242,188,0}  ));
		arrayCategorias.add(new Categorias(R.drawable.bancos, getString(R.string.bancos)                 ,6, 5 , new int[]{96,2,72}    , new int[]{76,15,56}   ));
		arrayCategorias.add(new Categorias(R.drawable.bazares, getString(R.string.bazares)               ,7, 5 , new int[]{189,235,7}  , new int[]{180,224,3}  ));
		arrayCategorias.add(new Categorias(R.drawable.bijouterias, getString(R.string.bijuterias)        ,8, 17, new int[]{255,107,107}, new int[]{241,97,97}  ));
		arrayCategorias.add(new Categorias(R.drawable.brinquedos, getString(R.string.brinquedos)         ,9, 4 , new int[]{63,176,148} , new int[]{62,166,141} ));
		arrayCategorias.add(new Categorias(R.drawable.calcados_bolsas_malas, getString(R.string.calcados),10,8 , new int[]{242,100,61} , new int[]{227,88,49}  ));
		
		arrayCategorias.add(new Categorias(R.drawable.cama_mesa_banho, getString(R.string.cama)              ,11, 8 , new int[]{78,205,196} , new int[]{66,191,183}));
		arrayCategorias.add(new Categorias(R.drawable.carnaval, getString(R.string.carnaval)                 ,12, 5 , new int[]{255,162,0}  , new int[]{235,149,2}));
		arrayCategorias.add(new Categorias(R.drawable.confeccao_tecidos, getString(R.string.confeccao)       ,13, 10, new int[]{82,221,92}  , new int[]{68,207,78}));
		arrayCategorias.add(new Categorias(R.drawable.decoracao, getString(R.string.decoracao)               ,14, 5 , new int[]{242,29,65}  , new int[]{228,22,58}));
		arrayCategorias.add(new Categorias(R.drawable.descartavais_embalagens, getString(R.string.embalagens),15, 3 , new int[]{255,198,0}  , new int[]{242,188,0}));
		arrayCategorias.add(new Categorias(R.drawable.drogarias, getString(R.string.drogaria)                ,16, 2 , new int[]{96,2,72}    , new int[]{76,15,56}));
		arrayCategorias.add(new Categorias(R.drawable.emporio, getString(R.string.emporio)                   ,17, 2 , new int[]{189,235,7}  , new int[]{180,224,3}));
		arrayCategorias.add(new Categorias(R.drawable.estacionamento, getString(R.string.estacionamento)     ,18, 2 , new int[]{255,107,107}, new int[]{241,97,97}));
		arrayCategorias.add(new Categorias(R.drawable.ferragens, getString(R.string.ferragens)               ,19, 5 , new int[]{63,176,148} , new int[]{62,166,141}));
		arrayCategorias.add(new Categorias(R.drawable.flores, getString(R.string.flor)                       ,20, 2 , new int[]{242,100,61} , new int[]{227,88,49}));
		
		arrayCategorias.add(new Categorias(R.drawable.relogios, getString(R.string.joias)              ,21, 10 , new int[]{78,205,196} , new int[]{66,191,183} ));
		arrayCategorias.add(new Categorias(R.drawable.lingerie_erotica, getString(R.string.lingerie)   ,22, 10 , new int[]{255,162,0}  , new int[]{235,149,2}));
		arrayCategorias.add(new Categorias(R.drawable.malhas_praia, getString(R.string.malhas)         ,23, 23 , new int[]{82,221,92}  , new int[]{68,207,78}));
		arrayCategorias.add(new Categorias(R.drawable.moda_indiana, getString(R.string.moda)           ,24, 17 , new int[]{242,29,65}  , new int[]{228,22,58}));
		arrayCategorias.add(new Categorias(R.drawable.palhas, getString(R.string.palhas)               ,25, 17 , new int[]{255,198,0}  , new int[]{242,188,0}));
		arrayCategorias.add(new Categorias(R.drawable.papelaria, getString(R.string.papelaria)         ,26, 10 , new int[]{96,2,72}    , new int[]{76,15,56}));
		arrayCategorias.add(new Categorias(R.drawable.perfumes_cosmeticos, getString(R.string.perfumes),27, 23 , new int[]{189,235,7}  , new int[]{180,224,3}));
		arrayCategorias.add(new Categorias(R.drawable.plasticos, getString(R.string.plasticos)         ,28, 17 , new int[]{255,107,107}, new int[]{241,97,97}));
		arrayCategorias.add(new Categorias(R.drawable.presentes, getString(R.string.presentes)         ,29, 17 , new int[]{63,176,148} , new int[]{62,166,141}));
		arrayCategorias.add(new Categorias(R.drawable.roupa_feminina, getString(R.string.feminina)     ,20, 2 , new int[]{242,100,61} , new int[]{227,88,49}));
		
		arrayCategorias.add(new Categorias(R.drawable.roupa_infantil, getString(R.string.infantil)     ,1, 17, new int[]{78,205,196} , new int[]{66,191,183} ));
		arrayCategorias.add(new Categorias(R.drawable.silki, getString(R.string.silk)                  ,2, 3 , new int[]{255,162,0}  , new int[]{235,149,2}  ));
		arrayCategorias.add(new Categorias(R.drawable.vestuario_geral, getString(R.string.vestuario)   ,3, 8 , new int[]{82,221,92}  , new int[]{68,207,78}  ));
		arrayCategorias.add(new Categorias(R.drawable.outros, getString(R.string.outros)               ,4, 12, new int[]{242,29,65}  , new int[]{228,22,58}  ));
		

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
