package com.example.gymlogapplication;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gymlogapplication.databinding.ActivityMainBinding;
import java.util.Locale;
import android.text.method.ScrollingMovementMethod;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    public static final String TAG = "QUOC_GYMLOG";

    String mExercise = "";
    double mWeight = 0.0;
    int mReps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod());

        binding.logButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();
                updateDisplay();
            }
        });
    }

//    private void updateDisplay(){
//        String currentInfo = binding.LogDisplayTextView.getText().toString();
//        Log.d(TAG,"current info: " + currentInfo);
//        String newDisplay = String.format(Locale.US,"Exercise:%s%nWeight:%.2f%nReps:%d%n=-=-=-=%n%s,mExercise,mWeight,mReps,currentInfo);
//        binding.logDisplayTextView.setText(newDisplay);
//    }

    private void updateDisplay() {
        String currentInfo = binding.logDisplayTextView.getText().toString();

        String newDisplay = String.format(
                Locale.US,
                "Exercise: %s%nWeight: %.2f%nReps: %d%n=-=-=-= %n",
                mExercise, mWeight, mReps
        );

        //binding.logDisplayTextView.setText(newDisplay);
        binding.logDisplayTextView.setText(currentInfo + newDisplay);
    }


    private void getInformationFromDisplay(){
        mExercise = binding.exerciseInputEditText.getText().toString();
        try{
            mWeight =  Double.parseDouble(binding.weightInputEditText.getText().toString());
        } catch (NumberFormatException e) {
            Log.d(TAG, "Error reading value from Weight edit text.");
            //throw new RuntimeException(e);
        }

        try{
            mReps =  Integer.parseInt(binding.repInputEditText.getText().toString());
        } catch (NumberFormatException e) {
            Log.d(TAG, "Error reading value from reps edit text.");
            //throw new RuntimeException(e);
        }
    }
}