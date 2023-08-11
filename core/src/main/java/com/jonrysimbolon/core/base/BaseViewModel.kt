package com.jonrysimbolon.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.jonrysimbolon.core.navigation.NavigationCommand
import com.jonrysimbolon.core.utils.Event

abstract class BaseViewModel: ViewModel() {

    protected val _snackbarError = MutableLiveData<Event<String>>()
    val snackBarError: LiveData<Event<String>> get() = _snackbarError

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

     fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(directions))
    }
}