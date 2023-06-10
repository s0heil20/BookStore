package edu.sharif.bookstore.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Arrays;

import edu.sharif.bookstore.navigationBar.NavBarActivity;
import edu.sharif.bookstore.R;

public class SettingsActivity extends NavBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pageName = "Settings";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_settings);
        handleParentView(R.layout.nav_settings);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        switch (mode) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                radioGroup.check(R.id.systemDefault);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                radioGroup.check(R.id.light);
                break;
            default:
                radioGroup.check(R.id.dark);
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        switch(view.getId()) {
            case R.id.systemDefault:
                mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                break;
            case R.id.light:
                mode = AppCompatDelegate.MODE_NIGHT_NO;
                break;
            default:
                mode = AppCompatDelegate.MODE_NIGHT_YES;
                break;
        }

        AppCompatDelegate.setDefaultNightMode(mode);
        getDelegate().applyDayNight();
    }
}