package com.speerthomas.fetchrewardscodingexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.speerthomas.fetchrewardscodingexercise.databinding.FragmentHomeBinding
import kotlinx.coroutines.*

class HomeFragment : Fragment() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    // View model is scoped to lifecycle of our nav_graph, which in this case would be the applications whole lifecycle
    private val fetchRewardsViewModel: FetchRewardsViewModel by navGraphViewModels(R.id.nav_graph)

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fetchButton.setOnClickListener {
            coroutineScope.launch {
                temporarilyEnableLoading(4000)
                fetchRewardsViewModel.fetchItems()
                lifecycleScope.launch {
                    delay(400)
                    navigateToFetchRewardsListFragment()
                }
            }
        }

    }

    private fun navigateToFetchRewardsListFragment() {
        if (findNavController().currentDestination?.id != R.id.fragment_fetch_rewards_list) {
            findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToFragmentFetchRewardsList())
        }
    }

    private fun temporarilyEnableLoading(delayTime: Long) {
        binding.progressBar.visibility = View.VISIBLE
        binding.fetchButton.isEnabled = false
        lifecycleScope.launch {
            delay(delayTime)
            disableLoading()
        }
    }

    private fun disableLoading() {
        binding.progressBar.visibility = View.GONE
        binding.fetchButton.isEnabled = true
    }
}