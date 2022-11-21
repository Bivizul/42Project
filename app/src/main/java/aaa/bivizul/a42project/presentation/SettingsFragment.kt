package aaa.bivizul.a42project.presentation

import aaa.bivizul.a42project.R
import aaa.bivizul.a42project.domain.di.appComponent
import android.content.Context
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

}