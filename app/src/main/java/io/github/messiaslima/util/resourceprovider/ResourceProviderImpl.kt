package io.github.messiaslima.util.resourceprovider

import android.content.Context
import java.lang.ref.WeakReference

class ResourceProviderImpl(contextReference: WeakReference<Context>) : ResourceProvider {
    private val context = contextReference.get()
        ?: throw IllegalArgumentException("there is no context reference")

    override fun getString(stringRes: Int, vararg arguments: Any): String {
        return context.getString(stringRes, *arguments)
    }
}