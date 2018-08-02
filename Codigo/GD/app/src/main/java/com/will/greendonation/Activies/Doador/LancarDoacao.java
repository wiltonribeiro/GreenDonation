package com.will.greendonation.Activies.Doador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.will.greendonation.Activies.Coletador.LancarSolicitacao;
import com.will.greendonation.Activies.MainActivity;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Empresa;
import com.will.greendonation.Classes.Material;
import com.will.greendonation.Classes.Pessoa;
import com.will.greendonation.Classes.Regiao;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

import java.util.ArrayList;
import java.util.List;

public class LancarDoacao extends AppCompatActivity {

    Spinner spinner_region, spinner_material;
    Button btn_create_doacao;
    EditText input_peso;
    Sistema sistema = MainActivity.sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar_doacao);

        spinner_region = (Spinner)findViewById(R.id.spinner_region);
        spinner_material = (Spinner)findViewById(R.id.spinner_material);
        btn_create_doacao = (Button)findViewById(R.id.btn_create_doacao);
        input_peso = (EditText)findViewById(R.id.input_peso);

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

        btn_create_doacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinner_material.getSelectedItemPosition()==0 || spinner_material.getSelectedItemPosition()==0 || input_peso.getText().toString().isEmpty())
                    Toast.makeText(LancarDoacao.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    Material material = sistema.showMateriais().get(spinner_material.getSelectedItemPosition()-1);
                    Regiao regiao = sistema.showRegioes().get(spinner_region.getSelectedItemPosition()-1);
                    double peso = Double.parseDouble(input_peso.getText().toString());

                    if(Sistema.usuario instanceof Pessoa){
                        Pessoa pessoa = (Pessoa) Sistema.usuario;
                        pessoa.doarMaterial(peso,material,regiao);
                    } else if(Sistema.usuario instanceof Empresa){
                        Empresa empresa = (Empresa) Sistema.usuario;
                        empresa.doarMaterial(peso,material,regiao);
                    }

                    LancarDoacao.super.onBackPressed();
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
