package com.example.universitylistapp.presentation

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.universitylistapp.data.remote.dto.University
import com.example.universitylistapp.presentation.ui.theme.UniversityListAppTheme

@Composable
fun UniversityDetail(
    university: University,
    viewModel: UniversityViewModel,
    modifier: Modifier = Modifier,
){
    val context = LocalContext.current
    val values = viewModel.universityPaginFlow.collectAsLazyPagingItems()
    Card(
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(7.dp)

        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    Card (
                        shape = CircleShape,
                        elevation = CardDefaults.cardElevation(2.dp),
                        modifier = Modifier.size(55.dp)
                    ){
                        Text(text = "→", fontWeight = FontWeight.Bold, fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                // Trigger refresh load state

                                 values.refresh()
                                // Finish the activity
                                (context as? Activity)?.finish()

                            })
                    }
                }
                Spacer(modifier = Modifier.padding(15.dp))
                Row {
                    Text(text = "Name:", fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Start)
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "${university.name}", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, textAlign = TextAlign.Start)
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Row {
                    Text(text = "State:", fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "${university.stateProvince}", fontWeight = FontWeight.Light, fontSize = 14.sp, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.padding(5.dp))
                if (!university.domains.isNullOrEmpty()) {
                    Row {
                        Text(text = "Domains:", fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.padding(5.dp))
                        LazyColumn(){
                            items(university.domains!!){
                                    domain ->
                                Text(text = domain, fontWeight = FontWeight.Light, fontSize = 14.sp, textAlign = TextAlign.Center)

                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                if (!university.webPages.isNullOrEmpty()) {
                    Row {
                        Text(text = "Web Pages:", fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.padding(5.dp))
                        LazyColumn(){
                            items(university.webPages!!){
                                    page ->
                                Text(text = page, fontWeight = FontWeight.Light, fontSize = 14.sp, textAlign = TextAlign.Center)

                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Row {
                    Text(text = "Alpha Code:", fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "${university.alpha_two_code}", fontWeight = FontWeight.Light, fontSize = 14.sp, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Row {
                    Text(text = "Country:", fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "${university.country}", fontWeight = FontWeight.Light, fontSize = 14.sp, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.padding(5.dp))
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Card (
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(2.dp),
                    modifier = Modifier.size(55.dp)
                ){
                    Text(text = "→", fontWeight = FontWeight.Bold, fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxSize()
                        .clickable {

                        })
                }
            }
        }

    }
}

//@Preview
//@Composable
//fun UniversityPreview(){
//    UniversityListAppTheme {
//        UniversityDetail(
//            university = University(
//                country = "United Arab Emirates",
//                domains = listOf("mbzuai.ac.ae"),
//                name = "Mohamed bin Zayed University of Artificial Intelligence (MBZUAI)",
//                stateProvince = "Abu Dhabi",
//                webPages = listOf("https://mbzuai.ac.ae/"),
//                alpha_two_code = "AE"
//            )
//        )
//
//    }
//}