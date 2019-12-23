package id.revan.topstory.di

import dagger.Component
import id.revan.topstory.di.modules.ApiModule
import id.revan.topstory.di.modules.RepositoryModule
import id.revan.topstory.di.modules.ViewModelFactoryModule
import id.revan.topstory.ui.storydetail.StoryDetailActivity
import id.revan.topstory.ui.topstory.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class, ViewModelFactoryModule::class])
interface AppComponent {
    fun inject(activity: StoryDetailActivity)

    fun inject(activity: MainActivity)
}