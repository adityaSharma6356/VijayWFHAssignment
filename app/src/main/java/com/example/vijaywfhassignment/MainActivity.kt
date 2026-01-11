package com.example.vijaywfhassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.vijaywfhassignment.presentation.navigation.Home
import com.example.vijaywfhassignment.presentation.navigation.TitleDetails
import com.example.vijaywfhassignment.presentation.ui.HomeScreen
import com.example.vijaywfhassignment.presentation.ui.TitleDetailsScreen
import com.example.vijaywfhassignment.ui.theme.AppColors
import com.example.vijaywfhassignment.ui.theme.VijayWFHAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VijayWFHAssignmentTheme {
                val navController = rememberNavController()
                NavHost(
                    modifier = Modifier.fillMaxSize().background(AppColors.BACKGROUND),
                    navController = navController,
                    startDestination = Home
                ){
                    composable<Home> {
                        HomeScreen(navController)
                    }

                    composable<TitleDetails>{ backStackEntry ->
                        val title = backStackEntry.toRoute<TitleDetails>()
                        TitleDetailsScreen(
                            titleId = title.titleId
                        )
                    }
                }
            }
        }
    }
}
