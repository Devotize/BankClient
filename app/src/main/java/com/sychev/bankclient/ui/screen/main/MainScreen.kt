package com.sychev.bankclient.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sychev.bankclient.ui.screen.main.components.BankCard
import com.sychev.bankclient.ui.screen.main.components.CurrencyCard
import com.sychev.bankclient.ui.screen.main.components.LoadingBankCard
import com.sychev.bankclient.ui.screen.main.components.LoadingCurrencyCard
import com.sychev.bankclient.ui.screen.main.components.LoadingTransactionHistoryBlock
import com.sychev.bankclient.ui.screen.main.components.TopBarMain
import com.sychev.bankclient.ui.screen.main.components.TransactionHistoryBlock
import kotlin.math.roundToInt

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    goToChooseCardFragment: () -> Unit,
) {
    val users = viewModel.users.value
    val currentUser = viewModel.currentUser.value
    val selectedCurrency = viewModel.selectedCurrency.value
    val currency = viewModel.currency.value
    val loadingUsers = viewModel.loadingUsers.value
    val loadingCurrency = viewModel.loadingCurrency.value
    val refreshState =
        rememberSwipeRefreshState(isRefreshing = (users == null && currency == null && loadingCurrency || loadingUsers))
    val bankCardOffsetX = remember { mutableStateOf(0f) }
    val hideSensitiveInformation = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        TopBarMain()
        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                viewModel.refreshData()
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (currentUser == null && viewModel.loadingUsers.value) {
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                        LoadingBankCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                        )
                    }
                }
                currentUser?.let { user ->
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                        BankCard(
                            modifier = Modifier
                                .offset { IntOffset(bankCardOffsetX.value.roundToInt(), 0) }
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                                .draggable(
                                    orientation = Orientation.Horizontal,
                                    state = rememberDraggableState { delta ->
                                        bankCardOffsetX.value += delta
                                    },
                                    onDragStopped = {
                                        bankCardOffsetX.value = 0f
                                        hideSensitiveInformation.value =
                                            !hideSensitiveInformation.value
                                    }
                                ),
                            user = user,
                            onClick = {
                                goToChooseCardFragment()
                            },
                            currency = currency,
                            selectedCurrency = selectedCurrency,
                            hideSensitiveInformation = hideSensitiveInformation.value
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
                if (currency == null && loadingCurrency) {
                    item {
                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .width(157.dp)
                                .background(
                                    color = MaterialTheme.colors.background,
                                    shape = MaterialTheme.shapes.medium
                                ),
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            repeat(3) {
                                LoadingCurrencyCard()
                            }
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
                currency?.let { currency ->
                    item {
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = "Change currency",
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            currency.valute.gBP.let {
                                CurrencyCard(
                                    modifier = Modifier
                                        .size(100.dp),
                                    currencyItem = it,
                                    isSelected = it == selectedCurrency,
                                    onClick = {
                                        viewModel.onSelectedCurrencyChange(it)
                                    },
                                )
                            }
                            currency.valute.eUR.let {
                                CurrencyCard(
                                    modifier = Modifier
                                        .size(100.dp),
                                    currencyItem = it,
                                    isSelected = it == selectedCurrency,
                                    onClick = {
                                        viewModel.onSelectedCurrencyChange(it)
                                    },
                                )
                            }
                            currency.valute.rUB.let {
                                CurrencyCard(
                                    modifier = Modifier
                                        .size(100.dp),
                                    currencyItem = it,
                                    isSelected = it == selectedCurrency,
                                    onClick = {
                                        viewModel.onSelectedCurrencyChange(it)
                                    },
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
                item {
                    currentUser?.let {
                        TransactionHistoryBlock(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            transactions = it.transactionHistory,
                            selectedCurrency = selectedCurrency,
                            currency = currency
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
                if (currentUser == null && viewModel.loadingUsers.value) {
                    item {
                        LoadingTransactionHistoryBlock(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }

    }

}