package com.example.retrofitviacep

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CepsAdapter(var context: Context) : RecyclerView.Adapter<CepsAdapter.CepViewHolder>() {

    private var listaCeps = emptyList<Cep>()

    fun updateListaCeps(lista: List<Cep>) {
        listaCeps = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CepViewHolder {
        val view =
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.ceps_recyder_view_layout,
                        parent,
                        false)

        return CepViewHolder(view)
    }

    override fun onBindViewHolder(holder: CepViewHolder, position: Int) {

        Log.i("xptoholder", "onBindViewHolder")

        val cep = listaCeps[position]

        holder.tvCep.text = cep.cep
        holder.tvLogadouro.text = cep.logadouro
        holder.tvLocalidade.text = cep.cidade
    }

    override fun getItemCount(): Int {
        Log.i("xptoholder", "getItemCount")
        return listaCeps.size
    }

    class CepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvCep = itemView.findViewById<TextView>(R.id.tv_cep)
        val tvLogadouro = itemView.findViewById<TextView>(R.id.tv_logadouro)
        val tvLocalidade = itemView.findViewById<TextView>(R.id.tv_Localidade)

    }
}