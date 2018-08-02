package com.will.greendonation.Classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.will.greendonation.Activies.Coletador.ColetadorActivity;
import com.will.greendonation.Activies.Doador.DoadorActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sistema {
	private List<Regiao> regioes;
	private List<Material> materiais;
	private HashMap<String, Usuario> usuarios;
	public static Usuario usuario = null;
	
	public Sistema(){
		regioes = new ArrayList<Regiao>();
		materiais = new ArrayList<Material>();
		usuarios = new HashMap<String, Usuario>();
	}
	
	public boolean fazerLogin(String chave, String senha) throws Exception{
		if(chave.isEmpty() || senha.isEmpty())
			throw new Exception("Usuário ou senha em branco");

		if(usuarios.containsKey(chave)) {
			if (usuarios.get(chave).fazerLogin(senha))
				return true;
			else
				throw new Exception("Usuário ou senha incorretos");//System.out.println("Usuario ou senha incorretos");

		} else throw new Exception("Usuário não existe");
	}

	public static void useSystem(Usuario usuario_atual, Context context){
		switch (usuario_atual.getClass().getSimpleName()) {
			case "Pessoa":
				context.startActivity(new Intent(context, DoadorActivity.class));
				break;

			case "Coletador":
				context.startActivity(new Intent(context, ColetadorActivity.class));
				break;

			case "Empresa":
				context.startActivity(new Intent(context, DoadorActivity.class));
				break;

			default:
				Toast.makeText(context, "Erro inesperado", Toast.LENGTH_SHORT).show();
				break;
		}
	}
	
	public void fazerLogout() {
		usuario.fazerLogout();
	}
	
	public void distribuirDoacoes(){
		for(Regiao regiao: regioes)
			for(Solicitacao solicitacao: regiao.getSolicitacoes())
				if(!solicitacao.isStatus()) {
					for(Doacao doacao: regiao.getDoacoes()) {
						if(!doacao.isStatus() 
								&& doacao.getTipoDoacao().getNome().equals(solicitacao.getMaterial().getNome())) {
									notificar(doacao, solicitacao);
									break;
						}
					}
				} else continue;
	}
	
	public void notificar(Doacao doacao, Solicitacao solicitacao) {
		Notificacao notificacao = new Notificacao(doacao, solicitacao);
		solicitacao.getColetador().addNotificaco(notificacao);
		doacao.getDoador().addEmAndamento(notificacao);
	}
	
	public double calcularPesoTotal() {
		double peso_total = 0;
		for(Regiao regiao: regioes) 
			for(Doacao doacao: regiao.getDoacoesRealizadas())
				peso_total += doacao.getPeso();
		
		return peso_total;
	}
	
	public double calcularPeso(Regiao regiao) {
		double peso_total = 0;
		
		for(Doacao doacao: regiao.getDoacoesRealizadas())
			peso_total += doacao.getPeso();
	
		return peso_total;
	}
	
	public double calcularPesoMaterial() {
		
//		Map<Material, Double> pesoMaterial = new HashMap<>();
//		
//		for(Material material: materiais) {
//			pesoMaterial.put(material, 0.0);
//		}
//			
//		for(Regiao regiao: regioes) {
//			for(Doacao doacao: regiao.getDoacoesRealizadas())
//				 
//				pesoMaterial.get(doacao.getTipoDoacao())
//		}
		
		return 0;
	}
	
	public boolean addRegiao(String nome) {
		for(Regiao regiao: regioes) {
			if(regiao.getNome().equals(nome)) {
				return false;
			}
		}
		regioes.add(new Regiao(nome));
		System.out.println("Regiao adicionado");
		return true;
	}
	
	public boolean addMaterial(String nome, String descricao) {
		for(Material material: materiais) {
			if(material.getNome().equals(nome)) {
				return false;
			}
		}
		materiais.add(new Material(nome, descricao));
		System.out.println("Material adicionado");
		return true;
	}
	
	public boolean verificarUsuario(String key) {
		if(usuarios.containsKey(key))
			return false;
		else
			return true;
	}

	public void addUsuario(String nome, String senha, String endereco, String chave, String contato, Drawable perfil, int type) throws Exception{
        if(nome.isEmpty() || senha.isEmpty() || endereco.isEmpty() || chave.isEmpty()){
            throw new Exception("Preencha todos os campos");
        } else if(senha.length()<=5){
            throw new Exception("A senha deve conter no mínimo 6 caracteres");
        } else if(verificarUsuario(chave)){
            switch (type) {
				case 0:
					Pessoa pessoa = new Pessoa(nome, senha, endereco, chave,contato, perfil);
					usuarios.put(pessoa.getCpf(), pessoa);
					if(fazerLogin(pessoa.getCpf(),pessoa.getSenha())){
						//vai embora
					}
					break;

				case 1:
					Coletador coletador = new Coletador(nome, senha, endereco, chave, contato, perfil);
					usuarios.put(coletador.getCpf(), coletador);
					break;

				case 2:
					Empresa empresa = new Empresa(nome, senha, endereco, chave, contato, perfil);
					usuarios.put(empresa.getCnpj(), empresa);
					break;
				}
            }
		else
			throw new Exception("Usuário já existente com esse email");
    }
		
	
	public List<Usuario> showUsuarios(){
		List<Usuario> usuarios = new ArrayList<Usuario>(this.usuarios.values());
		return usuarios;
	}
	
	public List<Regiao> showRegioes(){
		return regioes;
	}
	
	public List<Material> showMateriais(){
		return materiais;
	}
	
	public List<Doacao> showDoacoes(){
		List<Doacao> doacoes = new ArrayList<Doacao>();
		for(Regiao regiao : regioes) {
			for(Doacao doacao : regiao.getDoacoes()) {
				doacoes.add(doacao);
			}
		}
		return doacoes;	
	}
	
	public List<Solicitacao> showSolicitacao(){
		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		for(Regiao regiao : regioes) {
			for(Solicitacao solicitacao: regiao.getSolicitacoes()) {
				solicitacoes.add(solicitacao);
			}
		}
		return solicitacoes;	
	}
}
