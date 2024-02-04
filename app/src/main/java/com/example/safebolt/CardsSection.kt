package com.example.safebolt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebolt.data.Card
import com.example.safebolt.ui.theme.BlueEnd
import com.example.safebolt.ui.theme.BlueStart
import com.example.safebolt.ui.theme.GreenEnd
import com.example.safebolt.ui.theme.GreenStart
import com.example.safebolt.ui.theme.OrangeEnd
import com.example.safebolt.ui.theme.OrangeStart
import com.example.safebolt.ui.theme.PurpleEnd
import com.example.safebolt.ui.theme.PurpleStart

val items = listOf(
    Card(
        cardType = "VISA",
        cardNumber = "3867 5150 8679 8994",
        cardName = "Business",
        balance = 45340,
        color = getGradient(GreenStart, GreenEnd),
    ),
    Card(
        cardType = "MASTERCARD",
        cardNumber = "4113 1097 4626 6214",
        cardName = "Savings",
        balance = 33340,
        color = getGradient(BlueStart, BlueEnd),
    ),
    Card(
        cardType = "VISA",
        cardNumber = "5726 8079 1953 4682",
        cardName = "School",
        balance = 40340,
        color = getGradient(OrangeStart, OrangeEnd),
    ),
    Card(
        cardType = "MASTERCARD",
        cardNumber = "4136 1383 4404 4098",
        cardName = "Trips",
        balance = 20340,
        color = getGradient(PurpleStart, PurpleEnd),
    ),
)

fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}

@Preview
@Composable
fun CardsSection() {
    LazyRow{
        items(items.size) { index ->
            CardItem(index)
        }
    }
}

@Composable
fun CardItem(
    index: Int
) {
    val card = items[index]
    var lastItemPaddingEnd = 0.dp
    if (index == items.size - 1) {
        lastItemPaddingEnd = 16.dp
    }

    var image = painterResource(id = R.drawable.ic_visa)
    if (card.cardType == "MASTERCARD") {
        image = painterResource(id = R.drawable.ic_mastercard)
    }

    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = lastItemPaddingEnd)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(card.color)
                .width(250.dp)
                .height(160.dp)
                .clickable { }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Image(
                painter = image,
                contentDescription = card.cardName,
                modifier = Modifier.width(60.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = card.cardName,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "KES ${card.balance}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = card.cardNumber,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}