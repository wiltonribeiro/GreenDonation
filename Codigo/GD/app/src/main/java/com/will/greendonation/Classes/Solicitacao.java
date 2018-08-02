package com.will.greendonation.Classes;

public class Solicitacao {

	private static int id = 0;
	private int idIndividual;
	private Coletador coletador;
	private Material material;
	private Regiao regiao;
	private boolean status;
	
	public Solicitacao(Coletador coletador, Material material, Regiao regiao){
		this.coletador = coletador;
		this.material = material;
		this.regiao = regiao;
		this.status = false;
	}
	
	public boolean removerSolicitacao() {
		this.regiao.removerSolicitacao(this);
		this.coletador.removerSolicitacao(this);
		return true;
	}
	
	public void setEspera(boolean status) {
		this.status = status;
	}

	public Material getMaterial() {
		return material;
	}

	public boolean isStatus() {
		return status;
	}

	public Coletador getColetador() {
		return coletador;
	}

	@Override
	public String toString() {
		return "Coletador = " + coletador + ", material=" + material + ", regiao=" + regiao + ", status="
				+ status;
	}

	public Regiao getRegiao() {
		return regiao;
	}
	
	
	
	
}
