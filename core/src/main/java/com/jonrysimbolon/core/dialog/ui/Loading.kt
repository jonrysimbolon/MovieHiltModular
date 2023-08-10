package com.jonrysimbolon.core.dialog.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.jonrysimbolon.core.R
import com.jonrysimbolon.core.dialog.CustomDialog
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class Loading @Inject constructor(
    @ActivityContext var context: Context
) : CustomDialog {

    private val dialog = Dialog(context)
    private val constraintLayout = ConstraintLayout(context)
    private val inflater = LayoutInflater.from(context)

    init {
        init()
    }

    override fun init() {
        val dialogView = inflater.inflate(R.layout.dialog_loading, constraintLayout)
        dialog.setContentView(dialogView)
        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDimAmount(1f)
            setFlags(
                WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND
            )
        }
        dialog.setCancelable(false)
    }

    override fun show(show: Boolean) {
        dialog.let {
            if (show) {
                it.show()
            } else {
                it.dismiss()
            }
        }
    }
}