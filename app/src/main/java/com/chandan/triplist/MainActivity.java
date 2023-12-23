package com.chandan.triplist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.seearch,menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);
        searchView  = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
               int index = 0,flag = 0;
               if(tabLayout.getSelectedTabPosition()==0) {
                   for (DataModel obj : DataBase.getAllData()) {
                       index++;
                       if (obj.getRoll().equalsIgnoreCase(s)) {
                           flag = 1;
                           break;
                       }
                   }
               }else if(tabLayout.getSelectedTabPosition()==1){
                   for (DataModel obj : DataBase.getBoysData()) {
                       index++;
                       if (obj.getRoll().equalsIgnoreCase(s)) {
                           flag = 1;
                           break;
                       }
                   }
               }else if(tabLayout.getSelectedTabPosition()==2){
                   for (DataModel obj : DataBase.getGirlsData()) {
                       index++;
                       if (obj.getRoll().equalsIgnoreCase(s)) {
                           flag = 1;
                           break;
                       }
                   }
               }
                if(flag == 1 && tabLayout.getSelectedTabPosition()==0){
                    if(ListFrag.getAdapter()!=null)
                    ListFrag.getRecyclerView().scrollToPosition(index - 1);
                    Toast toast = Toast.makeText(getApplicationContext(),"record found",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();
                }else if(flag == 1 && tabLayout.getSelectedTabPosition()==1){
                    if(BoysFrag.getAdapter()!=null)
                        BoysFrag.getRecyclerView().scrollToPosition(index - 1);
                        Toast toast = Toast.makeText(getApplicationContext(),"record found",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP,0,0);
                        toast.show();
                }else if(flag == 1 && tabLayout.getSelectedTabPosition()==2){
                    if(GirlsFrag.getAdapter()!=null)
                        GirlsFrag.getRecyclerView().scrollToPosition(index - 1);
                    Toast toast = Toast.makeText(getApplicationContext(),"record found",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),"No records found",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();

            }
                searchView.setIconified(true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
        }else {
            super.onBackPressed();
        }
    }
}