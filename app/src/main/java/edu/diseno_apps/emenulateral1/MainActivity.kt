package edu.diseno_apps.emenulateral1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                AppToolbar()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiTopAppBar(
    onMenuClick: () -> Unit,
    onPerfilClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text("Mi Primera App") },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, "Abrir menú")
            }
        },
        actions = {
            IconButton(onClick = onPerfilClick) {
                Icon(Icons.Default.Person, "Abrir perfil")
            }
        }
    )
}

@Composable
fun AppToolbar() {

    val navController = rememberNavController()

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val backStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute = backStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp)
            ) {
                Text(
                    text = "Menú de Opciones",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(24.dp)
                )

                HorizontalDivider()

                Spacer(Modifier.height(8.dp))

                NavigationDrawerItem(
                    icon = {
                        Icon(Icons.Default.Home, null)
                    },
                    label = { Text("Inicio") },
                    selected = currentRoute == Destinations.INICIO,
                    onClick = {
                        navController.navigate(Destinations.INICIO) {
                            popUpTo(Destinations.INICIO) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                NavigationDrawerItem(
                    icon = {
                        Icon(Icons.Default.Settings, null)
                    },
                    label = { Text("Configuración") },
                    selected = currentRoute == Destinations.CONFIGURACION,
                    onClick = {
                        navController.navigate(Destinations.CONFIGURACION) {
                            launchSingleTop = true
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                NavigationDrawerItem(
                    icon = {
                        Icon(Icons.Default.Info, null)
                    },
                    label = { Text("Acerca de") },
                    selected = currentRoute == Destinations.ACERCA_DE,
                    onClick = {
                        navController.navigate(Destinations.ACERCA_DE) {
                            launchSingleTop = true
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                MiTopAppBar(
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onPerfilClick = {
                        navController.navigate(Destinations.PERFIL) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) { paddingValues ->

            NavHost(
                navController = navController,
                startDestination = Destinations.INICIO,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                composable(Destinations.INICIO) {
                    InicioScreen()
                }

                composable(Destinations.CONFIGURACION) {
                    ConfiguracionScreen()
                }

                composable(Destinations.PERFIL) {
                    PerfilScreen()
                }

                composable(Destinations.ACERCA_DE) {
                    AcercaDeScreen()
                }
            }
        }
    }
}