package com.example.week2latihan3

import androidx.core.text.isDigitsOnly
import java.text.DecimalFormat

data class Produk(var namaProduk: String, var stockProduk: String, var hargaProduk: String){
    fun getStock(): String {
        return if (stockProduk.isDigitsOnly()) "$stockProduk ${printPc(stockProduk)}" else stockProduk
    }
    private fun printPc(stockProduk: String): String { return if (stockProduk != "1") "pcs" else "pc" }
    fun getHarga(): String {
        return if (hargaProduk.isDigitsOnly()) DecimalFormat("Rp #,###").format(Integer.valueOf(hargaProduk)) else hargaProduk
    }
}

