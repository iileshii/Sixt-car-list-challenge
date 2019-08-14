package mobi.jedi.example.sixtcarlist.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_car.view.*
import mobi.jedi.example.sixtcarlist.R
import mobi.jedi.example.sixtcarlist.domain.Car

internal class CarListAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {

    private val carList = mutableListOf<Car>()

    fun updateData(newData: List<Car>) {
        carList.clear()
        carList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_car, parent, false)
        return CarViewHolder(listener, view)
    }

    override fun getItemCount() = carList.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.update(carList[position])
    }

    class CarViewHolder(listener: (String) -> Unit, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private var currentItemId: String? = null

        init {
            itemView.setOnClickListener { currentItemId?.let { listener.invoke(it) } }
        }

        fun update(item: Car) {
            currentItemId = item.id
            itemView.make.text = item.make
            itemView.car_name.text = item.name
            itemView.model_name.text = item.modelName
            itemView.color.text = item.color
        }
    }
}
