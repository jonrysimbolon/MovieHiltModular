package com.jonrysimbolon.core.utils

import android.os.Bundle
import androidx.navigation.NavDirections

class CustomNavDirection(
    override val actionId: Int,
    override val arguments: Bundle
) : NavDirections