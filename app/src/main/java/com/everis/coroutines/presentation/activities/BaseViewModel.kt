package com.everis.coroutines.presentation.activities

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(BaseViewModel::class.java.simpleName, throwable.toString())
    }

    private val job: Job
        //        get() = Job()
        get() = SupervisorJob()

    protected var scope = CoroutineScope(context = job + dispatcher + exceptionHandler)
        get() {
            if (!field.isActive) {
                field = CoroutineScope(context = job + dispatcher + exceptionHandler)
            }
            return field
        }

    override fun onCleared() {
        scope.cancel()
    }

}