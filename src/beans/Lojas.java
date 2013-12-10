package beans;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Lojas implements Serializable {

	private String nome;
	private String endereco;
	private double latitude;
	private double longitude;
	private String telefone;
	private int idLoja;
	private int id_categoria;
	private String categoria;
	private int likes;
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public Lojas(String nome, String endereco,
			double latitude, double longitude, int idLoja ) {
		super();
		this.nome = nome;
		this.endereco  = endereco;
		this.latitude  = latitude;
		this.longitude = longitude;
		this.idLoja    = idLoja;
	}
	
	public Lojas() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getIdLoja() {
		return idLoja;
	}
	public void setIdLoja(int idLoja) {
		this.idLoja = idLoja;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
	
}
