package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.DataInicioAntesDoFim;
import br.ufscar.dc.dsw.validation.DataPromocao;
import br.ufscar.dc.dsw.validation.UniquePromocao;

@Entity
@Table(name="Promocao")
@UniquePromocao(message = "{Unique.promocao.message}")
@DataInicioAntesDoFim(message = "{Inicio.Fim.message}")
public class Promocao  extends AbstractEntity<Long>{
	@Size(min = 3)
	@NotEmpty
	@Column(nullable = false, unique = false, length = 70)
	private String URL;
	
	@Size(min = 18)
	@NotEmpty
	@Column(nullable = false, unique = false, length = 18)
	private String CNPJ;
	
	@Min(1)
	@NotNull
	@Column(nullable = false, unique = false)
	private float preco;
	
	@NotNull
	@DataPromocao(message = "{Data.Promocao.message}")
	@Column(name = "inicio", nullable = false, unique = false)
	private Date inicio;
	
	@NotNull
	@DataPromocao(message = "{Data.Promocao.message}")
	@Column(name = "fim",nullable = false, unique = false)
	private Date fim;
	
	@ManyToOne
	private Hotel hotel;
	
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Hotel getHotel() {
		return hotel;
	}
	
	@ManyToOne 
	private Site site;
	
	public void setSite(Site site) {
		this.site = site;
	}
	
	public Site getSite() {
		return site;
	}
	public Promocao() {}
	public Promocao(String URL, String CNPJ, float preco, Date inicio, Date fim, Site site, Hotel hotel){
		this.URL = URL;
		this.CNPJ = CNPJ;
		this.preco = preco;
		this.inicio = inicio;
		this.fim = fim;
		this.site = site;
		this.hotel = hotel;
	}
	/*
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	*/
	public String getURL() {
		return URL;
	}
	
	public void setURL(String URL) {
		this.URL = URL;
	}
	
	public String getCNPJ() {
		return CNPJ;
	}
	
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public Date getInicio() {
		return inicio;
	}
	
	public void setInicio(Date date) {
		this.inicio = date;
	}
	
	public Date getFim() {
		return fim;
	}
	
	public void setFim(Date date) {
		this.fim = date;
	}
} 