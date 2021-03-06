package com.example.retrofitviacep

import com.google.gson.annotations.SerializedName

data class Cep(
    var cep: String,
    var logadouro: String,
    var complemento: String,
    var bairro: String,
    @SerializedName("localidade") var cidade: String,
    var uf: String,
    var ibge: String,
    var gia: String,
    var ddd: String,
    var siafi: String
)
