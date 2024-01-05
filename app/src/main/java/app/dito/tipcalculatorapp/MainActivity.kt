package app.dito.tipcalculatorapp

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import app.dito.tipcalculatorapp.components.TipTimeLayout
import app.dito.tipcalculatorapp.ui.theme.TipCalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    TipCalculatorPreview()
                }
            }
        }
    }
}

@Composable
fun TipCalculatorApp() {
    TipTimeLayout(modifier = Modifier.background(color = Color.Gray))
}

/*
SET DARK MODE
 */
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
)
/*
SET LIGHT MODE
 */
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
)
annotation class ModePreviews

@ModePreviews
@Composable
fun TipCalculatorPreview() {
    TipCalculatorAppTheme {
        TipCalculatorApp()
    }
}
