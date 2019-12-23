package id.revan.topstory.di.modules

import dagger.Module
import dagger.Provides
import id.revan.topstory.ui.base.BaseViewModelFactory
import id.revan.topstory.ui.storydetail.StoryDetailViewModel
import id.revan.topstory.ui.topstory.MainViewModel
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun provideStoryDetailViewModelFactory(viewModel: StoryDetailViewModel): BaseViewModelFactory<StoryDetailViewModel> {
        return BaseViewModelFactory { viewModel }
    }

    @Provides
    @Singleton
    fun provideMainViewModelFactory(viewModel: MainViewModel): BaseViewModelFactory<MainViewModel> {
        return BaseViewModelFactory { viewModel }
    }
}