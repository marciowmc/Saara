package beans;

public class Categorias {
	
	private int icon;
	private String text;
	private int idCategoria;
	

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
	
	public Categorias(int icon, String text, int idCategoria) {
		super();
		this.icon = icon;
		this.text = text;
		this.idCategoria = idCategoria;
	}
	
	
}
