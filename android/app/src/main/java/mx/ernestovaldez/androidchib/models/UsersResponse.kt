package mx.ernestovaldez.androidchib.models

import com.google.gson.annotations.SerializedName

data class UsersResponse (
    @SerializedName("usuarios") val usuarios: List<Usuario>
)