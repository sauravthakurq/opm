# Contributing to OPM

Thank you for your interest in contributing to **OPM (Orpheus Music)**! Whether you're fixing a bug, adding a feature, or improving documentation, we appreciate your help in building a premium, open-source Android music experience.

---

## Table of Contents
- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Handling Sensitive Information](#handling-sensitive-information)
- [Pull Request Process](#pull-request-process)
- [Issue Guidelines](#issue-guidelines)
- [Coding Standards](#coding-standards)

---

## Code of Conduct
By participating in this project, you agree to abide by our [Code of Conduct](CODE_OF_CONDUCT.md). We are committed to providing a welcoming, inclusive, and professional community.

---

## Getting Started

### Prerequisites
- **Android Studio** (Hedgehog or later)
- **JDK 21** (Required for compiling)
- **Android SDK 26+**
- **Git**
- Familiarity with Kotlin, Jetpack Compose, and MVVM architecture.

### 1. Fork and Clone
1. Fork the repository on GitHub.
2. Clone your fork locally:
   ```bash
   git clone https://github.com/sauravthakurq/opm.git
   cd opm
   ```
3. Add the upstream repository:
   ```bash
   git remote add upstream https://github.com/sauravthakurq/opm.git
   ```

### 2. Development Setup
Create a `local.properties` file for your Android SDK path:
```bash
cp local.properties.template local.properties
```
Edit `local.properties`:
```properties
sdk.dir=/path/to/your/Android/sdk
```

---

## Handling Sensitive Information

**IMPORTANT:** Never commit sensitive files to the repository. The `.gitignore` is configured to exclude them, but always double-check.
- `local.properties` (Contains local SDK paths)
- `app/google-services.json` (Firebase API keys, if you test with Firebase)
- `*.keystore` / `*.jks` (Signing keys)

---

## Pull Request Process

We encourage pull requests! Here is the standard workflow:

### 1. Create a Branch
```bash
git checkout -b feature/your-feature-name
# or
git checkout -b fix/issue-description
```

### 2. Commit Your Changes
Use clear, descriptive, and conventional commit messages:
```bash
git add .
git commit -m "feat: add dynamic color support to mini player"
```

### 3. Test and Build
Ensure your code complies with the project's standards and builds successfully:
```bash
./gradlew assembleUniversalGmsDebug
```

### 4. Push and Open a PR
```bash
git push origin feature/your-feature-name
```
Navigate to your fork on GitHub and click "New Pull Request". Provide a clear description and testing instructions.

---

## Issue Guidelines

### Bug Reports
When reporting a bug, include:
- Clear title and steps to reproduce.
- Expected behavior vs. actual behavior.
- Device model and Android version.
- App version (e.g., v1.5.0).
- Relevant logs or screenshots.

### Feature Requests
When requesting a feature, include:
- Clear description of the proposed functionality.
- The core use-case and why it benefits OPM users.

---

## Coding Standards

### Kotlin & Android
- Follow standard [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html).
- Build UIs using **Jetpack Compose**.
- Utilize **MVVM Architecture** and modern Jetpack libraries.
- Prefer `val` over `var` and use immutable data structures where possible.

### Naming Conventions
- **Classes**: PascalCase (`MusicPlayerViewModel`)
- **Functions/Variables**: camelCase (`playMusic`)
- **Constants**: UPPER_SNAKE_CASE (`MAX_VOLUME`)

---

If you have any questions, feel free to open a Discussion or reach out to the developer. Thank you for contributing to OPM!
