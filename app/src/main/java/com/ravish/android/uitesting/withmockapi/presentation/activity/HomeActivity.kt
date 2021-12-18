package com.ravish.android.uitesting.withmockapi.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ravish.android.uitesting.withmockapi.R
import com.ravish.android.uitesting.withmockapi.data.model.User
import com.ravish.android.uitesting.withmockapi.databinding.ActivityHomeBinding
import com.ravish.android.uitesting.withmockapi.presentation.adapter.UserListAdapter
import com.ravish.android.uitesting.withmockapi.presentation.model.UIState
import com.ravish.android.uitesting.withmockapi.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val userListAdapter: UserListAdapter by lazy { UserListAdapter() }
    private val homeViewModel: HomeViewModel? by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeFlowData()
        initUI()
        homeViewModel?.loadUserData()
    }

    private fun observeFlowData() {
        lifecycleScope.launchWhenStarted {
            homeViewModel?.userFlow?.collect { state ->
                when (state) {
                    is UIState.Loading -> {
                        showProgress()
                    }
                    is UIState.Success -> {
                        hideProgress()
                        state.data?.let { updateUI(it) } ?: showError()
                    }
                    is UIState.Error -> {
                        hideProgress()
                        showError()
                    }
                }
            }
        }
    }

    private fun initUI() {
        binding.rvUserList.adapter = userListAdapter
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showError() {
        binding.errorMessage.text = getString(R.string.error_message)
        binding.errorMessage.visibility = View.VISIBLE
    }

    private fun updateUI(usersList: List<User>) {
        userListAdapter.submitList(usersList)
    }
}