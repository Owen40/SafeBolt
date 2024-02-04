package com.example.safebolt

import android.icu.text.ListFormatter.Width
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.CurrencyBitcoin
import androidx.compose.material.icons.rounded.CurrencyPound
import androidx.compose.material.icons.rounded.CurrencyRuble
import androidx.compose.material.icons.rounded.CurrencyRupee
import androidx.compose.material.icons.rounded.CurrencyYen
import androidx.compose.material.icons.rounded.Euro
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebolt.data.Currency
import com.example.safebolt.ui.theme.GreenStart

val currencies = listOf(
    Currency(
        name = "USD",
        buy = 157.00f,
        sell = 157.78f,
        icon = Icons.Rounded.AttachMoney
    ),
    Currency(
        name = "EURO",
        buy = 171.75f,
        sell = 172.68f,
        icon = Icons.Rounded.Euro
    ),
    Currency(
        name = "POUND",
        buy = 198.42f,
        sell = 199.47f,
        icon = Icons.Rounded.CurrencyPound
    ),
    Currency(
        name = "YEN (100)",
        buy = 109.84f,
        sell = 110.41f,
        icon = Icons.Rounded.CurrencyYen
    ),
    Currency(
        name = "RUPEE",
        buy = 1.88f,
        sell = 1.89f,
        icon = Icons.Rounded.CurrencyRupee
    ),
    Currency(
        name = "RUBLE",
        buy = 1.76f,
        sell = 1.84f,
        icon = Icons.Rounded.CurrencyRuble
    )
)

@Preview
@Composable
fun CurrenciesSection() {
    var isVisible by remember {
        mutableStateOf(false)
    }
    var iconState by remember {
        mutableStateOf(Icons.Rounded.KeyboardArrowUp)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        isVisible = !isVisible
                        if (isVisible) {
                            iconState = Icons.Rounded.KeyboardArrowUp
                        } else {
                            iconState = Icons.Rounded.KeyboardArrowDown
                        }
                    }
            ) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = iconState,
                    contentDescription = "Currencies",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Currencies",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )

        if (isVisible) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                    .background(MaterialTheme.colorScheme.background)
            ) {
                val boxWithConstraintsScope = this
                val width = boxWithConstraintsScope.maxWidth / 3

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                         modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.width(width),
                            text = "Currency",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            modifier = Modifier.width(width),
                            text = "Buy",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.End
                        )
                        Text(
                            modifier = Modifier.width(width),
                            text = "Sell",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn{
                        items(currencies.size) { index ->
                            CurrencyItem(
                                index = index,
                                width = width
                            )
                        }
                    }
                }
            }
        }
    } }
}

@Composable
fun CurrencyItem(index: Int, width: Dp) {
    val currency = currencies[index]

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.width(width)
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(GreenStart)
                    .padding(4.dp)
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = currency.icon,
                    contentDescription = currency.name,
                    tint = Color.White
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = currency.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.End
            )
        }
        Text(
            modifier = Modifier
                .width(width)
                .padding(start = 10.dp),
            text = "Kes ${currency.buy}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.End
        )
        Text(
            modifier = Modifier
                .width(width)
                .padding(start = 10.dp),
            text = "Kes ${currency.sell}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.End
        )
    }
}