package com.will.greendonation.Classes;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
	private String nome;
	private String senha;
	private static int id = 0;
	private int idIndividual;
	private String endereco;
	private String contato;
	private Drawable perfil;
	private List<Notificacao> notificacoes;
	
	public Usuario(String nome, String senha, String endereco, String contato, Drawable perfil) {
		this.nome = nome;
		this.senha = senha;
		this.endereco = endereco;
		this.contato = contato;
		this.perfil = perfil;
		notificacoes = new ArrayList<>();
		idIndividual = id;
		id++;
	}
	
	public boolean fazerLogin(String senha) {
		if(senha.equals(this.senha))
			Sistema.usuario = this;
		else return false;
		
		return true;
	}

    public String getSenha() {
        return senha;
    }

    public boolean fazerLogout() {
		Sistema.usuario = null;
		return true;
	}
	
	public String getNome() {
		return this.nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getContato() {
		return contato;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Drawable getPerfil() {
		return perfil;
	}

	public void setPerfil(Drawable perfil) {
		this.perfil = perfil;
	}
}
