package com.example.aplikacjaio

data class UserBusItem(val imageResource: Int, val line: Int, val id: Int, val model: String, val access: Int){
    val lineString: String
    get() = "Autobus linii $line"
}
