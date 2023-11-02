package com.sagar.nirfpredictor.ui.theme

import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.ETC1.decodeImage
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sagar.nirfpredictor.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sagar.nirfpredictor.ui.theme.screens.NavigationButton
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import java.io.File
import java.util.Base64


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    onValueChange:(String) -> Unit = {},
    placeHolder: String = "Enter TLRScore",
    @DrawableRes leadingIcon: Int? = null,
    leadingVector: ImageVector? = null,
    enabled: Boolean = true,
    isError: Boolean = false
){
    var text by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(modifier = modifier){
        OutlinedTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
                text = it
            },
            placeholder = {Text(text = placeHolder)},
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                if(leadingIcon != null) {
                    Icon(
                        painterResource(id = leadingIcon), contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                } else if(leadingVector != null) Icon(leadingVector, contentDescription = null)},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onValueChange(text) }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0x77000000)
            ),
            shape = RoundedCornerShape(20.dp),
            enabled = enabled,
            isError = isError
        )
        Divider(
            modifier = Modifier
                .padding(start = 44.dp)
                .width(1.dp)
                .height(55.dp)
        )

    }

}

@Composable
fun PredictButton(modifier: Modifier = Modifier,
                onClick: () -> Unit,
                  text: String = "Predict",
                  ){
    Button(onClick = onClick,
        modifier = modifier
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(Color(0x00000000)),
        shape = RoundedCornerShape(20.dp)

    ) {
        Box(modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF6AB7FF), Color(0xFFA47DFF),)
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                BorderStroke(
                    3.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFA47DFF), Color(0xFF6AB7FF))
                    ),
                ),
                RoundedCornerShape(10.dp)
            )
            ,
            contentAlignment = Alignment.Center
        ){
            Text(
                text = text,
                fontSize = 25.sp,
            )
        }
    }
}

@Composable
fun BackgroundScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier.background(Color.White)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
        ) {
            BoxGradient(
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun BoxGradient(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFA47DFF), Color(0xFF6AB7FF))
                )
            )
    )
}

@Composable
fun Graph(
    modifier:Modifier = Modifier,
    imgSrc: String,
    context: Context
){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        shape = RoundedCornerShape(20.dp)

    ) {
        Image(painter = painterResource(id = R.drawable.graph),
            contentDescription = null,
            modifier= Modifier.height(300.dp).fillMaxWidth(),
            contentScale = ContentScale.Crop)
    }

}

@Composable
fun GraphBackground(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF536DFE),  // Slightly dark blue
                        Color(0xFFAB47BC)   // Purple)
                    )
                )
            )
    )
}

@Composable
fun NavButton(
    navButton: NavigationButton,
    modifier: Modifier = Modifier,
){
    Button(
        onClick = { navButton.onClick() },
        colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.size(60.dp),
        border = BorderStroke( 2.dp,
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xFFAB47BC), Color(0xFF536DFE))
            )
        ),
        ) {
        Text(
            text = navButton.letter,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Cyan
        )
    }
}

@Composable
fun BackButton(modifier: Modifier = Modifier,
               onClick: () -> Unit = {},
               text: String
               ){
    Button(onClick = onClick,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(40.dp)
        ,
        colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            color = Color.Cyan,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun SignupButton(
    modifier: Modifier = Modifier,
    text: String
 ){

}

@Composable
fun LoginBackgroundScreen(
    modifier: Modifier = Modifier
){

    Box(modifier = modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                listOf(
                    // Slightly Dark Blue
                    Color(0xF70600A0),
                    Color(0xAAFFC0CB),
                )
            )
        )
    )

}

@Composable
fun SignupCard(
    modifier: Modifier = Modifier,
    userNameError: String? = null,
    onEmailChange: (String) -> Unit,
    emailError: String? = null,
    onPasswordChange: (String) -> Unit,
    passwordError: String? = null,
    onUserNameChange: (String) -> Unit
){
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(30.dp),

    ) {
        Box(modifier = modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFF8E24AA), Color(0xFFFFC0CB))
            )
        )) {
            Column(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.mavericks_logo_nobg),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }
                MyTextField(
                    modifier = Modifier.padding(12.dp),
                    onValueChange = onUserNameChange,
                    placeHolder = "Enter username",
                    leadingVector = Icons.Filled.Person,
                )
                if(userNameError != null){
                    Text(text = userNameError,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                MyTextField(
                    modifier = Modifier.padding(12.dp),
                    onValueChange = onEmailChange,
                    placeHolder = "Enter email",
                    leadingVector = Icons.Filled.Email,
                    enabled = userNameError == null
                )
                if(userNameError == null && emailError != null){
                    Text(text = emailError,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
                MyTextField(
                    modifier = Modifier.padding(12.dp),
                    onValueChange = onPasswordChange,
                    placeHolder = "Enter password",
                    leadingVector = Icons.Filled.Lock,
                    enabled = (userNameError == null && emailError == null)
                )
                if(userNameError == null && emailError == null && passwordError != null){
                    Text(text = passwordError,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }

    }

}

@Composable
fun LoginCard(
    modifier: Modifier = Modifier,
    onEmailChange: (String) -> Unit,
    emailError: String? = null,
    onPasswordChange: (String) -> Unit,
    passwordError: String? = null,
){
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(30.dp),

        ) {
        Box(modifier = modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFF8E24AA),
                    Color(0xFFFFC0CB))
            )
        )) {
            Column {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.mavericks_logo_nobg),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                        )
                }
                MyTextField(
                    modifier = Modifier.padding(12.dp),
                    onValueChange = onEmailChange,
                    placeHolder = "Enter email",
                    leadingVector = Icons.Filled.Email
                )
                if(emailError != null){
                    Text(text = emailError,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
                MyTextField(
                    modifier = Modifier.padding(12.dp),
                    onValueChange = onPasswordChange,
                    placeHolder = "Enter password",
                    leadingVector = Icons.Filled.Lock,
                    enabled = emailError==null
                )
                if(emailError == null && passwordError != null){
                    Text(text = passwordError,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }

    }

}

@Composable
fun Base64Image(base64BitmapString: String) {
    // Decode the base64-encoded bitmap string into a byte array
    val decodedBytes = android.util.Base64.decode(base64BitmapString, android.util.Base64.DEFAULT)

    // Create a Bitmap from the decoded byte array
    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

    // Display the Bitmap using the Image composable
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = null, // Provide a description if needed
        modifier = Modifier.fillMaxSize() // Modify the modifier as needed
    )
}

@Preview
@Composable
fun MyPreview(){
    NIRFPredictorTheme {
//        Base64Image(base64BitmapString = stringResource(id = R.string.bitImage))
    }
}

