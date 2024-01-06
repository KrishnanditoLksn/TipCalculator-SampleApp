package app.dito.tipcalculatorapp.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import app.dito.tipcalculatorapp.R
import java.text.NumberFormat
import kotlin.math.ceil


@Composable
fun TipTimeLayout(modifier: Modifier = Modifier) {

    var amountInput by remember {
        mutableStateOf(" ")
    }

    var billInput by remember {
        mutableStateOf(" ")
    }

    var switchRoundTip by remember {
        mutableStateOf(false)
    }


    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val bill = billInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, bill, switchRoundTip)

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
            label = R.string.bill_amount,
            value = amountInput,
            onValueChange = { amountInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )

        Spacer(modifier.height(10.dp))
        EditNumberField(
            label = R.string.hows_the_service,
            value = billInput,
            onValueChange = { billInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Default
            ),
            modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        Spacer(modifier.height(40.dp))
        /*
        update the value of tip
         */
        Text(
            text = stringResource(R.string.calculate_tip, tip),
            style = MaterialTheme.typography.displaySmall,
        )

        Spacer(modifier.height(25.dp))

        RoundedTipSwitch(
            checkRound = switchRoundTip,
            onRoundAppChange = { switchRoundTip = it }
        )
    }
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
): String {
    var tip = tipPercent / 100 * amount
    /*
    if switch was true  , round up the tip
     */
    if (roundUp) {
        tip = ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}


@Composable
fun EditNumberField(
    /*
    indicates that the integer to be passed is
    a string resource from the values/strings.xml file
     */
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {/*
    The value parameter is a text box that displays the string value you pass here
     */
    TextField(
        label = { Text(stringResource(label)) },
        value = value,
        singleLine = true,
        /*
        The onValueChange parameter is the lambda callback that's triggered when the user enters text in the text box
         */
        onValueChange = onValueChange,
        /*
        selected num-pad
         */
        keyboardOptions = keyboardOptions,
        modifier = modifier,
    )
    Spacer(modifier.height(4.dp))
}

@Composable
fun RoundedTipSwitch(
    modifier: Modifier = Modifier,
    checkRound: Boolean,
    /*
     take boolean , return nothing
     */
    onRoundAppChange: (Boolean) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip))
        Switch(
            checked = checkRound,
            onCheckedChange = onRoundAppChange,
            modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End),
        )
    }
}