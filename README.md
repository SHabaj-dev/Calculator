# Calculator - Jetpack Compose Android App

A modern calculator application built with Jetpack Compose to learn and practice Android's declarative UI framework.

## 📱 Features

- ✨ Modern UI built with Jetpack Compose
- 🎨 Material 3 Design with custom color scheme
- 🌓 Light and Dark theme support
- 🧮 Basic arithmetic operations (+, -, ×, ÷)
- 📐 Clean architecture with ViewModel pattern
- 🔄 State management using StateFlow

## 🛠️ Tech Stack

- **Language**: Kotlin 2.0.21
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Build System**: Gradle (Kotlin DSL)
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 36

### Dependencies

- AndroidX Core KTX
- AndroidX Lifecycle Runtime
- Jetpack Compose BOM (2024.09.00)
- Material 3
- Accompanist System UI Controller

## 🚀 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- **Android Studio**: Hedgehog (2023.1.1) or later
- **JDK**: 11 or higher
- **Android SDK**: API Level 24-36
- **Gradle**: 8.13.0 (included in wrapper)

### Installation

1. **Clone the repository**
   ```bash
   git clone <your-repository-url>
   cd Calculator
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned `Calculator` directory
   - Click "OK"

3. **Sync Gradle**
   - Android Studio will automatically prompt to sync
   - Or manually: File → Sync Project with Gradle Files

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button (▶️) or press `Shift + F10`
   - Select your device and click "OK"

## 📂 Project Structure

```
Calculator/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/sbz/calculator/
│   │   │   │   ├── model/
│   │   │   │   │   └── CalculatorButton.kt      # Button data model
│   │   │   │   ├── ui/theme/
│   │   │   │   │   ├── Color.kt                 # Color definitions
│   │   │   │   │   ├── Theme.kt                 # App theme
│   │   │   │   │   └── Type.kt                  # Typography
│   │   │   │   ├── AppViewModel.kt              # Calculator logic
│   │   │   │   └── MainActivity.kt              # Main UI
│   │   │   ├── res/
│   │   │   │   ├── drawable/                    # Icons and drawables
│   │   │   │   ├── values/                      # Strings, colors, themes
│   │   │   │   └── xml/                         # Data extraction rules
│   │   │   └── AndroidManifest.xml
│   │   └── test/                                # Unit tests
│   └── build.gradle.kts                         # App-level build config
├── gradle/
│   └── libs.versions.toml                       # Version catalog
├── build.gradle.kts                             # Project-level build config
├── settings.gradle.kts                          # Gradle settings
├── .gitignore                                   # Git ignore rules
└── README.md                                    # This file
```

## 🎨 Color Scheme

- **Black200**: `#22252D` - Primary dark background
- **Black500**: `#292D36` - Secondary dark background
- **Cyan**: `#26DABD` - Reset button color
- **Red**: `#D66060` - Action button color
- **DarkWhite**: `#F9F9F9` - Light theme background

## 🔧 Build Configuration

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### Run Tests
```bash
./gradlew test
```

### Clean Build
```bash
./gradlew clean build
```

## 📝 Key Components

### AppViewModel
Manages the calculator state including:
- First and second numbers
- Current operation
- Result calculation
- State reset functionality

### MainActivity
Main composable UI containing:
- Calculator display
- Button grid (4×5 layout)
- Theme toggle icons
- Material 3 Scaffold

### CalculatorButton
Data model defining:
- Button text or icon
- Button type (Normal, Action, Reset)
- Button behavior

## 🎯 Usage

1. **Enter numbers**: Tap numeric buttons (0-9)
2. **Perform operations**: Tap operation buttons (+, -, ×, ÷)
3. **Get result**: Tap equals (=) button
4. **Clear**: Tap AC (All Clear) button
5. **Toggle theme**: Tap theme icons at the top

## 🐛 Known Issues

- Decimal point functionality needs refinement
- Operation chaining needs improvement
- Error handling for division by zero

## 🚧 Future Enhancements

- [ ] Add percentage calculation
- [ ] Implement backspace/delete button
- [ ] Add calculation history
- [ ] Support for scientific calculations
- [ ] Landscape mode optimization
- [ ] Add vibration feedback
- [ ] Implement theme persistence

## 📄 License

This project is for learning purposes. Feel free to use and modify as needed.

## 👤 Author

Created while learning Jetpack Compose and Android development.

## 🙏 Acknowledgments

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- Android Developer Community

---

**Happy Coding! 🚀**
