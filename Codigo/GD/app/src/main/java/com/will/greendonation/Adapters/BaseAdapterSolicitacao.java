package com.will.greendonation.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.will.greendonation.Activies.Coletador.Solicitacoes;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.Classes.Solicitacao;
import com.will.greendonation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will Neto on 16/06/2017.
 */

public class BaseAdapterSolicitacao extends BaseAdapter {
    List<Solicitacao> myList = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    MyViewHolder mViewHolder;

    public BaseAdapterSolicitacao(Context context, List<Solicitacao> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }


    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Solicitacao getItem(int position) {
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
            convertView = inflater.inflate(R.layout.layout_solicitacao, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final Solicitacao currentListData = getItem(position);

        mViewHolder.tvMaterial.setText(("Material: "+currentListData.getMaterial().getNome()));
        mViewHolder.tvRegiao.setText(("Região: "+currentListData.getRegiao().getNome()));
        //final RelativeLayout loading_layout = (RelativeLayout)((Activity) context).findViewById(R.id.loadingLayout);
        mViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Excluir Solicitação")
                        .setMessage("Se excluir essa solicitação, nenhuma doação será encaminhada para esta solicitação na próxima distribuição.")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Coletador coletador = (Coletador) Sistema.usuario;
//                                currentListData.getRegiao().getSolicitacoes().remove(currentListData);
//                                coletador.showSolicitacoes().remove(currentListData);
                                coletador.removerSolicitacao(currentListData);
                                Solicitacoes.updateList(context);
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
            }
        });

        return convertView;
    }

    private class MyViewHolder {
        TextView tvRegiao, tvMaterial;
        RelativeLayout btnDelete;
        public MyViewHolder(View item) {
            btnDelete = (RelativeLayout) item.findViewById(R.id.btn_delete_solicitacao);
            tvMaterial = (TextView) item.findViewById(R.id.tvMaterial);
            tvRegiao = (TextView) item.findViewById(R.id.tvRegiao);
        }
    }
}
