package com.example.pc03.api;


import com.example.pc03.entity.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ServiceLibroApi {

    @POST("libro")
    public abstract Call<Libro> insertaLibo(@Body Libro obj);

    @GET("libro")
    public abstract Call<List<Libro>> listaLibro();



}
