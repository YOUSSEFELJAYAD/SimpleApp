# Simple App

## Overview

Simple App is an Android application designed with a modern user interface and robust functionality. It leverages various libraries to enhance performance, streamline development, and provide a rich user experience.

## Libraries Used

### Jetpack Compose
- **Purpose**: To build the UI using a declarative approach, allowing for a responsive and flexible design.
- **Key Components**: UI components like buttons, text fields, and layouts.

### Dagger Hilt
- **Purpose**: For dependency injection, which simplifies code management and improves testability.
- **Usage**: Managing dependencies efficiently throughout the app.

### Retrofit
- **Purpose**: To handle network requests and data fetching.
- **Features**: Supports REST APIs and integrates seamlessly with Kotlin Coroutines for asynchronous operations.

### Firebase
- **Purpose**: A comprehensive suite for backend services.
    - **Authentication**: Simplifies user login and registration.
    - **Analytics**: Provides insights into user behavior and app performance.
    - **Crashlytics**: Helps in tracking and managing app crashes.
    - **Performance Monitoring**: Assists in monitoring app performance in real-time.

### Coil
- **Purpose**: An image loading library that efficiently loads and displays images in the app.
- **Benefits**: Lightweight and supports modern Android development practices.

### Navigation Component
- **Purpose**: Manages app navigation and deep linking.
- **Benefits**: Simplifies the implementation of navigation flows within the app.

### Kotlin Serialization
- **Purpose**: Facilitates the conversion of Kotlin objects to and from JSON.
- **Usage**: Streamlines data handling in network requests and responses.

## Getting Started

### Installation

To run the app, clone the repository and open it in Android Studio. Ensure that you have the required libraries and dependencies configured in the `build.gradle` file.

### Service Account for Google Services

**Note**: You need to add a service account for Google services to enable functionalities like Firebase Authentication and Analytics. Follow the Firebase documentation to set up a service account and download the JSON credentials file.

### Running the Application

Simply run the app on an emulator or a physical device after ensuring all configurations are correctly set up.

## Workflow

### Overview
The deployment process for Simple App is automated using GitHub Actions. The workflow consists of two main jobs: **test** and **distribute**.

### Jobs

#### Job: test
- **Purpose**: To run unit tests before the deployment process.
- **Steps**:
    1. **Checkout the repository** to access the code.
    2. **Set up JDK 17** using the `actions/setup-java` action.
    3. **Grant execute permissions** for the Gradle wrapper.
    4. **Run unit tests** (currently a placeholder step).

#### Job: distribute
- **Purpose**: To prepare and deploy the app bundle to Google Play.
- **Dependencies**: It runs after the **test** job is completed successfully.
- **Steps**:
    1. **Checkout the repository** again.
    2. **Set up JDK 17**.
    3. **Get and increment version codes**:
        - Reads the current `versionCode` and `versionName` from `build.gradle.kts`.
        - Increments `versionCode` and updates the `versionName` by bumping the patch version.
    4. **Commit the version bump** changes to the repository.
    5. **Grant execute permissions** for Gradle.
    6. **Assemble the release bundle** using Gradle.
    7. **Sign the release bundle** using the `r0adkll/sign-android-release` action.
    8. **Set up authorization** with Google Play by creating a service account JSON file.
    9. **Deploy the signed bundle** to Google Play using the `r0adkll/upload-google-play` action.

### Notes
- Ensure that you have configured all necessary secrets (like `ANDROID_KEYSTORE`, `KEYSTORE_PASSWORD`, etc.) in your GitHub repository settings.
- The workflow triggers on pushes to the `master` branch, making it suitable for continuous deployment.

This setup helps streamline your deployment process, reducing manual effort and minimizing the risk of errors.
