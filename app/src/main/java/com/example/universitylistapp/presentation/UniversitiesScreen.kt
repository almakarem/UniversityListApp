package com.example.universitylistapp.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.universitylistapp.data.remote.dto.University

@Composable
fun UniversitiesScreen(
    universities: LazyPagingItems<University>
){
    val context = LocalContext.current
    LaunchedEffect(key1 = universities.loadState){
        if (universities.loadState.refresh is LoadState.Error){
            val error = (universities.loadState.refresh as LoadState.Error).error
            Log.e("ERROR", "UniversitiesScreen: Error loading universities: $error", error)
            Toast.makeText(
                context,
                "Error loading universities: ${error.localizedMessage}",
                Toast.LENGTH_LONG
            ).show()


    }
    }

    Box(modifier = Modifier.fillMaxSize()){
        if(universities.loadState.refresh is LoadState.Loading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(universities.itemSnapshotList.items){university ->
                    UniversityItem(university = university)
                }
                item{
                    if(universities.loadState.append is LoadState.Loading){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }

}

