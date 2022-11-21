package aaa.bivizul.a42project.domain.di

import aaa.bivizul.a42project.presentation.*
import aaa.bivizul.a42project.presentation.poplaavi.PoplaaviFragment
import aaa.bivizul.a42project.presentation.plaavislist.PlaavisListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(poplaaviFragment: PoplaaviFragment)
    fun inject(plaaviFragment: PlaaviFragment)
    fun inject(plaavisListFragment: PlaavisListFragment)
    fun inject(settingsFragment: SettingsFragment)

}