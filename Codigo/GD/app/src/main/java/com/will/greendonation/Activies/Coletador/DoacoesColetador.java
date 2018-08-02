package com.will.greendonation.Activies.Coletador;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.will.greendonation.Adapters.BaseAdapterDoacaoEspera;
import com.will.greendonation.Adapters.BaseAdapterNotificacaoColetador;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Doacao;
import com.will.greendonation.Classes.Empresa;
import com.will.greendonation.Classes.Notificacao;
import com.will.greendonation.Classes.Pessoa;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

import java.util.ArrayList;

public class DoacoesColetador extends AppCompatActivity {

    static Context context;
    static ListView list_doacao_coletador;
    static RelativeLayout nothing_doacao_coletador;
    static ArrayList myDoacoes = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doacoes_coletador);

        list_doacao_coletador = (ListView)findViewById(R.id.list_notificacoes);
        nothing_doacao_coletador = (RelativeLayout)findViewById(R.id.nothing_doacao_coletador);
        context = DoacoesColetador.this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList(context);
    }

    public static void updateList(Context context) {
            myDoacoes.clear();
            Coletador coletador = (Coletador)Sistema.usuario;

            for (Notificacao notificacao : coletador.listarNotificacao()){
                myDoacoes.add(notificacao);
            }

            list_doacao_coletador.setAdapter(new BaseAdapterNotificacaoColetador(context, myDoacoes));

            if (coletador.listarNotificacao().size() == 0) {
                nothing_doacao_coletador.setVisibility(View.VISIBLE);
                list_doacao_coletador.setVisibility(View.GONE);
            } else {
                nothing_doacao_coletador.setVisibility(View.GONE);
                list_doacao_coletador.setVisibility(View.VISIBLE);
            }
        }
    }

