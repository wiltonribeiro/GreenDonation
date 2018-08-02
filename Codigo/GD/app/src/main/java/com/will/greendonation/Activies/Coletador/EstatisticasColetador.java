package com.will.greendonation.Activies.Coletador;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Doador;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

public class EstatisticasColetador extends android.support.v4.app.Fragment {

    static Context context;
    static TextView estFeitas, estColetadores, estMaterial, estPeso;
    static RelativeLayout nothing_est;
    static LinearLayout layout_est;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_estatisticas_coletador, container, false);
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
        Coletador coletador = (Coletador) Sistema.usuario;
        if(coletador.showDoacoesAceitas().size()==0){
            nothing_est.setVisibility(View.VISIBLE);
            layout_est.setVisibility(View.GONE);
        }else{
            nothing_est.setVisibility(View.GONE);
            layout_est.setVisibility(View.VISIBLE);

            estPeso.setText((""+coletador.qntdMaterial()+" Kg"));
            estMaterial.setText(coletador.estMaterial().getNome());
            estColetadores.setText((""+coletador.numDoadores()));
            estFeitas.setText((""+coletador.numDoacoes()));
        }
    }
}