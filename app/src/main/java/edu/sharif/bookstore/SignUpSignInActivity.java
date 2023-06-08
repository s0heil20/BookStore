package edu.sharif.bookstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import edu.sharif.bookstore.entity.User;

public class SignUpSignInActivity extends AppCompatActivity {
    private static User loggedInUser;
    private ViewFlipper viewFlipper;

    private static final String fileName = "login";
    private static final String username = "username";
    private static final String password = "password";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_in2);

        loggedInUser = new User("", "", "");

        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(username)) {
            startActivity(new Intent(this, FakeActivity.class));
        }

        viewFlipper = findViewById(R.id.viewFlipper);
    }

    public void goToSignIn(View view) {
        viewFlipper.showPrevious();
    }

    public void goToSignUp(View view) {
        viewFlipper.showNext();
    }

    public void trySignUp(View view) {
        TextInputLayout usernameInput = findViewById(R.id.signUpUsername);
        String username = usernameInput.getEditText().getText().toString();

        TextInputLayout passwordInput = findViewById(R.id.signUpPassword);
        String password = usernameInput.getEditText().getText().toString();

        TextInputLayout confirmPasswordInput = findViewById(R.id.signUpConfirmPassword);
        String confirmPassword = usernameInput.getEditText().getText().toString();

        TextInputLayout nicknameInput = findViewById(R.id.signUpNickname);
        String nickname = usernameInput.getEditText().getText().toString();

        boolean checkLengthResult = checkLength("username", username) && checkLength("password", username) && checkLength("confirmPassword", username) && checkLength("nickname", username);
        boolean checkConfirmPasswordResult = checkConfirmPassword(password, confirmPassword);

//        checking username uniqueness

        if (checkLengthResult && checkConfirmPasswordResult) {
//            using database here
            viewFlipper.showNext();
        }
    }

    private boolean checkLength(String fieldName, String content) {
        if (content.length() < 6 || content.length() > 30) {
            Toast.makeText(this, fieldName + " should be between 6 and 30 characters.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean checkConfirmPassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        }
        Toast.makeText(this, "password and confirm password don't match.", Toast.LENGTH_LONG).show();
        return false;
    }

    public void trySignIn(View view) {
        TextInputLayout usernameInput = findViewById(R.id.signInUsername);
        String username = usernameInput.getEditText().getText().toString();

        TextInputLayout passwordInput = findViewById(R.id.signInPassword);
        String password = usernameInput.getEditText().getText().toString();

        CheckBox rememberInput = findViewById(R.id.signInRemember);
        boolean remember = rememberInput.isChecked();

//        implement logic

        if (remember) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SignUpSignInActivity.username, username);
            editor.putString(SignUpSignInActivity.password, password);
            editor.commit();
        }

        startActivity(new Intent(this, FakeActivity.class));

    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}