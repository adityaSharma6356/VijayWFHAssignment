
---

# 📱 WatchMode Titles – Android Assignment

An Android app built using **MVVM**, **Jetpack Compose**, **Hilt Dependency Injection**, **Retrofit**, and **Paging 3** to display a paginated list of Movies and TV Shows and fetch detailed information for a selected title.

---

## 🚀 Features

* Fetches data from **WatchMode API**
* Displays **paginated list** of:

    * 🎬 Movies
    * 📺 TV Shows
* Fetches & displays **title details**
* Built with:

    * MVVM clean architecture
    * Repository pattern
    * Use of Coroutines & Flows
    * Kotlin & Jetpack Compose UI
    * Hilt for Dependency Injection
* Proper **error handling**
* Loading indicators + shimmer placeholders
* Snackbar for error messages
* Navigation between list & details screens

---

## 🧩 Architecture

This project follows **MVVM** with separation of concerns:

```
data/
    remote/
        api/
            WatchModeApi
        dto/
            TitleDetailsResponse
            TitleItem
            TitleResponse
        paging/
            TitlePagingSource
    repository/
        TitleRepository

di/
    NetworkModule

presentation/
    navigation/
        MainNavigationSystem
    ui/
        HomeScreen
        TitleDetailsScreen
    viewmodel/
        TitleDetailsViewModel
        TitlesViewModel
util/
    ApiConstants
    ErrorHandler
    Resource
    ComposeUtils
```

Technologies used:

* Kotlin
* Jetpack Compose
* Paging 3
* Retrofit
* OkHttp
* Hilt DI
* Kotlin Coroutines & Flow

---

## 🛠 API Used

WatchMode API sample endpoint:

```
https://api.watchmode.com/v1/list-titles/?apiKey={api_key}&regions=US&sourceIds=203&types=tv_series&page=1&limit=10
```

You will need to:

1. Generate your API key from WatchMode
2. Place it in `ApiConstants.API_KEY`

---

## ⚠️ Error Handling Strategy

The app handles errors gracefully, including:

* No internet connection
* Server errors
* Unexpected response format

Centralized error mapper:

```kotlin
fun Throwable.toReadableMessage(): String {
    return when (this) {
        is IOException ->
            "No internet connection. Please try again."

        is HttpException -> {
            val errorBody = response()?.errorBody()?.string()
            val message = if (!errorBody.isNullOrBlank()) errorBody else message()
            "Server error ${code()}: $message"
        }

        is SerializationException, is ClassCastException ->
            "We received unexpected data from the server."

        else ->
            this.message ?: this.toString()
    }
}
```

---

## 🧪 Unit Tests Included

The project contains unit tests for:

* Error message mapper
* Repository mapping behavior
* Paging data source behavior


Example already implemented test:

* `Throwable.toReadableMessage()` mapping tests
---

## ▶️ Video Demonstration

The submission includes:

* APK for installation
* Video demo showing:

    * App walkthrough
    * API calls in action
    * Error scenarios
    * At least 4 user inputs/interactions

[Video Assignment Overview](https://drive.google.com/file/d/1zImrkGUPiTV5xSvI8gjj-2ZEb7ZC6dmz/view?usp=sharing)

---

## 📦 APK

APK is included in the repository under:

```
/apk/release.apk
```

Install directly on device.

---

## 🧰 How to Run the Project

1. Clone repository
2. Open in Android Studio
3. Add your API key in:

```
ApiConstants.API_KEY = "YOUR_KEY"
```

4. Build & run

Minimum SDK: **30+**

---

## 😅 Challenges Faced

* Pagination handling in release build
* Serialization inconsistencies → moved to Gson field names
* Image URLs failing with Coil fetcher → Null parameters were being received

---

## ✅ Assumptions Made

* UI design is secondary to functionality

---

## 👤 Author

Aditya Sharma
Android Developer — Kotlin | Jetpack Compose

---
