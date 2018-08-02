package com.will.greendonation.Activies.Doador;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.will.greendonation.Adapters.BaseAdapterDoacaoAndamento;
import com.will.greendonation.Adapters.BaseAdapterDoacaoEspera;
import com.will.greendonation.Classes.Doacao;
import com.will.greendonation.Classes.Empresa;
import com.will.greendonation.Classes.Notificacao;
import com.will.greendonation.Classes.Pessoa;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

import java.util.ArrayList;

public class DoacoesEmAndamento extends Fragment {

    static Context context;
    static ListView list_doacao_andamento;
    static RelativeLayout nothing_doacao;
    static ArrayList myDoacoesAndamento = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_doacoes_em_andamento, container, false);
        context = rootView.getContext();

        nothing_doacao = (RelativeLayout)rootView.findViewById(R.id.nothing_doacao_andamento);
        list_doacao_andamento = (ListView)rootView.findViewById(R.id.list_doacao_andamento);

        updateList(context);

        return  rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList(context);
    }

    public static void updateList(Context context) {
        myDoacoesAndamento.clear();

        if (Sistema.usuario instanceof Pessoa) {
            Pessoa pessoa = (Pessoa)Sistema.usuario;

            for (Notificacao notificacao : pessoa.getEmAndamento())
                myDoacoesAndamento.add(notificacao);

            list_doacao_andamento.setAdapter(new BaseAdapterDoacaoAndamento(context, myDoacoesAndamento));

            if (pessoa.getEmAndamento().size() == 0) {
                nothing_doacao.setVisibility(View.VISIBLE);
                list_doacao_andamento.setVisibility(View.GONE);
            } else {
                nothing_doacao.setVisibility(View.GONE);
                list_doacao_andamento.setVisibility(View.VISIBLE);
            }
        } else if (Sistema.usuario instanceof Empresa) {
            Empresa empresa = (Empresa) Sistema.usuario;

            for (Notificacao notificacao : empresa.getEmAndamento())
                myDoacoesAndamento.add(notificacao);

            list_doacao_andamento.setAdapter(new BaseAdapterDoacaoAndamento(context, myDoacoesAndamento));

            if (empresa.getEmAndamento().size() == 0) {
                nothing_doacao.setVisibility(View.VISIBLE);
                list_doacao_andamento.setVisibility(View.GONE);
            } else {
                nothing_doacao.setVisibility(View.GONE);
                list_doacao_andamento.setVisibility(View.VISIBLE);
            }
        }
    }

}
