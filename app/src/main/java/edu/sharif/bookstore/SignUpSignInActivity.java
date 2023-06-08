package edu.sharif.bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class SignUpSignInActivity extends MainActivity {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_in);

        viewFlipper = findViewById(R.id.viewFlipper);
    }

    public void goToSignIn(View view) {
        viewFlipper.showNext();
    }

    public void goToSignUp(View view) {
        viewFlipper.showPrevious();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, SignUpSignInActivity.class));
                break;
            case R.id.cart:
                Toast.makeText(this, "cart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cartHistory:
                Toast.makeText(this, "cart history", Toast.LENGTH_SHORT).show();
                break;
            case R.id.account:
                Toast.makeText(this, "account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorite:
                Toast.makeText(this, "favorite", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}