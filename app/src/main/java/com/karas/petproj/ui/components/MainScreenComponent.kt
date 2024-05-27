package com.karas.petproj.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
@Preview(showSystemUi = true)
fun ShowItemCard() {
    var expanded by remember { mutableStateOf(false) }
    val offsetY by animateFloatAsState(targetValue = if (expanded) -100f else 0f)

    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxHeight()
        .clickable {
            expanded = !expanded
        }
        .background(Color.Black, shape = RoundedCornerShape(22.dp)), verticalAlignment = Alignment.Top) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.imgag),
                    modifier = Modifier
                        .animateContentSize()
                        .fillMaxWidth()
                        .height(if (expanded) 700.dp else 300.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        .graphicsLayer { translationY = offsetY }, contentScale = ContentScale.FillBounds, contentDescription = "")
            }
            CardBigTitle(text = "NAME")
            CardSmallTitle(text = "BalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DA", modifier = Modifier.padding(8.dp, 0.dp, 0.dp))
            CategoryDivider(iconId = Icons.Filled.AccountCircle, text = "Activities")
            CategoryContainer(content = {
                CategoryLabel(catIcon = Icons.Filled.Build, catLabel = "Engineering", iconTint = Color.White, tint = Color.Green)
                CategoryLabel(catIcon = Icons.Filled.Call, catLabel = "Networking", iconTint = Color.White, tint = Color.Blue)
                CategoryLabel(catIcon = Icons.Filled.Place, catLabel = "Traveling", iconTint = Color.White, tint = Color.Cyan)
            })
            CategoryDivider(iconId = Icons.Filled.Face, text = "Here for")
            CategoryContainer(content = {
                CategoryLabel(catIcon = Icons.Filled.Build, catLabel = "Dating", iconTint = Color.White, tint = Color.Green)
                CategoryLabel(catIcon = Icons.Filled.Call, catLabel = "ONS", iconTint = Color.White, tint = Color.Blue)
                CategoryLabel(catIcon = Icons.Filled.Place, catLabel = "FWB", iconTint = Color.White, tint = Color.Cyan)
                CategoryLabel(catIcon = Icons.Filled.Place, catLabel = "Relationship", iconTint = Color.White, tint = Color.Cyan)
            })
        }
    }
}

@Composable
fun ItemCard(swipeAbleUser: SwipeAbleUser = SwipeAbleUser(null, "John DOE","https://i.pinimg.com/236x/c2/fc/9d/c2fc9d585f744fdc86993f2d062848b1.jpg")) {
    var expanded by remember { mutableStateOf(true) }
    val offsetY by animateFloatAsState(targetValue = if (expanded) -100f else 0f)

    DisposableEffect(swipeAbleUser.image) {
        expanded = true
        onDispose {

        }
    }

    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxHeight()
        .clickable {
            expanded = !expanded
        }
        .background(Color.Black, shape = RoundedCornerShape(22.dp)), verticalAlignment = Alignment.Top) {
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
                        .animateContentSize()
                        .fillMaxWidth()
                        .height(if (expanded) 800.dp else 300.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        .graphicsLayer { translationY = offsetY }, contentScale = ContentScale.FillBounds, contentDescription = "")
            }
            CardBigTitle(text = "NAME")
            CardSmallTitle(text = "BalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DABalinaAAS:DA:SDA:D:A:DA", modifier = Modifier.padding(8.dp, 0.dp, 0.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp))
            CategoryDivider(text = "Activities")
        }
    }
}

@Composable
fun ItemCardPreview() {
    ItemCard()
}

@Composable
fun CardBigTitle(text: String = "", color: Color = Color.White) {
    Text(text = text, color = color, modifier = Modifier.padding(8.dp, 0.dp, 0.dp,0.dp), fontSize = 22.sp, fontWeight = FontWeight.Bold)
}
@Composable
fun CardSmallTitle(text: String = "", color: Color = Color.White, modifier: Modifier = Modifier) {
    Text(text = text, color = color, modifier = modifier, fontSize = 18.sp, overflow = TextOverflow.Ellipsis)
}

//@Preview(showSystemUi = true)
@Composable
fun CategoryDivider(iconId: ImageVector = Icons.Filled.Favorite,text: String = "AAAA", color: Color = Color.White) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        Icon(imageVector = iconId, modifier = Modifier.padding(0.dp, 0.dp,8.dp, 0.dp), tint = Color.White, contentDescription = "")
        Spacer(modifier = Modifier
            .background(Color.Gray)
            .width(16.dp)
            .height(2.dp))
        Text(text = text, color = color, modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp), fontSize = 18.sp, overflow = TextOverflow.Ellipsis)
        Spacer(modifier = Modifier
            .background(Color.Gray)
            .weight(1f)
            .height(2.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
fun CategoryLabel(tint: Color = Color.Red, iconTint: Color = Color.Black, catIcon: ImageVector = Icons.Filled.Face, catLabel: String = "Fun") {
    Row(verticalAlignment = Alignment.CenterVertically, modifier =
    Modifier
        .padding(8.dp)
        .border(
            width = 2.dp,
            color = tint,
            shape = RoundedCornerShape(15.dp)
        )
        .padding(7.dp)) {
        Icon(imageVector = catIcon, modifier = Modifier.padding(0.dp, 0.dp,8.dp, 0.dp), tint = iconTint, contentDescription = "")
        Spacer(modifier = Modifier
            .width(2.dp))
        Text(text = catLabel, color = iconTint, fontSize = 16.sp)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryContainer (content: @Composable () -> Unit){

    FlowRow(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
        content()
    }
}
