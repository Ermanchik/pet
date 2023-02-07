package com.example.psur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view) {
        Log.i("asdf123", "asdf123123123");
        PetAPI petAPI = PetAPI.retrofit.create(PetAPI.class);
        Log.i("asdf", "asdf1231231231231223112331212");
        final Call<Pet> call = petAPI.getPet("404");
        Log.i("asdf", "asdf");
        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                if (response.isSuccessful()) {
                    Pet result = response.body();
                    final TextView t = (TextView) findViewById(R.id.textView);
                    t.setText(result.getName());
                } else {
                    Log.i("asdf", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable throwable) {
                final TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("Что-то пошло не так: " + throwable.getMessage());
            }
        });
    }
}
