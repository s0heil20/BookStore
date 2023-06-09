package edu.sharif.bookstore.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import edu.sharif.bookstore.navigationBar.NavBarActivity;
import edu.sharif.bookstore.R;

public class SettingsActivity extends NavBarActivity {
    private SwitchMaterial switchMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_settings);
        handleParentView(R.layout.nav_settings);

        switchMaterial = findViewById(R.id.toggle);
    }

    public void changeTheme(View view) {
        Log.d("XXXXXXXXXXXXXXXXXXXXXXX", "changeTheme: hi");
        TextView textView = findViewById(R.id.themeText);
        if (switchMaterial.isChecked()) {
            textView.setText("Dark Mode");
        } else {
            textView.setText("Light Mode");
        }
    }


}