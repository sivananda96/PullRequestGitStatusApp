package com.example.pullrequestgitstatusapp.base


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pullrequestgitstatusapp.network.NetworkError
import com.example.pullrequestgitstatusapp.data.source.repository.AppDataSource
import com.example.pullrequestgitstatusapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel(protected val dataSource: AppDataSource,
                         protected val schedulerProvider: SchedulerProvider,
                         protected val compositeDisposable: CompositeDisposable) : ViewModel() {

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val errorMsg: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun isLoading() = isLoading

    fun getErrorMsg() = errorMsg

    fun handleApiError(throwable: Throwable?) {

        if (throwable == null) {
            errorMsg.value = "An Error Occurred"
            return
        }

        val networkError = NetworkError(throwable)
        errorMsg.value = networkError.appErrorMessage

    }

}