package com.sbz.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbz.calculator.model.CalculatorButton
import com.sbz.calculator.model.CalculatorButtonType
import com.sbz.calculator.ui.theme.CalculatorTheme
import com.sbz.calculator.ui.theme.Cyan
import com.sbz.calculator.ui.theme.Red

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme(dynamicColor = false) {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primary),
                        color = MaterialTheme.colorScheme.secondary
                    ) {

                        val calculatorButton = remember {
                            mutableStateListOf(
                                CalculatorButton("AC", CalculatorButtonType.Reset),
                                CalculatorButton("C", CalculatorButtonType.Reset),
                                CalculatorButton("%", CalculatorButtonType.Normal),
                                CalculatorButton("/", CalculatorButtonType.Action),

                                CalculatorButton("7", CalculatorButtonType.Normal),
                                CalculatorButton("8", CalculatorButtonType.Normal),
                                CalculatorButton("9", CalculatorButtonType.Normal),
                                CalculatorButton("X", CalculatorButtonType.Action),

                                CalculatorButton("4", CalculatorButtonType.Normal),
                                CalculatorButton("5", CalculatorButtonType.Normal),
                                CalculatorButton("6", CalculatorButtonType.Normal),
                                CalculatorButton("-", CalculatorButtonType.Action),

                                CalculatorButton("1", CalculatorButtonType.Normal),
                                CalculatorButton("2", CalculatorButtonType.Normal),
                                CalculatorButton("3", CalculatorButtonType.Normal),
                                CalculatorButton("+", CalculatorButtonType.Action),

                                CalculatorButton(
                                    icon = Icons.Outlined.Refresh,
                                    type = CalculatorButtonType.Reset
                                ),
                                CalculatorButton("0", CalculatorButtonType.Normal),
                                CalculatorButton(".", CalculatorButtonType.Normal),
                                CalculatorButton("=", CalculatorButtonType.Action),
                            )
                        }

                        val (input, setInput) = remember {
                            mutableStateOf<String?>(null)
                        }
                        val (uiText, setUiText) = remember {
                            mutableStateOf("0")
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.secondary),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Column {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    text = uiText,
                                    fontSize = 46.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                                Spacer(modifier = Modifier.height(32.dp))
                                LazyVerticalGrid(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                                        .background(MaterialTheme.colorScheme.primary)
                                        .padding(8.dp),
                                    columns = GridCells.Fixed(4),
                                    verticalArrangement = Arrangement.spacedBy(16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    contentPadding = PaddingValues(8.dp)
                                ) {
                                    items(calculatorButton) {
                                        CalcButton(
                                            it,
                                            onClick = {
                                                when (it.type) {
                                                    CalculatorButtonType.Normal -> {
                                                        // Handle number and decimal input
                                                        if (it.text != null) {
                                                            val currentDisplay = if (uiText == "0") "" else uiText
                                                            val newText = currentDisplay + it.text
                                                            setUiText(newText)
                                                            setInput((input ?: "") + it.text)
                                                        }
                                                    }

                                                    CalculatorButtonType.Action -> {
                                                        if (it.text == "=") {
                                                            // Calculate result when equals is pressed
                                                            if (input != null && input.isNotEmpty()) {
                                                                viewModel.setSecondNumber(input.toDoubleOrNull() ?: 0.0)
                                                                val result = viewModel.getResult()
                                                                
                                                                // Format result to remove unnecessary decimals
                                                                val formattedResult = if (result % 1.0 == 0.0) {
                                                                    result.toInt().toString()
                                                                } else {
                                                                    result.toString()
                                                                }
                                                                
                                                                setUiText(formattedResult)
                                                                setInput(formattedResult)
                                                                viewModel.resetAll()
                                                            }
                                                        } else {
                                                            // Handle operators (+, -, X, /)
                                                            if (input != null && input.isNotEmpty()) {
                                                                if (viewModel.firstNumber.value != null && viewModel.action.value.isNotEmpty()) {
                                                                    // If there's already a calculation in progress, compute it first
                                                                    viewModel.setSecondNumber(input.toDoubleOrNull() ?: 0.0)
                                                                    val result = viewModel.getResult()
                                                                    
                                                                    val formattedResult = if (result % 1.0 == 0.0) {
                                                                        result.toInt().toString()
                                                                    } else {
                                                                        result.toString()
                                                                    }
                                                                    
                                                                    setUiText(formattedResult)
                                                                    viewModel.setFirstNumber(result)
                                                                    viewModel.setAction(it.text ?: "")
                                                                } else {
                                                                    // Store first number and action
                                                                    viewModel.setFirstNumber(input.toDoubleOrNull() ?: 0.0)
                                                                    viewModel.setAction(it.text ?: "")
                                                                    setUiText(uiText + " ${it.text} ")
                                                                }
                                                                setInput(null)
                                                            }
                                                        }
                                                    }

                                                    CalculatorButtonType.Reset -> {
                                                        // Reset calculator
                                                        if (it.text == "C" && uiText.isNotEmpty()) {
                                                            // C button: delete last character
                                                            val newText = uiText.dropLast(1)
                                                            setUiText(if (newText.isEmpty()) "0" else newText)
                                                            if (input != null && input.isNotEmpty()) {
                                                                setInput(input.dropLast(1))
                                                            }
                                                        } else {
                                                            // AC or Refresh: clear all
                                                            setUiText("0")
                                                            setInput(null)
                                                            viewModel.resetAll()
                                                        }
                                                    }
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            /*Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            ) {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(R.drawable.ic_nightmode),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(R.drawable.ic_dark_mode),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }*/
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CalcButton(button: CalculatorButton, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxHeight()
            .aspectRatio(1f)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        val contentColor =
            when (button.type) {
                CalculatorButtonType.Normal -> MaterialTheme.colorScheme.onSecondary
                CalculatorButtonType.Action -> Red
                else -> Cyan
            }
        if (button.text != null) {
            Text(
                button.text,
                color = contentColor,
                fontWeight = FontWeight.Bold,
                fontSize = if (button.type == CalculatorButtonType.Action) 25.sp else 20.sp
            )
        } else {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = button.icon!!,
                contentDescription = null,
                tint = contentColor
            )
        }
    }
}

