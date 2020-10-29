package com.example.pullrequestgitstatusapp.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Helper class to provide [Schedulers] for RxJava related operations
 */

class AppSchedulerProvider : SchedulerProvider {

    override val ui: Scheduler = AndroidSchedulers.mainThread()

    override val computation: Scheduler = Schedulers.computation()

    override val io: Scheduler = Schedulers.io()

    override val trampoline: Scheduler = Schedulers.trampoline()

}
