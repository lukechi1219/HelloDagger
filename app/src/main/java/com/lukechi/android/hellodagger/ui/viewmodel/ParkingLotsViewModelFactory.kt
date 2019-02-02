package com.lukechi.android.hellodagger.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ParkingLotsViewModelFactory @Inject constructor(
    private val parkingLotsViewModel: ParkingLotsViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        // Unnecessary non-null assertion (!!) on a non-null receiver of type Class<ParkingLotsViewModel> ??
        if (modelClass.isAssignableFrom(ParkingLotsViewModel::class.java!!)) return parkingLotsViewModel as T
        throw IllegalArgumentException("Unknown class name")
    }
}