package com.example.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        setFragment(new BlankFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.firstFrag) {
                setFragment(new BlankFragment());
            }
            else if (item.getItemId() == R.id.twoFrag) {
                setFragment(new SecondFragment());
            }
            return true;
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Настройки выбраны", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.firstFrag) {
            setFragment(new BlankFragment());
            bottomNavigationView.setSelectedItemId(R.id.firstFrag);
        } else if (id == R.id.twoFrag) {
            setFragment(new SecondFragment());
            bottomNavigationView.setSelectedItemId(R.id.twoFrag);
        } else if (id == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.firstFrag) {
            setFragment(new BlankFragment());
            bottomNavigationView.setSelectedItemId(R.id.firstFrag);
        }
        else if (id == R.id.twoFrag) {
            setFragment(new SecondFragment());
            bottomNavigationView.setSelectedItemId(R.id.twoFrag);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.selectedMenu, fragment, null).commit();
    }


}