package app.dito.tipcalculatorapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import app.dito.tipcalculatorapp.R
import java.text.NumberFormat


@Composable
fun TipTimeLayout(modifier: Modifier = Modifier) {

    var amountInput by remember {
        mutableStateOf(" ")
    }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount)

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = modifier.height(10.dp))

        Text(
            text = stringResource(R.string.calculate_tip, tip),
            modifier = modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start),
        )
        EditNumberField(
            value = amountInput,
            onValueChange = { amountInput = it },
            modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = stringResource(R.string.calculate_tip, tip),
            style = MaterialTheme.typography.displaySmall,
        )
    }
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}


@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit, modifier: Modifier = Modifier
) {/*
    The value parameter is a text box that displays the string value you pass here
     */
    TextField(
        label = { Text(stringResource(R.string.tip_amount)) },
        value = value,
        singleLine = true,
        /*
        The onValueChange parameter is the lambda callback that's triggered when the user enters text in the text box
         */
        onValueChange = onValueChange,
        /*
        selected num-pad
         */
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier,
    )
    Spacer(modifier = Modifier.height(4.dp))
}
