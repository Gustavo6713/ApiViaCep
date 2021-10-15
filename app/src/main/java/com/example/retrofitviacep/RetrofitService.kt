package com.example.retrofitviacep

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    // Método que será responsável por chmara a api
    // https://viacep.com.br/ws/06600025/json/
    @GET("{CEP}/json/")
    fun getCEP(@Path("CEP") cep: String) : Call<Cep>

    @GET("{uf}/{cidade}/{logadouro}/json")
    fun getCEPByLogadouro(
            @Path("uf") uf: String,
            @Path("cidade") cidade: String,
            @Path("logadouro") logadouro: String)

    @POST("/ceps")
    fun gravarCep(@Body cep: Cep) : Call<Cep> {

    }

    @DELETE("/ceps/{id}")
    fun excluir(@Path("id")id: Int) {

    }
}