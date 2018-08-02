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

import com.will.greendonation.Activies.Coletador.Solicitacoes;
import com.will.greendonation.Activies.Doador.DoacoesEmAndamento;
import com.will.greendonation.Activies.Doador.DoacoesEmEspera;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Doacao;
import com.will.greendonation.Classes.Doador;
import com.will.greendonation.Classes.Empresa;
import com.will.greendonation.Classes.Pessoa;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.Classes.Solicitacao;
import com.will.greendonation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will Neto on 16/06/2017.
 */

public class BaseAdapterDoacaoEspera extends BaseAdapter {
    List<Doacao> myList = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    MyViewHolder mViewHolder;

    public BaseAdapterDoacaoEspera(Context context, List<Doacao> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }


    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Doacao getItem(int position) {
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
            convertView = inflater.inflate(R.layout.layout_doacao_espera, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final Doacao currentListData = getItem(position);

        mViewHolder.tvMaterial.setText(("Material: "+currentListData.getTipoDoacao().getNome()));
        mViewHolder.tvRegiao.setText(("Região: "+currentListData.getRegiao().getNome()));
        mViewHolder.tvPeso.setText(("Peso: "+currentListData.getPeso()+" kg"));
        //final RelativeLayout loading_layout = (RelativeLayout)((Activity) context).findViewById(R.id.loadingLayout);
        mViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Excluir Doação")
                        .setMessage("Deseja excluir essa doação? Cada doação é muito importante para nós.")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            Doador doador = (Doador) Sistema.usuario;
                            doador.cancelarDoacao(currentListData);
                            DoacoesEmEspera.updateList(context);
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
        TextView tvRegiao, tvMaterial, tvPeso;
        RelativeLayout btnDelete;
        public MyViewHolder(View item) {
            btnDelete = (RelativeLayout) item.findViewById(R.id.btn_delete_dooacao);
            tvMaterial = (TextView) item.findViewById(R.id.tvMaterial);
            tvRegiao = (TextView) item.findViewById(R.id.tvRegiao);
            tvPeso = (TextView) item.findViewById(R.id.tvPeso);
        }
    }
}
