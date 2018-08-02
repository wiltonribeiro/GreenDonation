package com.will.greendonation.Activies;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.will.greendonation.Classes.Sistema;
import com.will.greendonation.R;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RegisterUser extends AppCompatActivity {

    Sistema sistema;
    EditText nome, endereço, contato, chave, senha;
    Button btn_register, btn_picture;
    RadioGroup radioGroup;
    private static int SELECT_PICTURE = 1;
    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        nome = (EditText)findViewById(R.id.input_name);
        endereço = (EditText)findViewById(R.id.input_addres);
        contato = (EditText) findViewById(R.id.input_contact);
        chave = (EditText) findViewById(R.id.input_email);
        senha = (EditText) findViewById(R.id.input_password);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_picture = (Button) findViewById(R.id.btn_picture);
        radioGroup = (RadioGroup)findViewById(R.id.radioType);

        sistema = MainActivity.sistema;

        btn_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            int checkedRadioButtonId = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
            ImageView image  = (ImageView) findViewById(R.id.profile_image);
            Drawable profile = image.getDrawable();
            try{
                sistema.addUsuario(nome.getText().toString(), senha.getText().toString(), endereço.getText().toString(), chave.getText().toString(),
                        contato.getText().toString(), profile, checkedRadioButtonId);
                sistema.fazerLogin(chave.getText().toString(),senha.getText().toString());
                Sistema.useSystem(Sistema.usuario,RegisterUser.this);
            } catch (Exception e){
                Toast.makeText(RegisterUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageURI = data.getData();
                if (Build.VERSION.SDK_INT < 19){
                    selectedImagePath = getPath(selectedImageURI);
                    Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);
                    final ImageView image  = (ImageView) findViewById(R.id.profile_image);
                    image.setImageBitmap(bitmap);
                } else {
                    ParcelFileDescriptor parcelFileDescriptor;
                    try {
                        parcelFileDescriptor = getContentResolver().openFileDescriptor(selectedImageURI, "r");
                        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        parcelFileDescriptor.close();

                        final ImageView image  = (ImageView) findViewById(R.id.profile_image);
                        image.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String getPath(Uri uri) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }
}
