package com.jonrysimbolon.moviehiltmodular.fragment.viewmodel

import com.jonrysimbolon.core.base.BaseViewModel
import com.jonrysimbolon.core.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    fun showToast(
        content: Int
    ){
        _snackbarError.value = Event(content)
    }
}