package mx.ernestovaldez.androidchib.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.ernestovaldez.androidchib.models.Usuario
import mx.ernestovaldez.androidchib.R
import mx.ernestovaldez.androidchib.utils.fromUrl
import java.math.RoundingMode

class UsersListAdapter(context: Context, myDataSet: List<Usuario>) :
    RecyclerView.Adapter<UsersListAdapter.MyViewHolder>() {
    private val mDataSet: List<Usuario> = myDataSet

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tvName)
        var tvProfession: TextView = view.findViewById(R.id.tvProfession)
        var imgView: ImageView = view.findViewById(R.id.imgView)
        var tvPrice: TextView = view.findViewById(R.id.tvPrice)
        var tvPhone: TextView = view.findViewById(R.id.tvPhone)
        var tvDescription: TextView = view.findViewById(R.id.tvDescription)

        fun bind(image: String) {
            imgView.fromUrl(image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // create a new view
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_component, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        var text = mDataSet[position].nombre
        holder.tvName.text = text
        text = "$${mDataSet[position].precioEnDolares.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)} USD por hora"
        holder.tvPrice.text = text
        text = mDataSet[position].profesion
        holder.tvProfession.text = text
        text = mDataSet[position].telefono
        holder.tvPhone.text = text
        text = mDataSet[position].descripcionProfesion
        holder.tvDescription.text = text

        val imageURL = mDataSet[position].avatar
        holder.bind(imageURL)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

}