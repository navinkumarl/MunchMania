# Munch Mania

Munch Mania is a native Android application prototype built around sign-in and
account-registration screens. It opens with a splash screen, then lets the user
switch between the two forms. The current implementation is a UI and
client-side-validation demo; it does not connect to an account service.

## Features

- Splash screen that transitions to the main screen after three seconds.
- Sign-in and sign-up forms with animated transitions between them.
- Client-side checks for username length, phone-number length, email format,
  password format, and matching password confirmation.
- Confirmation dialogs for exiting from the sign-in screen or returning from
  sign-up.

Sign-in compares the entered values against fixed sample values in the app
code. Registration validates the form locally but does not create or save an
account. No credentials are required to build the project. The authentication
flow is intended for demonstrating Android UI development and form validation,
not for providing actual user authentication or account security. The values
used by the sign-in check are hard-coded demo placeholders, not credentials to
reuse for real accounts.

## Technology

| Area | Details |
| --- | --- |
| Platform | Android |
| Application language | Java; Java 8 source and target compatibility |
| Android Gradle Plugin | 8.1.1 |
| Gradle wrapper | 8.0 |
| Build scripts | Gradle Kotlin DSL |
| Android SDK | Compile SDK 33; minimum SDK 27; target SDK 33 |
| UI and support libraries | AndroidX AppCompat 1.6.1, Material Components 1.9.0, ConstraintLayout 2.1.4 |
| Tests | JUnit 4.13.2, AndroidX Test JUnit 1.1.5, Espresso 3.5.1 |

## Project structure

```text
.
├── README.md
├── .gitignore
└── Munch Mania App/
    ├── .gitignore
    ├── .idea/
    │   ├── .gitignore
    │   ├── assetWizardSettings.xml
    │   ├── compiler.xml
    │   ├── gradle.xml
    │   └── misc.xml
    ├── app/
    │   ├── .gitignore
    │   ├── build.gradle.kts
    │   ├── proguard-rules.pro
    │   └── src/
    │       ├── main/
    │       │   ├── AndroidManifest.xml
    │       │   ├── java/com/example/food_bytes/
    │       │   │   ├── MainActivity.java
    │       │   │   └── SplashScreen.java
    │       │   └── res/
    │       │       ├── anim/
    │       │       ├── drawable/
    │       │       ├── layout/
    │       │       ├── mipmap-*/
    │       │       ├── values/
    │       │       ├── values-night/
    │       │       └── xml/
    │       ├── test/java/com/example/food_bytes/
    │       │   └── ExampleUnitTest.java
    │       └── androidTest/java/com/example/food_bytes/
    │           └── ExampleInstrumentedTest.java
    ├── build.gradle.kts
    ├── gradle/
    │   └── wrapper/
    │       ├── gradle-wrapper.jar
    │       └── gradle-wrapper.properties
    ├── gradle.properties
    ├── gradlew
    ├── gradlew.bat
    └── settings.gradle.kts
```

`MainActivity` inflates the sign-in and sign-up layouts into a shared
`FrameLayout`, handles form validation and back-navigation dialogs, and applies
the slide animations. `SplashScreen` displays the splash layout and starts
`MainActivity` after its delay. Android layouts, styles, strings, icons, and
animation resources are under `app/src/main/res/`.

The visible app label and Gradle project name are **Munch Mania**. The Android
namespace and application ID remain `com.example.food_bytes`. The splash screen
still uses the existing `food_jet_logo.png` artwork.

## Requirements

- JDK 17 for the Android Gradle Plugin version used by this project.
- Android SDK Platform 33.
- An Android device or emulator to install and run the app. A device or
  emulator is also required for the instrumented test task.

The Gradle wrapper is included, so a separate Gradle installation is not
required.

## Build and run

Run commands from the repository root.

### Windows PowerShell

```powershell
cd "Munch Mania App"
.\gradlew.bat assembleDebug
```

### macOS or Linux

```sh
cd "Munch Mania App"
./gradlew assembleDebug
```

To install the debug build on a connected device or emulator, run
`.\gradlew.bat installDebug` on Windows or `./gradlew installDebug` on
macOS/Linux from the Android project directory.

## Tests

Run the local unit tests from `Munch Mania App`:

```powershell
.\gradlew.bat testDebugUnitTest
```

On macOS or Linux, use `./gradlew testDebugUnitTest`.

The instrumented Android test can be run with
`.\gradlew.bat connectedDebugAndroidTest` on Windows or
`./gradlew connectedDebugAndroidTest` on macOS/Linux when a device or emulator
is available. The current test sources are the standard sample tests: a simple
arithmetic unit test and a package-context instrumentation test. They do not
exercise the sign-in or sign-up flows.

## Current scope

- Sign-in is a local demonstration and does not authenticate against a server.
- Sign-up data is not persisted or sent to a service.
- There is no configured backend, database, external API, or project dataset.
- The splash artwork retains its original Food Jet branding.
- No license file is included in the repository.

Relevant files: [app module configuration](<Munch Mania App/app/build.gradle.kts>),
[project settings](<Munch Mania App/settings.gradle.kts>),
[main activity](<Munch Mania App/app/src/main/java/com/example/food_bytes/MainActivity.java>),
[splash activity](<Munch Mania App/app/src/main/java/com/example/food_bytes/SplashScreen.java>),
[manifest](<Munch Mania App/app/src/main/AndroidManifest.xml>).
