package aaa.bivizul.a42project.presentation

import aaa.bivizul.a42project.R
import aaa.bivizul.a42project.databinding.FragmentMainBinding
import aaa.bivizul.a42project.domain.di.appComponent
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonList.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_plaavisListFragment)
            }
            buttonSettings.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            }
        }

    }


}