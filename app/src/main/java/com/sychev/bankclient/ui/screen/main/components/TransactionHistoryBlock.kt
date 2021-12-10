package com.sychev.bankclient.ui.screen.main.components

import android.text.SpannableString
import android.text.SpannableStringBuilder
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.color
import coil.compose.rememberImagePainter
import com.sychev.bankclient.R
import com.sychev.bankclient.domain.model.currency.Currency
import com.sychev.bankclient.domain.model.currency.CurrencyItem
import com.sychev.bankclient.domain.model.user_data.TransactionHistory
import com.sychev.bankclient.ui.theme.GreyStrokeColor
import com.sychev.bankclient.utils.CurrencyChange
import com.sychev.bankclient.utils.CurrencySign
import com.sychev.bankclient.utils.toMoneyString
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.absoluteValue

@Composable
fun TransactionHistoryBlock(
    modifier: Modifier = Modifier,
    transactions: List<TransactionHistory>,
    selectedCurrency: CurrencyItem?,
    currency: Currency?,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.padding(start = 22.dp),
                text = "History",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(18.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                transactions.forEachIndexed { index: Int, item: TransactionHistory ->
                    TransactionItem(
                        transaction = item,
                        selectedCurrency = selectedCurrency,
                        currency = currency,
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                }
            }
        }
    }
}

@Composable
private fun TransactionItem(
    transaction: TransactionHistory,
    selectedCurrency: CurrencyItem?,
    currency: Currency?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 22.dp, end = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //icon with title row
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                border = BorderStroke(2.dp, GreyStrokeColor),
                shape = CircleShape,
                color = MaterialTheme.colors.primary
            ) {
                val painter = rememberImagePainter(data = transaction.iconUrl, builder = {
                    crossfade(true)
                    error(R.drawable.question_mark_icon)
                })
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painter,
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.width(17.dp))
            Column() {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 11.sp,
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }
        //amount
        Column(horizontalAlignment = Alignment.End) {
            if (selectedCurrency == null || currency == null) {
                Box(
                    modifier = Modifier
                        .height(26.dp)
                        .width(78.dp)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = MaterialTheme.shapes.medium
                        ),
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            selectedCurrency?.let { selectedCurrency ->
                currency?.let { currency ->
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = MaterialTheme.colors.onSurface)) {
                                append("- ${CurrencySign.getCurrencySign(selectedCurrency.charCode)}")
                            }
                            withStyle(SpanStyle(color = MaterialTheme.colors.onPrimary)) {
                                append(
                                    " ${
                                        BigDecimal(
                                            CurrencyChange.changeCurrency(
                                                transaction.amount.toDouble().absoluteValue,
                                                currency.valute.uSD.value,
                                                selectedCurrency.value
                                            )
                                        ).setScale(2, RoundingMode.HALF_EVEN)
                                            .toDouble()
                                            .toMoneyString()
                                    }"
                                )
                            }
                        },
                        style = MaterialTheme.typography.h2,
                    )
                }
            }
                Text(
                    text = "${CurrencySign.getCurrencySign("USD")} ${transaction.amount.toDouble().absoluteValue.toMoneyString()}",
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                )
        }

    }
}

@Composable
fun LoadingTransactionHistoryBlock(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .height(22.dp)
                    .width(73.dp)
                    .padding(start = 22.dp)
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = MaterialTheme.shapes.medium
                    ),
            )
            Spacer(modifier = Modifier.height(18.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                repeat(3) {
                    LoadingTransactionItem()
                    Spacer(modifier = Modifier.height(22.dp))
                }
            }
        }
    }
}

@Composable
private fun LoadingTransactionItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 22.dp, end = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //icon with title row
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                border = BorderStroke(2.dp, GreyStrokeColor),
                shape = CircleShape,
                color = MaterialTheme.colors.primary
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = MaterialTheme.shapes.medium
                        ),
                )
            }
            Spacer(modifier = Modifier.width(17.dp))
            Column() {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .width(40.dp)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = MaterialTheme.shapes.medium
                        ),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .height(19.dp)
                        .width(60.dp)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = MaterialTheme.shapes.medium
                        ),
                )
            }
        }
        //amount
        Column(horizontalAlignment = Alignment.End) {
            Box(
                modifier = Modifier
                    .height(26.dp)
                    .width(78.dp)
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = MaterialTheme.shapes.medium
                    ),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .height(22.dp)
                    .width(40.dp)
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = MaterialTheme.shapes.medium
                    ),
            )

        }

    }
}












