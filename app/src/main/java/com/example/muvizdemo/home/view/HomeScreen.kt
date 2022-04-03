package com.example.muvizdemo.home.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.muvizdemo.R
import com.example.muvizdemo.home.model.HomeTrendingTvDayResultResponse
import com.example.muvizdemo.common.model.MDConstants.IMAGE_BASE_UR
import com.example.muvizdemo.home.viewModel.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val pagingItems = viewModel.resultResponseState
        .value.collectAsLazyPagingItems()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (image, column) = createRefs()
        Image(
            painterResource(
                id = R.drawable.icon_home_top_bar
            ),
            contentDescription = "top bar image",
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        LazyColumn(modifier = Modifier
            .padding(top = 16.dp)
            .constrainAs(column) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            }
        ) {
            items(pagingItems) { item ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(width = 2.dp, color = Color(0xFFE9E9E9)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9F)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    ItemConstraintLayout(item)
                }
            }
        }
    }
}

@Composable
private fun ItemConstraintLayout(item: HomeTrendingTvDayResultResponse?) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (itemImage, itemDivider, itemText) = createRefs()
        Image(
            painter = rememberImagePainter(
                data = "$IMAGE_BASE_UR/${item?.posterPath}",
                builder = {
                    placeholder(R.drawable.ic_loading)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.75f)
                .constrainAs(itemImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(itemDivider.top)
                    height = Dimension.fillToConstraints
                },
            contentScale = ContentScale.Crop,
            contentDescription = "Movie Banner"
        )
        Divider(modifier = Modifier
            .background(Color.Gray)
            .height(1.dp)
            .constrainAs(itemDivider) {
                top.linkTo(itemImage.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(itemText.top)
            })
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight(.25f)
                .fillMaxWidth()
                .constrainAs(itemText) {
                    top.linkTo(itemDivider.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
        ) {
            Text(
                item?.name ?: "",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}