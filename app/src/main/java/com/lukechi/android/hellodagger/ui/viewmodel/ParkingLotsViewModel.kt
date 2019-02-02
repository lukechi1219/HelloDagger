package com.lukechi.android.hellodagger.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukechi.android.hellodagger.data.source.ParkingLotsRepository
import com.lukechi.android.opendata.database.entity.ParkingLot
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class ParkingLotsViewModel @Inject constructor(
    private val parkingLotsRepository: ParkingLotsRepository
) : ViewModel() {

    lateinit var disposableObserver: DisposableObserver<List<ParkingLot>>
    var parkingLotsResult: MutableLiveData<List<ParkingLot>> = MutableLiveData()
    var parkingLotsError: MutableLiveData<String> = MutableLiveData()
    var parkingLotsLoader: MutableLiveData<Boolean> = MutableLiveData()

    fun disposeElements() {
        if (null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
    }

    fun parkingLotsResult(): LiveData<List<ParkingLot>> {
        return parkingLotsResult
    }

    fun parkingLotsError(): LiveData<String> {
        return parkingLotsError
    }

    fun loadParkingLots(limit: Int, offset: Int) {

        disposableObserver = object : DisposableObserver<List<ParkingLot>>() {
            override fun onComplete() {
            }

            override fun onNext(parkingLots: List<ParkingLot>) {
                parkingLotsResult.postValue(parkingLots)
                parkingLotsLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                parkingLotsError.postValue(e.message)
                parkingLotsLoader.postValue(false)
            }
        }

        parkingLotsRepository.getParkingLots(limit, offset)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, MILLISECONDS)
            .subscribe(disposableObserver)
    }
}