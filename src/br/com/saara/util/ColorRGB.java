package br.com.saara.util;

import beans.Categorias;
import br.com.saara.R;

public class ColorRGB {

	public int[] getColorList(int cat){
		int[] rgb = null;
		int categoria = cat;
		
		if(categoria > 10 && categoria <= 20){
			categoria -= 10;
		}else if(categoria > 20	&& categoria <= 30){
			categoria -= 20;
		}else if(categoria > 30){
			categoria -= 30;
		}
		
		switch(categoria){
		case 1:
			 rgb = new int[] {78,205,196,66,191,183};
			 break;
		case 2:	 
			rgb =  new int[]{255,162,0,235,149,2};
			break;
		case 3:{
			rgb = new int[]{82,221,92,68,207,78};
			break;
		}
		
		case 4:{
			rgb = new int[]{242,29,65,228,22,58};
			break;
		}
		
		case 5:{
			rgb = new int[]{255,198,0,242,188,0};
			break;
		}
		
		case 6:{
			rgb = new int[]{96,2,72 , 76,15,56};
			break;
		}
		
		case 7:{
			rgb = new int[]{189,235,7,180,224,3};
			break;
		}
		
		case 8:{
			rgb = new int[]{255,107,107,241,97,97};
			break;
		}
		
		case 9:{
			rgb = new int[]{63,176,148,62,166,141};
			break;
		}
		
		case 0:{
			rgb = new int[]{242,100,61,227,88,49};
			break;
		}
		default:{
			rgb = new int[] {78,205,196,66,191,183};
			break;
		}
		}
		return rgb;
	}
	
	
	public int getDrawableCategoria(int categoria){
		int drawable = 0;
		
		switch(categoria){
		case 1:
			drawable = R.drawable.alimentacao; 
			break;
		case 2:
			drawable = R.drawable.armarinhos;
			break;
		case 3:
			drawable = R.drawable.artesanato;
			break;
		case 4:
			drawable = R.drawable.art_festas;
			break;
		case 5:
			drawable = R.drawable.art_esportivos;
			break;
		case 6:
			drawable = R.drawable.bancos;
			break;
		case 7:
			drawable = R.drawable.bazares;
			break;
		case 8:
			drawable = R.drawable.bijouterias;
			break;
		case 9:
			drawable = R.drawable.brinquedos;
			break;
		case 10:
			drawable = R.drawable.calcados_bolsas_malas;
			break;
		case 11:
			drawable = R.drawable.cama_mesa_banho;
			break;
		case 12:
			drawable = R.drawable.carnaval;
			break;
		case 13:
			drawable = R.drawable.confeccao_tecidos;
			break;
		case 14:
			drawable = R.drawable.decoracao;
			break;
		case 15:
			drawable = R.drawable.descartavais_embalagens;
			break;
		case 16:
			drawable = R.drawable.drogarias;
			break;
		case 17:
			drawable = R.drawable.emporio;
			break;
		case 18:
			drawable = R.drawable.estacionamento;
			break;
		case 19:
			drawable = R.drawable.ferragens;
			break;
		case 20:
			drawable = R.drawable.flores;
			break;
		case 21:
			drawable = R.drawable.relogios;
			break;
		case 22:
			drawable = R.drawable.lingerie_erotica;
			break;
		case 23:
			drawable = R.drawable.malhas_praia;
			break;
		case 24:
			drawable = R.drawable.moda_indiana;
			break;
		case 25:
			drawable = R.drawable.outros;
			break;
		case 26:
			drawable = R.drawable.palhas;
			break;
		case 27:
			drawable = R.drawable.papelaria;
			break;
		case 28:
			drawable = R.drawable.perfumes_cosmeticos;
			break;
		case 29:
			drawable = R.drawable.plasticos;
			break;
		case 30:
			drawable = R.drawable.presentes;
			break;
		case 31:
			drawable = R.drawable.roupa_feminina;
			break;
		case 32:
			drawable = R.drawable.roupa_infantil;
			break;
		case 33:
			drawable = R.drawable.silki;
			break;
		case 34:
			drawable = R.drawable.vestuario_geral;
			break;
		default:
			drawable = R.drawable.outros;
			break;
		}
		return drawable;
	}
}
