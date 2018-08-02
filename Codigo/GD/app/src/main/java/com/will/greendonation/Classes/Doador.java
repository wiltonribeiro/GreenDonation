package com.will.greendonation.Classes;

import java.util.List;

public interface Doador {
	
	public void doarMaterial(Double peso, Material tipoDoacao, Regiao regiao);

	public void cancelarDoacao(Doacao doacao);
	
	public String toString();
	
	public String getNome();

	public String getEndereco();
	
	public void addEmAndamento(Notificacao notificacao);
	
	public void addRealizadas(Notificacao notificacao);
	
	public List<Notificacao> getEmAndamento();
	
	public List<Notificacao> getRealizadas();

	public int numDoacoes();

	public int numColetadores();

	public Material estMaterial();

	public  double qntdMaterial();

}
