package com.example.machinetask

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.machinetask.model.Post
import com.example.machinetask.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: PostViewModel = viewModel()
            MyApp(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(viewModel: PostViewModel) {
    val posts = viewModel.posts.observeAsState(emptyList())
    val isLoading = viewModel.isLoading.observeAsState(initial = false)

    Scaffold(
        topBar = { TopAppBar(title = { Text("List of Posts") }) },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp) // Add top padding/margin here
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoading.value) {
                    // Show progress dialog
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        color = Color.Red
                    )
                } else {
                    Spacer(modifier = Modifier.height(40.dp)) // Add a Spacer with desired height
                    PostList(posts = posts.value, viewModel = viewModel)
                }
            }
        }
    )
}

@Composable
fun PostList(posts: List<Post>, viewModel: PostViewModel) {
    val context = LocalContext.current
    LazyColumn {
        itemsIndexed(posts) { index, post ->
            PostItem(post = post) {
                viewModel.onPostClicked(context, post.id, post.title, post.body)
            }
            if (index == posts.size - 1) {
                viewModel.fetchPosts()
            }
        }
    }
}

@Composable
fun PostItem(post: Post, onClick: () -> Unit) {
    val context = LocalContext.current
    val isProcessing = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                isProcessing.value = true
                // Simulate heavy computation
                Handler(Looper.getMainLooper()).postDelayed({
                    isProcessing.value = false
                    onClick()
                }, 1000) // 1 seconds delay
            }
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = "${post.id}. ${post.title}",
                modifier = Modifier.padding(bottom = 4.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            if (isProcessing.value) {
                // Show a progress indicator while processing
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.Red
                )
            }
        }
    }
}
