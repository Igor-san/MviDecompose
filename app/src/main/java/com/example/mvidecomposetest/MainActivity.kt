package com.example.mvidecomposetest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.defaultComponentContext
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.presentation.DefaultRootComponent
import com.example.mvidecomposetest.ui.content.AddContact
import com.example.mvidecomposetest.ui.content.Contacts
import com.example.mvidecomposetest.ui.content.EditContact
import com.example.mvidecomposetest.ui.content.RootContent
import com.example.mvidecomposetest.ui.theme.MviDecomposeTestTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("STORE_FACTORY", "CREATED MainActivity")

        super.onCreate(savedInstanceState)

        // Create the root component before starting Compose
        val root = DefaultRootComponent(componentContext = defaultComponentContext())


        setContent {
            RootContent(component = root)
        }
    }
}
