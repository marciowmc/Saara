package br.com.saara.util;

import beans.Categorias;
import br.com.saara.R;

public class ColorRGB {

	public int[] getColorList(int cat){
		int[] rgb = null;
		int categoria = cat;
		
		if(categoria > 10){
			String scat = ""+categoria;
			scat = scat.substring(1, 1);
			categoria = Integer.parseInt(scat);
		}
		
		switch(categoria){
		case 1:
			 rgb = new int[] {78,205,196,66,191,183};
			
		case 2:	 
			rgb =  new int[]{255,162,0,235,149,2};
		
		case 3:{
			rgb = new int[]{82,221,92,68,207,78};
		}
		
		case 4:{
			rgb = new int[]{242,29,65,228,22,58};
		}
		
		case 5:{
			rgb = new int[]{255,198,0,242,188,0}; 
		}
		
		case 6:{
			rgb = new int[]{96,2,72 , 76,15,56}; 
		}
		
		case 7:{
			rgb = new int[]{189,235,7,180,224,3};
		}
		
		case 8:{
			rgb = new int[]{255,107,107,241,97,97};
		}
		
		case 9:{
			rgb = new int[]{63,176,148,62,166,141};
		}
		
		case 0:{
			rgb = new int[]{242,100,61,227,88,49};
		}
		default:{
			rgb = new int[] {78,205,196,66,191,183};
		}
		}
		return rgb;
	}
	
	
	public int getDrawableCategoria(int categoria){
		int drawable = 0;
		
		switch(categoria){
		case 1:
			drawable = R.drawable.alimentacao;
		case 2:
			drawable = R.drawable.armarinhos;
		case 3:
			drawable = R.drawable.artesanato;
		case 4:
			drawable = R.drawable.art_festas;
		case 5:
			drawable = R.drawable.art_esportivos;
		case 6:
			drawable = R.drawable.bancos;
		case 7:
			drawable = R.drawable.bazares;
		case 8:
			drawable = R.drawable.bijouterias;
		case 9:
			drawable = R.drawable.brinquedos;
		case 10:
			drawable = R.drawable.calcados_bolsas_malas;
		case 11:
			drawable = R.drawable.cama_mesa_banho;
		case 12:
			drawable = R.drawable.carnaval;
		case 13:
			drawable = R.drawable.confeccao_tecidos;
		case 14:
			drawable = R.drawable.decoracao;
		case 15:
			drawable = R.drawable.descartavais_embalagens;
		case 16:
			drawable = R.drawable.drogarias;
		case 17:
			drawable = R.drawable.emporio;
		case 18:
			drawable = R.drawable.estacionamento;
		case 19:
			drawable = R.drawable.ferragens;
		case 20:
			drawable = R.drawable.flores;
		case 21:
			drawable = R.drawable.relogios;
		case 22:
			drawable = R.drawable.lingerie_erotica;
		case 23:
			drawable = R.drawable.malhas_praia;
		case 24:
			drawable = R.drawable.moda_indiana;
		case 25:
			drawable = R.drawable.outros;
		case 26:
			drawable = R.drawable.palhas;
		case 27:
			drawable = R.drawable.papelaria;
		case 28:
			drawable = R.drawable.perfumes_cosmeticos;
		case 29:
			drawable = R.drawable.plasticos;
		case 30:
			drawable = R.drawable.presentes;
		case 31:
			drawable = R.drawable.roupa_feminina;
		case 32:
			drawable = R.drawable.roupa_infantil;
		case 33:
			drawable = R.drawable.silki;
		case 34:
			drawable = R.drawable.vestuario_geral;
		default:
			drawable = R.drawable.outros;		
		}
		return drawable;
	}
}
