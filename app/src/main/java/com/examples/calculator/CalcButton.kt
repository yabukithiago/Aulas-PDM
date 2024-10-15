package com.examples.calculator

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.examples.calculator.ui.theme.CalculatorTheme
import com.examples.calculator.ui.theme.Grey
import com.examples.calculator.ui.theme.Orange
import com.examples.calculator.ui.theme.White

@Composable
fun CalcButton(modifier: Modifier = Modifier,
               label : String = "",
               isOperation : Boolean = false,
               onButtonPress : (String) -> Unit){
    Button(
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp),
        colors = ButtonDefaults.run { buttonColors( if (isOperation) Orange else Grey) },
        onClick = { onButtonPress(label)}) {

        Text(text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalcButtonPreview(){
    CalculatorTheme {
        CalcButton {  }
    }
}