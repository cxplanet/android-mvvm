package com.vicarial.mobile.android.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicarial.mobile.android.mvvmdemo.adapter.WineryListAdapter
import com.vicarial.mobile.android.mvvmdemo.databinding.ActivityMainBinding
import com.vicarial.mobile.android.mvvmdemo.ui.WineryViewModel
import com.vicarial.mobile.android.mvvmdemo.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WineryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // XXX for dev use , uncomment the next line to remove the DB - always
        // version the DB in production
        applicationContext.deleteDatabase("wineries")
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wineListAdapter = WineryListAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = wineListAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            viewModel.wineries.observe(this@MainActivity) { result -> // ie a Resource
                wineListAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading
                        && result.data.isNullOrEmpty()

                textViewErrMessage.isVisible = result is Resource.Error
                        && result.data.isNullOrEmpty()
                textViewErrMessage.text = result.error?.localizedMessage
            }
        }
    }
}