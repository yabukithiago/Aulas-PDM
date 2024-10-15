package com.examples.calculator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.examples.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorView(modifier: Modifier = Modifier) {
    var display by remember { mutableStateOf("0") }
    var previousValue by remember { mutableStateOf("0") }
    var operation by remember { mutableStateOf<String?  > (null) }

    fun getDisplay() : Double {
        return display.toDouble()
    }

    fun setDisplay(value: Double) {
        if ( value % 1 == 0.0) {
            display = value.toInt().toString()
        } else {
            display = value.toString()
        }
    }

    var userIsInTheMiddleOfTyping by remember {
        mutableStateOf(false)
    }

    val calculateResult: () -> Unit = {
        val currentValue = display.toDoubleOrNull()
        val prevValue = previousValue.toDoubleOrNull()

        if (currentValue != null && prevValue != null && operation != null) {
            display = when (operation) {
                "+" -> (prevValue + currentValue).toString()
                "-" -> (prevValue - currentValue).toString()
                "X" -> (prevValue * currentValue).toString()
                "/" -> if (currentValue != 0.0) (prevValue / currentValue).toString() else "Error"
                else -> {
                    operation = ""
                    display
                }
            }
        }
        previousValue = "0"
        operation = null
    }

    val onNumPress : (String) -> Unit = { num ->
        if (display.length <= 8) {
            if (display == "0") {
                if(num == "."){
                    display = "0."
                }
                else {
                display = num
                    }
            } else {
                if (num == ".") {
                    if (!display.contains(".")) {
                        display += num
                }
                } else {
                    display += num
                }
            }
        }

        userIsInTheMiddleOfTyping = true
    }

    val onOperationPress: (String) -> Unit = { op ->
        userIsInTheMiddleOfTyping = false
        operation = op
        previousValue = display
        display = "0"
    }

    val onEqualsPress: (String) -> Unit = {
        calculateResult()
    }

    val onAcPress: (String) -> Unit = {
        display = "0"
        previousValue = "0"
        operation = null
    }

    Column(modifier = modifier){
        Text(modifier = Modifier.fillMaxWidth(),
            text = display,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.displayLarge
        )
        Row {
            CalcButton (
                modifier = Modifier.weight(1f),
                label = "AC",
                onButtonPress = onAcPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+/-",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "%",
                onButtonPress = onOperationPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "÷",
                isOperation = true,
                onButtonPress = onOperationPress
            )
        }
        Row {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "7",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "8",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "9",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "X",
                isOperation = true,
                onButtonPress = onOperationPress
            )
        }
        Row {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "4",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "5",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "6",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "-",
                isOperation = true,
                onButtonPress = onOperationPress
            )
        }
        Row {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "1",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "2",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "3",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+",
                isOperation = true,
                onButtonPress = onOperationPress
            )
        }
        Row {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "0",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = ".",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "=",
                isOperation = true,
                onButtonPress = onEqualsPress
            )
        }}}


@Preview(showBackground = true)
@Composable
fun CalculatorPreview(){
    CalculatorTheme {
        CalculatorView()
    }
}