package com.example.myapplication

import android.icu.text.CaseMap

data class MainModel (val results: ArrayList<Results>) {
    data class Results (
        val id : Id,
        val name : Name,
        val email : String?,
        val picture : Picture,
        val gender : String?,
        val phone : String?
    )

    data class Id(
        val value : String?
    )

    data class Name(
        val title : String?,
        val first : String?,
        val last : String?
    )

    data class Picture(
        val large : String?
    )
}