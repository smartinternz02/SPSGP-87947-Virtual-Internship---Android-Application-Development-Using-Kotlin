package com.example.vicinity.ui.detailsPlace

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vicinity.MyApplication
import com.example.vicinity.data.Repository
import com.example.vicinity.models.Directions
import com.example.vicinity.models.Places
import com.example.vicinity.util.Features
import com.example.vicinity.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsPlaceViewModel @Inject constructor(private val repository: Repository, application: Application): AndroidViewModel(application) ,
    LifecycleObserver {

    private var features: Features = Features()

    var directions: MutableLiveData<NetworkResult<Directions?>> = MutableLiveData()

    val Isfavorites: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>(false)

    fun getDirections(sensor: Boolean = false, mode: String = "DRIVING", alternatives: Boolean = true, key: String ="", origin: String = "", destination: String = "") = viewModelScope.launch {
        getDirectionsSafeCall(sensor, mode, alternatives, key, origin, destination)
    }

    private suspend fun getDirectionsSafeCall(sensor: Boolean = false, mode: String = "DRIVING", alternatives: Boolean = true, key: String ="", origin: String = "", destination: String = "") {
        directions.value = NetworkResult.Loading()
        if (features.isConnected(MyApplication.context!!)){
            try {

                val responsePlaces = repository.remote.getPlaceDirections(sensor, mode, alternatives, key, origin, destination)

                if(responsePlaces.isSuccessful){

                    directions.value = NetworkResult.Success(responsePlaces.body())

                }else{

                    directions.value = NetworkResult.Error("Error.")

                }

            }catch (e: Exception){

                directions.value = NetworkResult.Error("Error in the request.")
                Log.i("DetailsPlaceActivity", e.message.toString())
                Log.i("DetailsPlaceActivity", e.localizedMessage.toString())


            }
        }else{

            directions.value = NetworkResult.Error("No internet.")

        }
    }

    fun insertFavorites(places: Places) = viewModelScope.launch {
        insertFavoritesSafeCall(places)
    }

    private suspend fun insertFavoritesSafeCall(places: Places) {
        repository.local.insertFavorite(places)
    }

    fun deleteFavoritesByPlaceId(placeId: String) = viewModelScope.launch {
        deleteFavoritesByPlaceIdSafeCall(placeId)
    }

    private suspend fun deleteFavoritesByPlaceIdSafeCall(placeId: String) {
        repository.local.deleteFavoriteByPlaceId(placeId)
    }

    fun getFavorites(placeId: String) = viewModelScope.launch {
        getFavoritesSafeCall(placeId)
    }

    private suspend fun getFavoritesSafeCall(placeId: String) {
        val response: Places? = repository.local.getFavoriteByPlaceId(placeId)
        Isfavorites.value = response != null
    }


}