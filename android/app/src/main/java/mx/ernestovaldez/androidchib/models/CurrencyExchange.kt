package mx.ernestovaldez.androidchib.models

import com.google.gson.annotations.SerializedName

data class CurrencyExchange (
    @SerializedName("rates") val rates: Map<String, Double>,
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String
)