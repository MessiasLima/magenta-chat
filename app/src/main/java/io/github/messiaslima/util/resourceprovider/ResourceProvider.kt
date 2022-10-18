package io.github.messiaslima.util.resourceprovider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringRes: Int, vararg arguments: Any): String
}