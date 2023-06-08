package edu.sharif.bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Test for query and google API!
        startActivity(new Intent(this, MainMenuActivity.class));

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        drawer = findViewById(R.id.drawerLayout);
//        NavigationView navigationView = findViewById(R.id.navView);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open,R.string.nav_drawer_close);
//
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();


//        startActivity(new Intent(this, SignUpSignInActivity.class));
//        startActivity(new Intent(this, FinalizeOrderActivity.class));
//        startActivity(new Intent(this, DetailedBookActivity.class));
//        startActivity(new Intent(this, SearchActivity.class));
// startActivity(new Intent(this, MainMenuActivity.class));
//        startActivity(new Intent(this, SearchActivity.class));

//        startActivity(new Intent(this, MainMenuActivity.class));
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