package com.aadi.makelifegreat.ui.screens.home
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aadi.makelifegreat.ui.theme.MlgMint
import com.aadi.makelifegreat.ui.theme.MlgPanel
import com.aadi.makelifegreat.ui.theme.MlgSky
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(onStartRoutine:(Long)->Unit,onOpenActiveRoutine:(Long)->Unit,onOpenActiveFocus:(Long)->Unit,onGoToRoutines:()->Unit,onGoToTodos:()->Unit,onGoToFocus:()->Unit,onGoToBlocker:()->Unit,onNewTodo:()->Unit,onNewRoutine:()->Unit,onOpenStats:()->Unit={},onOpenSchedule:()->Unit={},onOpenNotes:()->Unit={},onOpenFitness:()->Unit={},onOpenMusic:()->Unit={},viewModel:HomeViewModel=hiltViewModel()){
 val s by viewModel.uiState.collectAsState()
 val hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
 val greeting=when(hour){in 5..11->"Good morning, Aadi";in 12..17->"Good afternoon, Aadi";else->"Good evening, Aadi"}
 val date=SimpleDateFormat("EEEE, d MMMM",Locale.getDefault()).format(Date())
 LazyColumn(Modifier.fillMaxSize(),contentPadding=PaddingValues(16.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
  item{Card(colors=CardDefaults.cardColors(containerColor=MlgPanel),shape=MaterialTheme.shapes.extraLarge){Box(Modifier.background(Brush.linearGradient(listOf(MlgPanel,MaterialTheme.colorScheme.primaryContainer))).padding(20.dp)){Column(verticalArrangement=Arrangement.spacedBy(8.dp)){Row(verticalAlignment=Alignment.CenterVertically){Column(Modifier.weight(1f)){Text(greeting,style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.Black);Text(date,color=MaterialTheme.colorScheme.onSurfaceVariant)};Icon(Icons.Filled.Insights,null,tint=MlgMint,modifier=Modifier.size(34.dp))};Text("Your day, scheduled. Your progress, local. Your life, in one place.",style=MaterialTheme.typography.bodyLarge);Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){FilledTonalButton(onClick=onOpenSchedule){Icon(Icons.Filled.Schedule,null);Spacer(Modifier.width(6.dp));Text("Today")};FilledTonalButton(onClick=onNewRoutine){Icon(Icons.Filled.Alarm,null);Spacer(Modifier.width(6.dp));Text("Alarm")}}}}}}
  item{Text("My day",style=MaterialTheme.typography.titleMedium,fontWeight=FontWeight.Bold)}
  item{Row(horizontalArrangement=Arrangement.spacedBy(10.dp),modifier=Modifier.fillMaxWidth()){Quick("Schedule","Classes & routines",Icons.Filled.School,onOpenSchedule,Modifier.weight(1f));Quick("Tasks",s.openTodoCount.toString()+" open",Icons.Filled.AddTask,onGoToTodos,Modifier.weight(1f))}}
  item{Row(horizontalArrangement=Arrangement.spacedBy(10.dp),modifier=Modifier.fillMaxWidth()){Quick("Fitness","Run & workouts",Icons.Filled.DirectionsRun,onOpenFitness,Modifier.weight(1f));Quick("Notes","Ideas & school",Icons.Filled.EditNote,onOpenNotes,Modifier.weight(1f))}}
  item{Row(horizontalArrangement=Arrangement.spacedBy(10.dp),modifier=Modifier.fillMaxWidth()){Quick("Focus","Deep work",Icons.Filled.SelfImprovement,onGoToFocus,Modifier.weight(1f));Quick("Music","Local playback",Icons.Filled.Headphones,onOpenMusic,Modifier.weight(1f))}}
  item{Text("Automation",style=MaterialTheme.typography.titleMedium,fontWeight=FontWeight.Bold)}
  item{Card(colors=CardDefaults.cardColors(containerColor=MlgPanel),shape=MaterialTheme.shapes.large){Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){Auto("Wake-up alarm","Morning routine starts at your chosen time",Icons.Filled.Alarm);Auto("Class / study","Use timed routines to launch your workflow",Icons.Filled.School);Auto("School mode","Scheduled focus and app-control rules",Icons.Filled.Timer);Auto("Device-safe controls","Uses Android permissions instead of bypasses",Icons.Filled.Security)}}}
  item{Text("Progress",style=MaterialTheme.typography.titleMedium,fontWeight=FontWeight.Bold)}
  item{Row(horizontalArrangement=Arrangement.spacedBy(10.dp)){Metric("Tasks",s.openTodoCount.toString(),"open",Modifier.weight(1f));Metric("Blocked",s.blockedAppCount.toString(),"rules",Modifier.weight(1f));Metric("Screen",if(s.usage.permissionGranted)screen(s.usage.totalScreenTimeMinutes) else "—","today",Modifier.weight(1f))}}
 }}
@Composable private fun Quick(t:String,sub:String,icon:androidx.compose.ui.graphics.vector.ImageVector,on:()->Unit,modifier:Modifier){Card(onClick=on,modifier=modifier,colors=CardDefaults.cardColors(containerColor=MlgPanel),shape=MaterialTheme.shapes.large){Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(7.dp)){Icon(icon,null,tint=MlgSky,modifier=Modifier.size(28.dp));Text(t,fontWeight=FontWeight.Bold);Text(sub,style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant)}}}
@Composable private fun Auto(t:String,sub:String,icon:androidx.compose.ui.graphics.vector.ImageVector){Row(verticalAlignment=Alignment.CenterVertically){Icon(icon,null,tint=MlgSky,modifier=Modifier.size(25.dp));Spacer(Modifier.width(12.dp));Column{Text(t,fontWeight=FontWeight.SemiBold);Text(sub,style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant)}}}
@Composable private fun Metric(t:String,v:String,h:String,m:Modifier){Card(modifier=m,colors=CardDefaults.cardColors(containerColor=MlgPanel),shape=MaterialTheme.shapes.medium){Column(Modifier.padding(13.dp)){Text(v,style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Black);Text(t,fontWeight=FontWeight.SemiBold);Text(h,style=MaterialTheme.typography.labelSmall,color=MaterialTheme.colorScheme.onSurfaceVariant)}}}
private fun screen(m:Long)=if(m>=60)(m/60).toString()+"h "+(m%60)+"m" else m.toString()+"m"
}