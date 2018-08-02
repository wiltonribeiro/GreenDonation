package com.will.greendonation.Classes;

public class Notificacao {
	private Doacao doacao;
	private Solicitacao solicitacao;
	
	public Notificacao(Doacao doacao, Solicitacao solicitacao) {
		this.doacao = doacao;
		this.solicitacao = solicitacao;
	}
	
	public boolean reacao(boolean resposta) {
		this.solicitacao.setEspera(resposta);
		this.doacao.setEspera(resposta);
		return true;
	}

	public Doacao getDoacao() {
		return doacao;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}
	
	public String toString() {
		return doacao.toString() + solicitacao.toString();
	}
	
	

}
