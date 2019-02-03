package com.lukechi.android.hellodagger.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lukechi.android.hellodagger.R
import com.lukechi.android.opendata.database.entity.ParkingLot

class ParkingLotsAdapter(
    parkingLots: List<ParkingLot>?
) : RecyclerView.Adapter<ParkingLotsAdapter.ParkingLotsViewHolder>() {

    private var parkingLotsList = ArrayList<ParkingLot>()

    init {
        this.parkingLotsList = parkingLots as ArrayList<ParkingLot>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingLotsViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.parking_lot_list_item, parent, false)

        return ParkingLotsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return parkingLotsList.size
    }

    override fun onBindViewHolder(holder: ParkingLotsViewHolder, position: Int) {
        val parkingLot = parkingLotsList[position]
        holder.parkingLotItem(parkingLot)
    }

    /*
     */
    fun addCryptocurrencies(parkingLots: List<ParkingLot>) {
        val initPosition = parkingLotsList.size
        parkingLotsList.addAll(parkingLots)
        notifyItemRangeInserted(initPosition, parkingLotsList.size)
    }

    /*
     */
    class ParkingLotsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var parkingLotId = itemView.findViewById<TextView>(R.id.parking_lot_id)

        fun parkingLotItem(parkingLot: ParkingLot) {
            parkingLotId.text = parkingLot.id().toString()
        }
    }
}