# Mini TaskBoard App

A multi‑module sample Android application built with **Kotlin**, **Jetpack Compose**, **Room**, and **Hilt Dependency Injection**. The project demonstrates Clean Architecture, modularization, offline-first storage, and dummy network syncing.

---

##  **Objective**

Build a simple TaskBoard application that allows users to:

* Create tasks
* Update tasks
* Delete tasks
* Mark tasks as completed
* Sync tasks with a dummy network source (simulated API)

---

## **Architecture Overview**

The project follows **MVVM + Clean Architecture**.

###  **Key Technologies**

* **Kotlin**
* **Jetpack Compose** (UI)
* **Room Database** (local storage)
* **Hilt** (Dependency Injection)
* **Coroutines + Flow/StateFlow** (async & reactive streams)
* **Repository Pattern** (abstract data sources)

---

## **Modules**

This project uses a multi‑module setup:

### **1. app**

* Application module
* Includes `MultiModuleApplication.kt`
* Hosts navigation and Compose setup

### **2. data**

Contains all data‑related logic:

* Room entities & DAOs
* Database configuration
* Dummy network layer
* Repository implementations
* DI modules (Room, Network, Repository)

### **3. domain**

Pure business logic module:

* Domain models
* Repository interfaces
* Use cases
* DI for use‑case providers

### **4. presentation (feature-taskboard)**

Handles:

* UI using Jetpack Compose
* ViewModels
* Navigation
* Screens: Task List, Add/Edit Task
* State management

### **5. utils/common**

Contains shared classes:

* Utilities
* Constants

---

##  **Features Implemented**

### **1. Task List Screen**

* Shows all tasks in a scrollable list
* Displays: **title**, **description**, **completion status**
* Includes **Floating Action Button (FAB)** → Add new task

### **2. Add/Edit Task Screen**

* Create a new task or modify existing one
* Form validation
* Compose‑based UI feedback

### **3. Task Actions**

* Mark complete / incomplete
* Delete tasks

### **4. Dummy Network Sync**

* Toolbar **Sync** button
* Simulated network delay
* Fetches dummy tasks & merges with local Room DB

---

## **User Interface**

* Built entirely with **Jetpack Compose**
* Follows **Material Design 3**
* Uses **NavHost** for navigation
* UI auto‑updates with Room flow data

---

## **Dependency Injection (Hilt)**

Injected components:

* ViewModels
* Repositories
* DAOs
* Dummy Network Service

---

## **Required Libraries**

* Jetpack Compose
* Room
* Hilt
* Kotlin Coroutines
* Kotlin Flow / StateFlow

---

## **Project Folder Structure**

```
Multi-Module-TaskBoard-Sample
├── app
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com/example/multimodule/MultiModuleApplication.kt
│
├── build.gradle.kts
│
├── data
│   ├── build.gradle.kts
│   ├── consumer-rules.pro
│   ├── proguard-rules.pro
│   └── src
│       └── main/java/com/example/data
│           ├── api/NetworkService.kt
│           ├── di
│           │   ├── NetworkModule.kt
│           │   ├── qualifiers.kt
│           │   └── RepositoryModule.kt
│           └── repository/TopHeadlineRepositoryImpl.kt
│
├── domain
│   ├── build.gradle.kts
│   ├── consumer-rules.pro
│   ├── proguard-rules.pro
│   └── src
│       └── main/java/com/example/domain
│           ├── di/module/UseCaseModule.kt
│           ├── model
│           │   ├── Article.kt
│           │   ├── Source.kt
│           │   └── TopHeadlinesResponse.kt
│           ├── repository/TopHeadlineRepository.kt
│           └── usecase
│               ├── GetCommentUseCase.kt
│               ├── GetPostUseCase.kt
│               ├── GetTopHeadlineUseCase.kt
│               └── PostDetailUseCases.kt
│
├── gradle
│   ├── libs.versions.toml
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
│
├── presentationn
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/main/java/com/example/presentationn/ui
│       ├── base
│       │   ├── CommonUi.kt
│       │   ├── NewsNavigation.kt
│       │   └── UiState.kt
│       ├── main/MainActivity.kt
│       ├── postdetails
│       ├── theme
│       │   ├── Color.kt
│       │   ├── Theme.kt
│       │   └── Type.kt
│       └── topheadlines
│           ├── TopHeadlineScreen.kt
│           └── TopHeadlineViewModel.kt
│
├── settings.gradle.kts
│
└── utils
    ├── build.gradle.kts
    ├── consumer-rules.pro
    ├── proguard-rules.pro
    └── src/main/java/com/example/utils/AppConstant.kt

```

---

## **Preview**

![Homepage](https://raw.githubusercontent.com/manmohanpal3/multi-module-taskboard-sample/main/app/src/main/res/assets/homepage.png)


---

## *Contributions**

Pull requests and improvements are welcome.

