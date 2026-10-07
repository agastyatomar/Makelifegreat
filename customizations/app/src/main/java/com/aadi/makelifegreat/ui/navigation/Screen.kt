package com.aadi.makelifegreat.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.ViewDay
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object Routines : Screen("routines", "Routines", Icons.Filled.ViewDay)
    data object Todos : Screen("todos", "Todos", Icons.Filled.CheckCircle)
    data object Blocker : Screen("blocker", "Control", Icons.Filled.Block)
    data object Focus : Screen("focus", "Focus", Icons.Filled.SelfImprovement)
}
object SettingsRoutes { const val SETTINGS = "settings" }
object OnboardingRoutes { const val ONBOARDING = "onboarding" }
object StatsRoutes { const val STATS = "stats" }
object SleepRoutes { const val SLEEP = "sleep"; const val CREATE_LINKED_ROUTINE = "sleep/routine/create/{linkTarget}"; fun createLinkedRoutine(linkTarget: String) = "sleep/routine/create/$linkTarget" }
object LifeRoutes { const val SCHEDULE = "life/schedule"; const val NOTES = "life/notes"; const val FITNESS = "life/fitness"; const val MUSIC = "life/music" }
object RoutineRoutes { const val LIST="routines"; const val CREATE="routine/create"; const val EDIT="routine/edit/{routineId}"; const val ACTIVE="routine/active/{routineId}"; fun edit(id:Long)="routine/edit/$id"; fun active(id:Long)="routine/active/$id" }
object TodoRoutes { const val LIST="todos"; const val CREATE="todo/create"; const val EDIT="todo/edit/{todoId}"; fun edit(id:Long)="todo/edit/$id" }
object BlockerRoutes { const val LIST="blocker"; const val SETTINGS="blocker/settings"; const val DISCLOSURE="blocker/disclosure/{type}"; fun disclosure(type:String)="blocker/disclosure/$type" }
object FocusRoutes { const val LIST="focus"; const val CREATE="focus/create"; const val EDIT="focus/edit/{sessionId}"; const val ACTIVE="focus/active/{sessionId}"; fun edit(id:Long)="focus/edit/$id"; fun active(id:Long)="focus/active/$id" }
val bottomNavScreens = listOf(Screen.Home, Screen.Routines, Screen.Todos, Screen.Blocker, Screen.Focus)
