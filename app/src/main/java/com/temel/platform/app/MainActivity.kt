package com.temel.platform.app

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.temel.platform.R
import com.temel.platform.extension.renderViewState
import com.temel.platform.ui.CommonActivity

class MainActivity : CommonActivity() {

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        viewModel.test()

        viewModel.sendIntent(MainIntent.ChangeText("Hello world again"))
    }

    override fun onStart() {
        super.onStart()

        renderViewState(viewModel.state, {
            findViewById<TextView>(R.id.text_hello_world).text = it?.text
        })
    }
}