package com.use.dagger.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelProviderFactory @Inject constructor(private var creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    private val TAG = ViewModelProviderFactory::class.simpleName

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        var creator = creators.get(modelClass)
        if (creator == null) // if the viewmodel has not been created
        {
            // loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for ((k, v) in creators.entries) {

                // if it's allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(k)) {
                    creator = v
                    break
                }
            }
        }
        // if this is not one of the allowed keys, throw exception
        if (creator == null) {
            throw IllegalArgumentException("unknown model class $modelClass")
        }

        // return the Provider
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}