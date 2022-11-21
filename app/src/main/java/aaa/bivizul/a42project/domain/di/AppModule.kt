package aaa.bivizul.a42project.domain.di

import dagger.Module

@Module(includes = [NetworkModule::class, AppBindModule::class])
class AppModule