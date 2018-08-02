package com.will.greendonation.Activies.Doador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.will.greendonation.Activies.EditProfile;
import com.will.greendonation.Activies.Estatisticas;
import com.will.greendonation.Activies.MainActivity;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

public class DoadorActivity extends AppCompatActivity {
    TextView output_name;
    Button btn_logout;
    Sistema sistema = MainActivity.sistema;
    LinearLayout btn_editProfile, btn_doacao, btn_estatisticas;
    ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doador);

        btn_logout = (Button) findViewById(R.id.btn_logout);
        profile_image = (ImageView) findViewById(R.id.profile_image);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sistema.fazerLogout();
                startActivity(new Intent(DoadorActivity.this, MainActivity.class));
                finishAffinity();
            }
        });

        btn_doacao = (LinearLayout) findViewById(R.id.btn_doacao);
        btn_editProfile = (LinearLayout) findViewById(R.id.btn_editProfile);
        btn_estatisticas = (LinearLayout) findViewById(R.id.btn_estatisticas);

        btn_doacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoadorActivity.this, NotificacoesDoador.class));
            }
        });

        btn_estatisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoadorActivity.this, Estatisticas.class));
            }
        });

        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoadorActivity.this, EditProfile.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        output_name = (TextView)findViewById(R.id.output_userName);
        output_name.setText(Sistema.usuario.getNome());
        profile_image.setImageDrawable(Sistema.usuario.getPerfil());
    }
}
