package com.example.practica221

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica221.ui.theme.Practica221Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica221Theme {
                ViewContainer()
            }
        }
    }
}

/*/ Preview Horizontal
// device = "spec:width=411dp,height=891dp,orientation=landscape"
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    device = "spec:width=411dp,height=891dp,orientation=portrait",
    showBackground = true,
    locale = "es-rMX"
)
@Composable
fun ViewContainer(){
    val intentContext = LocalContext.current;
    val snackbarHostState = remember { SnackbarHostState() };
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },

        topBar = {
            TopAppBar(
                title = {Text(stringResource(R.string.title))},
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    scrolledContainerColor = MaterialTheme.colorScheme.secondary,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    subtitleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_btn_desc),
                            tint = Color.White
                        )
                    }
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.bottom_app_bar),
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }
        },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Mostrar")},
                icon = { Icon(Icons.Default.Add, contentDescription = "") },
                onClick={
                    scope.launch {
                        val result = snackbarHostState
                            .showSnackbar(
                                message = intentContext.getString(R.string.texto_snackbar),
                                actionLabel = "Action",
                                duration = SnackbarDuration.Indefinite
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {

                            }
                            SnackbarResult.Dismissed -> {

                            }
                        }
                    }
                }
            )
        }


        //modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ContentView()
    }
}

@Composable
fun ContentView(){
    var counter by rememberSaveable { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.formula1),
                contentDescription = stringResource(R.string.image_desc),
                modifier= Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(8.dp)
            )

            Row(modifier = Modifier.padding(top = 2.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_directions_car_24),
                    contentDescription = "Car",
                    modifier = Modifier
                        .clickable { counter++ }
                        .padding(start = 12.dp)
                )
                Text(
                    text = counter.toString(),
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Text(
                text = stringResource(R.string.materia),
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.nombre),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource( R.string.apellido),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                item {
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(R.drawable.imagen1),
                        contentDescription = "Mobile Apps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(R.drawable.imagen2),
                        contentDescription = "Mobile Apps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(R.drawable.imagen3),
                        contentDescription = "Mobile Apps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(R.drawable.imagen4),
                        contentDescription = "Mobile Apps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(R.drawable.imagen5),
                        contentDescription = "Mobile Apps"
                    )
                }
            }
        }
    }
}
