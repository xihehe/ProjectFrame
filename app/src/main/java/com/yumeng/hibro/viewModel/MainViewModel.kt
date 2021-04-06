package com.yumeng.hibro.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yumeng.libbaseProject.service.ResponseModel.TestResponse
import com.yumeng.libbaseProject.service.repository.NewsDetailRepository
import com.yumeng.libcommon.utils.LogUtils
import com.yumeng.libcommonview.viewmodel.BaseViewModel
import com.yumeng.libcore.IResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel :BaseViewModel(){
    data class MainUiModel(
        val showLoading: Boolean,
        val showError: String?,
        val showSuccess:List<TestResponse>?
    )
    private val _uiState = MutableLiveData<MainUiModel>()
    val uiState: LiveData<MainUiModel>
        get() = _uiState

    private fun emitUiState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess:List<TestResponse>?=null
    ) {
        val uiModel =
            MainUiModel(showLoading, showError, showSuccess)
        _uiState.value = uiModel
    }

    private val repository by lazy { NewsDetailRepository() }


    fun getTest() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                emitUiState(showLoading = true)
            }
            val result = repository.getArticles("0")
            withContext(Dispatchers.Main) {
                if (result is IResult.Success) {
                    val data = result.data
                    if (data?.records?.size == 0) {
                        emitUiState(showError = "123123")
                        return@withContext
                    }
                    emitUiState(
                        showSuccess = data?.records
                    )
                } else if (result is IResult.Error) {
                    LogUtils.e("test","result:${result.toString()}")
                    emitUiState(showError = result.exception.message)
                }
            }
        }
    }

}