package com.sychev.bankclient.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sychev.bankclient.data.local.data_store.StoreUserCardNumber
import com.sychev.bankclient.ui.navigation.Screen
import com.sychev.bankclient.ui.screen.main.MainScreen
import com.sychev.bankclient.ui.screen.main.MainViewModel
import com.sychev.bankclient.ui.screen.main.cards.CardsScreen
import com.sychev.bankclient.ui.theme.BankClientTheme
import com.sychev.bankclient.utils.ConnectionLiveData
import com.sychev.bankclient.utils.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var connectionLiveData: ConnectionLiveData
    private lateinit var storeUserCardNumber: StoreUserCardNumber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData = ConnectionLiveData(this)
        storeUserCardNumber = StoreUserCardNumber(this)
        lifecycleScope.launch {
                storeUserCardNumber.getUserCardNumber.collect { str ->
                    str?.let {
                        viewModel.setPreviouslySelectedUserCardNumber(it)
                    }
                    this.cancel()
                }
        }
        setContent {
            BankClientTheme {
                val navController = rememberNavController()
                val networkHasConnection = connectionLiveData.observeAsState(initial = false).value
                var isDataUpdated by remember { mutableStateOf(false) }
                Scaffold { paddingValues ->
                    Column(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        if (!networkHasConnection) {
                            if (viewModel.users.value == null && viewModel.currency.value == null) {
                                viewModel.refreshData()
                            }
                            isDataUpdated = false
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colors.primary),
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(top = 8.dp, bottom = 8.dp)
                                        .align(Alignment.Center),
                                    text = "No internet connection",
                                    style = MaterialTheme.typography.subtitle2,
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                        networkHasConnection.also {
                            if (it && !isDataUpdated) {
                                Log.d(TAG, "onCreate: viewModel refresh data called")
                                viewModel.refreshData()
                                isDataUpdated = true
                            }
                        }
                        NavHost(
                            navController = navController,
                            startDestination = Screen.MainScreen.route
                        ) {
                            composable(route = Screen.MainScreen.route) {
                                MainScreen(
                                    viewModel = viewModel,
                                    goToChooseCardFragment = {
                                        navController.navigate(Screen.CardsScreen.route)
                                    }
                                )
                            }
                            composable(route = Screen.CardsScreen.route) {
                                CardsScreen(
                                    viewModel = viewModel,
                                    onBackClick = {
                                        onBackPressedDispatcher.onBackPressed()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        lifecycleScope.launch{
            withContext(IO) {
                viewModel.currentUser.value?.let { user ->
                    storeUserCardNumber.saveUserCardNumber(user.cardNumber)

                }
            }
        }
    }
}
