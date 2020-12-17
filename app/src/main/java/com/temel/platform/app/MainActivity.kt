package com.temel.platform.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.temel.platform.R
import com.temel.mvi.extension.renderViewState
import com.temel.mvi.ui.CommonActivity

class MainActivity : com.temel.mvi.ui.CommonActivity() {

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    private var i = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        findViewById<Button>(R.id.button).setOnClickListener {
            if (i % 2 == 0) {
                viewModel.sendIntent(MainIntent.ChangeText(i.toString()))
//                viewModel.sendIntent(MainIntent.SetIsLoading(false))
            }else {
                viewModel.sendIntent(MainIntent.SetIsLoading(true))
            }
            i++
        }
    }

    override fun onStart() {
        super.onStart()

        renderViewState(viewModel.state, {
            findViewById<TextView>(R.id.text_hello_world).text = it?.text
            findViewById<TextView>(R.id.text_is_loading).text = it?.isLoading.toString()
        })
    }
}