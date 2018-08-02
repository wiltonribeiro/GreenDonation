package com.will.greendonation.Activies.Coletador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.will.greendonation.Activies.Doador.DoadorActivity;
import com.will.greendonation.Activies.EditProfile;
import com.will.greendonation.Activies.Estatisticas;
import com.will.greendonation.Activies.MainActivity;
import com.will.greendonation.Classes.Coletador;
import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

public class ColetadorActivity extends AppCompatActivity {

    LinearLayout btn_editProfile, btn_solicitacao, btn_doacao, btn_estatisticas;
    TextView output_name;
    Button btn_logout;
    Sistema sistema = MainActivity.sistema;
    ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coletador);

        btn_estatisticas = (LinearLayout) findViewById(R.id.btn_estatisticas);
        btn_editProfile = (LinearLayout) findViewById(R.id.btn_editProfile);
        btn_solicitacao = (LinearLayout) findViewById(R.id.btn_solicitacao);
        btn_doacao = (LinearLayout) findViewById(R.id.btn_doacao);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        profile_image = (ImageView) findViewById(R.id.profile_image);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sistema.fazerLogout();
                startActivity(new Intent(ColetadorActivity.this, MainActivity.class));
                finishAffinity();
            }
        });

        btn_solicitacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ColetadorActivity.this, Solicitacoes.class));
            }
        });

        btn_estatisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ColetadorActivity.this, Estatisticas.class));
            }
        });

        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ColetadorActivity.this, EditProfile.class));
            }
        });

        btn_doacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ColetadorActivity.this, DoacoesColetador.class));
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
