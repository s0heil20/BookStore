package edu.sharif.bookstore;

import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpSignInActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_in2);

        viewFlipper = findViewById(R.id.viewFlipper);
    }

    public void goToSignIn(View view) {
        viewFlipper.showNext();
    }

    public void goToSignUp(View view) {
        viewFlipper.showPrevious();
    }

    public void trySignUp(View view) {
        TextInputLayout username = findViewById(R.id.signUpUsername);
        TextInputLayout password = findViewById(R.id.signUpPassword);
        TextInputLayout nickname = findViewById(R.id.signUpNickname);

    }

}
