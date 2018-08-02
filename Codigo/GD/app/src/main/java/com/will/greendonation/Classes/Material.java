package com.will.greendonation.Classes;

public class Material {
	private String nome;
	private String descricao;

	public Material(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String toString() {
		return this.nome;
	}
}
