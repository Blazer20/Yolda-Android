package com.yolda.presentation.viewModel.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yolda.common.SnackBarManager
import com.yolda.data.remote.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.yolda.R
import com.yolda.common.EMPTY_STRING
import com.yolda.data.remote.Ad
import com.yolda.data.remote.Station
import com.yolda.data.remote.StationBrand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

data class FuelStationUiState(
    val listOfUsers: List<User> = emptyList(),
    val listOfAds: List<Ad> = emptyList(),
    val listOfStationBrands: List<StationBrand> = emptyList(),
    val listOfFavorites: List<StationBrand> = emptyList(),
    val listOfCategories: List<Int> = emptyList(),
    val isCategoriesVisible: Boolean = true,
    val isLoading: Boolean = false,
    val selectedTypeName: String = EMPTY_STRING
)

sealed class FuelStationNavEvent {
    data class ToFueStationDetailsScreen(val stationData: Station) : FuelStationNavEvent()
    data class ToFilterScreen(val selectedTypeName: String) : FuelStationNavEvent()
    object ToAdDetailsScreen : FuelStationNavEvent()
}

@HiltViewModel
class FuelStationViewModel @Inject constructor(
    private val snackBarManager: SnackBarManager,
    private val fireStore: FirebaseFirestore
) : ViewModel() {

    private val _uiState = MutableStateFlow(FuelStationUiState())
    val uiState: StateFlow<FuelStationUiState> = _uiState.asStateFlow()

    private val _navEvent = MutableSharedFlow<FuelStationNavEvent>()
    val navEvent: SharedFlow<FuelStationNavEvent> get() = _navEvent.asSharedFlow()

    fun getAds() {
        viewModelScope.launch(Dispatchers.IO) {
            changeProgressBarVisibility()
            fireStore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

            fireStore
                .collection("ads")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    try {
                        if (querySnapshot != null) {
                            val adsList = mutableListOf<Ad>()

                            for (document in querySnapshot.documents) {
                                val ad = document.toObject(Ad::class.java)
                                ad?.let { adsList.add(it) }
                            }

                            _uiState.update {
                                it.copy(
                                    listOfAds = adsList.toList(),
                                    isLoading = false
                                )
                            }
                        } else {
                            snackBarManager.showMessage("No such document!")
                            changeProgressBarVisibility()
                        }
                    } catch (ex: Exception) {
                        Log.e("Error", "getAds: ${ex.message}")
                    }
                }
                .addOnFailureListener {
                    snackBarManager.showMessage(it.message.toString())
                    changeProgressBarVisibility()
                }
        }
    }

    fun getStationBrands() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val listOfStationBrands = fetchStationBrands()
                if (listOfStationBrands.isNotEmpty()) {
                    _uiState.update {
                        it.copy(
                            listOfStationBrands = listOfStationBrands,
                            isLoading = false
                        )
                    }
                }
            } catch (ex: Exception) {
                handleException(ex)
            }
        }
    }

    private suspend fun fetchStationBrands(): List<StationBrand> {
        val querySnapshot = fireStore.collection("station_brands").get().await()

        val listOfStationBrands = mutableListOf<StationBrand>()

        querySnapshot.forEach { stationBrandDocument ->
            val stationBrand = stationBrandDocument.toObject(StationBrand::class.java)
            val listOfStations = getStations(stationBrandDocument.id)
            listOfStationBrands.add(stationBrand.copy(stations = listOfStations))
        }

        return listOfStationBrands
    }

    private suspend fun getStations(brandId: String): List<Station> {
        val querySnapshot = fireStore.collection("station_brands")
            .document(brandId)
            .collection("stations")
            .get()
            .await()

        return querySnapshot.documents.mapNotNull { stationDocument ->
            stationDocument.toObject(Station::class.java)
        }
    }

    private fun handleException(ex: Exception) {
        snackBarManager.showMessage(ex.message.toString())
        changeProgressBarVisibility()
    }


    fun onFilterCLick() {
        viewModelScope.launch { _navEvent.emit(FuelStationNavEvent.ToFilterScreen(_uiState.value.selectedTypeName)) }
    }

    fun onNavigationClick() {
        _uiState.update { it.copy(isCategoriesVisible = true, selectedTypeName = EMPTY_STRING) }
    }

    fun onCategoriesItemClick(isVisible: Boolean, selectedTypeName: String) {
        _uiState.update {
            it.copy(
                isCategoriesVisible = isVisible,
                selectedTypeName = selectedTypeName
            )
        }
    }

    private fun changeProgressBarVisibility() {
        _uiState.update { it.copy(isLoading = !it.isLoading) }
    }

    fun fillCategoriesList() {
        _uiState.update {
            it.copy(
                listOfCategories = listOf(
                    R.string.fuel,
                    R.string.gasoline,
                    R.string.electro,
                    R.string.diesel
                )
            )
        }
    }
}