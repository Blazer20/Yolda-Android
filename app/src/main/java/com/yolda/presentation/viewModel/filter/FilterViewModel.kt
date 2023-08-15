package com.yolda.presentation.viewModel.filter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yolda.common.EMPTY_STRING
import com.yolda.common.emptyCommonSubTypesLocal
import com.yolda.common.emptyElectroSubTypesLocal
import com.yolda.data.local.CommonSubTypesLocal
import com.yolda.data.local.ElectroSubTypesLocal
import com.yolda.data.local.FilterTypesLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FilterUiState(
    val fuelTypes: FilterTypesLocal = FilterTypesLocal(),
    val currentElectroSubType: ElectroSubTypesLocal? = emptyElectroSubTypesLocal,
    val subTypes: List<CommonSubTypesLocal> = emptyList(),
    val sortPriceBy: List<CommonSubTypesLocal> = emptyList(),
    val currentSubType: CommonSubTypesLocal = emptyCommonSubTypesLocal,
    val isNoneSelected: Boolean = false,
    val isAscendingSelected: Boolean = false,
    val isDescendingSelected: Boolean = false,
    val isAzsNoneSelected: Boolean = false,
    val isLastUpdatedSelected: Boolean = false,
    val isByDistanceSelected: Boolean = false,
    val isByPopularitySelected: Boolean = false,
    val selectedFuelType: String = EMPTY_STRING,
    val electroName: String = "Electro",
    val electroDefaultSubTypeName: String = "GB/T DC",
)

sealed class FilterNavEvent {
    object ToBackNavigation : FilterNavEvent()
}

enum class FilterTypesState(
    val fuelName: String
) {
    FUEL(fuelName = "Fuel"),
    GASOLINE(fuelName = "Gasoline"),
    DIESEL(fuelName = "Diesel"),
}

enum class ElectroFilterSubTypesState(
    val electroSubTypeName: String
) {
    GB_T_AC(electroSubTypeName = "GB/T AC"),
    GB_T_DC(electroSubTypeName = "GB/T DC"),
    CCS_COMBO_1(electroSubTypeName = "CCS Combo 1"),
    CCS_COMBO_2(electroSubTypeName = "CCS Combo 2"),
    TYPE_1_J1772(electroSubTypeName = "Type 1 (J1772)"),
    TYPE_2_3_PHASE(electroSubTypeName = "Type 2 (3 phase)"),
    CHADEMO(electroSubTypeName = "CHAdeMO")
}

@HiltViewModel
class FilterViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(FilterUiState())
    val uiState: StateFlow<FilterUiState> = _uiState.asStateFlow()

    private val _navEvent = MutableSharedFlow<FilterNavEvent>()
    val navEvent: SharedFlow<FilterNavEvent> get() = _navEvent.asSharedFlow()

    fun saveNavArguments(selectedFuelType: String) {
        _uiState.update { it.copy(selectedFuelType = selectedFuelType) }
    }

    fun onNavigationClicked() {
        viewModelScope.launch { _navEvent.emit(FilterNavEvent.ToBackNavigation) }
    }

    fun onActionClick() {
        viewModelScope.launch { }
    }

    fun onElectroSubTypeClick(electroSubType: ElectroSubTypesLocal) {
        _uiState.update { it.copy(currentElectroSubType = electroSubType) }
    }

    fun onSubTypeClick(subType: CommonSubTypesLocal) {
        val updatedsubTypes = _uiState.value.subTypes.toMutableList()
        val index = updatedsubTypes.indexOfFirst { it.name == subType.name }
        val newValue = updatedsubTypes[index].selected.not()
        updatedsubTypes[index] =
            updatedsubTypes[index].copy(selected = newValue)

        _uiState.update {
            it.copy(
                subTypes = updatedsubTypes
            )
        }
    }

    fun onSortSubTypeClick(subType: CommonSubTypesLocal) {
        val updatedsubTypes = _uiState.value.sortPriceBy.toMutableList()
        val index = updatedsubTypes.indexOfFirst { it.name == subType.name }
        val newValue = updatedsubTypes[index].selected.not()
        updatedsubTypes[index] =
            updatedsubTypes[index].copy(selected = newValue)

        _uiState.update {
            it.copy(
                sortPriceBy = updatedsubTypes
            )
        }
    }

    fun onSortByOptionsClick(
        isNoneSelected: Boolean,
        isAscendingSelected: Boolean,
        isDescendingSelected: Boolean
    ) {
        _uiState.update {
            it.copy(
                isNoneSelected = isNoneSelected,
                isAscendingSelected = isAscendingSelected,
                isDescendingSelected = isDescendingSelected
            )
        }
    }

    fun onAZSOptionsClick(
        isAzsNoneSelected: Boolean,
        isLastUpdatedSelected: Boolean,
        isByDistanceSelected: Boolean,
        isByPopularitySelected: Boolean
    ) {
        _uiState.update {
            it.copy(
                isAzsNoneSelected = isAzsNoneSelected,
                isLastUpdatedSelected = isLastUpdatedSelected,
                isByDistanceSelected = isByDistanceSelected,
                isByPopularitySelected = isByPopularitySelected,
            )
        }
    }


    fun defineFilerList() {
        when (_uiState.value.selectedFuelType) {
            FilterTypesState.FUEL.fuelName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.fuel.subTypes,
                        sortPriceBy = _uiState.value.fuelTypes.fuel.subTypes
                    )
                }
            }

            FilterTypesState.GASOLINE.fuelName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.gasoline.subTypes,
                        sortPriceBy = _uiState.value.fuelTypes.gasoline.subTypes
                    )
                }
            }

            FilterTypesState.DIESEL.fuelName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.diesel.subTypes,
                        sortPriceBy = _uiState.value.fuelTypes.diesel.subTypes
                    )
                }
            }
        }
    }

    fun defineElectroFilterList() {

        when (_uiState.value.currentElectroSubType?.name) {
            ElectroFilterSubTypesState.GB_T_AC.electroSubTypeName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList(),
                        sortPriceBy = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList()
                    )
                }
            }

            ElectroFilterSubTypesState.GB_T_DC.electroSubTypeName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList(),
                        sortPriceBy = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList()
                    )
                }
            }

            ElectroFilterSubTypesState.CCS_COMBO_1.electroSubTypeName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList(),
                        sortPriceBy = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList()
                    )
                }
            }

            ElectroFilterSubTypesState.CCS_COMBO_2.electroSubTypeName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList(),
                        sortPriceBy = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList()
                    )
                }
            }

            ElectroFilterSubTypesState.TYPE_1_J1772.electroSubTypeName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList(),
                        sortPriceBy = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList()
                    )
                }
            }

            ElectroFilterSubTypesState.TYPE_2_3_PHASE.electroSubTypeName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList(),
                        sortPriceBy = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList()
                    )
                }
            }

            ElectroFilterSubTypesState.CHADEMO.electroSubTypeName -> {
                _uiState.update {
                    it.copy(
                        subTypes = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList(),
                        sortPriceBy = _uiState.value.fuelTypes.electro.electroSubTypes
                            .find { it.name == _uiState.value.currentElectroSubType?.name }
                            ?.electroSubTypes ?: emptyList()
                    )
                }
            }
        }
    }

    fun setElectroDefaultType() {
        val electroDefaultName = _uiState.value.electroDefaultSubTypeName

        _uiState.update {
            it.copy(
                currentElectroSubType = _uiState.value.fuelTypes.electro.electroSubTypes.find { it.name == electroDefaultName },
                sortPriceBy = _uiState.value.fuelTypes.electro.electroSubTypes.find { it.name == electroDefaultName }?.electroSubTypes
                    ?: emptyList()
            )
        }
    }

}