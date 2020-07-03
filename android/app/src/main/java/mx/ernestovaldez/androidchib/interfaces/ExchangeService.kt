package mx.ernestovaldez.androidchib.interfaces

import mx.ernestovaldez.androidchib.models.CurrencyExchange
import retrofit2.Call
import retrofit2.http.*

interface ExchangeService {

    @GET("latest")
    fun getExchangeRates(@Query("base") base: String): Call<CurrencyExchange>

}