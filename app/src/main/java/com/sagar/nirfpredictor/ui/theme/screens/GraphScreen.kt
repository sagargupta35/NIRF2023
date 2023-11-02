package com.sagar.nirfpredictor.ui.theme.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sagar.nirfpredictor.ui.theme.BackButton
import com.sagar.nirfpredictor.ui.theme.Graph
import com.sagar.nirfpredictor.ui.theme.GraphBackground
import com.sagar.nirfpredictor.ui.theme.NIRFPredictorTheme
import com.sagar.nirfpredictor.ui.theme.NavButton


@Composable
fun GraphScreen(modifier: Modifier = Modifier,
                imgsrc:String,
                context: Context,
                suggestion: String,
                navButtonList: List<NavigationButton>? = null,
                onBackButtonClick: () -> Unit,
                backText: String
                ) {
    Box(
        modifier.fillMaxSize()
    ) {
        GraphBackground()
        Column {
            Graph(imgSrc = imgsrc,
                context = context,
                modifier = Modifier.weight(0.5f)
                )
            Text(
                text = suggestion,
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                color = Color(0xFFFDFF32), // Pumpkin Orange
                fontSize = 22.sp,
                lineHeight = 30.sp

            )
            if(navButtonList != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (button in navButtonList) {
                        NavButton(navButton = button)
                    }
                }
            } else{
                BackButton(onClick = onBackButtonClick, text = backText)
            }
        }

    }
}



@Preview
@Composable
fun GraphScreenPreview(){
    NIRFPredictorTheme {
    }
}