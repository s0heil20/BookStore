package edu.sharif.bookstore.utils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import edu.sharif.bookstore.CartHistoryActivity;
import edu.sharif.bookstore.MainMenuActivity;
import edu.sharif.bookstore.ProfileActivity;
import edu.sharif.bookstore.R;
import edu.sharif.bookstore.SearchActivity;
import edu.sharif.bookstore.SettingsActivity;

public class NavBarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void handleParentView(int id) {
        setContentView(id);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(NavBarActivity.this, MainMenuActivity.class));
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(NavBarActivity.this, ProfileActivity.class));
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(NavBarActivity.this, SearchActivity.class));
                        break;
                    case R.id.nav_cart:
//                        startActivity(new Intent(NavBarActivity.this, CartActivity.class));
                        break;
                    case R.id.nav_favorites:
//                        startActivity(new Intent(NavBarActivity.this, FavouritesActivity.class));
                        break;
                    case R.id.nav_cart_history:
                        startActivity(new Intent(NavBarActivity.this, CartHistoryActivity.class));
                        break;
                    case R.id.nav_settings:
                        startActivity(new Intent(NavBarActivity.this, SettingsActivity.class));
                        break;
                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}