package id.revan.topstory.di.modules

import dagger.Module
import dagger.Provides
import id.revan.topstory.data.repository.StoryRepository
import id.revan.topstory.data.repository.StoryRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideStoryRepository(repository: StoryRepositoryImpl): StoryRepository {
        return repository
    }
}