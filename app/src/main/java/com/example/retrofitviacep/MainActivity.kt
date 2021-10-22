package com.example.retrofitviacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Path

class MainActivity : AppCompatActivity() {

    lateinit var  buttonBuscar: Button
    lateinit var  buttonBuscar2: Button

    lateinit var  textViewEndereco: TextView
    lateinit var  textViewEndereco2: TextView

    lateinit var editTextCep: EditText
    lateinit var editTextNomeRua: EditText
    lateinit var editTextCidade: EditText
    lateinit var editTextUf: EditText

    lateinit var rvCeps: RecyclerView
    lateinit var cepsAdapter: CepsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBuscar = findViewById(R.id.buttonBuscar)
        buttonBuscar2 = findViewById(R.id.buttonBuscar2)

        textViewEndereco = findViewById(R.id.textViewEndereco)


        editTextCep = findViewById(R.id.editTextCep)
        editTextNomeRua = findViewById(R.id.editTextNomeRua)
        editTextCidade = findViewById(R.id.editTextCidade)
        editTextUf = findViewById(R.id.editTextEstado)

        // configuração da RecyclerView
        // inicialização da RV e do Adapter
        rvCeps = findViewById(R.id.rv_ceps)
        cepsAdapter = CepsAdapter(this)

        //determinar o layout da RV
//        rvCeps.layoutManager =
//                LinearLayoutManager(
//                        this,
//                        LinearLayoutManager.HORIZONTAL,
//                        false)

        rvCeps.layoutManager = GridLayoutManager(this, 2)

        //definir o adapter da RecyclerView
        rvCeps.adapter = cepsAdapter


        buttonBuscar.setOnClickListener {

            // Obter uma instância da conexão com o Backend
            val remote = RetrofitFactory().retrofitService()

            // Criar uma chamada para o endpoint / cep/ json
            val call: Call<Cep> = remote.getCEP(editTextCep.text.toString())

            // Executar a chamda para a api
            call.enqueue(object : Callback<Cep>{
                override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                    val ceps = response.body()

                    cepsAdapter.updateListaCeps(ceps!!)
                }

                override fun onFailure(call: Call<Cep>, t: Throwable) {
                    Log.i("cep", t.message.toString())
                }

            })


        }


        buttonBuscar2.setOnClickListener {

            // Obter uma instância da conexão com o Backend
            val remote = RetrofitFactory().retrofitService()

            // Criar uma chamada para o endpoint / uf/ cidade/ nome da rua / json
            val call: Call<List<Cep>> = remote.getCEPByLogradouro(
                    editTextUf.text.toString(),
                    editTextCidade.text.toString(),
                    editTextNomeRua.text.toString())

            // Executar a chamda para a api

            call.enqueue(object : Callback<List<Cep>> {
                override fun onResponse(call: Call<List<Cep>>, response: Response<List<Cep>>) {
                    val cep = response.body()
                    textViewEndereco2.text = cep.toString()
                }

                override fun onFailure(call: Call<List<Cep>>, t: Throwable) {
                    Log.i("cep", t.message.toString())

                }

            } )

        }

    }
}