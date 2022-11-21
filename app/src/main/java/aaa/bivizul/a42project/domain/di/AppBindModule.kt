package aaa.bivizul.a42project.domain.di

import aaa.bivizul.a42project.data.repository.PlaaviRepositoryImpl
import aaa.bivizul.a42project.data.repository.PlaavisRepositoryImpl
import aaa.bivizul.a42project.domain.repository.PlaaviRepository
import aaa.bivizul.a42project.domain.repository.PlaavisRepository
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Suppress("FunctionName")
    @Binds
    fun bindPlaaviRepositoryImpl_to_PlaaviRepository(
        plaaviRepositoryImpl: PlaaviRepositoryImpl
    ): PlaaviRepository

    @Suppress("FunctionName")
    @Binds
    fun bindPlaavisRepositoryImpl_to_PlaavisRepository(
        plaavisRepositoryImpl: PlaavisRepositoryImpl
    ): PlaavisRepository

}