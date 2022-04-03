package com.example.muvizdemo.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.muvizdemo.common.model.BottomNavigationItemModel

@Composable
fun MDScaffold(
    controller: NavController,
    showBottomBar: Boolean = true,
    list: List<BottomNavigationItemModel> = listOf(
        BottomNavigationItemModel.Home,
        BottomNavigationItemModel.Collect,
        BottomNavigationItemModel.Shopping,
        BottomNavigationItemModel.Search,
        BottomNavigationItemModel.Member
    ),
    content: @Composable (paddingValues: PaddingValues) -> Unit,
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(
                    backgroundColor = Color(0xFFF7F7F7),
                    contentColor = Color.White,
                    elevation = 5.dp
                ) {
                    val navBackStackEntry by controller.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    list.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontSize = 11.sp
                                )
                            },
                            selectedContentColor = Color(0xFF000000),
                            unselectedContentColor = Color(0xFF999999),
                            alwaysShowLabel = true,
                            selected = currentDestination?.route?.contains(item.destination.route) == true,
                            onClick = {
                                controller.navigate(item.destination.route) {
                                    controller.graph.startDestinationRoute?.let { screen_route ->
                                        popUpTo(screen_route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}