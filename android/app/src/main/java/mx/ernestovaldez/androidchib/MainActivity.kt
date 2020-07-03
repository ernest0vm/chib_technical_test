package mx.ernestovaldez.androidchib

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mx.ernestovaldez.androidchib.adapters.UsersListAdapter
import mx.ernestovaldez.androidchib.interfaces.ApiService
import mx.ernestovaldez.androidchib.interfaces.ExchangeService
import mx.ernestovaldez.androidchib.managers.ApiManager
import mx.ernestovaldez.androidchib.models.CurrencyExchange
import mx.ernestovaldez.androidchib.models.UsersResponse
import mx.ernestovaldez.androidchib.models.Usuario
import mx.ernestovaldez.androidchib.utils.OnItemClickListener
import mx.ernestovaldez.androidchib.utils.addOnItemClickListener
import mx.ernestovaldez.androidtest.interfaces.ResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.RoundingMode

class MainActivity : AppCompatActivity(), ResponseListener<Any> {
    private val completeUserList: MutableList<Usuario> = mutableListOf()
    var currencyExchangeInMXN = 0.0
    val responseListener = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initApp()

        btnRetry.setOnClickListener {
            initApp()
        }
    }

    private fun initApp() {
        val internetLayout: LinearLayout = this.findViewById(R.id.InternetLayout)

        if (isOnline(this)){
            internetLayout.visibility = View.INVISIBLE
            fetchCurrencyExchange()
        } else {
            internetLayout.visibility = View.VISIBLE
            Log.e("Internet", "internet connection is not available.")
        }
    }

    override fun onSuccess(responseObject: Any) {

        Log.d("onSuccess[Response]", "the request was successful")
        //makeText(this, "the request was successful", Toast.LENGTH_LONG).show()

        for (item in (responseObject as List<*>)) {
            val user: Usuario = item as Usuario
            user.precioEnDolares = (user.precioPorHora).toDouble() / currencyExchangeInMXN
            completeUserList.add(user)
        }

        ///order by name
        completeUserList.sortBy { it.nombre }

        ///create list adapter
        val mAdapter = UsersListAdapter(this, completeUserList)
        itemList.layoutManager = LinearLayoutManager(this)
        itemList.setHasFixedSize(true)
        itemList.adapter = mAdapter

        itemList.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val intent = Intent(applicationContext, CheckoutActivity::class.java).apply {
                    val userData = completeUserList[position]
                    putExtra("data", userData)
                }
                startActivity(intent)
                //makeText(applicationContext, "item tapped:" + position.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onError(error: String) {
        Log.e("onError[Response]", error)
        makeText(this, error, Toast.LENGTH_SHORT).show()

    }

    private fun fetchData(responseListener: ResponseListener<Any>) {

        completeUserList.clear()
        val service = ApiManager().getApiService().create(ApiService::class.java)

        val call = service.getUsersFromApi()

        call.enqueue(object : Callback<UsersResponse> {
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                responseListener.onError("Something was wrong in the request: ${t.message}")
            }

            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.code() == 200) {
                    val usersResponse = response.body() as UsersResponse
                    makeText(applicationContext, "Trabajadores encontrados: " + usersResponse.usuarios.size.toString(), Toast.LENGTH_SHORT).show()
                    responseListener.onSuccess(usersResponse.usuarios)
                } else {
                    responseListener.onError("Something was wrong in the request: ${response.code()}")
                }
            }

        })
    }

    private fun fetchCurrencyExchange() {

        completeUserList.clear()
        val service = ApiManager().getExchangeRates().create(ExchangeService::class.java)

        val call = service.getExchangeRates("USD")

        call.enqueue(object : Callback<CurrencyExchange> {
            override fun onFailure(call: Call<CurrencyExchange>, t: Throwable) {
                Log.e("CurrencyExchangeError:", "Something was wrong in the exchange request: ${t.message}")
            }

            override fun onResponse(call: Call<CurrencyExchange>, response: Response<CurrencyExchange>) {
                if (response.code() == 200) {
                    val currencyExchange = response.body() as CurrencyExchange
                    val roundedCurrencyRate = (currencyExchange.rates["MXN"] as Double).toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()
                    makeText(applicationContext, "El tipo de cambio por dolar es $" + roundedCurrencyRate.toString() + " MXN", Toast.LENGTH_SHORT).show()
                    currencyExchangeInMXN = roundedCurrencyRate

                    fetchData(responseListener)
                } else {
                    Log.e("CurrencyExchangeError:", "Something was wrong in the request: ${response.code()}")
                }
            }

        })
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}

