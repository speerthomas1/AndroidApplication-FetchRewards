package com.speerthomas.fetchrewardscodingexercise

import android.util.Log
import com.speerthomas.fetchrewardscodingexercise.model.FetchRewardsItem
import com.speerthomas.fetchrewardscodingexercise.networking.FetchRewardsApi

class FetchRewardsRepository() {
    private val fetchRewardsApi: FetchRewardsApi = FetchRewardsApi()

    suspend fun fetchItems(): List<FetchRewardsItem>{
        var list = fetchRewardsApi.fetchItems()
        // Filter out any objects that have a null or blank name value
        list = list.filter { !it.name.isNullOrBlank() }
        list = sortList(list)
        return list
    }

    // Use sortedWith which returns a new list that is sorted first by listId then by our item name.
    private fun sortList(fetchRewardsItemList: List<FetchRewardsItem>): List<FetchRewardsItem>{
        return fetchRewardsItemList.sortedWith(compareBy<FetchRewardsItem> {item ->
            item.listId
        }.thenBy { item ->
            extractInt(item.name)
        })
    }

    // Since the names in our list are returned as "Item 123", we must change them into ints to sort properly
    // or else it is sorted by string value which would be incorrect.
    // e.g [Item 12, Item 2, Item 22]
    private fun extractInt(name: String): Int{
        val num = name.replace("Item ", "")
        return num.toInt()
    }


}