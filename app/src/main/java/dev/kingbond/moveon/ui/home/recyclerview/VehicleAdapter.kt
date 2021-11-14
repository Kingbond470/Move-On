package dev.kingbond.moveon.ui.home.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.item_layout_vehicles.view.*

class VehicleAdapter(
    private var vehicleList: ArrayList<Vehicles>,
    private val clickListener: VehicleClickListener
) :
    RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    val bool = BooleanArray(vehicleList.size)
    private val itemViewList = ArrayList<View>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_vehicles, parent, false)
        itemViewList.add(view)
        return VehicleViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicleList[position]
        holder.setData(vehicle)
    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }

    inner class VehicleViewHolder(
        private val view: View,
        private val clickListener: VehicleClickListener
    ) : RecyclerView.ViewHolder(view) {
        internal fun setData(vehicle: Vehicles) {
            var labourCount = 0
            view.apply {
                vehicleImage.setImageResource(vehicle.vehicleImage)
                vehicleName.text = vehicle.vehicleName
                vehicleTime.text = vehicle.time
                vehiclePrice.text = vehicle.price
                tvBookVehicle.text = "Book ${vehicle.vehicleName}"

                labourAdd.setOnClickListener {
                    labourCount++;
                    tvLabour.text = labourCount.toString()
                }

                labourMinus.setOnClickListener {
                    if (labourCount > 0) {
                        labourCount--
                    }
                    tvLabour.text = labourCount.toString()
                }

                vehicleInfo.setOnClickListener {
                    clickListener.onVehicleInfoClick(vehicle, adapterPosition)
                }

                labourInfo.setOnClickListener {
                    clickListener.onAddLabourClick(vehicle, adapterPosition, labourCount)
                }

                tvBookVehicle.setOnClickListener {
                    clickListener.onBookVehicleClick(vehicle, adapterPosition)
                }

                rlPaymentMethods.setOnClickListener {
                    clickListener.onPaymentMethodsClick(vehicle,adapterPosition)
                }



                rlMainLayout.setOnClickListener {
                    if (bool[adapterPosition]) {
                        labourLayout.visibility = View.GONE
                        mainLayout.setBackgroundColor(Color.parseColor("#141414"))
                        bool[adapterPosition] = false
                    } else {
                        check()
                        labourLayout.visibility = View.VISIBLE
                        mainLayout.setBackgroundColor(Color.parseColor("#000000"))
                        bool[adapterPosition] = true
                    }
                    clickListener.onVehicleClick(vehicle, adapterPosition)
                }
            }
        }

        private fun check() {
            for (i in 0 until vehicleList.size) {
                if (bool[i] && i != adapterPosition) {
                    itemViewList[i].mainLayout.setBackgroundColor(Color.parseColor("#373737"))
                    itemViewList[i].labourLayout.visibility = View.GONE
                }
            }
        }

    }
}

interface VehicleClickListener {
    fun onVehicleClick(vehicle: Vehicles, position: Int)
    fun onVehicleInfoClick(vehicle: Vehicles, position: Int)
    fun onAddLabourClick(vehicle: Vehicles, position: Int, labourCount: Int)
    fun onBookVehicleClick(vehicle: Vehicles, position: Int)
    fun onPaymentMethodsClick(vehicle: Vehicles, position: Int)

}
