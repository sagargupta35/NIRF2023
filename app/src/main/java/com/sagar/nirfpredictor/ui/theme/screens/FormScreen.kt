package com.sagar.nirfpredictor.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sagar.nirfpredictor.R
import com.sagar.nirfpredictor.model.Parameter
import com.sagar.nirfpredictor.ui.theme.BackgroundScreen
import com.sagar.nirfpredictor.ui.theme.MyTextField
import com.sagar.nirfpredictor.ui.theme.PredictButton


@Composable
fun FormScreen(modifier: Modifier = Modifier,
               formState: NIRFState,
               viewModel: NIRFViewModel,
               parameters: List<Parameter>,
               onPredictClick: () -> Unit
               ) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        BackgroundScreen(modifier = Modifier.fillMaxSize())
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.tasktable3), contentDescription = null,
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.Crop
                )
            ElevatedCard(
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 25.dp, bottom = 10.dp, start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(20.dp),
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    contentPadding = PaddingValues(15.dp)
                ) {
                    items(parameters) { param ->
                        MyTextField(
                            onValueChange = { viewModel.onEvent(FormEvent(param.id, it)) },
                            placeHolder = "Enter ${stringResource(id = param.name)} score",
                            leadingIcon = param.leadingIcon,
                            isError = param.isError
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            PredictButton(
                onClick = onPredictClick
            )
        }
    }
}
