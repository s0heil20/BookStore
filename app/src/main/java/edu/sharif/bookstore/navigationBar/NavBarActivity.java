package edu.sharif.bookstore.navigationBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import edu.sharif.bookstore.cartHistory.CartHistoryActivity;
import edu.sharif.bookstore.favourtie.FavouriteActivity;
import edu.sharif.bookstore.mainMenu.MainMenuActivity;
import edu.sharif.bookstore.profile.ProfileActivity;
import edu.sharif.bookstore.R;
import edu.sharif.bookstore.search.SearchActivity;
import edu.sharif.bookstore.settings.SettingsActivity;
import edu.sharif.bookstore.shoppingCart.ShoppingCartActivity;

public class NavBarActivity extends AppCompatActivity {
    protected int mode;
    protected String pageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mode = AppCompatDelegate.getDefaultNightMode();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (AppCompatDelegate.getDefaultNightMode() != mode) {
            recreate();
        }
    }

    protected void handleParentView(int id) {
        setContentView(id);
        TextView textView = findViewById(R.id.title);
        textView.setText(pageName + " Page");
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
                        startActivity(new Intent(NavBarActivity.this, ShoppingCartActivity.class));
                        break;
                    case R.id.nav_favorites:
                        startActivity(new Intent(NavBarActivity.this, FavouriteActivity.class));
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