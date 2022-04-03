package com.example.muvizdemo.common.model

import com.example.muvizdemo.R
import com.example.muvizdemo.destinations.Destination
import com.example.muvizdemo.destinations.HomeScreenDestination
import com.example.muvizdemo.destinations.LoginScreenDestination

sealed class BottomNavigationItemModel(
    var title: String, var icon: Int,
    var destination: Destination
) {
    object Home : BottomNavigationItemModel(
        title = "首頁",
        icon = R.drawable.ic_home,
        destination = HomeScreenDestination
    )

    object Collect : BottomNavigationItemModel(
        title = "我的收藏",
        icon = R.drawable.ic_collect,
        destination = LoginScreenDestination
    )

    object Shopping : BottomNavigationItemModel(
        title = "購物車",
        icon = R.drawable.ic_shopping,
        destination = LoginScreenDestination
    )

    object Search : BottomNavigationItemModel(
        title = "搜尋",
        icon = R.drawable.ic_search,
        destination = LoginScreenDestination
    )

    object Member : BottomNavigationItemModel(
        title = "會員專區",
        icon = R.drawable.ic_member,
        destination = LoginScreenDestination
    )
}