package com.will.greendonation.Activies;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.will.greendonation.Adapters.BaseAdapterDoacaoEntregue;
import com.will.greendonation.Adapters.BaseAdapterDoacaoEspera;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Doacao;
import com.will.greendonation.Classes.Empresa;
import com.will.greendonation.Classes.Notificacao;
import com.will.greendonation.Classes.Pessoa;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

import java.util.ArrayList;

public class Entregues extends Fragment {

    static Context context;
    static ListView list_doacao_entrgue;
    static RelativeLayout nothing_entrgue;
    static ArrayList myDoacoes = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_entregues_doador, container, false);
        context = rootView.getContext();

        nothing_entrgue = (RelativeLayout)rootView.findViewById(R.id.nothing_doacao_entregue);
        list_doacao_entrgue = (ListView)rootView.findViewById(R.id.list_doacao_entregue);

        return  rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList(context);
    }

    public static void updateList(Context context) {
        myDoacoes.clear();

        if (Sistema.usuario instanceof Empresa) {
            Empresa empresa = (Empresa) Sistema.usuario;

            for (Notificacao notificacao : empresa.getRealizadas()){
                myDoacoes.add(notificacao);
            }

            list_doacao_entrgue.setAdapter(new BaseAdapterDoacaoEntregue(context, myDoacoes));

            if (empresa.getRealizadas().size() == 0) {
                nothing_entrgue.setVisibility(View.VISIBLE);
                list_doacao_entrgue.setVisibility(View.GONE);
            } else {
                nothing_entrgue.setVisibility(View.GONE);
                list_doacao_entrgue.setVisibility(View.VISIBLE);
            }
        } else if (Sistema.usuario instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) Sistema.usuario;

            for (Notificacao notificacao : pessoa.getRealizadas()){
                myDoacoes.add(notificacao);
            }

            list_doacao_entrgue.setAdapter(new BaseAdapterDoacaoEntregue(context, myDoacoes));

            if (pessoa.getRealizadas().size() == 0) {
                nothing_entrgue.setVisibility(View.VISIBLE);
                list_doacao_entrgue.setVisibility(View.GONE);
            } else {
                nothing_entrgue.setVisibility(View.GONE);
                list_doacao_entrgue.setVisibility(View.VISIBLE);
            }
        } else if (Sistema.usuario instanceof Coletador) {
            Coletador coletador = (Coletador) Sistema.usuario;

            for (Notificacao notificacao : coletador.showDoacoesAceitas()){
                myDoacoes.add(notificacao);
            }

            list_doacao_entrgue.setAdapter(new BaseAdapterDoacaoEntregue(context, myDoacoes));

            if (coletador.showDoacoesAceitas().size() == 0) {
                nothing_entrgue.setVisibility(View.VISIBLE);
                list_doacao_entrgue.setVisibility(View.GONE);
            } else {
                nothing_entrgue.setVisibility(View.GONE);
                list_doacao_entrgue.setVisibility(View.VISIBLE);
            }
        }
    }

}

