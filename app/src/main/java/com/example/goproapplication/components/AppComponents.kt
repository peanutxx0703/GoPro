package com.example.goproapplication.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goproapplication.R
import androidx.compose.material3.Text as Text

@Composable
fun NormalTextComponent(value: String) {
    Text(text = value, modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 30.dp)
        .padding(10.dp)
        .padding(bottom = 10.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.regular_font))
        )
        , color = Color.White
    )
}

@Composable
fun BoldTextComponent(value: String) {
    Text(text = value, modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp)
        .padding(10.dp)
        .heightIn(min = 10.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.regular_font))
        )
        , color = colorResource(id = R.color.white)
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(text = value, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.title_font))
        ),
        color = Color.White,
        textAlign = TextAlign.Center,
        lineHeight = 50.sp
    )
}

@Composable
fun MyTextFieldComponent(labelValue: String, painterResource: Painter, onTextSelected: (String) -> Unit, errorStatus: Boolean = false){
    val textValue = remember { mutableStateOf("") }

    OutlinedTextField(
        label = { Text(text = labelValue)},
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(painter = painterResource,
                contentDescription = null)
        },
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular_font)),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        ),
        isError = !errorStatus
    )
}

@Composable
fun PasswordTextFieldComponent(labelValue: String, painterResource: Painter, onTextSelected: (String) -> Unit, errorStatus: Boolean = false){
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current

    val icon = if (passwordVisible.value) {
        painterResource(id = R.drawable.visibility_on)
    } else {
        painterResource(id = R.drawable.visibility_off)
    }
    
    OutlinedTextField(
        label = { Text(text = labelValue)},
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(painter = painterResource,
                contentDescription = null)
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value })
            {
                Icon(painter = icon,
                    contentDescription = null
                )
            }
        },
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular_font)),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        ),
        visualTransformation = if (passwordVisible.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        isError = !errorStatus
    )
}

@Composable
fun PinTextFieldComponent(labelValue: String, painterResource: Painter, expectedPin: String, onPinEntered: (String) -> Unit) {
    val pin = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current

    val icon = if (passwordVisible.value) {
        painterResource(id = R.drawable.visibility_on)
    } else {
        painterResource(id = R.drawable.visibility_off)
    }
    TextField(
        label = { Text(text = labelValue) },
        value = pin.value,
        onValueChange = {
            pin.value = it
            onPinEntered(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(painter = icon, contentDescription = null)
            }
        },
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular_font)),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        ),
        visualTransformation = if (passwordVisible.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ButtonComponent(value: String, onButtonClicked : () -> Unit, isEnabled : Boolean){
    Button(
        onClick = {
            onButtonClicked.invoke()
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp)
            .padding(20.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Magenta),
        enabled = isEnabled
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Cyan)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.regular_font))
            )
        }
    }
}

@Composable
fun DividerTextComponent(){
    Row(modifier = Modifier
        .fillMaxWidth()
        ) {
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(8.dp),
            color = Color.White,
            thickness = 1.dp,
        )
        Text(
            text = stringResource(id = R.string.or_text),
            fontSize = 15.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.regular_font))
        )
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(8.dp),
            color = Color.White,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginComponent(tryingToLogin:Boolean = true, onTextSelected: (String) -> Unit){
    val accountText = if(tryingToLogin) "Already have an account? " else "Don't have an account? "
    val loginText = if(tryingToLogin) "Login" else "Sign up"
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(Color.White,
            fontFamily = FontFamily(Font(R.font.regular_font)),
            fontSize = 18.sp)
        ){
            append(accountText)
        }
        withStyle(
            style = SpanStyle(Color.Blue,
            fontFamily = FontFamily(Font(R.font.regular_font)),
            fontSize = 20.sp, fontWeight = FontWeight.Bold)
        ){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{ span ->
                Log.d("ClickableTextComponent", "{$span")
                if(span.item == loginText){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun UnderlinedTextComponent(value: String, onForgotClicked: () -> Unit) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 30.dp)
            .clickable { onForgotClicked() },
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.regular_font)),
            color = Color.White,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    )
}

@Composable
fun RoleButtonComponent(value: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Magenta)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(50.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Cyan)),
                shape = RoundedCornerShape(40.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.regular_font))
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItemComponent(painterResource: Painter, value: String, onClick: () -> Unit){
    Card(
        onClick = { onClick() },
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.dark_purple)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically){
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = RoundedCornerShape(40.dp))
                        .background(Color.LightGray)
                ){
                    Icon(painter = painterResource,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                BoldTextComponent(value = value)
            }
        }
    }
}

@Composable
fun ContactInfoItem(leadingIcon: ImageVector, infoText: String, buttonText: String, onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = leadingIcon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = infoText,
            fontSize = 16.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.regular_font)),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(Color.Magenta),
            modifier = Modifier.height(30.dp)
        ) {
            Text(text = buttonText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.regular_font))
            )
        }
    }
}




