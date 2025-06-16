# Suraksha Voice Verification

This project implements a voice verification application using TensorFlow Lite for model inference. It allows users to verify their voice input based on features such as pitch, tone, cadence, and energy.

## Features

- Voice input verification with animation feedback.
- Animated UI to enhance user experience.
- Error handling for smooth operation.

## Usage

1. Clone the repository or download the project files.
2. Open the project in Replit.
3. Ensure you have the TensorFlow Lite model (`voice_model.tflite`) in your assets directory.
4. Run the project. The main UI will display input fields for pitch, tone, cadence, and energy.
5. Enter the parameters and click the "Verify Voice" button to perform the verification.

## Implementation Details

### Main Components

- **MainActivity**: The main activity that handles user interactions, input processing, and displaying results.

### Key Methods

- `performVerification()`: Collects user input, standardizes data, runs inference, and displays results or errors.
- `showResultWithAnimation(String message, boolean isFraud)`: Displays the verification result with appropriate animations.
- `showErrorWithAnimation(String message)`: Displays error messages when an exception occurs during verification.
- `initializeViews()`: Initializes UI components like inputs and buttons.
- `loadModel()`: Loads the TensorFlow Lite model from the assets.
- `setupScaler()`: Configures the data scaler with predefined mean and scale values.

### Animation

- Various animations are implemented to provide visual feedback, such as entrance animations for UI components, result display animations, and button press animations.

## Requirements

- Android development environment (Android Studio or equivalent).
- TensorFlow Lite model for voice verification (`voice_model.tflite`).

## License

This project is licensed under the MIT License. You can freely use, modify, and distribute the code.