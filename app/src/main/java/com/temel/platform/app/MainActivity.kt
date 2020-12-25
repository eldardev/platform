package com.temel.platform.app

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.temel.platform.R
import com.temel.mvi.extension.renderViewState
import com.temel.mvi.extension.throwable
import com.temel.mvi.ui.CommonActivity

class MainActivity : CommonActivity() {

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

//        val list = listOf(
//            MainViewModel.MainAction.ChangeText(i.toString()),
//            MainViewModel.MainAction.SetIsLoading(false)
//        )

        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.getFacts()
        }
    }

    override fun onStart() {
        super.onStart()

        renderViewState(viewModel.state, {
            val progress = findViewById<ProgressBar>(R.id.progress_bar)
            val textView = findViewById<TextView>(R.id.text_hello_world)

            it?.let {
                if(it.isLoading) {
                    progress.visibility = VISIBLE
                    textView.text = ""
                }else{
                    progress.visibility = GONE
                    textView.text = it.text
                }
            }

        })
    }

    override fun handleThrowable(throwable: Throwable) {
        println(throwable.localizedMessage)
    }
}