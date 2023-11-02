package com.sagar.nirfpredictor.ui.theme.screens.Graphs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sagar.nirfpredictor.ui.theme.BackButton

@Composable
fun Suggestions(suggestions: List<String>,
                onBackButtonClick: () -> Unit
                ){
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(suggestions) {
                Text(
                    text = it,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(modifier = Modifier.padding(bottom = 4.dp))
            }
        }
        BackButton(
            onClick = onBackButtonClick,
            text = "Navigate Back",
        )
    }
}