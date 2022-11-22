package aaa.bivizul.a42project.presentation

import aaa.bivizul.a42project.R
import aaa.bivizul.a42project.databinding.ActivityMainBinding
import aaa.bivizul.a42project.domain.di.appComponent
import aaa.bivizul.a42project.domain.util.checkPlaavinet
import aaa.bivizul.a42project.domain.util.getPlaaviScrOrnt
import aaa.bivizul.a42project.domain.util.getPlaavidlg
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        if (!checkPlaavinet(this)) {
            getPlaavidlg(this, this)
        } else {
            setContentView(R.layout.activity_main)
            val image = getPlaaviScrOrnt(resources)
            binding.imageViewMainActivity.load(image)
        }
    }
}