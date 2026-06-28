# Setup Instructions

This document provides instructions for setting up the **OPM (Orpheus Music)** project for local development.

---

## Prerequisites

- **Android Studio** (latest stable version recommended)
- **Android SDK** (API level 34+)
- **JDK 21**
- **Git**

---

## Initial Setup

### 1. Clone the Repository

Clone the OPM repository to your local machine:

```bash
git clone https://github.com/sauravthakurq/opm.git
cd opm
```

### 2. Configure Local Properties

Create a `local.properties` file from the provided template:

```bash
cp local.properties.template local.properties
```

Edit `local.properties` and set your Android SDK path:

```properties
sdk.dir=/path/to/your/android/sdk
```

**Example SDK Paths:**
- **macOS:** `/Users/username/Library/Android/sdk`
- **Linux:** `/home/username/Android/sdk`
- **Windows:** `C:\\Users\\username\\AppData\\Local\\Android\\sdk`

---

## Building the Project

Open the project in Android Studio or build it directly from the command line using the Gradle wrapper.

### Debug Builds
To build the Universal GMS (Google Mobile Services) debug variant, run:

```bash
./gradlew assembleUniversalGmsDebug
```

*(On Windows, use `.\gradlew.bat` instead of `./gradlew`)*

### Release Builds
Release builds require keystore signing credentials. These can be set as environment variables or provided in your CI environment.

```bash
# Example release build command
./gradlew assembleUniversalGmsRelease
```

---

## Troubleshooting

### Build Fails with "SDK location not found"
Make sure you've successfully created the `local.properties` file in the root of the project and that `sdk.dir` points to the correct, absolute path of your Android SDK.

### Gradle Sync Issues
If you encounter unresolved dependencies or Gradle caching errors, try cleaning and rebuilding:

```bash
./gradlew clean
./gradlew build
```

---

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, the development workflow, and the process for submitting pull requests.

## License

This project is licensed under the [GPL-3.0 License](LICENSE).
