package com.yolda.presentation.viewModel.onBoarding

import androidx.lifecycle.ViewModel
import com.yolda.common.SnackBarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LastOnBoardingViewModel @Inject constructor(
    private val snackBarManager: SnackBarManager
) : ViewModel() {

}