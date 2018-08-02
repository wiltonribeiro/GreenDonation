package com.will.greendonation.Classes;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pessoa extends Usuario implements Doador{
	private String cpf;
	private String endereco;
	private List<Doacao> doacoes_espera;
	private List<Notificacao> doacoes_andamento;
	private List<Notificacao> doacoes_realizadas;

	public Pessoa(String nome, String senha, String endereco, String cpf, String contato, Drawable perfil){
		super(nome, senha, endereco,contato,perfil);
		this.cpf = cpf;
		this.endereco = endereco;
		this.doacoes_espera = new ArrayList<>();
		this.doacoes_andamento = new ArrayList<>();
		this.doacoes_realizadas = new ArrayList<>();
	}

	@Override
	public void doarMaterial(Double peso, Material tipoDoacao, Regiao regiao) {
		Doacao doacao = new Doacao(peso, this,tipoDoacao,regiao);
		regiao.addDoacao(doacao);
		getDoacoesEmEspera().add(doacao);
	}

	@Override
	public void cancelarDoacao(Doacao doacao) {
		Pessoa pessoa = (Pessoa) Sistema.usuario;
		doacao.getRegiao().removerDoacao(doacao);
		pessoa.getDoacoesEmEspera().remove(doacao);
	}

	public String getCpf(){
		return this.cpf;
	}
	
	public String toString() {
		return  " " + super.getNome();
	}
	
	public List<Doacao> getDoacoesEmEspera(){
		return this.doacoes_espera;
	}

	@Override
	public void addEmAndamento(Notificacao notificacao) {
		this.doacoes_andamento.add(notificacao);
	}
	
	@Override
	public void addRealizadas(Notificacao notificacao) {
		this.doacoes_realizadas.add(notificacao);
	}
	
	@Override
	public List<Notificacao> getEmAndamento() {
		return this.doacoes_andamento;
	}

	@Override
	public List<Notificacao> getRealizadas() {
		return this.doacoes_realizadas;
	}

	@Override
	public int numDoacoes() {
		return getRealizadas().size();
	}

	@Override
	public int numColetadores() {
		HashMap<Coletador,String> numColetadores = new HashMap<>();
		for(Notificacao notificacao: getRealizadas()){
			Coletador coletador = notificacao.getSolicitacao().getColetador();
			if(!numColetadores.containsKey(coletador))
				numColetadores.put(coletador, coletador.getNome());
		}

		return numColetadores.size();
	}

	@Override
	public Material estMaterial() {
		HashMap<Material,Double> estMaterial = new HashMap<>();

		for(Notificacao notificacao: getRealizadas()){
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

	@Override
	public double qntdMaterial() {
		double total = 0;
		for(Notificacao notificacao: getRealizadas()){
			total += notificacao.getDoacao().getPeso();
		}

		return  total;
	}

	public String getEndereco(){
		return this.endereco;
	}
}
