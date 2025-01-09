package com.example.marvelmobileapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MarvelData (
    @ColumnInfo(name = "Marvelİsim")
    @SerializedName("Marvelİsim")
    val marvelİsim: String? ,

    @ColumnInfo(name = "MarvelKahramanÖzellikleri")
    @SerializedName("MarvelKahramanÖzellikleri")
    val marvelKahramanÖzellikleri: String?,

    @ColumnInfo(name = "MarvelKahramanUlke")
    @SerializedName("MarvelKahramanUlke")
    val marvelKahramanUlke: String?,

    @ColumnInfo(name = "MarvelKahramanYas")
    @SerializedName("MarvelKahramanYas")
    val marvelKahramanYas: String?,

    @ColumnInfo(name = "MarvelKahramanCinsiyet")
    @SerializedName("MarvelKahramanCinsiyet")
    val marvelKahramanCinsiyet: String?,

    @ColumnInfo(name = "MarvelGorsel")
    @SerializedName("MarvelGorsel")
    val marvelGorsel: String?

)
{
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}