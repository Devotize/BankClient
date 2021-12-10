package com.sychev.bankclient.ui.screen.main.cards

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sychev.bankclient.domain.model.user_data.User
import com.sychev.bankclient.ui.screen.main.MainViewModel
import com.sychev.bankclient.utils.CardType
import com.sychev.bankclient.utils.TAG

@Composable
fun CardsScreen(
    viewModel: MainViewModel,
    onBackClick: () -> Unit,
) {
    val users = viewModel.users.value
    val currentUser = viewModel.currentUser.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            elevation = 4.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                IconButton(
                    modifier = Modifier
                        .padding(start = 17.dp)
                        .align(Alignment.CenterStart),
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondary
                    )
                }

                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Мои карты",
                    style = MaterialTheme.typography.h4
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            users?.let { users ->
                Log.d(TAG, "CardsScreen: $users not null")
                itemsIndexed(users.users) { index, user ->
                    UserData(
                        user = user,
                        isSelected = user == currentUser,
                        onClick = {
                            viewModel.onCurrentUserChange(user)
                            onBackClick()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun UserData(
    modifier: Modifier = Modifier,
    user: User,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val painter = rememberImagePainter(data = CardType.getCardIconRes(user.type))
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painter,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = user.cardNumber,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(color = MaterialTheme.colors.secondary, shape = CircleShape),
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colors.background)
        )
    }
}