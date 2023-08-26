package com.example.graduationproject.data.presestance

import android.app.Application
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.presentation.viewmodels.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        SharedObject.initContext(this)
        val viewModel = SharedViewModel()
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.getList()
            viewModel.flowForList.collectLatest {
                SharedObject.initList(it.currency_list)
                println("it $it")
            }
        }
    }
}