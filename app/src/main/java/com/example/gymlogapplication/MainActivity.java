package com.example.gymlogapplication;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gymlogapplication.database.GymLogRepository;
import com.example.gymlogapplication.database.entities.GymLog;
import com.example.gymlogapplication.databinding.ActivityMainBinding;
import java.util.Locale;
import android.text.method.ScrollingMovementMethod;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private GymLogRepository repository;


    public static final String TAG = "QUOC_GYMLOG";

    String mExercise = "";
    double mWeight = 0.0;
    int mReps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = GymLogRepository.getRepository(getApplication());

        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod());

        binding.logButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();
                insertGymlogRecord();
                updateDisplay();
            }
        });
    }

    private void insertGymlogRecord(){
        GymLog log = new GymLog(mExercise,mWeight,mReps);
        repository.insertGymLog(log);
    }

//    private void updateDisplay(){
//        String currentInfo = binding.LogDisplayTextView.getText().toString();
//        Log.d(TAG,"current info: " + currentInfo);
//        String newDisplay = String.format(Locale.US,"Exercise:%s%nWeight:%.2f%nReps:%d%n=-=-=-=%n%s,mExercise,mWeight,mReps,currentInfo);
//        binding.logDisplayTextView.setText(newDisplay);
//    }

//    private void updateDisplay() {
//        String currentInfo = binding.logDisplayTextView.getText().toString();
//
//        String newDisplay = String.format(
//                Locale.US,
//                "Exercise: %s%nWeight: %.2f%nReps: %d%n=-=-=-= %n",
//                mExercise, mWeight, mReps
//        );
//
//        //binding.logDisplayTextView.setText(newDisplay);
//        binding.logDisplayTextView.setText(currentInfo + newDisplay);
//    }

    private void updateDisplay() {
        String currentInfo = binding.logDisplayTextView.getText().toString();

        String newDisplay = String.format(
                Locale.US,
                "Exercise: %s%nWeight: %.2f%nReps: %d%n=-=-=-=%n",
                mExercise, mWeight, mReps
        );

        // If this is the first time and the TextView still has "Hello World!", remove it
        if (currentInfo.equals("Hello World!")) {
            currentInfo = "";
        }

        // PREPEND new data to show it at the TOP
        String updatedText = newDisplay + currentInfo;

        binding.logDisplayTextView.setText(updatedText);
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