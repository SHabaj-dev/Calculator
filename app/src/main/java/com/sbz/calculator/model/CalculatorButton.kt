package com.sbz.calculator.model

import androidx.compose.ui.graphics.vector.ImageVector

data class CalculatorButton(
    val text: String? = null,
    val type: CalculatorButtonType,
    val icon: ImageVector? = null,
)

enum class CalculatorButtonType {
    Normal,
    Action,
    Reset
}
