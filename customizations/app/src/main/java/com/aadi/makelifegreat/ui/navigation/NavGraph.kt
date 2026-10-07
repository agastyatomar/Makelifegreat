package com.aadi.makelifegreat.ui.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aadi.makelifegreat.ui.screens.blocker.*
import com.aadi.makelifegreat.ui.screens.focus.*
import com.aadi.makelifegreat.ui.screens.home.HomeScreen
import com.aadi.makelifegreat.ui.screens.life.*
import com.aadi.makelifegreat.ui.screens.onboarding.OnboardingScreen
import com.aadi.makelifegreat.ui.screens.routine.*
import com.aadi.makelifegreat.ui.screens.settings.SettingsScreen
import com.aadi.makelifegreat.ui.screens.sleep.*
import com.aadi.makelifegreat.ui.screens.stats.StatsScreen
import com.aadi.makelifegreat.ui.screens.todo.*
@Composable fun NavGraph(navController:NavHostController,modifier:Modifier=Modifier,startDestination:String=Screen.Home.route){
 NavHost(navController,startDestination,modifier=modifier){
  composable(Screen.Home.route){fun tab(r:String){navController.navigate(r){popUpTo(Screen.Home.route){saveState=true};launchSingleTop=true;restoreState=true}};HomeScreen({id->navController.navigate(RoutineRoutes.active(id))},{id->navController.navigate(RoutineRoutes.active(id))},{id->navController.navigate(FocusRoutes.active(id))},{tab(RoutineRoutes.LIST)},{tab(TodoRoutes.LIST)},{tab(FocusRoutes.LIST)},{tab(BlockerRoutes.LIST)},{navController.navigate(TodoRoutes.CREATE)},{navController.navigate(RoutineRoutes.CREATE)},{navController.navigate(StatsRoutes.STATS)},{navController.navigate(LifeRoutes.SCHEDULE)},{navController.navigate(LifeRoutes.NOTES)},{navController.navigate(LifeRoutes.FITNESS)},{navController.navigate(LifeRoutes.MUSIC)})}
  composable(LifeRoutes.SCHEDULE){ScheduleScreen({navController.popBackStack()},{navController.navigate(RoutineRoutes.LIST)},{navController.navigate(TodoRoutes.LIST)})}
  composable(LifeRoutes.NOTES){NotesScreen{navController.popBackStack()}}
  composable(LifeRoutes.FITNESS){FitnessScreen{navController.popBackStack()}}
  composable(LifeRoutes.MUSIC){MusicScreen{navController.popBackStack()}}
  composable(StatsRoutes.STATS){StatsScreen{navController.popBackStack()}}
  composable(OnboardingRoutes.ONBOARDING){OnboardingScreen({create->val t=if(create)RoutineRoutes.CREATE else Screen.Home.route;navController.navigate(t){popUpTo(OnboardingRoutes.ONBOARDING){inclusive=true}}},{type->navController.navigate(BlockerRoutes.disclosure(type))})}
  composable(RoutineRoutes.LIST){RoutineListScreen({navController.navigate(RoutineRoutes.CREATE)},{id->navController.navigate(RoutineRoutes.edit(id))},{id->navController.navigate(RoutineRoutes.active(id))})}
  composable(RoutineRoutes.CREATE){CreateRoutineScreen{navController.popBackStack()}}
  composable(RoutineRoutes.EDIT,listOf(navArgument("routineId"){type=NavType.LongType})){CreateRoutineScreen{navController.popBackStack()}}
  composable(RoutineRoutes.ACTIVE,listOf(navArgument("routineId"){type=NavType.LongType})){ActiveRoutineScreen{navController.navigate(RoutineRoutes.LIST){popUpTo(RoutineRoutes.LIST){inclusive=true}}}}
  composable(TodoRoutes.LIST){TodoListScreen({navController.navigate(TodoRoutes.CREATE)},{id->navController.navigate(TodoRoutes.edit(id))})}
  composable(TodoRoutes.CREATE){CreateTodoScreen{navController.popBackStack()}}
  composable(TodoRoutes.EDIT,listOf(navArgument("todoId"){type=NavType.LongType})){CreateTodoScreen{navController.popBackStack()}}
  composable(BlockerRoutes.LIST){BlockerListScreen({navController.navigate(BlockerRoutes.SETTINGS)},{type->navController.navigate(BlockerRoutes.disclosure(type))})}
  composable(BlockerRoutes.SETTINGS){BlockerSettingsScreen{navController.popBackStack()}}
  composable(BlockerRoutes.DISCLOSURE,listOf(navArgument("type"){type=NavType.StringType})){b->DisclosureScreen(b.arguments?.getString("type")?:""){navController.popBackStack()}}
  composable(FocusRoutes.LIST){FocusListScreen({navController.navigate(FocusRoutes.CREATE)},{id->navController.navigate(FocusRoutes.edit(id))},{id->navController.navigate(FocusRoutes.active(id))},{navController.navigate(SleepRoutes.SLEEP)})}
  composable(FocusRoutes.CREATE){CreateFocusScreen{navController.popBackStack()}}
  composable(FocusRoutes.EDIT,listOf(navArgument("sessionId"){type=NavType.LongType})){CreateFocusScreen{navController.popBackStack()}}
  composable(FocusRoutes.ACTIVE,listOf(navArgument("sessionId"){type=NavType.LongType})){ActiveFocusScreen{navController.navigate(FocusRoutes.LIST){popUpTo(FocusRoutes.LIST){inclusive=true}}}}
  composable(SleepRoutes.SLEEP){SleepScreen({navController.popBackStack()},{target->navController.navigate(SleepRoutes.createLinkedRoutine(target))})}
  composable(SleepRoutes.CREATE_LINKED_ROUTINE,listOf(navArgument("linkTarget"){type=NavType.StringType})){b->val e=navController.getBackStackEntry(SleepRoutes.SLEEP);val vm:SleepViewModel=hiltViewModel(e);CreateRoutineScreen({navController.popBackStack()},{id->if((b.arguments?.getString("linkTarget")?:"bedtime")=="bedtime")vm.setBedtimeRoutine(id)else vm.setMorningRoutine(id)})}
  composable(SettingsRoutes.SETTINGS){SettingsScreen({navController.popBackStack()},{navController.navigate(BlockerRoutes.SETTINGS)},{type->navController.navigate(BlockerRoutes.disclosure(type))},{navController.navigate(OnboardingRoutes.ONBOARDING)},{navController.navigate(SleepRoutes.SLEEP)})}
 }}
