package com.will.greendonation.Activies.Doador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.will.greendonation.Activies.Coletador.Solicitacoes;
import com.will.greendonation.Adapters.BaseAdapterDoacaoEspera;
import com.will.greendonation.Adapters.BaseAdapterSolicitacao;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Doacao;
import com.will.greendonation.Classes.Empresa;
import com.will.greendonation.Classes.Pessoa;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.Classes.Solicitacao;
import com.will.greendonation.R;

import java.util.ArrayList;

public class DoacoesEmEspera extends Fragment {

    static Context context;
    Button btn_doacao;
    static ListView list_doacao_espera;
    static RelativeLayout nothing_doacao;
    static ArrayList myDoacoes = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_doacoes_em_espera, container, false);
        context = rootView.getContext();

        btn_doacao = (Button)rootView.findViewById(R.id.btn_doacao);

        btn_doacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, LancarDoacao.class));
            }
        });

        nothing_doacao = (RelativeLayout)rootView.findViewById(R.id.nothing_doacao_espera);
        list_doacao_espera = (ListView)rootView.findViewById(R.id.list_doacao_espera);

        return  rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList(context);
    }

    public static void updateList(Context context) {
        myDoacoes.clear();

        if (Sistema.usuario instanceof Pessoa) {
            Pessoa pessoa = (Pessoa)Sistema.usuario;

            for (Doacao doacao : pessoa.getDoacoesEmEspera()){
                if(!doacao.isStatus())
                    myDoacoes.add(doacao);
            }
            list_doacao_espera.setAdapter(new BaseAdapterDoacaoEspera(context, myDoacoes));

            if (pessoa.getDoacoesEmEspera().size() == 0) {
                nothing_doacao.setVisibility(View.VISIBLE);
                list_doacao_espera.setVisibility(View.GONE);
            } else {
                nothing_doacao.setVisibility(View.GONE);
                list_doacao_espera.setVisibility(View.VISIBLE);
            }
        } else if (Sistema.usuario instanceof Empresa) {
            Empresa empresa = (Empresa) Sistema.usuario;

            for (Doacao doacao : empresa.getDoacoesEspera()){
                if(!doacao.isStatus())
                    myDoacoes.add(doacao);
            }


            list_doacao_espera.setAdapter(new BaseAdapterDoacaoEspera(context, myDoacoes));

            if (empresa.getDoacoesEspera().size() == 0) {
                nothing_doacao.setVisibility(View.VISIBLE);
                list_doacao_espera.setVisibility(View.GONE);
            } else {
                nothing_doacao.setVisibility(View.GONE);
                list_doacao_espera.setVisibility(View.VISIBLE);
            }
        }
    }

}
