# Weather Application

## 🌦 Overview

The **Weather Application** is an Android app that provides comprehensive and real-time weather information for your **current location** or any **searched city**. Designed with **MVVM architecture** for clean and maintainable code, it utilizes **Retrofit** for seamless API communication, ensuring a smooth and reliable user experience.

---

## ✨ Features

### 🌍 Current Location Weather

- Automatically detects your **current location** and displays city-specific weather details.
- If the user denies location permission, the app defaults to displaying weather for **New Delhi**.

### 🔍 Search Functionality

- Search for weather updates in **any city worldwide**.

### 📊 Detailed Weather Metrics

- Get insights on:
  - **Sea Level** 🌊
  - **Humidity** 💧
  - **Cloud Cover** ☁️
  - **Wind Speed** 🌬️
  - **Sunrise & Sunset Timings** 🌅

### 🛠️ Clean Architecture

- Built using the **MVVM pattern** for better scalability and maintainability.

### 🔗 API Integration

- Fetches accurate, real-time weather data using the **OpenWeatherMap API**.

### 📅 5-Day Weather Forecast in RecyclerView

- Displays a 5-day weather forecast in an elegant **RecyclerView** layout.

---

## 💻 Technologies Used

| **Technology**                | **Purpose**                      |
| ----------------------------- | -------------------------------- |
| **Kotlin**                    | Programming Language             |
| **MVVM Architecture**         | Clean, scalable app architecture |
| **Retrofit**                  | Networking and API integration   |
| **Android Location Services** | Current location detection       |

---

## 🌐 API Information

The app interacts with the **OpenWeatherMap API** to fetch weather data for both current locations and searched cities.

---

## 🚶‍♂️ User Journey

1. **Welcome & Permissions**  
   - Upon launching the app, the user is prompted to grant location permissions.  
   - If permissions are granted, the app fetches weather data for the current location.  
   - If denied, the app defaults to displaying weather data for **New Delhi**.  

2. **Search for a City**  
   - Users can enter a city's name in the search bar to get weather updates for that location.  
   - A clean UI ensures the search results are displayed instantly.  

3. **Detailed Weather Insights**  
   - The main screen presents real-time weather metrics such as temperature, humidity, cloud cover, and wind speed.  
   - Users can also see sunrise and sunset timings for the selected location.  

4. **Explore the 5-Day Forecast**  
   - Navigate through an interactive **RecyclerView** to view the weather forecast for the next five days.  
   - Each day's data includes temperature, humidity, and wind conditions.  

5. **Seamless Experience**  
   - The app's **MVVM architecture** ensures a smooth and responsive user experience.  
   - Lightweight and optimized, the app is designed for easy use on all supported Android devices.  

---
