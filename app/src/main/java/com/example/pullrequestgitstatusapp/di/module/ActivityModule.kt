package com.example.pullrequestgitstatusapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

import com.example.pullrequestgitstatusapp.di.ActivityContext
import com.example.pullrequestgitstatusapp.utils.rx.AppSchedulerProvider
import com.example.pullrequestgitstatusapp.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    internal fun provideContext(): Context {
        return activity
    }

    @Provides
    internal fun provideActivity(): AppCompatActivity {
        return activity
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

}
