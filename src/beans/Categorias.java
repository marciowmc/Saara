package beans;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Categorias implements Serializable {
	
	private int icon;
	private String text;
	private int idCategoria;
	private int qtdLojas;
	private int[] rgbColor;
	private int[] rgbColorListaLojas;
	
  
	public int[] getRgbColorListaLojas() {
		return rgbColorListaLojas;
	}

	public void setRgbColorListaLojas(int[] rgbColorListaLojas) {
		this.rgbColorListaLojas = rgbColorListaLojas;
	}
	
	public int[] getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(int[] rgbColor) {
		this.rgbColor = rgbColor;
	}

	public int getQtdLojas() {
		return qtdLojas;
	}

	public void setQtdLojas(int qtdLojas) {
		this.qtdLojas = qtdLojas;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIcon() {
		return icon;
	}
	
	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Categorias(int icon, String text, int idCategoria , int qtd , int[] hexColor , int[] rgbColorLojas) {
		super();
		this.icon = icon;
		this.text = text;
		this.idCategoria = idCategoria;
		this.qtdLojas = qtd;
		this.rgbColor = hexColor;
		this.rgbColorListaLojas = rgbColorLojas;
	}
	
	
}
