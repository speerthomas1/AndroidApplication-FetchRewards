package com.speerthomas.fetchrewardscodingexercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.speerthomas.fetchrewardscodingexercise.model.FetchRewardsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FetchRewardsViewModel: ViewModel() {
    private val repository: FetchRewardsRepository = FetchRewardsRepository()

    // Good practice not to publicly expose our mutable live data"
    private val _fetchRewardsItemList = MutableLiveData<List<FetchRewardsItem>>()
    val fetchRewardsItemList: LiveData<List<FetchRewardsItem>>
        get() = _fetchRewardsItemList

    fun fetchItems(){
        viewModelScope.launch(Dispatchers.IO){
            _fetchRewardsItemList.postValue(repository.fetchItems())
        }

    }
}