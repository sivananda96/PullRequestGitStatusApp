package com.example.pullrequestgitstatusapp.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pullrequestgitstatusapp.ui.main.prlist.PRListViewModel
import com.example.pullrequestgitstatusapp.data.source.repository.AppDataSource
import com.example.pullrequestgitstatusapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * View Model Factory Maker for [ViewModel] that require AppDataSource
 */
class AppViewModelFactory @Inject
constructor(private val dataSource: AppDataSource,
            private val schedulerProvider: SchedulerProvider,
            private val compositeDisposable: CompositeDisposable) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PRListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PRListViewModel(dataSource, schedulerProvider, compositeDisposable) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}