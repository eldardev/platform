package com.temel.platform.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.temel.platform.R
import com.temel.platform.extension.renderViewState
import com.temel.platform.ui.CommonActivity

class MainActivity : CommonActivity() {

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        viewModel.test()

        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.sendIntent(MainIntent.ChangeText(i.toString()))
            i++
        }
    }

    override fun onStart() {
        super.onStart()

        renderViewState(viewModel.state, {
            findViewById<TextView>(R.id.text_hello_world).text = it?.text
        })
    }
}