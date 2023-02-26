package com.purnendu.employee.composeUi

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomTextField(
    placeHolder: String,
    value: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.White
    )
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .indicatorLine(
                enabled = true,
                isError = false,
                interactionSource = interactionSource,
                colors = colors
            ),
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(

                value = value,
                innerTextField = innerTextField,
                interactionSource = interactionSource,
                singleLine = true,
                enabled = true,
                colors = colors,
                placeholder = {
                    Text(
                        text = placeHolder,
                    )
                },
                contentPadding = PaddingValues(
                    start = 0.dp,
                    top = 2.dp,
                    bottom = 2.dp,
                    end = 0.dp
                ),
                visualTransformation = VisualTransformation.None
            )
        },
    )
}