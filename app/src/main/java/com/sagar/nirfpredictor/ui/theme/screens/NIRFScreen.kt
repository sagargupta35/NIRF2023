package com.sagar.nirfpredictor.ui.theme.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sagar.nirfpredictor.R
import com.sagar.nirfpredictor.model.parametersList
import com.sagar.nirfpredictor.ui.theme.login.screens.LoginScreen
import com.sagar.nirfpredictor.ui.theme.login.screens.LoginViewModel
import com.sagar.nirfpredictor.ui.theme.login.screens.SignupScreen
import com.sagar.nirfpredictor.ui.theme.screens.Graphs.RankScreen
import com.sagar.nirfpredictor.ui.theme.screens.Graphs.Suggestions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier,
           screen: ScreenName,
           canNavigateBack: Boolean,
           navigateUp: () -> Unit,
           ){
    if(screen != ScreenName.Predict) {
        CenterAlignedTopAppBar(
            title = { stringResource(id = screen.title) },
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            },
        )
    }
}


enum class ScreenName(@StringRes val title: Int){
    Predict(R.string.prediction),
    RankSore(R.string.rank_score),
    Signup(R.string.sign_up),
    Login(R.string.login),
    Suggestions(R.string.suggestions)
}

@Composable
fun NIRFComposable(
    viewModel: NIRFViewModel,
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ScreenName.valueOf(backStackEntry?.destination?.route ?: ScreenName.Predict.name)

    Scaffold() {
        val context = LocalContext.current
        val state = viewModel.state
        LaunchedEffect(key1 = context){
            loginViewModel.channel.collect{result ->
                if(result == null){
                    navController.navigate(ScreenName.Predict.name)
                    navController.popBackStack(ScreenName.Predict.name, inclusive = false)
                }
            }
        }

        LaunchedEffect(key1 = context){
            viewModel.validationEvents.collect {
                if(it){
                    navController.navigate(ScreenName.RankSore.name)
                }
            }
        }
        NavHost(
            navController = navController,
            startDestination = ScreenName.Signup.name,
            modifier = Modifier.padding(paddingValues = it)
        ) {
            composable(route = ScreenName.Predict.name){
                FormScreen(formState = state, viewModel = viewModel,
                    onPredictClick = {
                        viewModel.submitData() },
                    parameters = parametersList
                    )
            }
            composable(route = ScreenName.Signup.name){
                SignupScreen(viewModel = loginViewModel,
                    onLoginClick = {
                        navController.navigate(ScreenName.Login.name)
                    }
                    )
            }
            composable(route = ScreenName.Login.name){
                LoginScreen(viewModel = loginViewModel,
                    onRegisterClick = {navController.navigateUp()}
                    )
            }
            composable(route = ScreenName.RankSore.name){
                RankScreen(imgSrc = "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631300503690E01_DXXX.jpg",
                    context = context,
                    suggestion = stringResource(id = R.string.sample_text),
                    onBackButtonClick = {
                        navController.navigate(ScreenName.Suggestions.name)
                    }
                )
            }
            composable(route = ScreenName.Suggestions.name){
                Suggestions(suggestions = list){
                    navController.navigateUp()
                }
            }

        }


    }

}

val list = listOf(
    "The attributes in the tlr group that need attention due to below-average values are: FRU",
    "The attributes in the rpc group that need attention due to below-average values are: IPR, FPPP",
    "The attributes in the go group that need attention are: GUE, GPHD",
    "The attributes in the oi group that need attention due to below-average values are: RD, WD, ESCS",
    "The attribute in the perception group needs attention: PPR",
    "The attributes in the tlr group that need attention due to below-average values are: FRU",
    "The attributes in the rpc group that need attention due to below-average values are: IPR, FPPP",
    "The attributes in the go group that need attention are: GUE, GPHD",
    "The attributes in the oi group that need attention due to below-average values are: RD, WD, ESCS",
    "The attribute in the perception group needs attention: PR"
)

