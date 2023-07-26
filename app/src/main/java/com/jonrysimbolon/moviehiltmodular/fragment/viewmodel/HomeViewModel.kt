package com.jonrysimbolon.moviehiltmodular.fragment.viewmodel

import com.jonrysimbolon.core.base.BaseViewModel
import com.jonrysimbolon.moviehiltmodular.R
import com.stockbit.common.utils.Event

class HomeViewModel : BaseViewModel() {

    fun showToast(
        content: Int
    ){
        _snackbarError.value = Event(content)
    }
}