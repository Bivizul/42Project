package aaa.bivizul.a42project.domain.di

import aaa.bivizul.a42project.presentation.MainActivity
import aaa.bivizul.a42project.presentation.MainFragment
import aaa.bivizul.a42project.presentation.PlaaviFragment
import aaa.bivizul.a42project.presentation.SettingsFragment
import aaa.bivizul.a42project.presentation.plaavislist.PlaavisListFragment
import aaa.bivizul.a42project.presentation.poplaavi.PoplaaviFragment
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