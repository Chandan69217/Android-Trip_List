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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
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
        SearchView searchView  = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
               int index = 0,flag = 0;
                for(DataModel obj : DataBase.getAllData()){
                    index++;
                    if(obj.getRoll().equalsIgnoreCase(s)){
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1){
                    if(tabLayout.getSelectedTabPosition()==0)
                    ListFrag.getRecyclerView().scrollToPosition(index - 1);
                   // else if(tabLayout.getSelectedTabPosition()==1)
                   // RecyclerViewAdapter.getRecyclerView(1).scrollToPosition(index - 1);
                   // else if(tabLayout.getSelectedTabPosition()==2)
                   //     RecyclerViewAdapter.getRecyclerView(2).scrollToPosition(index - 1);
                }else {
                //    Toast.makeText(MainActivity.this, "no record found", Toast.LENGTH_SHORT).show();
//                    AlertDialog.Builder deleteDialog = new AlertDialog.Builder(getApplicationContext());
//                    deleteDialog.setMessage("Do you want to delete")
//                            .setTitle("Delete")
//                            .setCancelable(true)
//                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//
//                                }
//                            })
//                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    dialogInterface.cancel();
//                                }
//                            })
//                            .setIcon(R.drawable.delete)
//                            .show();
                }

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
}