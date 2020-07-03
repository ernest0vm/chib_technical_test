package mx.ernestovaldez.androidchib.interfaces

import mx.ernestovaldez.androidchib.models.UsersResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    fun getUsersFromApi(): Call<UsersResponse>

}