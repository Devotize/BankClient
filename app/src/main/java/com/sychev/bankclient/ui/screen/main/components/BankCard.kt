package com.sychev.bankclient.ui.screen.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sychev.bankclient.R
import com.sychev.bankclient.utils.CardType
import com.sychev.shared.domain.model.currency.Currency
import com.sychev.shared.domain.model.currency.CurrencyItem
import com.sychev.shared.domain.model.user_data.User
import com.sychev.shared.utils.CurrencyChange
import com.sychev.shared.utils.CurrencySign
import com.sychev.shared.utils.toMoneyString

@Composable
fun BankCard(
    modifier: Modifier = Modifier,
    user: User,
    onClick: () -> Unit,
    currency: Currency? = null,
    selectedCurrency: CurrencyItem? = null,
    hideSensitiveInformation: Boolean = false,
) {
    Surface(
        modifier = modifier,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick)
        ) {
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                modifier = Modifier
                    .padding(start = 22.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val painter = rememberImagePainter(data = CardType.getCardIconRes(user.type))
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painter,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(18.dp))
                val cardNumber = if (hideSensitiveInformation) {
                    "**** **** **** ****"
                } else {
                    user.cardNumber
                }
                Text(
                    text = cardNumber,
                    style = MaterialTheme.typography.h2,
                )

            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val painter = rememberImagePainter(data = R.drawable.user_icon)
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painter,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(
                        text = user.cardholderName,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                Column {
                    Text(
                        text = "VALID THRU",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = user.valid,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
            Spacer(modifier = Modifier.height(21.dp))
            Row(
                modifier = Modifier
                    .padding(start = 22.dp, end = 22.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (selectedCurrency == null || currency == null) {
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .width(126.dp)
                            .background(
                                color = MaterialTheme.colors.background,
                                shape = MaterialTheme.shapes.medium
                            ),
                    )
                }
                currency?.let { currency ->
                    selectedCurrency?.let { currencyItem ->
                        val balanceValueText = if (hideSensitiveInformation) {
                            "******"
                        } else {
                            CurrencySign.getCurrencySign(currencyItem.charCode) +
                                    CurrencyChange.changeCurrencyAndRound(
                                        user.balance,
                                        currency.valute.uSD.value,
                                        currencyItem.value
                                    )
                        }
                        Text(
                            text = balanceValueText
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = "Your balance",
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    val balanceValueText = if (hideSensitiveInformation) {
                        "***"
                    } else {
                        "${CurrencySign.getCurrencySign("USD")}${user.balance.toMoneyString()}"
                    }
                    Text(
                        text = balanceValueText,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
fun LoadingBankCard(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                modifier = Modifier
                    .padding(start = 22.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.background, shape = CircleShape)
                        .size(32.dp),
                )
                Spacer(modifier = Modifier.width(18.dp))
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .width(217.dp)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = MaterialTheme.shapes.medium
                        ),
                )

            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.background,
                                shape = CircleShape
                            )
                            .size(30.dp),
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Box(
                        modifier = Modifier
                            .height(25.dp)
                            .width(99.dp)
                            .background(
                                color = MaterialTheme.colors.background,
                                shape = MaterialTheme.shapes.medium
                            ),
                    )
                }
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = MaterialTheme.shapes.medium
                        ),
                )
            }
            Spacer(modifier = Modifier.height(21.dp))
            Row(
                modifier = Modifier
                    .padding(start = 22.dp, end = 22.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(34.dp)
                        .width(126.dp)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = MaterialTheme.shapes.medium
                        ),
                )
                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    Box(
                        modifier = Modifier
                            .height(51.dp)
                            .width(82.dp)
                            .background(
                                color = MaterialTheme.colors.background,
                                shape = MaterialTheme.shapes.medium
                            ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}