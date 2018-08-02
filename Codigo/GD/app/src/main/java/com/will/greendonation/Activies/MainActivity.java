package com.will.greendonation.Activies;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Pessoa;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

public class MainActivity extends AppCompatActivity {

    EditText input_email;
    EditText input_password;
    Button btn_login;
    Button btn_register, btn_distribuir;
    public static Sistema sistema = new Sistema();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //teste
        try{
            sistema.addMaterial("Plastico", " vem do Petroleo");
            sistema.addMaterial("Lata", "Vem do ferro");
            sistema.addMaterial("Papelão", " vem das arvores");
            sistema.addRegiao("Babilonia");
            sistema.addRegiao("KingsLanding");
            sistema.addRegiao("Hiuss Islandy");

            Drawable image = ContextCompat.getDrawable(this, R.drawable.avatar);
            sistema.addUsuario("Wilton","123123","Lugar qualquer", "willrcneto@gmail.com","889998717", image,0);
            sistema.addUsuario("Clebson","123123","Lugar qualquer", "clebson@gmail.com","889998717",image,1);


            sistema.fazerLogin("willrcneto@gmail.com","123123");
            Pessoa doador = (Pessoa) Sistema.usuario;
            doador.doarMaterial(10.2,sistema.showMateriais().get(0),sistema.showRegioes().get(0));
            doador.doarMaterial(10.3,sistema.showMateriais().get(1),sistema.showRegioes().get(0));
            doador.fazerLogout();

            sistema.fazerLogin("clebson@gmail.com","123123");
            Coletador coletador = (Coletador) Sistema.usuario;
            coletador.lancarSolicitacao(sistema.showMateriais().get(0),sistema.showRegioes().get(0));
            coletador.lancarSolicitacao(sistema.showMateriais().get(1),sistema.showRegioes().get(0));
            coletador.fazerLogout();

            coletador.fazerLogout();

            sistema.distribuirDoacoes();


            sistema.fazerLogin("willneto@gmail.com","123123");
        } catch (Exception w){

        }


        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_distribuir = (Button)findViewById(R.id.btn_distribuir);

        btn_distribuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sistema.distribuirDoacoes();
                Toast.makeText(MainActivity.this, "Distribuido", Toast.LENGTH_SHORT).show();
            }
        });

        input_email = (EditText)findViewById(R.id.input_email);
        input_password = (EditText)findViewById(R.id.input_password);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goto_register = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(goto_register);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = input_email.getText().toString();
                String password = input_password.getText().toString();
                try {
                    sistema.fazerLogin(email,password);
                    Sistema.useSystem(Sistema.usuario,MainActivity.this);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
