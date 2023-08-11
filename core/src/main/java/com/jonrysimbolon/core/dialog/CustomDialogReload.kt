package com.jonrysimbolon.core.dialog

interface CustomDialogReload: CustomDialog {

    var reloadAction: (() -> Unit)?
    fun setDescription(description: String)
    fun setReloadClickListener(function: (() -> Unit))
}