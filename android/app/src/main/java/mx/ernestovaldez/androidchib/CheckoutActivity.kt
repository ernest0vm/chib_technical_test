package mx.ernestovaldez.androidchib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_checkout.*
import mx.ernestovaldez.androidchib.models.Usuario
import mx.ernestovaldez.androidchib.utils.fromUrl
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import java.math.RoundingMode

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val data = intent.getSerializableExtra("data")
        val userData: Usuario = (data as Usuario)
        this.title = userData.nombre

        val priceInUSD = userData.precioEnDolares.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()
        val variableCommission = priceInUSD * 0.029
        val fixedCommission = 0.30
        val totalCost = priceInUSD + variableCommission + fixedCommission

        imgAvatar.fromUrl(userData.avatar)
        tvProfession2.text = userData.profesion
        tvDescription2.text = userData.descripcionProfesion
        lblPrice.text = "Precio por Hora:"
        tvPrice2.text = "$${priceInUSD} USD"

        lblComission.text = "Comision por contratacion (2.9%):"
        tvComission.text = "$${variableCommission.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)} USD"

        lblCost.text = "Costo por contratacion"
        tvCost.text = "$${fixedCommission.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)} USD"

        lblTotalCost.text = "Costo total:"
        tvTotalCost.text = "$${totalCost.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)} USD"

        btnContract.setOnClickListener {
            alert("El trabajador ha sido notificado y se pondrá en contacto contigo.") {
                title("Gracias por contratar")
                positiveButton("Aceptar") { }
            }.show()
        }
    }
}