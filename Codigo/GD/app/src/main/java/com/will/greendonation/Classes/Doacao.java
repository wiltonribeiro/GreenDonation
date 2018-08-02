package com.will.greendonation.Classes;

public class Doacao {
	private double peso;
	private Doador doador;
	private Material tipoDoacao;
	private Regiao regiao;
	private static int id = 1;
	private int idIndividual;
	private boolean status;
	
	public Doacao(Double peso, Doador doador, Material tipoDoacao, Regiao regiao){
		this.peso = peso;
		this.doador = doador;
		this.tipoDoacao = tipoDoacao;
		this.regiao = regiao;
		this.status = false;
		idIndividual = id;
		id++;
	}
	
	public boolean removerDoacao() {
		this.regiao.removerDoacao(this);
		return true;
	}
	
	public void setEspera(boolean status) {
		this.status = status;
	}

	public double getPeso() {
		return peso;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public Doador getDoador() {
		return doador;
	}

	public Material getTipoDoacao() {
		return tipoDoacao;
	}

	public boolean isStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "peso=" + peso + ", doador=" + doador + ", tipoDoacao=" + tipoDoacao + ", regiao=" + regiao
				+ ", status=" + status + "\n";
	}	
	
}
