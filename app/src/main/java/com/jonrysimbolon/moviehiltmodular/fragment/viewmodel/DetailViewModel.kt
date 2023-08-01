package com.jonrysimbolon.moviehiltmodular.fragment.viewmodel

import com.jonrysimbolon.core.base.BaseViewModel
import com.jonrysimbolon.core.utils.Event

class DetailViewModel : BaseViewModel() {
    fun showToast(
        content: Int
    ){
        _snackbarError.value = Event(content)
    }
}