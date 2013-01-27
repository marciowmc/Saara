package br.com.saara.util;

import java.util.ArrayList;

import beans.Categorias;
import beans.Lojas;

public class MontaListaLojas {
	
	private Categorias categoria;
	private ArrayList<Lojas> lojas;
	
	public MontaListaLojas(Categorias categoria){
		this.categoria = categoria;
	}
	
	public ArrayList<Lojas> getLojas(){

		switch(categoria.getIdCategoria()){
		
		case 1:{

		}
		
		case 2:{
			
		}
		
		case 3:{
			
		}
		
		case 4:{
			
		}
		
		case 5:{
			
		}
		}
		
		
		return lojas;
	}
	
}
