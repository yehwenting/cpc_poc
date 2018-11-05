package com.example.wentingy.cpcmaximopoc.Mainpage;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.wentingy.cpcmaximopoc.AccidentActivity.AccidentActivity;
import com.example.wentingy.cpcmaximopoc.Data.MySingleTon;
import com.example.wentingy.cpcmaximopoc.Model.AccidentList;
import com.example.wentingy.cpcmaximopoc.Model.WorkOrder;
import com.example.wentingy.cpcmaximopoc.Navigation_BaseActivity;
import com.example.wentingy.cpcmaximopoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Navigation_BaseActivity {

    private TextView toolBar_title,search,num;
//    private ImageView search;
    private android.support.design.widget.TabLayout mTabs;
    private ViewPager mViewPager;
    private TextView report;
    List<WorkOrder> workOrderList = new ArrayList<>();
    List<AccidentList> accidentLists = new ArrayList<>();
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarSetting();
        initData();
        initView();
        initListener();
    }

    private void toolbarSetting(){
        toolBar_title=findViewById(R.id.toolbar_title);
        //toolbar
        toolbar.setTitle("");//設置ToolBar Title
//        toolBar_title.setText("事件速報");
        setUpToolBar();//使用父類別的setUpToolBar()，設置ToolBar
        CurrentMenuItem = 0;
        NV.getMenu().getItem(CurrentMenuItem).setChecked(true);//設置Navigation目前項目被選取狀態
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }

    private void initData(){

        String url="http://nickall.asuscomm.com:9080/maximo/oslc/os/zz_accidentrep?lean=1&&oslc.select=*";

        if(checkNetworkConnection()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("uuuuu", response);
                            try {
                                JSONObject jObject = new JSONObject(response);
                                JSONArray member = jObject.getJSONArray("member");

                                for (int i=0; i<member.length(); i++) {
                                    num.setText("共"+member.length()+"筆");
                                    JSONObject accident = member.getJSONObject(i);
                                    if(accident.has("accidentid") && accident.has("description") &&
                                            accident.has("location") && accident.has("createdate")
                                            ){
                                        String id= accident.getString("accidentid");
                                        String description = accident.getString("description");
                                        String location = accident.getString("location");
                                        String time = accident.getString("createdate");
//                                        String type = accident.getString("type");
//                                        String department = accident.getString("dept");


                                        AccidentList accidentList = new AccidentList(id,"no","3","2",location,
                                                "吳大才",time,"傷亡","1","通報緊急應變中心","yes",
                                                "2700802",description,"20012","null","0","0","汽化一課");
                                        accidentLists.add(accidentList);
                                    }else {
                                        continue;
                                    }

                                }



                                TodayAdapter todayAdapter = new TodayAdapter(accidentLists);
                                //要記得有layoutmanager
                                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
                                recyclerView.setAdapter(todayAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("error", "do not save to mysql 2");

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("maxauth", "bWF4YWRtaW46emFxMXhzVzI=");

                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleTon.getmInstance(MainActivity.this).addToRequestque(stringRequest);

        }else{
            Log.d("error","connect error");
        }



        //fake data
//        for (int i=0;i<4;i++){
////            List<WorkOrderChild> workOrderChildren=new ArrayList<>();
////            if(i%2==0){
////                WorkOrderChild workOrderChild=new WorkOrderChild("F15-ASSET100","MAXADAMO","已回報","hello");
////                workOrderChildren.add(workOrderChild);
////            }
////            WorkOrder workOrder=new WorkOrder("ACC1070000003","Y_TILSMJ","待回報","detail",workOrderChildren);
////            workOrderList.add(workOrder);
//            AccidentList accidentList = new AccidentList("AC107011112","no","3","2","CDU單元",
//                    "吳大才","2018-10-22 10:10:09","意外事故:氣爆","1","通報緊急應變中心","yes",
//                    "2700802","氣爆事件","20012","null","0","0","機械二課");
//            accidentLists.add(accidentList);
//        }

    }
    private void initView(){
//        search=findViewById(R.id.searchDetail);
        mTabs =findViewById(R.id.tabs);
        mTabs.addTab(mTabs.newTab().setText("已通報之事故案件"));
//        mTabs.addTab(mTabs.newTab().setText("本日結案"));
        recyclerView=findViewById(R.id.main_rv);
        report=findViewById(R.id.report);
        num=findViewById(R.id.num);

    }
    private void initListener(){
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
//                startActivity(intent);
//            }
//        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccidentList accidentList = new AccidentList("","","","","",
                        "","","","","","",
                        "","","","","","","");
                Intent intent=new Intent(MainActivity.this,AccidentActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("accidentList ", accidentList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("test", String.valueOf(tab.getPosition()));
                TodayAdapter todayAdapter = new TodayAdapter(accidentLists);
                //要記得有layoutmanager
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
                recyclerView.setAdapter(todayAdapter);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    }

