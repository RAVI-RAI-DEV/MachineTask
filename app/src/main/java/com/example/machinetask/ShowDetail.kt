package com.example.machinetask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier

class ShowDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postId = intent.getIntExtra(EXTRA_POST_ID, 0)
        val postTitle = intent.getStringExtra(EXTRA_POST_TITLE) ?: ""
        val postBody = intent.getStringExtra(EXTRA_POST_BODY) ?: ""

        setContent {
            DetailScreen(postTitle = postTitle, postBody = postBody)
        }
    }

    companion object {
        const val EXTRA_POST_ID = "extra_post_id"
        const val EXTRA_POST_TITLE = "extra_post_title"
        const val EXTRA_POST_BODY = "extra_post_body"
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(postTitle: String, postBody: String) {
    Scaffold(
        content = {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = postTitle,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = postBody,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    )
}
