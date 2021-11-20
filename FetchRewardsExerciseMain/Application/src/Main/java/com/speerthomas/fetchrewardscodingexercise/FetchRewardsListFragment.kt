package com.speerthomas.fetchrewardscodingexercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.speerthomas.fetchrewardscodingexercise.databinding.FragmentFetchRewardsListBinding

class FetchRewardsListFragment: Fragment() {

    // View model is scoped to lifecycle of our nav_graph, which in this case would be the applications whole lifecycle
    private val fetchRewardsViewModel: FetchRewardsViewModel by navGraphViewModels(R.id.nav_graph)

    private lateinit var adapter: FetchRewardsAdapter

    private lateinit var binding: FragmentFetchRewardsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize and return view binding
        binding = FragmentFetchRewardsListBinding.inflate(inflater)
        val view = binding.root

        // Define lambda function that is passed into Adapter
        adapter = FetchRewardsAdapter { fetchRewardsItem ->  
            Toast.makeText(requireContext(), "ID: ${fetchRewardsItem.id} Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchRewardsViewModel.fetchRewardsItemList.observe(viewLifecycleOwner, Observer { fetchRewardsItemList ->
            adapter.bindData(fetchRewardsItemList)
            binding.progressBar.visibility = View.GONE
        })

        fetchRewardsViewModel.fetchItems()
    }

}