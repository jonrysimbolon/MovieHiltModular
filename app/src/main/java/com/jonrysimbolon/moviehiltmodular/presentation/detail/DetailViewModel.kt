package com.jonrysimbolon.moviehiltmodular.presentation.detail

import com.jonrysimbolon.core.base.BaseViewModel
import com.jonrysimbolon.core.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {
    fun showToast(
        content: String
    ){
        _snackbarError.value = Event(content)
    }
}