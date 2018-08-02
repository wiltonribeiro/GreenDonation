package com.will.greendonation.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.will.greendonation.Activies.Doador.DoacoesEmEspera;
import com.will.greendonation.Classes.Doacao;
import com.will.greendonation.Classes.Empresa;
import com.will.greendonation.Classes.Notificacao;
import com.will.greendonation.Classes.Pessoa;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will Neto on 16/06/2017.
 */

public class BaseAdapterDoacaoAndamento extends BaseAdapter {
    List<Notificacao> myList = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    MyViewHolder mViewHolder;

    public BaseAdapterDoacaoAndamento(Context context, List<Notificacao> myList) {
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
            convertView = inflater.inflate(R.layout.layout_doacao_andamento, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final Notificacao currentListData = getItem(position);

        mViewHolder.tvMaterial.setText(("Material: "+currentListData.getDoacao().getTipoDoacao().getNome()));
        mViewHolder.tvRegiao.setText(("Região: "+currentListData.getDoacao().getRegiao().getNome()));
        mViewHolder.tvPeso.setText(("Peso: "+currentListData.getDoacao().getPeso()+" kg"));
        mViewHolder.tvNome.setText(("Nome: "+currentListData.getSolicitacao().getColetador().getNome()));
        mViewHolder.tvEmail.setText(("Email: "+currentListData.getSolicitacao().getColetador().getCpf()));

        return convertView;
    }

    private class MyViewHolder {
        TextView tvRegiao, tvMaterial, tvPeso, tvNome, tvEmail;

        public MyViewHolder(View item) {
            tvMaterial = (TextView) item.findViewById(R.id.tvMaterial);
            tvRegiao = (TextView) item.findViewById(R.id.tvRegiao);
            tvPeso = (TextView) item.findViewById(R.id.tvPeso);
            tvNome = (TextView) item.findViewById(R.id.tvNome);
            tvEmail = (TextView) item.findViewById(R.id.tvEmail);
        }
    }
}
