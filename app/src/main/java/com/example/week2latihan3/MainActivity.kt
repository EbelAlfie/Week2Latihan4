package com.example.week2latihan3

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity: AppCompatActivity() {
    private lateinit var etNama: EditText
    private lateinit var etStok: EditText
    private lateinit var etHarga: EditText
    private lateinit var btnSubmit: Button
    private lateinit var tvError: TextView
    private lateinit var recView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter

    private lateinit var listProduk: MutableList<Produk>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        viewInteraction()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun viewInteraction() {
        btnSubmit.setOnClickListener {
            try {
                val namaProduk = etNama.text.toString()
                val stokProduk = etStok.text.toString()
                val hargaProduk = etHarga.text.toString()

                if (invalidProduk(namaProduk, stokProduk, hargaProduk)) {
                    tvError.text = getString(R.string.input_kosong)
                    return@setOnClickListener
                }
                //cek duplikat dan spasi di depan
                if (listProduk.find {
                    namaProduk.equals(it.namaProduk, ignoreCase = true)
                } != null){
                    tvError.text = getString(R.string.produk_duplpikat)
                    return@setOnClickListener
                }
                if (listProduk.isEmpty()) listProduk.add(Produk(getString(R.string.header_nama), getString(
                                    R.string.header_stok), getString(R.string.header_harga)))
                tvError.text = ""
                listProduk.add(Produk(namaProduk, stokProduk, hargaProduk))
                produkAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.default_errormsg), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun invalidProduk(namaProduk: String, stokProduk: String, hargaProduk: String): Boolean {
        return namaProduk.isBlank() || stokProduk.isBlank()
                || hargaProduk.isBlank() || stokProduk.toInt() <=0
                || hargaProduk.toInt() <= 0
    }

    private fun initView() {
        etNama = findViewById(R.id.et_nama)
        etStok = findViewById(R.id.et_stok)
        etHarga = findViewById(R.id.et_harga)
        btnSubmit = findViewById(R.id.btn_submit)
        recView = findViewById(R.id.rv_allitem)
        tvError = findViewById(R.id.tv_error)

        listProduk = mutableListOf()

        produkAdapter = ProdukAdapter(listProduk)

        recView.layoutManager = LinearLayoutManager(this)

        recView.adapter = produkAdapter
    }
}