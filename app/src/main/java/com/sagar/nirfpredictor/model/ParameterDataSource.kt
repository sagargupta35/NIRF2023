package com.sagar.nirfpredictor.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.sagar.nirfpredictor.R

data class Parameter(
    @StringRes val name:Int,
    val id:Int,
    var isError: Boolean,
    var score: String,
    @DrawableRes val leadingIcon: Int = R.drawable.t_solid
)

val parametersList = mutableListOf(
    Parameter(
        name = R.string.ss,
        id = 0,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.fsr,
        id = 1,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.fqe,
        id = 2,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.fru,
        id = 3,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.pu,
        id = 4,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.qp,
        id = 5,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.ipr,
        id = 6,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.fppp,
        id = 7,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.gue,
        id = 8,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.gphd,
        id = 9,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.rd,
        id = 10,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.wd,
        id = 11,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.escs,
        id = 12,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.pcs,
        id = 13,
        isError = false,
        score = ""
    ),
    Parameter(
        name = R.string.pr,
        id = 14,
        isError = false,
        score = ""
    ),
)

