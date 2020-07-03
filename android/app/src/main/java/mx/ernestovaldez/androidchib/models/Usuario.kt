package mx.ernestovaldez.androidchib.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario (
    @SerializedName("_id") val id: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("fechaNacimiento") val fechaNacimiento: String,
    @SerializedName("profesion") val profesion: String,
    @SerializedName("descripcionProfesion") val descripcionProfesion: String,
    @SerializedName("precioPorHora") val precioPorHora: String,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("__v") val v: Long,
    var precioEnDolares: Double
) : Serializable