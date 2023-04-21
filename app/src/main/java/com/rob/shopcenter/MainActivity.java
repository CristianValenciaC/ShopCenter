package com.rob.shopcenter;

import androidx.activity.OnBackPressedCallback;
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

public class MainActivity extends AppCompatActivity {

    //private Spinner spinner;

    private TextView username, password;

    private Button buttonLogin;
    private Button signup;
    private Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();
        inicializarOnClickListenerButtons();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i2 = new Intent(this, MainActivity.class);
        startActivity(i2);
        finish();
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
                Resources res = MainActivity.this.getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                conf.locale = new Locale("en", "EN");
                res.updateConfiguration(conf,dm);
                Intent i2 = new Intent(this, MainActivity.class);
                startActivity(i2);
                finish();
                break;
            case R.id.language_es:
                Resources res2 = MainActivity.this.getResources();
                DisplayMetrics dm2 = res2.getDisplayMetrics();
                android.content.res.Configuration conf2 = res2.getConfiguration();
                conf2.locale = new Locale("es", "ES");
                res2.updateConfiguration(conf2,dm2);
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void inicializarComponentes(){
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonExit = findViewById(R.id.buttonExit);
        signup = findViewById(R.id.button2);
        username = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        /*
        spinner = findViewById(R.id.spinnerIdiom);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.language));
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mostrar(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            //No hace nada
            }
        });
        */
    }

    /*public void mostrar(View view){
        String seleccion = spinner.getSelectedItem().toString();
        switch (seleccion){
            case "EN":
                Resources res = MainActivity.this.getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                conf.locale = new Locale("en", "EN");
                res.updateConfiguration(conf,dm);
                Intent i2 = new Intent(this, MainActivity.class);
                startActivity(i2);
                finish();
                break;
            case "ES":
                Resources res2 = MainActivity.this.getResources();
                DisplayMetrics dm2 = res2.getDisplayMetrics();
                android.content.res.Configuration conf2 = res2.getConfiguration();
                conf2.locale = new Locale("es", "ES");
                res2.updateConfiguration(conf2,dm2);
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
        String resultado = getResources().getString(R.string.languageSelected);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }*/

    protected void inicializarOnClickListenerButtons(){
        buttonLogin.setOnClickListener(this::login);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create_userlogin(view);
            }
        });
        buttonExit.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });
    }

    public void create_userlogin(View view){
        Intent i = new Intent(this, create_user.class);
        startActivity(i);
    }

    public void login (View view) {
        SharedPreferences preferences = getSharedPreferences(username.getText().toString(), Context.MODE_PRIVATE);
        String userConfig = preferences.getString("username", "-1");
        String passwordConfig = preferences.getString("password", "-2");
        if(username.getText().toString().trim().equals(userConfig) && password.getText().toString().equals(passwordConfig)){
            Intent i = new Intent(this, HomeActivity.class);
            i.putExtra("nombre", preferences.getString("name_user", "GUEST"));
            startActivity(i);
        }else{
            Toast.makeText(this, "El usuario o contraseña introducida es incorrecta", Toast.LENGTH_SHORT).show();
        }

    }
}