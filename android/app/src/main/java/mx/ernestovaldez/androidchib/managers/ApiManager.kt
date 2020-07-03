package mx.ernestovaldez.androidchib.managers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    fun getApiService(): Retrofit {
        //TODO change ip from the api server
        val baseUrl = "http://192.168.0.26:3001/server/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getExchangeRates(): Retrofit {
        val baseUrl = "https://api.exchangeratesapi.io/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}