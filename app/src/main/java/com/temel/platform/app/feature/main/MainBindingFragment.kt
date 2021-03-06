package com.temel.platform.app.feature.main

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.temel.mvi.ui.fragment.StateBindingFragment
import com.temel.platform.R
import com.temel.platform.databinding.FragmentMainBinding

class MainBindingFragment : StateBindingFragment<FragmentMainBinding, MainState>() {

    override val viewModel by viewModels<MainViewModel> {
        viewModelFactory
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
        }
    }

    override fun handleThrowable(throwable: Throwable) {
        viewModel.dispatch(MainAction.Loaded)
        Snackbar.make(requireView(), "Exception", Snackbar.LENGTH_LONG).show()
    }

    override val bindingInflater: (LayoutInflater) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate
}