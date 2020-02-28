package com.assignment.weatherapp.di.component

import android.app.Application
import com.assignment.weatherapp.di.module.AppModule
import com.assignment.weatherapp.di.module.NetworkModule
import com.assignment.weatherapp.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface AppComponent {
    fun inject(app: MainActivity)


    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

}
