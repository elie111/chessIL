package com.example.chessil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,NavigateFragments {


    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigateFrag(new feed(),false);
        mAuth = FirebaseAuth.getInstance();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        navigationView=(NavigationView) findViewById(R.id.navigationview);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
         toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();


//



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.logout:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(Home.this, MainActivity.class));

                mAuth.signOut();
                break;
            case R.id.profile:
                drawerLayout.closeDrawer(GravityCompat.START);
                navigateFrag(new ProfileFragment(),false);
                break;
            case R.id.home:
                drawerLayout.closeDrawer(GravityCompat.START);
                navigateFrag(new feed(),false);
                break;

        }
        return false;
    }
    public void navigateFrag(Fragment fragment, Boolean addToStack) {;
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,fragment,"MY_FRAGMENT");
        if(addToStack){
            transaction.addToBackStack(null);

        }
        transaction.commit();
    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(toggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}