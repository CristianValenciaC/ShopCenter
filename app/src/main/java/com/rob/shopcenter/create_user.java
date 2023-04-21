package com.rob.shopcenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class create_user extends AppCompatActivity {

    TextView name, username, password;

    Button sign_up, login, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        inicializarComponentes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.language_en:
                Resources res = create_user.this.getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                conf.locale = new Locale("en", "EN");
                res.updateConfiguration(conf,dm);
                Intent i2 = new Intent(this, create_user.class);
                startActivity(i2);
                finish();
                break;
            case R.id.language_es:
                Resources res2 = create_user.this.getResources();
                DisplayMetrics dm2 = res2.getDisplayMetrics();
                android.content.res.Configuration conf2 = res2.getConfiguration();
                conf2.locale = new Locale("es", "ES");
                res2.updateConfiguration(conf2,dm2);
                Intent i = new Intent(this, create_user.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void inicializarComponentes(){
        name = findViewById(R.id.nameUser);
        username = findViewById(R.id.username_create);
        password = findViewById(R.id.password_create);

        sign_up = findViewById(R.id.buttonCreateUser);
        login = findViewById(R.id.backLogin);
        exit = findViewById(R.id.buttonExit_user);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameUser = name.getText().toString();
                String userNameUser = username.getText().toString();
                String passwordUser = password.getText().toString();

                String nombre_archivo = userNameUser;

                SharedPreferences newPreference = getSharedPreferences(nombre_archivo, Context.MODE_PRIVATE);
                String userConfig = newPreference.getString("username", "-1");
                if(userConfig.equals(userNameUser)){
                    enviarMensajeFallo();
                }else{
                    SharedPreferences.Editor editor = newPreference.edit();
                    editor.putString("username", userNameUser.trim());
                    editor.putString("password", passwordUser);
                    editor.putString("name_user", nameUser);
                    editor.apply();
                    enviarMensajeCorrecto();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

    public void enviarMensajeFallo(){
        Toast.makeText(this, "Existe un mismo Usuario", Toast.LENGTH_SHORT).show();
    }

    public void enviarMensajeCorrecto(){
        Toast.makeText(this, "Se ha creado el usuario correctamente", Toast.LENGTH_SHORT).show();
    }
}