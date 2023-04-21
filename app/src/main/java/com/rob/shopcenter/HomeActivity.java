package com.rob.shopcenter;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.rob.shopcenter.adapters.ProductAdapter;
import com.rob.shopcenter.clases.Product;
import com.rob.shopcenter.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements OnItemClickListener {

    private double precioFinal;
    private TextView carrito;

    private List<Product> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        precioFinal = 0;
        carrito = findViewById(R.id.precioFinal);

        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString("nombre");

        Toast.makeText(this, "Has iniciado sesion como " + nombre, Toast.LENGTH_SHORT).show();

        startElementsRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones_usuario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.close_session:
                Toast.makeText(this, "Has cerrado la sesion correctamente", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.language_en:
                Resources res = HomeActivity.this.getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                conf.locale = new Locale("en", "EN");
                res.updateConfiguration(conf,dm);
                Intent i2 = new Intent(this, HomeActivity.class);
                startActivity(i2);
                finish();
                break;
            case R.id.language_es:
                Resources res2 = HomeActivity.this.getResources();
                DisplayMetrics dm2 = res2.getDisplayMetrics();
                android.content.res.Configuration conf2 = res2.getConfiguration();
                conf2.locale = new Locale("es", "ES");
                res2.updateConfiguration(conf2,dm2);
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startElementsRecyclerView(){
        productsList = new ArrayList<>();
        productsList.add(new Product(R.mipmap.cpu_intel, "Pedro", "México", 10,120.50));
        productsList.add(new Product(R.mipmap.cpu_amd, "Julio", "Tabasco", 10, 135.20));
        productsList.add(new Product(R.mipmap.nvidia_rtx4090, "Alejandra", "Chihuahua", 20, 350.45));
        productsList.add(new Product(R.mipmap.amd_radeon_rx6800xt, "Jessica", "Durango", 40, 420.75));
        productsList.add(new Product(R.mipmap.hiditec_ventilador_cdisipador, "Armando", "Yucatan", 20, 362.10));
        productsList.add(new Product(R.mipmap.purewings_ventilador, "Pedro", "México", 100, 125.10));
        productsList.add(new Product(R.mipmap.aerocool_fuentealimentacion, "Julio", "Tabasco", 59, 62.10));
        productsList.add(new Product(R.mipmap.mpg_fuentedealimentacion, "Alejandra", "Chihuahua", 74, 45.10));
        productsList.add(new Product(R.mipmap.msi_carcasa_ordenador_negro, "Jessica", "Durango", 14, 46.10));
        productsList.add(new Product(R.mipmap.thermaltake_carcasa_ordenador, "Armando", "Yucatan", 102, 48.10));

        ProductAdapter listAdapter = new ProductAdapter(productsList, this);
        listAdapter.setOnItemClickListener(this::onItemClick);
        RecyclerView recyclerView = findViewById(R.id.recyclerCard);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(listAdapter);
    }

    public static String getUser(){
        return "";
    }

    @Override
    public void onItemClick(Product item, TextView numStock) {
        if(item.getNumStock() > 0){
            item.restProduct(1);
            if(item.getNumStock() == 1){
                numStock.setText(item.getNumStock() +" "+ getResources().getString(R.string.unit) + " "+ getResources().getString(R.string.remaining));
            }else if (item.getNumStock()>1){
                numStock.setText(item.getNumStock() + " "+ getResources().getString(R.string.units) + " " + getResources().getString(R.string.remaining));
            }else{
                numStock.setText(getResources().getString(R.string.end_product));
            }
            precioFinal += item.getPrice();
            carrito.setText(getResources().getString(R.string.price_total) + ": " + precioFinal + "€");
        }
    }
}