package com.example.universitylistapp.presentation.university_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.universitylistapp.R
import com.example.universitylistapp.data.remote.dto.University
import com.example.universitylistapp.presentation.UniversitiesScreen
import com.example.universitylistapp.presentation.UniversityDetail
import com.example.universitylistapp.presentation.UniversityViewModel
import com.example.universitylistapp.presentation.ui.theme.UniversityListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniversityDetails : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversityListAppTheme {
                var university = University(
                    country = "",
                    domains = listOf(""),
                    name = "",
                    stateProvince = "",
                    webPages = listOf(""),
                    alpha_two_code = ""
                )
                val intent = intent.getStringExtra("university")
                val viewModel = hiltViewModel<UniversityViewModel>()
                viewModel.universityPaginFlow.collectAsLazyPagingItems().itemSnapshotList.items.forEach{
                    item ->
                    if (item.name == intent)
                        university = item
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    UniversityDetail(university = university,viewModel)
                }
            }
        }
    }
}