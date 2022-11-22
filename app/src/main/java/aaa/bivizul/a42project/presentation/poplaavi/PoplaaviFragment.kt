package aaa.bivizul.a42project.presentation.poplaavi

import aaa.bivizul.a42project.R
import aaa.bivizul.a42project.databinding.FragmentPoplaaviBinding
import aaa.bivizul.a42project.domain.di.appComponent
import aaa.bivizul.a42project.domain.model.Plaavi
import aaa.bivizul.a42project.domain.model.Plaavivar
import aaa.bivizul.a42project.domain.util.*
import aaa.bivizul.a42project.presentation.PlaaviFragment.Companion.PLAAVIKOR
import aaa.bivizul.a42project.presentation.poplaavi.PoplaaviViewModel.Companion.ERROR_MESSAGE
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.appsflyer.AppsFlyerLib
import com.google.firebase.analytics.FirebaseAnalytics
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class PoplaaviFragment : Fragment(R.layout.fragment_poplaavi) {

    @Inject
    lateinit var factory: PoplaaviViewModelFactory.Factory
    private val binding by viewBinding(FragmentPoplaaviBinding::bind)
    private var progressStatus: Int = 0

    val viewModel: PoplaaviViewModel by viewModels {
        factory.create(
            Plaavi(
                getPlaavimm(),
                getPlaavisim(requireContext()),
                getPlaaviid(requireContext()),
                getPlaavil(),
                getPlaavit()
            )
        )
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.plaavig.observe(viewLifecycleOwner) { plaavig ->
            CoroutineScope(Dispatchers.Main).launch {
                while (progressStatus < 100) {
                    progressStatus += 1
                    binding.loadProgressBar.setProgress(progressStatus)
                    binding.tvProgress.setText("$progressStatus %")
                    try {
                        delay(25)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                if (plaavig.plaavig == ERROR_MESSAGE) {
                    getPlaavidlg(requireContext(), requireActivity())
                } else if (plaavig.plaavig == Plaavivar.PANO.pa) {
                    findNavController().navigate(R.id.action_poplaaviFragment_to_mainFragment)
                } else if (plaavig.plaavig == Plaavivar.PANP.pa) {
                    sigPlaavioff()
                    findNavController().navigate(R.id.action_poplaaviFragment_to_mainFragment)
                } else {
                    val plaavivid: String = getPlaavifit(plaavig.plaavig)
                    OneSignal.setExternalUserId(plaavivid)
                    AppsFlyerLib.getInstance().setCustomerUserId(plaavivid)
                    FirebaseAnalytics.getInstance(requireContext()).setUserId(plaavivid)
                    findNavController().navigate(
                        R.id.action_poplaaviFragment_to_plaaviFragment,
                        bundleOf(PLAAVIKOR to plaavig.plaavig)
                    )
                }
            }
        }
    }
}