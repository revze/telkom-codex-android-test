package id.revan.topstory.di

object Injector {
    fun getApp() = DaggerAppComponent.builder().build()
}