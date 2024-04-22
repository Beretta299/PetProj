package com.karas.petproj.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.karas.petproj.R
import com.karas.petproj.db.data.SwipeAbleUser
import com.karas.petproj.main.MainViewModel

@Composable
fun MainScreenComponent(viewModel: MainViewModel) {

    val swipeAbleUsers = viewModel.userSwipedImage
    for(itemIndex in 0..<swipeAbleUsers.size) {
        val item = swipeAbleUsers.get(itemIndex)
        var nextItem = item
        if(itemIndex != swipeAbleUsers.size - 1) {

            nextItem = swipeAbleUsers[itemIndex + 1]
            Surface(color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)) {
                SwipeCard(content = {
                    ItemCard(item)
                }, nextContent = {
                    ItemCard(nextItem) }
                    , onSwipeLeft = {
                    viewModel.onSwipeLeft()
                }, onSwipeRight = {
                    viewModel.onSwipeRight()
                }, model = item)
            }
        }
    }

}

@Composable
fun ItemCard(swipeAbleUser: SwipeAbleUser = SwipeAbleUser(null, "John DOE","https://i.pinimg.com/236x/c2/fc/9d/c2fc9d585f744fdc86993f2d062848b1.jpg")) {
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxHeight()
        .background(Color.Black, shape = RoundedCornerShape(22.dp)), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(swipeAbleUser.image)
                    .crossfade(true)
                    .build(), error = painterResource(id = R.drawable.outline_brightness_1_24),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(700.dp), contentScale = ContentScale.FillBounds, contentDescription = "")


            }

            CardBigTitle(text = swipeAbleUser.name)
            CardSmallTitle(text = "Balina", modifier = Modifier.padding(8.dp, 0.dp, 0.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ItemCardPreview() {
    ItemCard()
}

@Preview(showSystemUi = true)
@Composable
fun CardBigTitle(text: String = "", color: Color = Color.White) {
    Text(text = text, color = color, modifier = Modifier.padding(8.dp, 0.dp, 0.dp,0.dp), fontSize = 22.sp, fontWeight = FontWeight.Bold)
}
@Preview(showSystemUi = true)
@Composable
fun CardSmallTitle(text: String = "", color: Color = Color.White, modifier: Modifier = Modifier) {
    Text(text = text, color = color, modifier = modifier, fontSize = 18.sp)
}