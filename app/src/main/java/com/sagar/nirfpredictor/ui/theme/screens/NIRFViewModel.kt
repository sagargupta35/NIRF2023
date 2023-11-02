package com.sagar.nirfpredictor.ui.theme.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sagar.nirfpredictor.MainApplication
import com.sagar.nirfpredictor.model.RankingDataRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.sagar.nirfpredictor.model.parametersList
import com.sagar.nirfpredictor.network.User

class NIRFViewModel(
    private val rankingDataRepository: RankingDataRepository,
): ViewModel() {

    var state by mutableStateOf(NIRFState(parametersList))
    private set
    private val channel = Channel<Boolean>()
    val validationEvents = channel.receiveAsFlow()

    private fun validateScore(score: String): Boolean{
        Log.d("TAG", "Inside valid function")
        Log.d("TAG", score)
        if(score.isEmpty()) return false
        if(score.any {!it.isDigit()}) return false
        try {
            if (score.toInt() !in 1..100) return false
        } catch (e: NumberFormatException){
            return false
        }
        return true
    }


    fun onEvent(event: FormEvent){
        state.parameters[event.id].score = event.score
    }

    fun submitData(){
        Log.d("TAG", "Inside submit data")
        var error = false

         for(param in parametersList){
             param.isError = !validateScore(param.score)
             if(param.isError) {
                 Log.d("TAG", "error updated to true")
                 error = true
             } else{
                 Log.d("TAG", "Param is valid")
             }
         }


        state = state.copy(parameters = parametersList)
        if(error) return

        viewModelScope.launch {
            try {
//                val response = rankingDataRepository.getRank()
                channel.send(true)
            } catch (e: Exception){
                Log.d("TAG", e.localizedMessage)
            }
        }
    }



    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val rankingDataRepository = application.container.rankingDataRepository
                NIRFViewModel(rankingDataRepository = rankingDataRepository
                    )
            }
        }
    }

    fun getRank(){
        viewModelScope.launch {
            try {
                val response = rankingDataRepository.getRank()
                channel.send(true)
            } catch (e: Exception){
                Log.d("TAG", e.localizedMessage)
            }
        }
    }

}

private data class ValidationEvent(
    val success: Boolean,
    val errorMessage: String
)
