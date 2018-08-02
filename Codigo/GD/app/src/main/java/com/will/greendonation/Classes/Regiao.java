package com.will.greendonation.Classes;

import java.util.ArrayList;
import java.util.List;

public class Regiao {
	private String nome;
	private List<Doacao> doacoes;
	private List<Doacao> doacoesRealizadas;
	private List<Solicitacao> solicitacoes;
	
	public Regiao(String nome) {
		this.nome = nome;
		doacoes = new ArrayList<Doacao>();
		solicitacoes = new ArrayList<Solicitacao>();
		doacoesRealizadas = new ArrayList<>();
	}
	
	public List<Doacao> listarTodasDoacoes(){
		return null;
	}
	
	public List<Doacao> listarTodasDoacoes(Material material){
		return null;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public boolean addDoacao(Doacao doacao) {
		doacoes.add(doacao);
		return true;
	}
	
	public boolean removerDoacao(Doacao doacao) {
		return doacoes.remove(doacao);
	}
	
	public boolean addSolicitacao(Solicitacao solicitacao) {
		solicitacoes.add(solicitacao);
		return true;
	}
	
	public boolean removerSolicitacao(Solicitacao solicitacao) {
		return solicitacoes.remove(solicitacao);
	}

	public  List<Doacao> getDoacoes() {
		return doacoes;
	}

	public  List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}
	
	public String toString() {
		return this.nome;
	}

	public boolean addDoacaoRealizada(Doacao doacao) {
		return this.doacoesRealizadas.add(doacao);
	}
	
	public List<Doacao> getDoacoesRealizadas(){
		return this.doacoesRealizadas;
	}
	
	
}
