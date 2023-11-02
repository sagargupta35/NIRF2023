package com.sagar.nirfpredictor.model

import retrofit2.Call
import com.sagar.nirfpredictor.network.FlaskApiService
import com.sagar.nirfpredictor.network.RankingData
import com.sagar.nirfpredictor.ui.theme.screens.NIRFState
import retrofit2.http.Field

interface RankingDataRepository {
//suspend fun getRankingData(
//    nirfState: NIRFState
//) : Call<RankingData>

    suspend fun getRank():RankingData
}

class NetworkRankingRepository(
    private val flaskApiService: FlaskApiService
): RankingDataRepository{
    //    override suspend fun getRankingData(
//        nirfState: NIRFState
//    ): Call<RankingData> =
//        flaskApiService.sendData(
//            ss = nirfState.parameters[0].score,
//            fsr = nirfState.parameters[1].score,
//            fqe = nirfState.parameters[2].score,
//            fru = nirfState.parameters[3].score,
//            pu = nirfState.parameters[4].score,
//            qp = nirfState.parameters[5].score,
//            ipr = nirfState.parameters[6].score,
//            fppp = nirfState.parameters[7].score,
//            gue = nirfState.parameters[8].score,
//            gphd = nirfState.parameters[9].score,
//            rd = nirfState.parameters[10].score,
//            wd = nirfState.parameters[11].score,
//            escs = nirfState.parameters[12].score,
//            pcs = nirfState.parameters[13].score,
//            pr = nirfState.parameters[14].score
//        )
    override suspend fun getRank(): RankingData {
        return flaskApiService.getRank()
    }
}
