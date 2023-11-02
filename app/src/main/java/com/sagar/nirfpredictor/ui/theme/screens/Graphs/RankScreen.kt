package com.sagar.nirfpredictor.ui.theme.screens.Graphs

import android.content.Context
import androidx.compose.runtime.Composable
import com.sagar.nirfpredictor.ui.theme.screens.GraphScreen
import com.sagar.nirfpredictor.ui.theme.screens.NavigationButton


@Composable
fun RankScreen(
    imgSrc: String,
    context: Context,
    suggestion: String,
    onBackButtonClick: () -> Unit,
){
    GraphScreen(imgsrc = imgSrc, context = context,
        suggestion = suggestion, navButtonList = null,
        onBackButtonClick = onBackButtonClick,
        backText = "See Suggestions"
        )
}