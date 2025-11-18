Mini TaskBoard App Assignment
Objective:
Build a small TaskBoard App using Kotlin, Jetpack Compose, Room Database, and Dependency
Injection (Hilt or Koin).
The app should allow users to create, update, delete, and mark tasks as done, and should sync
tasks with a dummy network source.
Requirements Overview:
1. Architecture:
- Use MVVM + Clean Architecture pattern.
- Use Hilt or Koin for Dependency Injection.
- Use Room Database for local data storage.
- Use Repository pattern to abstract data sources.
- Use Coroutines and Flow or StateFlow for asynchronous data handling.
2. Modules:
- core-data: Contains Room entities, DAOs, and database configuration.
- core-network: Contains dummy network layer that simulates API behavior.
- feature-taskboard: Contains UI (Jetpack Compose) and ViewModel layer.
- (Optional) core-common: Shared utilities, constants, and models.
3. Features to Implement:
- Task List Screen:
Display all tasks in a scrollable list with task title, description, and completion status.
Include a Floating Action Button (FAB) to create a new task.
- Add/Edit Task Screen:
Allow users to create a new task or modify an existing one.
Include input validation and UI feedback.
- Task Actions:
  Users should be able to mark tasks as complete/incomplete and delete tasks from the list.
- Dummy Network Sync:
Add a toolbar button for "Sync" which simulates a network call.
The call should fetch dummy tasks and merge or replace local data.
Include simulated delay to mimic network behavior.
4. User Interface:
- Implement UI using Jetpack Compose only.
- Follow Material Design 3 guidelines.
- Use simple navigation between screens using NavHost.
- The UI should react automatically to data changes from Room.
5. Dependency Injection:
Use either Hilt or Koin for dependency injection.
Inject ViewModels, Repositories, DAOs, and the Dummy Network class.
6. Required Libraries:
- Jetpack Compose (Material3, Navigation, Lifecycle)
- Room (with Kotlin coroutines support)
- Hilt or Koin (for DI)
- Kotlin Coroutines and Flow
- AndroidX libraries for ViewModel, LiveData, and Navigation
7. Additional Guidelines:
- Code should follow Kotlin best practices and be modular.
- Use sealed UI state management for loading and empty states.
- Handle error states gracefully with user feedback.
- The app should compile and run without manual setup steps.
Submission:
- Submit a GitHub repository link containing your project.
- Include a short README explaining your architecture and setup instructions.
