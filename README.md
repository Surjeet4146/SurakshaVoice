# SurakshaVoice

[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
[![TensorFlow](https://img.shields.io/badge/TensorFlow-FF6F00?style=for-the-badge&logo=tensorflow&logoColor=white)](https://tensorflow.org)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://java.com)

**A sophisticated voice verification Android application using TensorFlow Lite for fraud detection based on voice biometric parameters.**

*Developed for **Suraksha Hackathon 2025** by **Team UnityVault***

## 🎯 Overview

This project implements an advanced voice verification system that analyzes voice biometric parameters including pitch, tone, cadence, and energy to detect potential fraud attempts. The application features a modern, animated UI with neon-themed design and real-time verification capabilities.

## ✨ Features

- **🔐 Voice Biometric Analysis**: Real-time verification using pitch, tone, cadence, and energy parameters
- **🤖 AI-Powered Detection**: TensorFlow Lite model for accurate fraud detection
- **🎨 Modern UI**: Neon-themed design with smooth animations and visual feedback
- **⚡ Real-time Processing**: Instant verification results with loading animations
- **📱 Material Design**: Following Android Material Design principles
- **🛡️ Security Focused**: Built specifically for security applications

## 🚀 Demo

The application provides an intuitive interface where users input voice parameters and receive immediate feedback on authentication status:

- ✅ **Authentication Successful**: Green indicator for legitimate voice patterns
- 🚨 **Fraud Detected**: Red alert for suspicious voice characteristics
- ⚠️ **Error Handling**: Orange notifications for processing errors

## 📋 Prerequisites

Before running this application, ensure you have:

- **Android Studio**: 4.0 or higher
- **Android SDK**: API level 24 (Android 7.0) or higher
- **Java**: JDK 8 or higher
- **Device/Emulator**: Android 7.0+ for testing
- **TensorFlow Lite Model**: `voice_model.tflite` file (see [Model Setup](#-model-setup))

## 🛠️ Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Surjeet4146/suraksha-voice-verification.git
cd suraksha-voice-verification
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select "Open an existing Android Studio project"
3. Navigate to the cloned repository folder
4. Wait for Gradle sync to complete

### 3. Model Setup

1. Create an `assets` folder in `app/src/main/` if it doesn't exist
2. Place your `voice_model.tflite` file in the `assets` directory
3. Ensure the model file is exactly named `voice_model.tflite`

### 4. Build and Run

1. Connect an Android device or start an emulator
2. Click "Run" button in Android Studio or use `Ctrl+R`
3. Grant necessary permissions when prompted

## 📱 Permissions

The application requires the following permissions:

- **RECORD_AUDIO**: For voice input capture
- **READ_EXTERNAL_STORAGE**: For accessing stored voice files
- **WRITE_EXTERNAL_STORAGE**: For saving processed data

## 🏗️ Project Structure

```
app/
├── src/main/
│   ├── java/com/example/surakshavoice/
│   │   ├── MainActivity.java          # Main application logic
│   │   └── StandardScaler.java        # Data preprocessing utility
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml      # Main UI layout
│   │   ├── values/
│   │   │   ├── colors.xml             # Color definitions
│   │   │   └── styles.xml             # UI styles
│   │   └── drawable/                  # UI graphics and backgrounds
│   ├── assets/
│   │   └── voice_model.tflite         # TensorFlow Lite model
│   └── AndroidManifest.xml            # App configuration
├── build.gradle                       # App-level dependencies
└── README.md                          # This file
```

## 🔧 Technical Specifications

### Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| AndroidX AppCompat | 1.6.1 | Backward compatibility |
| Material Components | 1.10.0 | Modern UI components |
| ConstraintLayout | 2.1.4 | Flexible layouts |
| CardView | 1.0.0 | Card-based UI elements |
| TensorFlow Lite | 2.16.1 | AI model inference |
| TensorFlow Lite Support | 0.4.4 | Model utilities |
| Dynamic Animation | 1.0.0 | Smooth animations |

### System Requirements

- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Architecture**: ARM64, x86_64
- **RAM**: Minimum 2GB recommended
- **Storage**: 50MB free space

## 🧠 Model Information

### Voice Model Details

- **Type**: TensorFlow Lite (.tflite)
- **Input Features**: 4 parameters
  - Pitch (Hz): Fundamental frequency of voice
  - Tone (0-1): Tonal characteristics
  - Cadence (words/sec): Speaking rhythm
  - Energy (dB): Voice intensity
- **Output**: Fraud probability (0-1 scale)
- **Preprocessing**: StandardScaler normalization
- **Inference Time**: <100ms on modern devices

### Model Architecture

The model uses standardized input preprocessing:

```java
// Preprocessing parameters
float[] mean = {134.52f, 0.66f, 1.76f, 0.57f};
float[] scale = {19.85f, 0.22f, 0.50f, 0.21f};
```

## 🎨 UI Components

### Key Features

- **Neon Theme**: Cyberpunk-inspired design with glowing effects
- **Smooth Animations**: Entrance, loading, and result animations
- **Material Design**: Following Google's design guidelines
- **Responsive Layout**: Adapts to different screen sizes
- **Visual Feedback**: Color-coded results and status indicators

### Animation System

- **Entrance Animations**: Sequential appearance of UI elements
- **Loading States**: Progress indicators during processing
- **Result Feedback**: Color-coded success/error animations
- **Interactive Elements**: Button press and hover effects

## 📖 Usage Guide

### Basic Verification Process

1. **Launch Application**: Open the Suraksha Voice Verification app
2. **Input Parameters**: Enter voice biometric values:
   - **Pitch**: Typical range 80-250 Hz
   - **Tone**: Decimal value 0.0-1.0
   - **Cadence**: Words per second (1.0-3.0)
   - **Energy**: Voice intensity in dB (0.0-1.0)
3. **Verify**: Tap the "🔐 Verify Voice" button
4. **Results**: View authentication status:
   - Green: ✅ Authentication Successful
   - Red: 🚨 Fraud Detected
   - Orange: ⚠️ Processing Error

### Example Input Values

**Legitimate Voice Pattern:**
- Pitch: 140 Hz
- Tone: 0.7
- Cadence: 2.1 words/sec
- Energy: 0.6 dB

**Suspicious Voice Pattern:**
- Pitch: 95 Hz
- Tone: 0.3
- Cadence: 0.8 words/sec
- Energy: 0.2 dB

## 🔍 API Reference

### MainActivity Class

#### Key Methods

```java
// Main verification logic
private void performVerification()

// Load TensorFlow Lite model
private void loadModel()

// Initialize UI components
private void initializeViews()

// Display results with animation
private void showResultWithAnimation(String message, boolean isFraud)

// Handle processing errors
private void showErrorWithAnimation(String message)
```

### StandardScaler Class

```java
// Constructor
public StandardScaler(float[] mean, float[] scale)

// Normalize input features
public float[] transform(float[] input)
```

## 🐛 Troubleshooting

### Common Issues

**Model Loading Error**
```
Error: java.io.IOException: voice_model.tflite not found
```
**Solution**: Ensure `voice_model.tflite` is placed in `app/src/main/assets/`

**Input Validation Error**
```
Error: NumberFormatException
```
**Solution**: Verify all input fields contain valid decimal numbers

**Animation Performance Issues**
```
Laggy animations on older devices
```
**Solution**: Test on devices with API 24+ and sufficient RAM

**Permission Denied**
```
SecurityException: Permission denied
```
**Solution**: Grant audio recording permissions in device settings

### Debug Mode

Enable debug logging by adding to `MainActivity`:

```java
private static final String TAG = "SurakshaVoice";
Log.d(TAG, "Debug message here");
```

## 🧪 Testing

### Manual Testing Checklist

- [ ] App launches successfully
- [ ] UI elements render properly
- [ ] Input validation works
- [ ] Model loads without errors
- [ ] Verification process completes
- [ ] Results display correctly
- [ ] Animations play smoothly
- [ ] Error handling functions

### Test Cases

| Input | Expected Output |
|-------|----------------|
| Valid voice params | Authentication successful |
| Suspicious params | Fraud detected |
| Invalid input | Error message |
| Empty fields | Validation error |

## 🤝 Contributing

We welcome contributions from the community! Please follow these guidelines:

### Development Setup

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/new-feature`
3. Make your changes
4. Test thoroughly
5. Commit: `git commit -m "Add new feature"`
6. Push: `git push origin feature/new-feature`
7. Submit a Pull Request

### Code Style

- Follow Java naming conventions
- Add comments for complex logic
- Maintain consistent indentation
- Update documentation as needed

## 👥 Team UnityVault

This project was developed for **Suraksha Hackathon 2025** by:

- **[Surjeet Yadav](https://github.com/Surjeet4146)** - Lead Developer & UI/UX
- **[Reshma Navale](https://github.com/reshmanawale27)** - AI/ML Engineer & Backend

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2025 Team UnityVault

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 🙏 Acknowledgments

- **Suraksha Hackathon 2025** organizers for the platform
- **TensorFlow Team** for the excellent mobile ML framework
- **Android Development Community** for resources and support
- **Material Design Team** for UI/UX guidelines

## 📞 Support

For questions, issues, or support:

- **GitHub Issues**: [Create an issue](https://github.com/Surjeet4146/suraksha-voice-verification/issues)
- **Email**: [Contact Team UnityVault](mailto:your-email@example.com)
- **Documentation**: Check this README and inline code comments

---

**Made with ❤️ by Team UnityVault for Suraksha Hackathon 2025**

*Securing the future, one voice at a time.* 🛡️