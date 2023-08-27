package br.ufscar.dc.dsw.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.FormatoURL;
import br.ufscar.dc.dsw.validation.UniqueCNPJ;
import br.ufscar.dc.dsw.validation.UniqueEmail;
import br.ufscar.dc.dsw.validation.UniqueURL;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table(name = "Site")
public class Site extends AbstractEntity<Long>{
	
	@UniqueEmail(message = "{Unique.email}")
	@Size(min = 3,max = 256)
	@NotEmpty
	@Email
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
	@NotEmpty
	@Column(nullable = false, unique = false, length = 100)
	private String senha;
	
	@FormatoURL(message = "{Formato.URL}")
	@UniqueURL (message = "{Unique.site.URL}")
	@NotEmpty(message = "{NotEmpty.site.URL}")
	@Column(nullable = false, unique = false, length = 100)
	private String URL;
	
	@Size(min = 3)
	@NotEmpty(message = "{NotEmpty.site.nome}")
	@Column(nullable = false, unique = false, length = 70)
	private String nome;
	
	@NotEmpty
	@Size(min = 1,max = 15, message = "{Size.site.telefone}")
	@Column(nullable = false, unique = false, length = 15)
	private String telefone;
	
	@OneToMany(targetEntity = Promocao.class, mappedBy = "site", fetch = FetchType.EAGER)
	private Set<Promocao> promocoes;
	
	@OneToOne
	private Usuario usuario;
	
	public Site() {}
	public Site(String email, String senha, String endereco, String nome, String telefone){
		this.email = email;
		this.senha = senha;
		this.URL = endereco;
		this.nome = nome;
		this.telefone = telefone;
	}
	/*	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	*/
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getURL() {
		return URL;
	}
	
	public void setURL(String URL) {
		this.URL = URL;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario getUsuario() {
    	return this.usuario;
    }
    public void setUsuario(Usuario user) {
    	this.usuario = user;
    }
    public Set<Promocao> getPromocoes(){
    	return this.promocoes;
    }
    public void setPromocoes(Set<Promocao> promocoes) {
    	this.promocoes = promocoes;
    }
    
    // PARA DEBUG
    /*
    @Override
	public String toString() {
		return "Site = [Nome = " + nome + ", email=" + email +", senha="+senha+ "]";
	}
	*/
}