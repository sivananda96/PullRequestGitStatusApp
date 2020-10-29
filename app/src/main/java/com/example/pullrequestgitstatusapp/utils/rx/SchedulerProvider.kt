package com.example.pullrequestgitstatusapp.utils.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    val ui: Scheduler

    val computation: Scheduler

    val io: Scheduler

    val trampoline: Scheduler

}
