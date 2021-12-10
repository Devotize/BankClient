package com.sychev.bankclient.ui.screen.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sychev.bankclient.domain.model.currency.CurrencyItem
import com.sychev.bankclient.utils.CurrencySign

@Composable
fun CurrencyCard(
    modifier: Modifier = Modifier,
    currencyItem: CurrencyItem,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
   Surface(
       modifier = modifier
           .size(100.dp),
       color = if (isSelected)
           MaterialTheme.colors.secondary
       else
           MaterialTheme.colors.primary,
       shape = MaterialTheme.shapes.large,
       elevation = 2.dp,
   ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick)){
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = CurrencySign.getCurrencySign(currencyItem.charCode),
                    style = MaterialTheme.typography.body1,
                    color = if (isSelected)
                        MaterialTheme.colors.primary
                    else
                        MaterialTheme.colors.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = currencyItem.charCode,
                    style = MaterialTheme.typography.body2,
                    color = if (isSelected)
                        MaterialTheme.colors.primary
                    else
                        MaterialTheme.colors.onSurface,
                )
            }
        }
    }
}

@Composable
fun LoadingCurrencyCard() {
    Surface(
        modifier = Modifier
            .size(100.dp),
        elevation = 2.dp,
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.large
    ) {
        Box(modifier = Modifier
            .fillMaxSize()){
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(24.dp)
                        .background(color = MaterialTheme.colors.background, shape = MaterialTheme.shapes.medium),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .width(32.dp)
                        .background(color = MaterialTheme.colors.background, shape = MaterialTheme.shapes.medium),
                )
            }
        }
    }
}