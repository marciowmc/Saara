	package br.com.saara;

import java.util.ArrayList;

import beans.Categorias;

import com.bugsense.trace.BugSenseHandler;

import adapters.CategoriaAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CategoriasActivity extends Activity  {

	
	ArrayList<Categorias> arrayCategorias;
	private ListView listCategorias;
	private Button btInfo;
	private Button btFavoritos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		BugSenseHandler.initAndStartSession(CategoriasActivity.this, "c8c053dd");
		setContentView(R.layout.categorias);	
		btFavoritos = (Button) findViewById(R.id.btFavoritos);
		btInfo      = (Button) findViewById(R.id.btInformacoes);
		
		btInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(CategoriasActivity.this,android.R.style.Theme_InputMethod);
				dialog.setContentView(R.layout.popup_info);
				Button btClose = (Button) dialog.findViewById(R.id.btClose);
				
			    WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();

			    WMLP.gravity = Gravity.CENTER;
			    dialog.getWindow().setAttributes(WMLP);
			    
			    btClose.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
			    
			    dialog.show();
			}
		});
		
		
		
		btFavoritos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent().setClass(CategoriasActivity.this, FavoritosActivity.class));
			}
		});
		
		arrayCategorias = new ArrayList<Categorias>();
		
		// Monta lista
		arrayCategorias.add(new Categorias(R.drawable.alimentacao, getString(R.string.alimentacao)       	 ,1, 20, new int[]{78,205,196} , new int[]{66,191,183} ));
		arrayCategorias.add(new Categorias(R.drawable.armarinhos, getString(R.string.armarinhos)         	 ,2, 3 , new int[]{255,162,0}  , new int[]{235,149,2}  ));
		arrayCategorias.add(new Categorias(R.drawable.artesanato, getString(R.string.artesanato)        	 ,3, 9 , new int[]{82,221,92}  , new int[]{68,207,78}  ));
		arrayCategorias.add(new Categorias(R.drawable.art_festas, getString(R.string.festas)             	 ,4, 12, new int[]{242,29,65}  , new int[]{228,22,58}  ));
		arrayCategorias.add(new Categorias(R.drawable.art_esportivos, getString(R.string.esportivos)     	 ,5, 1 , new int[]{255,198,0}  , new int[]{242,188,0}  ));
		arrayCategorias.add(new Categorias(R.drawable.bancos, getString(R.string.bancos)                 	 ,6, 5 , new int[]{96,2,72}    , new int[]{76,15,56}   ));
		arrayCategorias.add(new Categorias(R.drawable.bazares, getString(R.string.bazares)               	 ,7, 5 , new int[]{189,235,7}  , new int[]{180,224,3}  ));
		arrayCategorias.add(new Categorias(R.drawable.bijouterias, getString(R.string.bijuterias)       	 ,8, 17, new int[]{255,107,107}, new int[]{241,97,97}  ));
		arrayCategorias.add(new Categorias(R.drawable.brinquedos, getString(R.string.brinquedos)         	 ,9, 4 , new int[]{63,176,148} , new int[]{62,166,141} ));
		arrayCategorias.add(new Categorias(R.drawable.calcados_bolsas_malas, getString(R.string.calcados)	 ,10,8 , new int[]{242,100,61} , new int[]{227,88,49}  ));
		
		arrayCategorias.add(new Categorias(R.drawable.cama_mesa_banho, getString(R.string.cama)              ,11, 8 , new int[]{78,205,196} , new int[]{66,191,183}));
		arrayCategorias.add(new Categorias(R.drawable.carnaval, getString(R.string.carnaval)                 ,12, 6 , new int[]{255,162,0}  , new int[]{235,149,2}));
		arrayCategorias.add(new Categorias(R.drawable.confeccao_tecidos, getString(R.string.confeccao)       ,13, 10, new int[]{82,221,92}  , new int[]{68,207,78}));
		arrayCategorias.add(new Categorias(R.drawable.decoracao, getString(R.string.decoracao)               ,14, 5 , new int[]{242,29,65}  , new int[]{228,22,58}));
		arrayCategorias.add(new Categorias(R.drawable.descartavais_embalagens, getString(R.string.embalagens),15, 3 , new int[]{255,198,0}  , new int[]{242,188,0}));
		arrayCategorias.add(new Categorias(R.drawable.drogarias, getString(R.string.drogaria)                ,16, 2 , new int[]{96,2,72}    , new int[]{76,15,56}));
		arrayCategorias.add(new Categorias(R.drawable.emporio, getString(R.string.emporio)                   ,17, 2 , new int[]{189,235,7}  , new int[]{180,224,3}));
		arrayCategorias.add(new Categorias(R.drawable.estacionamento, getString(R.string.estacionamento)     ,18, 2 , new int[]{255,107,107}, new int[]{241,97,97}));
		arrayCategorias.add(new Categorias(R.drawable.ferragens, getString(R.string.ferragens)               ,19, 5 , new int[]{63,176,148} , new int[]{62,166,141}));
		arrayCategorias.add(new Categorias(R.drawable.flores, getString(R.string.flor)                       ,20, 2 , new int[]{242,100,61} , new int[]{227,88,49}));
		
		arrayCategorias.add(new Categorias(R.drawable.relogios, getString(R.string.joias)              		 ,21, 17 , new int[]{78,205,196} , new int[]{66,191,183} ));
		arrayCategorias.add(new Categorias(R.drawable.lingerie_erotica, getString(R.string.lingerie)   		 ,22, 3 , new int[]{255,162,0}  , new int[]{235,149,2}));
		arrayCategorias.add(new Categorias(R.drawable.malhas_praia, getString(R.string.malhas)         		 ,23, 8 , new int[]{82,221,92}  , new int[]{68,207,78}));
		arrayCategorias.add(new Categorias(R.drawable.moda_indiana, getString(R.string.moda)           		 ,24, 3 , new int[]{242,29,65}  , new int[]{228,22,58}));
		arrayCategorias.add(new Categorias(R.drawable.outros, getString(R.string.outros)               		 ,25, 29 , new int[]{255,198,0}  , new int[]{242,188,0}));
		arrayCategorias.add(new Categorias(R.drawable.palhas, getString(R.string.palhas)         		 	 ,26, 2 , new int[]{96,2,72}    , new int[]{76,15,56}));
		arrayCategorias.add(new Categorias(R.drawable.papelaria, getString(R.string.papelaria)		         ,27, 2 , new int[]{189,235,7}  , new int[]{180,224,3}));
		arrayCategorias.add(new Categorias(R.drawable.perfumes_cosmeticos, getString(R.string.perfumes)      ,28, 4 , new int[]{255,107,107}, new int[]{241,97,97}));
		arrayCategorias.add(new Categorias(R.drawable.plasticos, getString(R.string.plasticos)         		 ,29, 4 , new int[]{63,176,148} , new int[]{62,166,141}));
		arrayCategorias.add(new Categorias(R.drawable.presentes, getString(R.string.presentes)     		     ,30, 6 , new int[]{242,100,61} , new int[]{227,88,49}));
		
		arrayCategorias.add(new Categorias(R.drawable.roupa_feminina, getString(R.string.feminina)     		 ,31, 14, new int[]{78,205,196} , new int[]{66,191,183} ));
		arrayCategorias.add(new Categorias(R.drawable.roupa_infantil, getString(R.string.infantil)           ,32, 7 , new int[]{255,162,0}  , new int[]{235,149,2}  ));
		arrayCategorias.add(new Categorias(R.drawable.silki, getString(R.string.silk)   		 			 ,33, 1 , new int[]{82,221,92}  , new int[]{68,207,78}  ));
		arrayCategorias.add(new Categorias(R.drawable.vestuario_geral, getString(R.string.vestuario)         ,34, 15, new int[]{242,29,65}  , new int[]{228,22,58}  ));

		CategoriaAdapter adapter = new CategoriaAdapter(this,arrayCategorias,R.layout.categoria_itens);
		   
		   listCategorias = (ListView)findViewById(R.id.listCategoria);
		  
	       listCategorias.setAdapter(adapter);
	       
	       listCategorias.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
				   Intent intent = new Intent();
				   intent.setClass(CategoriasActivity.this, LojasActivity.class);
				   intent.putExtra("categoria",arrayCategorias.get(position));	
				   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				   startActivity(intent);
				}
			});
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(CategoriasActivity.this);
		alert.setTitle(getString(R.string.app_name));
		alert.setMessage(getString(R.string.dialogo_sair_app));
		alert.setPositiveButton(getString(R.string.bt_dialogo_sim), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				finish();
			}
		});
		
		alert.setNegativeButton(getString(R.string.bt_dialogo_nao), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		alert.create().show();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		BugSenseHandler.closeSession(CategoriasActivity.this);
	}
}
