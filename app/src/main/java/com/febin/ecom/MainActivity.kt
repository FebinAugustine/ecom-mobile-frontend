package com.febin.ecom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ecom.core.ui.theme.EcomTheme
import com.febin.ecom.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcomTheme {
                NavGraph()
            }
        }
    }
}
