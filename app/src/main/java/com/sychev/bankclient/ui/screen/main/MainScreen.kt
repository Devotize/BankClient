package com.sychev.bankclient.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sychev.bankclient.ui.screen.main.components.*

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
    val refreshState = rememberSwipeRefreshState(isRefreshing = (users == null && currency == null && loadingCurrency || loadingUsers))

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
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            user = user,
                            onClick = {
                                goToChooseCardFragment()
                            },
                            currency = currency,
                            selectedCurrency = selectedCurrency,
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