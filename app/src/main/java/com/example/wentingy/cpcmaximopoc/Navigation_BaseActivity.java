package com.example.wentingy.cpcmaximopoc;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wentingy.cpcmaximopoc.Mainpage.MainActivity;


/**
 * Created by Yehwenting on 2017/12/12.
 */

public class Navigation_BaseActivity extends AppCompatActivity {
    private DrawerLayout DL;
    private FrameLayout FL;
    private ImageView userPhoto;
    private TextView userName;
    protected NavigationView NV;
    protected Toolbar toolbar;
    protected int CurrentMenuItem = 6;//紀錄目前User位於哪一個項目
    private LinearLayout LL;
    private View view;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        DL = (DrawerLayout) getLayoutInflater().inflate(R.layout.navigation_drawer, null);
        LL = (LinearLayout) getLayoutInflater().inflate(R.layout.navigation_drawer_header,null);
        FL = DL.findViewById(R.id.content_frame);
        NV = DL.findViewById(R.id.Left_Navigation);
        view=NV.getHeaderView(0);
        userPhoto=view.findViewById(R.id.navigation_header_userPhoto);
        userName=view.findViewById(R.id.navigation_header_userID);
        getLayoutInflater().inflate(layoutResID, FL, true);
        super.setContentView(DL);
        toolbar =findViewById(R.id.toolbar);
        setUpNavigation();




    }

    private void setUpNavigation() {



        // Set navigation item selected listener
        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(!(menuItem == NV.getMenu().getItem(CurrentMenuItem))) {//判斷使者者是否點擊當前畫面的項目，若不是，根據所按的項目做出分別的動作
                    switch (menuItem.getItemId()) {
                        case R.id.navItemOne:
//                            Log.d("rrrrr","rrrrrr");
                            NV.setBackgroundColor(Color.parseColor("#F04C32"));
                            Intent intent = new Intent();
                            intent.setClass(Navigation_BaseActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            finish();

                            break;
                        case R.id.navItemTwo:
                            NV.setBackgroundColor(Color.parseColor("#F04C32"));
                            Intent intent2 = new Intent();
//                            intent2.setClass(Navigation_BaseActivity.this, PersonInfoActivity.class);
                            startActivity(intent2);
                            overridePendingTransition(0, 0);
                            finish();
                            break;

                        case R.id.navItemEight:
                            NV.setBackgroundColor(Color.parseColor("#F04C32"));

                            Log.d("rrrrr","rrrrrr");
//                            Intent intent1=new Intent(Navigation_BaseActivity.this,FriendsActivity.class);
//                            startActivity(intent1);
                            overridePendingTransition(0, 0);

                            break;

                        case R.id.navItemFive:
//                            Intent intent5=new Intent(Navigation_BaseActivity.this,LoginActivity.class);
//                            startActivity(intent5);
//                            overridePendingTransition(0, 0);

                    }
                }
                else {//點擊當前項目時，收起Navigation
                    DL.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

    }
    public void setUpToolBar() {//設置ToolBar
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DL.openDrawer(GravityCompat.START);
            }
        });
        //設定當使用者點擊ToolBar中的Navigation Icon時，Icon會隨著轉動
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle( this, DL, toolbar,R.string.open_navigation,R.string.close_navigation){
            @Override
            public void onDrawerClosed(View drawerView) {
                super .onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super .onDrawerOpened(drawerView);
            }
        };
        DL.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

}


