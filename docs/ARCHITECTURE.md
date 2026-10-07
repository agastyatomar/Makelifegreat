# Architecture

MakeLifeGreat uses Kotlin, Jetpack Compose, Material 3, MVVM/Clean Architecture patterns, Room, Hilt, WorkManager, AlarmManager and DataStore.

## Layers

**UI:** Compose screens, navigation and ViewModels.

**Application:** routines, focus, sleep, tasks and progress orchestration.

**Data:** Room entities/DAOs/repositories and local settings.

**System:** alarms, notifications, boot rescheduling, DND and app-blocking services.

Scheduled features must tolerate reboot, process death, missed alarms, time changes and permission changes.
