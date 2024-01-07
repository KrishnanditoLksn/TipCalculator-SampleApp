package app.dito.tipcalculatorapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import app.dito.tipcalculatorapp.components.TipTimeLayout
import app.dito.tipcalculatorapp.ui.theme.TipCalculatorAppTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorUITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipCalculatorAppTheme {
                TipTimeLayout()
            }
            composeTestRule.onNodeWithText("Bill Amount").performTextInput("10")
            composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
            val expectedTip = NumberFormat.getCurrencyInstance().format(2)
            composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
                "No node with this text was found."
            )
        }
    }

}

