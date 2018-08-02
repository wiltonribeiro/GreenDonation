package com.will.greendonation.Activies.Coletador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.will.greendonation.Activies.MainActivity;
import com.will.greendonation.Adapters.BaseAdapterSolicitacao;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.Classes.Solicitacao;
import com.will.greendonation.R;

import java.util.ArrayList;

public class Solicitacoes extends AppCompatActivity {

    Button btn_solicitacao;
    static ListView list_solicitacao;
    static RelativeLayout nothing_solicitacao;
    static ArrayList mySolicitacoes = new ArrayList();
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacoes);
        context = getApplicationContext();

        btn_solicitacao = (Button)findViewById(R.id.btn_solicitacao);

        btn_solicitacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Solicitacoes.this, LancarSolicitacao.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        nothing_solicitacao = (RelativeLayout)findViewById(R.id.nothing_solicitacao);
        list_solicitacao = (ListView) findViewById(R.id.list_solicitacao);

        updateList(Solicitacoes.this);

    }

    public static void updateList(Context context){
        mySolicitacoes.clear();
        Coletador coletador = (Coletador)Sistema.usuario;
        for(Solicitacao solicitacao: coletador.showSolicitacoes()){
            if(!solicitacao.isStatus())
                mySolicitacoes.add(solicitacao);
        }


        list_solicitacao.setAdapter(new BaseAdapterSolicitacao(context, mySolicitacoes));

        if(coletador.showSolicitacoes().size()==0){
            nothing_solicitacao.setVisibility(View.VISIBLE);
            list_solicitacao.setVisibility(View.GONE);
        } else{
            nothing_solicitacao.setVisibility(View.GONE);
            list_solicitacao.setVisibility(View.VISIBLE);
        }
    }

    public static Context getAppContext() {
        return Solicitacoes.context;
    }
}
