package com.example.vicinity.ui.favoritesPlaces

import android.app.Application
import androidx.lifecycle.*
import com.example.vicinity.data.Repository
import com.example.vicinity.models.Places
import com.example.vicinity.util.Features
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesPlacesViewModel @Inject constructor(private val repository: Repository, application: Application): AndroidViewModel(application) ,
    LifecycleObserver {

    private var features: Features = Features()

    val favorites: LiveData<List<Places>?> = repository.local.getFavorites().asLiveData()

}