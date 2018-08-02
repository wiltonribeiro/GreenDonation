package com.will.greendonation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.will.greendonation.Activies.Coletador.DoacoesColetador;
import com.will.greendonation.Activies.Doador.DoacoesEmEspera;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Notificacao;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will Neto on 16/06/2017.
 */

public class BaseAdapterNotificacaoColetador extends BaseAdapter {
    List<Notificacao> myList = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    MyViewHolder mViewHolder;

    public BaseAdapterNotificacaoColetador(Context context, List<Notificacao> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }


    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Notificacao getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_notificacao, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final Notificacao currentListData = getItem(position);

        mViewHolder.tvMaterial.setText(("Material: "+currentListData.getDoacao().getTipoDoacao().getNome()));
        mViewHolder.tvPeso.setText(("Peso: "+currentListData.getDoacao().getPeso()+" kg"));
        mViewHolder.tvDoador.setText(("Nome: "+currentListData.getDoacao().getDoador().getNome()));
        mViewHolder.tvEndereco.setText(("Endereço: "+currentListData.getDoacao().getDoador().getEndereco()));

        mViewHolder.btn_aceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Coletador coletador = (Coletador) Sistema.usuario;
                coletador.aceitarDoacao(currentListData);
                DoacoesColetador.updateList(context);
            }
        });

        mViewHolder.btn_rejeitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Coletador coletador = (Coletador) Sistema.usuario;
                coletador.rejeitarDoacao(currentListData);
                DoacoesColetador.updateList(context);
            }
        });

        return convertView;
    }

    private class MyViewHolder {
        TextView tvMaterial, tvPeso, tvDoador, tvEndereco;
        RelativeLayout btn_aceitar, btn_rejeitar;

        public MyViewHolder(View item) {
            tvMaterial = (TextView) item.findViewById(R.id.tvMaterial);
            tvPeso = (TextView) item.findViewById(R.id.tvPeso);
            tvDoador = (TextView) item.findViewById(R.id.tvDoador);
            tvEndereco = (TextView) item.findViewById(R.id.tvEndereco);
            btn_aceitar = (RelativeLayout) item.findViewById(R.id.btn_aceitar);
            btn_rejeitar = (RelativeLayout) item.findViewById(R.id.btn_rejeitar);
        }
    }
}
