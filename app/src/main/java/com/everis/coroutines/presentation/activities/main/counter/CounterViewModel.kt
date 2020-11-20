package com.everis.coroutines.presentation.activities.main.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.everis.coroutines.presentation.activities.BaseViewModel
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicBoolean

class CounterViewModel(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel(dispatcher = dispatcher) {
    private var intCounterJob: Job? = null
    private var intCounter = 0
        set(value) {
            field = value
            _intCounterLD.postValue(field)
        }
    private val isIntCounterThrowExceptionEnabled = AtomicBoolean(false)

    private var doubleCounterJob: Job? = null
    private var doubleCounter = 0.0
        set(value) {
            field = value
            _doubleCounterLD.postValue(field)
        }
    private val isDoubleCounterThrowExceptionEnabled = AtomicBoolean(false)

    private val _intCounterLD = MutableLiveData(intCounter)
    val intCounterLD: LiveData<Int>
        get() = _intCounterLD

    private val _doubleCounterLD = MutableLiveData(doubleCounter)
    val doubleCounterLD: LiveData<Double>
        get() = _doubleCounterLD

    fun onStartClick() {
        stopIntCounter()
        startIntCounter()

        stopDoubleCounter()
        startDoubleCounter()
    }

    private fun startIntCounter() {
        intCounterJob = scope.launch {
            doIntCounterLogic()
        }
    }

    private suspend fun doIntCounterLogic() {
        delay(1000)
        intCounter ++
        if (isIntCounterThrowExceptionEnabled.compareAndSet(true, false)) {
            throw Exception("Int counter throws an exception")
        } else {
            doIntCounterLogic()
        }
    }

    private fun startDoubleCounter() {
        doubleCounterJob = scope.launch {
            doDoubleCounterLogic()
        }
    }

    private suspend fun doDoubleCounterLogic() {
        delay(100)
        doubleCounter += 0.1
        if (isDoubleCounterThrowExceptionEnabled.compareAndSet(true, false)) {
            throw Exception("Double counter throws an exception")
        } else {
            doDoubleCounterLogic()
        }
    }

    fun onStopClick() {
        scope.cancel()
    }

    fun onIntCounterStopClick() {
        stopIntCounter()
    }

    fun onDoubleCounterStopClick() {
        stopDoubleCounter()
    }

    private fun stopIntCounter() {
        intCounterJob?.cancel()
        intCounter = 0
    }

    private fun stopDoubleCounter() {
        doubleCounterJob?.cancel()
        doubleCounter = 0.0
    }

    fun onIntCounterThrowExceptionClick() {
        isIntCounterThrowExceptionEnabled.set(true)
    }

    fun onDoubleCounterThrowExceptionClick() {
        isDoubleCounterThrowExceptionEnabled.set(true)
    }
}