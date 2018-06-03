package com.devmatic.fruits

import android.app.Application
import com.devmatic.fruits.di.components.ApplicationComponent
import com.devmatic.fruits.di.components.DaggerApplicationComponent
import com.devmatic.fruits.di.modules.AndroidModule
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration

class FruitApp:Application(){

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
        applicationComponent = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
        applicationComponent.inject(this)
    }

    companion object {
        @JvmStatic lateinit var applicationComponent: ApplicationComponent
    }
}