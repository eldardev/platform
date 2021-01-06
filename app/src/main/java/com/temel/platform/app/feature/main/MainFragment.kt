package com.temel.platform.app.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.temel.mvi.ui.fragment.StateFragment
import com.temel.platform.R
import com.temel.platform.app.feature.main.state.MainAction
import com.temel.platform.app.feature.main.state.MainState

class MainFragment : StateFragment<MainState>() {
    override val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onResume() {
        super.onResume()

        activity?.let {
            it.findViewById<Button>(R.id.button).setOnClickListener {
                viewModel.dispatch(MainAction.FetchFacts)
            }
        }
    }

    override fun onNewState(state: MainState) {
        activity?.let {
            val progress = it.findViewById<ProgressBar>(R.id.progress_bar)
            val textView = it.findViewById<TextView>(R.id.text_hello_world)

            if(state.isLoading) {
                progress.visibility = View.VISIBLE
                textView.text = ""
            }else{
                progress.visibility = View.GONE
                textView.text = state.text
            }
        }
    }
}