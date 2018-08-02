package com.will.greendonation.Classes;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Empresa extends Usuario implements Doador {
	private String cnpj;
	private String endereco;
	private List<Doacao> doacoes_espera;
	private List<Notificacao> doacoes_andamento;
	private List<Notificacao> doacoes_realizadas;
	
	public Empresa(String nome, String senha, String endereco, String cnpj, String contato, Drawable perfil) {
		super(nome, senha, endereco, contato, perfil);
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.doacoes_espera = new ArrayList<>();
		this.doacoes_andamento = new ArrayList<>();
		this.doacoes_realizadas = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public void dividirDoacao(Doacao doacao) {
		
		for(int i = 1; i<=doacao.getPeso()/2; i++) 
			this.doarMaterial(2.0, doacao.getTipoDoacao(), doacao.getRegiao());
		
		if(doacao.getPeso()%2 != 0) 
			this.doarMaterial(1.0, doacao.getTipoDoacao(), doacao.getRegiao());
	}
	
	@Override
	public void doarMaterial(Double peso, Material tipoDoacao, Regiao regiao) {
		Doacao doacao = new Doacao(peso, this,tipoDoacao,regiao);
		if(doacao.getPeso() >= 10)
			dividirDoacao(doacao);
		else {
			regiao.addDoacao(doacao);
			this.doacoes_espera.add(doacao);
		}
	}

	@Override
	public void cancelarDoacao(Doacao doacao) {
		Empresa empresa = (Empresa) Sistema.usuario;
		doacao.getRegiao().removerDoacao(doacao);
		empresa.getDoacoesEspera().remove(doacao);
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public List<Doacao> getDoacoesEspera() {
		return doacoes_espera;
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
