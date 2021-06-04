package com.example.pc03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pc03.adapter.LibroAdapter;
import com.example.pc03.api.ServiceLibroApi;
import com.example.pc03.entity.Libro;
import com.example.pc03.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibroCrudListaActivity extends AppCompatActivity {

    List<Libro> lstData=new ArrayList<Libro>();
    LibroAdapter adaptador =null;
    ListView lstView=null;
    ServiceLibroApi api=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro_crud_lista);

        lstView=(ListView) findViewById(R.id.idListCrudLibro);
        adaptador=new LibroAdapter(this,R.layout.acivity_libro_item,lstData);
        lstView.setAdapter(adaptador);

        api= ConnectionRest.getConnection().create(ServiceLibroApi.class);
        lista();
    }

    public  void lista(){

        Call<List<Libro>> call = api.listaLibro();
        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {


                if (response.isSuccessful()) {


                    List<Libro> data = response.body();
                    mensaje("LOG -> " + "size " + data.size());
                    lstData.clear();
                    lstData.addAll(data);
                    adaptador.notifyDataSetChanged();
                } else {
                    mensaje("ERROR -> " + "Error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                mensaje("ERROR -> " +   "Error en la respuesta");

            }

        });

    }
    void mensaje(String msg){
        Toast toast1=Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG);
        toast1.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.idMenuCrudLibro){
            Intent intent = new Intent(this,LibroCrudListaActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.idMenuCrudRevista){
            Intent intent = new Intent(this,RevistaCrudListaActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.idMenu){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }

            return super.onOptionsItemSelected(item);
    }






}