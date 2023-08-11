package com.jonrysimbolon.core.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.jonrysimbolon.core.utils.Event

fun Fragment.showSnackbar(snackbarText: String, timeLength: Int) {
    activity?.let { Snackbar.make(it.findViewById(android.R.id.content), snackbarText, timeLength).show() }
}

fun Fragment.setupSnackbar(lifecycleOwner: LifecycleOwner, snackbarEvent: LiveData<Event<String>>, timeLength: Int) {
    snackbarEvent.observe(lifecycleOwner) { event ->
        event.getContentIfNotHandled()?.let { data ->
            context?.let { showSnackbar(data, timeLength) }
        }
    }
}