package br.ufscar.dc.dsw.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import br.ufscar.dc.dsw.validation.UniqueCNPJ;
import br.ufscar.dc.dsw.validation.UniqueEmail;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@SuppressWarnings("serial")
@Entity
@Table(name="Hotel")
public class Hotel extends AbstractEntity<Long>{
	
	
	@UniqueCNPJ (message = "{Unique.hotel.CNPJ}")
	@NotBlank
	@Size(min = 18, max = 18, message = "{Size.hotel.CNPJ}")
	@Column(nullable = false, unique = true, length = 60)
	private String CNPJ;
	
	@UniqueEmail(message = "{Unique.email}")
	@NotEmpty
	@Size(max = 60)
	@Email
	@Column(nullable = false, unique = true, length = 50)
    private String email;
	
	@NotEmpty
	@Column(nullable = false, unique = false, length = 100)
    private String senha;
	
	@NotEmpty
	@Column(nullable = false, unique = false, length = 50)
    private String nome;
	
	@NotEmpty
	@Column(nullable = false, unique = false, length = 50)
    private String cidade;
    
	/*
	@OneToMany(targetEntity = Promocao.class, mappedBy = "hotel", fetch = FetchType.EAGER)
	private List<Promocao> promocoes;
	*/
	
	@OneToOne
	private Usuario usuario;
	public Hotel() {}
    public Hotel(String email, String senha, String CNPJ, String nome, String cidade){
        this.email = email;
        this.senha = senha;
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.cidade = cidade;
    }
    
    /*
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	*/
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public Usuario getUsuario() {
    	return this.usuario;
    }
    public void setUsuario(Usuario user) {
    	this.usuario = user;
    }
    
    /*
    public List<Promocao> getPromocoes(){
    	return this.promocoes;
    }
    public void setPromocoes(List<Promocao> promocoes) {
    	this.promocoes = promocoes;
    }
    */
    
    // PARA DEBUG
    /*
    @Override
	public String toString() {
		return "Hotel = [Nome = " + nome + ", email=" + email +", senha="+senha+ "]";
	}
    */
    
}