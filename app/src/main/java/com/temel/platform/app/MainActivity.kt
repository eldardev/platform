package com.temel.platform.app

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import com.temel.platform.R
import com.temel.mvi.ui.activity.StateActivity

class MainActivity : StateActivity<MainState>() {

    override val viewModel by viewModels<MainViewModel> {
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
            viewModel.dispatch(MainAction.FetchFacts)
        }
    }

    override fun onNewState(state: MainState) {
        val progress = findViewById<ProgressBar>(R.id.progress_bar)
        val textView = findViewById<TextView>(R.id.text_hello_world)

        if(state.isLoading) {
            progress.visibility = VISIBLE
            textView.text = ""
        }else{
            progress.visibility = GONE
            textView.text = state.text
        }
    }
}