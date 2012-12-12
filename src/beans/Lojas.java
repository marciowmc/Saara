package beans;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Lojas implements Serializable {

	private String nome;
	private String descricao;
	private String endereco;
	private double latitude;
	private double longitude;
	private int idLoja;
	
	
	public Lojas(String nome, String descricao, String endereco,
			double latitude, double longitude, int idLoja) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.endereco = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
		this.idLoja = idLoja;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	
	
}
