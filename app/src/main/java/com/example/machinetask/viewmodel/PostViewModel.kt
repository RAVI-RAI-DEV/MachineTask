package com.example.machinetask.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetask.ShowDetail
import com.example.machinetask.model.Post
import com.example.machinetask.network.ApiService
import com.example.machinetask.network.RetrofitInstance
import kotlinx.coroutines.launch
class PostViewModel : ViewModel() {
    private val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)
    private var currentPage = 1
    private val pageSize = 10
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true // Set loading state to true
                val newPosts = apiService.getPosts(currentPage, pageSize)
                val currentList = _posts.value ?: emptyList()
                _posts.value = currentList + newPosts
                currentPage++
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false // Set loading state to false
            }
        }
    }

    fun onPostClicked(context: Context, postId: Int, postTitle: String, postBody: String) {
        val intent = Intent(context, ShowDetail::class.java).apply {
            putExtra(ShowDetail.EXTRA_POST_ID, postId)
            putExtra(ShowDetail.EXTRA_POST_TITLE, postTitle)
            putExtra(ShowDetail.EXTRA_POST_BODY, postBody)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}