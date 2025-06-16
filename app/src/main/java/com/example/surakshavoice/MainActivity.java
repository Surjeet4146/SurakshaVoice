package com.example.surakshavoice;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import org.tensorflow.lite.Interpreter;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {
    private Interpreter tflite;
    private StandardScaler scaler;
    private EditText pitchInput, toneInput, cadenceInput, energyInput;
    private TextView resultText, titleText;
    private Button verifyButton;
    private ProgressBar loadingProgress;
    private CardView mainCard, resultCard;
    private View neonGlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        initializeViews();

        // Start entrance animations
        startEntranceAnimations();

        // Load the TFLite model
        loadModel();

        // Setup scaler with hardcoded values
        setupScaler();

        // Set up the verify button
        setupVerifyButton();

        // Start neon glow animation
        startNeonGlowAnimation();
    }

    private void initializeViews() {
        pitchInput = findViewById(R.id.pitch_input);
        toneInput = findViewById(R.id.tone_input);
        cadenceInput = findViewById(R.id.cadence_input);
        energyInput = findViewById(R.id.energy_input);
        resultText = findViewById(R.id.result_text);
        verifyButton = findViewById(R.id.verify_button);
        loadingProgress = findViewById(R.id.loading_progress);
        mainCard = findViewById(R.id.main_card);
        resultCard = findViewById(R.id.result_card);
        titleText = findViewById(R.id.title_text);
        neonGlow = findViewById(R.id.neon_glow);
    }

    private void startEntranceAnimations() {
        // Title slide in from top
        ObjectAnimator titleSlide = ObjectAnimator.ofFloat(titleText, "translationY", -200f, 0f);
        ObjectAnimator titleFade = ObjectAnimator.ofFloat(titleText, "alpha", 0f, 1f);
        AnimatorSet titleSet = new AnimatorSet();
        titleSet.playTogether(titleSlide, titleFade);
        titleSet.setDuration(800);
        titleSet.setInterpolator(new AccelerateDecelerateInterpolator());
        titleSet.start();

        // Main card slide in from bottom
        ObjectAnimator cardSlide = ObjectAnimator.ofFloat(mainCard, "translationY", 300f, 0f);
        ObjectAnimator cardFade = ObjectAnimator.ofFloat(mainCard, "alpha", 0f, 1f);
        AnimatorSet cardSet = new AnimatorSet();
        cardSet.playTogether(cardSlide, cardFade);
        cardSet.setDuration(800);
        cardSet.setStartDelay(200);
        cardSet.setInterpolator(new AccelerateDecelerateInterpolator());
        cardSet.start();

        // Input fields slide in from left sequentially
        EditText[] inputs = {pitchInput, toneInput, cadenceInput, energyInput};
        for (int i = 0; i < inputs.length; i++) {
            ObjectAnimator inputSlide = ObjectAnimator.ofFloat(inputs[i], "translationX", -100f, 0f);
            ObjectAnimator inputFade = ObjectAnimator.ofFloat(inputs[i], "alpha", 0f, 1f);
            AnimatorSet inputSet = new AnimatorSet();
            inputSet.playTogether(inputSlide, inputFade);
            inputSet.setDuration(600);
            inputSet.setStartDelay(400 + (i * 100));
            inputSet.setInterpolator(new AccelerateDecelerateInterpolator());
            inputSet.start();
        }

        // Button bounce in
        ObjectAnimator buttonScaleX = ObjectAnimator.ofFloat(verifyButton, "scaleX", 0f, 1f);
        ObjectAnimator buttonScaleY = ObjectAnimator.ofFloat(verifyButton, "scaleY", 0f, 1f);
        AnimatorSet buttonSet = new AnimatorSet();
        buttonSet.playTogether(buttonScaleX, buttonScaleY);
        buttonSet.setDuration(600);
        buttonSet.setStartDelay(800);
        buttonSet.setInterpolator(new OvershootInterpolator());
        buttonSet.start();
    }

    private void startNeonGlowAnimation() {
        ValueAnimator glowAnimator = ValueAnimator.ofFloat(0.3f, 1f);
        glowAnimator.setDuration(2000);
        glowAnimator.setRepeatCount(ValueAnimator.INFINITE);
        glowAnimator.setRepeatMode(ValueAnimator.REVERSE);
        glowAnimator.addUpdateListener(animation -> {
            float alpha = (Float) animation.getAnimatedValue();
            neonGlow.setAlpha(alpha);
        });
        glowAnimator.start();
    }

    private void loadModel() {
        try {
            tflite = new Interpreter(loadModelFile("voice_model.tflite"));
        } catch (IOException e) {
            e.printStackTrace();
            showErrorWithAnimation("Error loading model: " + e.getMessage());
        }
    }

    private void setupScaler() {
        float[] mean = new float[]{134.5175711f, 0.66361055f, 1.76104294f, 0.57206665f};
        float[] scale = new float[]{19.84776948f, 0.21552108f, 0.49626576f, 0.20655427f};
        scaler = new StandardScaler(mean, scale);
    }

    private void setupVerifyButton() {
        verifyButton.setOnClickListener(v -> {
            // Button press animation
            v.animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .withEndAction(() -> v.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start())
                    .start();

            verifyVoice();
        });
    }

    private MappedByteBuffer loadModelFile(String modelFileName) throws IOException {
        FileInputStream inputStream = new FileInputStream(getAssets().openFd(modelFileName).getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = getAssets().openFd(modelFileName).getStartOffset();
        long declaredLength = getAssets().openFd(modelFileName).getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private void verifyVoice() {
        // Hide result card and show loading
        resultCard.setVisibility(View.INVISIBLE);
        loadingProgress.setVisibility(View.VISIBLE);

        // Disable verify button during processing
        verifyButton.setEnabled(false);
        verifyButton.setText("Processing...");

        // Simulate processing delay for better UX
        new Handler().postDelayed(this::performVerification, 1500);
    }

    private void performVerification() {
        try {
            // Get user inputs
            float pitch = Float.parseFloat(pitchInput.getText().toString());
            float tone = Float.parseFloat(toneInput.getText().toString());
            float cadence = Float.parseFloat(cadenceInput.getText().toString());
            float energy = Float.parseFloat(energyInput.getText().toString());

            // Prepare input data
            float[][] input = new float[1][4];
            input[0][0] = pitch;
            input[0][1] = tone;
            input[0][2] = cadence;
            input[0][3] = energy;

            // Standardize the input
            input[0] = scaler.transform(input[0]);

            // Run inference
            float[][] output = new float[1][1];
            tflite.run(input, output);

            // Interpret the result
            float prediction = output[0][0];

            // Hide loading and show result with animation
            loadingProgress.setVisibility(View.GONE);
            verifyButton.setEnabled(true);
            verifyButton.setText("🔐 Verify Voice");

            if (prediction > 0.5) {
                showResultWithAnimation("🚨 Fraud Detected", true);
            } else {
                showResultWithAnimation("✅ Authentication Successful", false);
            }
        } catch (Exception e) {
            loadingProgress.setVisibility(View.GONE);
            verifyButton.setEnabled(true);
            verifyButton.setText("🔐 Verify Voice");
            showErrorWithAnimation("Error during verification: " + e.getMessage());
        }
    }

    private void showResultWithAnimation(String message, boolean isFraud) {
        resultText.setText(message);
        if (isFraud) {
            resultCard.setCardBackgroundColor(getResources().getColor(R.color.fraud_red));
        } else {
            resultCard.setCardBackgroundColor(getResources().getColor(R.color.success_green));
        }

        // Animate result card appearance
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(resultCard, "alpha", 0f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(resultCard, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(resultCard, "scaleY", 0f, 1f);
        AnimatorSet appearSet = new AnimatorSet();
        appearSet.playTogether(fadeIn, scaleX, scaleY);
        appearSet.setDuration(500);
        appearSet.setInterpolator(new AccelerateDecelerateInterpolator());
        appearSet.start();

        // Pulse animation
        ObjectAnimator pulseX = ObjectAnimator.ofFloat(resultCard, "scaleX", 1f, 1.1f, 1f);
        ObjectAnimator pulseY = ObjectAnimator.ofFloat(resultCard, "scaleY", 1f, 1.1f, 1f);
        AnimatorSet pulseSet = new AnimatorSet();
        pulseSet.playTogether(pulseX, pulseY);
        pulseSet.setDuration(600);
        pulseSet.setRepeatCount(2);
        pulseSet.start();
    }

    private void showErrorWithAnimation(String message) {
        resultText.setText(message);
        resultCard.setCardBackgroundColor(getResources().getColor(R.color.error_orange));
        resultCard.setVisibility(View.VISIBLE);

        // Shake animation
        ObjectAnimator shake1 = ObjectAnimator.ofFloat(resultCard, "translationX", 0f, 50f);
        ObjectAnimator shake2 = ObjectAnimator.ofFloat(resultCard, "translationX", 50f, -25f);
        ObjectAnimator shake3 = ObjectAnimator.ofFloat(resultCard, "translationX", -25f, 0f);

        shake1.setDuration(100);
        shake2.setDuration(100);
        shake3.setDuration(100);

        AnimatorSet shakeSet = new AnimatorSet();
        shakeSet.playSequentially(shake1, shake2, shake3);
        shakeSet.start();
    }
}

class StandardScaler {
    private float[] mean;
    private float[] scale;

    public StandardScaler(float[] mean, float[] scale) {
        this.mean = mean;
        this.scale = scale;
    }

    public float[] transform(float[] input) {
        float[] transformed = new float[input.length];
        for (int i = 0; i < input.length; i++) {
            transformed[i] = (input[i] - mean[i]) / scale[i];
        }
        return transformed;
    }
}