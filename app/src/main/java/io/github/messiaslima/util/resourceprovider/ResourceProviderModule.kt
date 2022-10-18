package io.github.messiaslima.util.resourceprovider

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.lang.ref.WeakReference

@Module
@InstallIn(SingletonComponent::class)
object ResourceProviderModule {
    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context) : ResourceProvider {
        return ResourceProviderImpl(WeakReference(context))
    }
}