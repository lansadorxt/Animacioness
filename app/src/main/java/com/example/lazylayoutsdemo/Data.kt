package com.example.lazylayoutsdemo

import androidx.annotation.DrawableRes

data class Data(
    val nombre: String,
    val description: String,
    val likes: Int,
    @DrawableRes val imagen: Int
)