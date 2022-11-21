package aaa.bivizul.a42project

import aaa.bivizul.a42project.databinding.ActivityMainBinding
import aaa.bivizul.a42project.domain.util.checkPlaavinet
import aaa.bivizul.a42project.domain.util.getPlaaviScrOrnt
import aaa.bivizul.a42project.domain.util.getPlaavidlg
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load

class MainActivity : AppCompatActivity() {

  private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (checkPlaavinet(this)) {
            setContentView(R.layout.activity_main)
            val image = getPlaaviScrOrnt(resources)
            binding.imageViewMainActivity.load(image)
        } else {
            getPlaavidlg(this, this)
        }
    }
}