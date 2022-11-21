package aaa.bivizul.a42project.presentation.plaavislist

import aaa.bivizul.a42project.R
import aaa.bivizul.a42project.databinding.FragmentPlaavisListBinding
import aaa.bivizul.a42project.domain.di.appComponent
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import javax.inject.Inject

class PlaavisListFragment : Fragment(R.layout.fragment_plaavis_list) {

    @Inject
    lateinit var factory: PlaavisViewModelFactory.Factory
    private val viewModel: PlaavisListViewModel by viewModels { factory.create() }
    private val binding by viewBinding(FragmentPlaavisListBinding::bind)
    private val plaavisListAdapter by lazy { PlaavisListAdapter() }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewPlaavis.adapter = plaavisListAdapter

        viewModel.plaavis.observe(viewLifecycleOwner) {
            Log.e("qwer", "observe : $it")
            plaavisListAdapter.submitList(it)
        }
    }

}