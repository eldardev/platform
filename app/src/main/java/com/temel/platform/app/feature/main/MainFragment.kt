package com.temel.platform.app.feature.main

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.temel.mvi.ui.fragment.StateFragment
import com.temel.platform.R
import java.util.*

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

            if (state.isLoading) {
                progress.visibility = View.VISIBLE
                textView.text = ""
            } else {
                progress.visibility = View.GONE
                textView.text = state.text
            }

            if (state.isNavigate){
                Handler().postDelayed({
                    viewModel.dispatch(MainAction.ToList)
                }, 3000)
            }
        }
    }

    override fun handleThrowable(throwable: Throwable) {
        viewModel.dispatch(MainAction.Loaded)
        Snackbar.make(requireView(), "Exception", Snackbar.LENGTH_LONG).show()
    }
}