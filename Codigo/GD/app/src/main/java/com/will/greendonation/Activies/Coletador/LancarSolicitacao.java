package com.will.greendonation.Activies.Coletador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.will.greendonation.Activies.MainActivity;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Material;
import com.will.greendonation.Classes.Regiao;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

import java.util.ArrayList;
import java.util.List;

public class LancarSolicitacao extends AppCompatActivity {

    Spinner spinner_region, spinner_material;
    Button btn_create_solicitacao;
    Sistema sistema = MainActivity.sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar_solicitacao);

        spinner_region = (Spinner)findViewById(R.id.spinner_region);
        spinner_material = (Spinner)findViewById(R.id.spinner_material);
        btn_create_solicitacao = (Button)findViewById(R.id.btn_create_solicitacao);

        List<String> listRegion =  new ArrayList<String>();
        listRegion.add("Selecionar região");

        List<String> listMaterial =  new ArrayList<String>();
        listMaterial.add("Selecionar material");

        for(Regiao regiao: sistema.showRegioes())
            listRegion.add(regiao.getNome());

        for(Material material: sistema.showMateriais())
            listMaterial.add(material.getNome());

        spinner_region.setAdapter(getAdapter(listRegion));
        spinner_material.setAdapter(getAdapter(listMaterial));

        btn_create_solicitacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinner_material.getSelectedItemPosition()==0 || spinner_material.getSelectedItemPosition()==0)
                    Toast.makeText(LancarSolicitacao.this, "Selecione todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    Material material = sistema.showMateriais().get(spinner_material.getSelectedItemPosition()-1);
                    Regiao regiao = sistema.showRegioes().get(spinner_region.getSelectedItemPosition()-1);

                    Coletador coletador = (Coletador)Sistema.usuario;
                    coletador.lancarSolicitacao(material,regiao);

                    LancarSolicitacao.super.onBackPressed();
                }
            }
        });
    }

    public ArrayAdapter<String> getAdapter(List<String> list){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
