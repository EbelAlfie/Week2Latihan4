package com.example.week2latihan3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProdukAdapter(val listProduk: MutableList<Produk>): RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder>() {

    class ProdukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etOutNama = itemView.findViewById<TextView>(R.id.tv_out_nama)
        val etOutStok = itemView.findViewById<TextView>(R.id.tv_out_stok)
        val etOutHarga = itemView.findViewById<TextView>(R.id.tv_out_harga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        return ProdukViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.one_item, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        val produk = listProduk[position]
        holder.etOutNama.text = produk.namaProduk
        holder.etOutStok.text = produk.getStock()
        holder.etOutHarga.text = produk.getHarga()
    }

    override fun getItemCount(): Int {
        return listProduk.size
    }
}