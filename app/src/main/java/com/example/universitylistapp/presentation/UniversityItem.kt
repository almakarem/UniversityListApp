package com.example.universitylistapp.presentation

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.universitylistapp.data.remote.dto.University
import com.example.universitylistapp.presentation.ui.theme.UniversityListAppTheme
import com.example.universitylistapp.presentation.university_detail.UniversityDetails

@Composable
fun UniversityItem(
    university: University,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.clickable {
            val intent = Intent(context, UniversityDetails::class.java)
            intent.putExtra("university", university.name)
            context.startActivity(intent)
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp)
                .padding(7.dp)

        ){
            Column(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "${university.name}", fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Start)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "${university.stateProvince}", fontWeight = FontWeight.Light, fontSize = 14.sp, textAlign = TextAlign.Center)
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
            Card (
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.size(35.dp)
            ){
                    Text(text = "→", fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
                }
            }
        }

    }
}

@Preview
@Composable
fun UniversityItemPreview(){
    UniversityListAppTheme {
        UniversityItem(
            university = University(
                country = "United Arab Emirates",
                domains = listOf("mbzuai.ac.ae"),
                name = "Mohamed bin Zayed University of Artificial Intelligence (MBZUAI)",
                stateProvince = "Abu Dhabi",
                webPages = listOf("https://mbzuai.ac.ae/"),
                alpha_two_code = "AE"
            ))

    }
}