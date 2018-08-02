package com.will.greendonation.Classes;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Coletador extends Usuario {
	private List<Notificacao> notificacoes;
	private List<Solicitacao> solicitacoes;
	private List<Notificacao> doacoesAceitas;
	private String cpf;

	public Coletador(String nome, String senha, String endereco, String cpf, String contato, Drawable perfil){
		super(nome, senha, endereco, contato, perfil);
		notificacoes = new ArrayList<Notificacao>();
		doacoesAceitas = new ArrayList<Notificacao>();
		solicitacoes = new ArrayList<Solicitacao>();
		this.cpf = cpf;
	}
	
	public  boolean lancarSolicitacao(Material material, Regiao regiao){
		Solicitacao solicitacao = new Solicitacao(this, material, regiao);
		regiao.addSolicitacao(solicitacao);
		solicitacoes.add(solicitacao);
		return true;
	}
	
	public List<Notificacao> listarNotificacao(){
		return notificacoes;
	}
	
	public boolean aceitarDoacao(Notificacao notificacao) {
		doacoesAceitas.add(notificacao);
		notificacoes.remove(notificacao);
		
		notificacao.getDoacao().getDoador().getEmAndamento().remove(notificacao);
		notificacao.getDoacao().getDoador().getRealizadas().add(notificacao);
		
		return notificacao.getDoacao().removerDoacao() && notificacao.getSolicitacao().removerSolicitacao() 
				&& notificacao.getDoacao().getRegiao().addDoacaoRealizada(notificacao.getDoacao());
	}
	
	public boolean rejeitarDoacao(Notificacao notificacao) {
		notificacao.getDoacao().getDoador().getEmAndamento().remove(notificacao);
		notificacoes.remove(notificacao);
		notificacao.getSolicitacao().removerSolicitacao();
		notificacao.reacao(false);
		return true;
	}
	
	public boolean removerSolicitacao(Solicitacao solicitacao) {
		return this.solicitacoes.remove(solicitacao) && solicitacao.getRegiao().getSolicitacoes().remove(solicitacao);
	}
	
	public boolean addNotificaco(Notificacao notificacao) {
		notificacao.getDoacao().setEspera(true);
		notificacao.getSolicitacao().setEspera(true);
		notificacoes.add(notificacao);
		return true;
	}
	
	public String toString() {
		return ""+this.getNome();
	}
	
	public List<Notificacao> showDoacoesAceitas(){
		return doacoesAceitas;
	}

	public String getCpf() {
		return cpf;
	}
	
	public List<Solicitacao> showSolicitacoes(){
		return this.solicitacoes;
	}

	public int numDoacoes(){
		return showDoacoesAceitas().size();
	}

	public int numDoadores() {
		HashMap<Doador,String> numDoadores = new HashMap<>();
		for(Notificacao notificacao: showDoacoesAceitas()){
			Doador doador = notificacao.getDoacao().getDoador();
			if(!numDoadores.containsKey(doador))
				numDoadores.put(doador, doador.getNome());
		}

		return numDoadores.size();
	}

	public Material estMaterial() {
		HashMap<Material,Double> estMaterial = new HashMap<>();

		for(Notificacao notificacao: showDoacoesAceitas()){
			Material material = notificacao.getSolicitacao().getMaterial();
			if(estMaterial.containsKey(material))
				estMaterial.put(material, estMaterial.get(material)+notificacao.getDoacao().getPeso());
			else
				estMaterial.put(material, notificacao.getDoacao().getPeso());
		}

		Material retorno = null;
		double maior = 0;

		for(Material material: estMaterial.keySet()){
			if(estMaterial.get(material)>maior){
				maior = estMaterial.get(material);
				retorno = material;
			}
		}

		return retorno;
	}

	public double qntdMaterial() {
		double total = 0;
		for(Notificacao notificacao: showDoacoesAceitas()){
			total += notificacao.getDoacao().getPeso();
		}
		return  total;
	}
	
	

}
