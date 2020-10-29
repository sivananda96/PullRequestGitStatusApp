package com.example.pullrequestgitstatusapp.di.component

import com.example.pullrequestgitstatusapp.di.PerActivity
import com.example.pullrequestgitstatusapp.di.module.ActivityModule
import com.example.pullrequestgitstatusapp.ui.main.prlist.PRListFragment
import com.example.pullrequestgitstatusapp.ui.main.prlist.MainActivity

import dagger.Component


@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: PRListFragment)

}
