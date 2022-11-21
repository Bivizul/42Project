@file:Suppress("DEPRECATION")

package aaa.bivizul.a42project.presentation

import aaa.bivizul.a42project.R
import aaa.bivizul.a42project.domain.di.appComponent
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class PlaaviFragment : Fragment() {

    private lateinit var plaaviwv: WebView

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_plaavi, container, false)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        plaaviwv = root.findViewById(R.id.plaaviwv)

        plaaviwv.webViewClient = WebViewClient()

        plaaviwv.webChromeClient = MyChromeClient()
        plaaviwv.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        plaaviwv.isScrollbarFadingEnabled = false

        setSettings()

        val url = arguments?.getString(PLAAVIKOR) ?: PLAAVIDOR

        if (savedInstanceState == null) {
            plaaviwv.post {
                kotlin.run { plaaviwv.loadUrl(url) }
            }
        }

        plaaviwv.canGoBack()
        plaaviwv.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.action == MotionEvent.ACTION_UP &&
                plaaviwv.canGoBack()
            ) {
                plaaviwv.goBack()
                return@OnKeyListener true
            }
            false
        })

        return root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setSettings() {
        val plaaviws = plaaviwv.settings
        plaaviws.javaScriptEnabled = true
        plaaviws.loadWithOverviewMode = true
        plaaviws.allowFileAccess = true
        plaaviws.domStorageEnabled = true
        plaaviws.builtInZoomControls = true
        plaaviws.displayZoomControls = false
        plaaviws.useWideViewPort = true
        plaaviws.setSupportZoom(true)
        plaaviws.userAgentString = plaaviws.userAgentString.replace("; wv", "")
    }

    var filePathCallback: ValueCallback<Array<Uri>>? = null
    private val REQUEST_CODE = 100

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        plaaviwv.saveState(outState)
    }

    inner class MyChromeClient : WebChromeClient() {

        override fun onShowFileChooser(
            view: WebView,
            filePath: ValueCallback<Array<Uri>>,
            fileChooserParams: FileChooserParams
        ): Boolean {
            filePathCallback = filePath
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
            startActivityForResult(intent, REQUEST_CODE)
            return true
        }


        private var plaaviCustomView: View? = null
        private var plaaviCustomViewCallback: CustomViewCallback? = null
        private var plaaviOriginalOrientation = 0
        private var plaaviOriginalSystemUiVisibility = 0

        override fun getDefaultVideoPoster(): Bitmap? {
            return if (plaaviCustomView == null) {
                null
            } else BitmapFactory.decodeResource(activity!!.applicationContext.resources, 2130837573)
        }

        override fun onHideCustomView() {
            (activity!!.window.decorView as FrameLayout).removeView(plaaviCustomView)
            plaaviCustomView = null
            activity!!.window.decorView.systemUiVisibility = plaaviOriginalSystemUiVisibility
            activity?.requestedOrientation = plaaviOriginalOrientation
            plaaviCustomViewCallback!!.onCustomViewHidden()
            plaaviCustomViewCallback = null
        }

        override fun onShowCustomView(
            paramView: View?,
            paramCustomViewCallback: CustomViewCallback?
        ) {
            if (plaaviCustomView != null) {
                onHideCustomView()
                return
            }
            plaaviCustomView = paramView
            plaaviOriginalSystemUiVisibility = activity!!.window.decorView.systemUiVisibility
            plaaviOriginalOrientation = activity?.requestedOrientation!!
            plaaviCustomViewCallback = paramCustomViewCallback
            (activity!!.window.decorView as FrameLayout).addView(
                plaaviCustomView,
                FrameLayout.LayoutParams(-1, -1)
            )
            activity!!.window.decorView.systemUiVisibility =
                3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == REQUEST_CODE) {
            filePathCallback!!.onReceiveValue(
                WebChromeClient.FileChooserParams.parseResult(
                    resultCode,
                    intent
                )
            )
            filePathCallback = null
        }
    }

    companion object {
        const val PLAAVIKOR = "plaavikor"
        const val PLAAVIDOR = "https://www.google.com/"
    }

}