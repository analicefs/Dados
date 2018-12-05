package br.ufc.quixada.npi.dadosabertos.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sugestoes", schema="sugestao")
public class Sugestao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	private String email;
	
	@Enumerated(EnumType.STRING)
    private TipoRelato tipoRelato;
	
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Timestamp tempo;
	
	public Sugestao(){
		
	}
	
	public Sugestao(String nome, String email, TipoRelato tipoRelato, String observacao, 
			Status status, Timestamp tempo) {
		this.nome = nome;
		this.email = email;
		this.tipoRelato = tipoRelato;
		this.observacao = observacao;
		this.status = status;
		this.tempo = tempo;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public TipoRelato getTipoRelato() {
		return tipoRelato;
	}

	public String getObservacao() {
		return observacao;
	}

	public Status getStatus() {
		return status;
	}

	public Timestamp getTempo() {
		return tempo;
	}
	
	public enum TipoRelato{
		erro,
		elogio,
		sugestao,
		outros
	}
	
	public enum Status{
		novo,
		corrigindo,
		ignorado,
		finalizado,
		outros
	}
}
