package com.everis.coroutines.presentation.activities.main.sum

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.everis.coroutines.presentation.activities.BaseViewModel
import kotlinx.coroutines.*

class SumViewModel(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel(dispatcher = dispatcher) {
    private var result = 0
        set(value) {
            field = value
            _resultLD.postValue(value.toString())
        }
    private var first = 0
    private var second = 0

    private val _resultLD = MutableLiveData(result.toString())
    val resultLD: LiveData<String>
        get() = _resultLD

    fun onFirstNumberChange(value: String) {
        first = try {
            value.toInt()
        } catch (e: Exception) {
            0
        }
    }

    fun onSecondNumberChange(value: String) {
        second = try {
            value.toInt()
        } catch (e: Exception) {
            0
        }
    }

    fun onCalculateClick() {
        scope.launch {
            val f = computeFirst()
            val s = computeSecond()
            result = f.await() + s.await()
        }
    }

    private suspend fun computeFirst() = scope.async {
        delay(2000)
        Log.d(SumViewModel::class.java.simpleName, "first=$first")
        first
    }

    private suspend fun computeSecond() = scope.async {
        delay(500)
        Log.d(SumViewModel::class.java.simpleName, "second=$second")
        second
    }

}