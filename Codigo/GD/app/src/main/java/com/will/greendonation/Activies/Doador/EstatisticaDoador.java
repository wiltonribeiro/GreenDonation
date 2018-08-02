package com.will.greendonation.Activies.Doador;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.will.greendonation.Classes.Doador;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

public class EstatisticaDoador extends android.support.v4.app.Fragment {

    static Context context;
    static TextView estFeitas, estColetadores, estMaterial, estPeso;
    static RelativeLayout nothing_est;
    static LinearLayout layout_est;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_estatistica_doador, container, false);
        context = rootView.getContext();

        estFeitas = (TextView)rootView.findViewById(R.id.est_feitas);
        estColetadores = (TextView)rootView.findViewById(R.id.est_coletadores);
        estMaterial = (TextView)rootView.findViewById(R.id.est_material);
        estPeso = (TextView)rootView.findViewById(R.id.est_peso);
        nothing_est = (RelativeLayout)rootView.findViewById(R.id.nothing_est);
        layout_est = (LinearLayout)rootView.findViewById(R.id.layout_est);

        return  rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList(context);
    }

    public static void updateList(Context context) {
        Doador doador = (Doador) Sistema.usuario;
        if(doador.getRealizadas().size()==0){
            nothing_est.setVisibility(View.VISIBLE);
            layout_est.setVisibility(View.GONE);
        }else{
            nothing_est.setVisibility(View.GONE);
            layout_est.setVisibility(View.VISIBLE);

            estPeso.setText((""+doador.qntdMaterial()+" Kg"));
            estMaterial.setText(doador.estMaterial().getNome());
            estColetadores.setText((""+doador.numColetadores()));
            estFeitas.setText((""+doador.numDoacoes()));
        }
    }
}
