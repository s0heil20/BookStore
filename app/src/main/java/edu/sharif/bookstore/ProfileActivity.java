package edu.sharif.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.entity.User;
import edu.sharif.bookstore.utils.NavBarActivity;

public class ProfileActivity extends NavBarActivity {
    private static final String fileName = "login";
    private static final String username = "username";
    private static final String password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_profile);
        handleParentView(R.layout.nav_profile);

        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        TextView usernameTextView = findViewById(R.id.profileUsername);
        usernameTextView.setText("Username: " + sqlDatabaseManager.getUserDatabaseManager().getLoggedInUser().getUsername());
        TextView nicknameTextView = findViewById(R.id.profileNickname);
        nicknameTextView.setText("Nickname: " + sqlDatabaseManager.getUserDatabaseManager().getLoggedInUser().getNickname());

    }

    public void logOut(View view) {
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        sqlDatabaseManager.getUserDatabaseManager().setLoggedInUser(null);
        SharedPreferences sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(this, SignUpSignInActivity.class));
    }

    public void deleteAccount(View view) {
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        sqlDatabaseManager.getUserDatabaseManager().deleteLoggedInUser();
        logOut(view);
    }

    public void changePassword(View view) {
        TextInputLayout oldPasswordInput = findViewById(R.id.profileOldPassword);
        String oldPassword = oldPasswordInput.getEditText().getText().toString();

        TextInputLayout newPasswordInput = findViewById(R.id.profileNewPassword);
        String newPassword = newPasswordInput.getEditText().getText().toString();

        TextInputLayout newPasswordConfirmInput = findViewById(R.id.profileNewPasswordConfirm);
        String newPasswordConfirm = newPasswordConfirmInput.getEditText().getText().toString();

        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        User user = new User(sqlDatabaseManager.getUserDatabaseManager().getLoggedInUser().getUsername(), oldPassword, sqlDatabaseManager.getUserDatabaseManager().getLoggedInUser().getNickname());
        boolean checkOldPassword = sqlDatabaseManager.getUserDatabaseManager().checkPassword(user);
        if (!checkOldPassword) {
            Toast.makeText(this, "wrong old password.", Toast.LENGTH_LONG).show();
            return;
        }

        boolean checkNewPassword = checkLength("password", newPassword);
        boolean checkConfirmPassword = checkConfirmPassword(newPassword, newPasswordConfirm);

        if (checkNewPassword && checkConfirmPassword) {
            user.setPassword(newPassword);
            sqlDatabaseManager.getUserDatabaseManager().changePassword(user);
            Toast.makeText(this, "password changed successfully.", Toast.LENGTH_LONG).show();
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
        Toast.makeText(this, "new password and confirm new password don't match.", Toast.LENGTH_LONG).show();
        return false;
    }

}