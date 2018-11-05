package com.example.wentingy.cpcmaximopoc.AccidentActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.LocaleDisplayNames;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wentingy.cpcmaximopoc.Data.MySingleTon;
import com.example.wentingy.cpcmaximopoc.Mainpage.MainActivity;
import com.example.wentingy.cpcmaximopoc.Mainpage.TodayAdapter;
import com.example.wentingy.cpcmaximopoc.Model.AccidentCategoryFragment;
import com.example.wentingy.cpcmaximopoc.Model.AccidentList;
import com.example.wentingy.cpcmaximopoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AccidentActivity extends AppCompatActivity implements AccidentCategoryFragment.OnInputSelected{

    private static final int CAMERA_PIC_REQUEST = 22;
    private static final int PICK_IMAGE_REQUEST = 1;
    public static final int RESULT_GALLERY = 0;


    Uri cameraUri;

//    private ImageView back;
    private Button BtnSelectImage,BtnSentImg,BtnOpenImg;
    private ImageView ImgPhoto;
    private String Camerapath ;
    public TextView accident_category,description,remedy,reported;
    private TextView dateText,timetext;
    private Spinner accidentType,location;
    private String level,time,date;

    @Override
    public void sendInput(String input) {
        accident_category.setText(input);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident3);
        initView();
        initDate();
        initListerer();
    }

    private void initView(){
//        back=findViewById(R.id.back);
        ImgPhoto = findViewById(R.id.ImgPhoto);
        BtnSelectImage = findViewById(R.id.BtnSelectImg);
        BtnOpenImg = findViewById(R.id.BtnOpenImg);
//        accident_categoryBtn = findViewById(R.id.accident_categoryBtn);
        //content
//        accident_category = findViewById(R.id.accident_category);
//        description=findViewById(R.id.description);
        dateText = findViewById(R.id.datetext);
        timetext = findViewById(R.id.timetext);
//        dead = findViewById(R.id.dead);
//        wounded = findViewById(R.id.wounded);
        remedy = findViewById(R.id.remedy);
        //sent
        BtnSentImg = findViewById(R.id.BtnSentImg);
        reported=findViewById(R.id.reported);
        accidentType=findViewById(R.id.acciTypeSpinner);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(
                this, R.array.accident, android.R.layout.simple_spinner_item );
        nAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        accidentType.setAdapter(nAdapter);
        location = findViewById(R.id.location);
        ArrayAdapter<CharSequence> lAdapter = ArrayAdapter.createFromResource(
                this, R.array.location, android.R.layout.simple_spinner_item );
        lAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(lAdapter);


    }
    private void initDate(){
        level="LL";
        time="13:19:00";
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String month;
        String day;
        if(mMonth<10){
            month="0"+mMonth+1;
        }else{
            month=String.valueOf(mMonth+1);
        }
        if(mDay<10){
            day="0"+mDay;
        }else{
            day=String.valueOf(mDay);
        }
        date=String.valueOf(mYear) + "-"
                + month + "-"
                + day;
        dateText.setText(date);


    }
    private void initListerer(){
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(AccidentActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
        BtnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Couldn't load photo", Toast.LENGTH_LONG).show();
                }
            }
        });

        BtnOpenImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Intent galleryIntent = new Intent(
//                        Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(galleryIntent , RESULT_GALLERY );
                try {
                    Intent intent = new Intent();
// Show only images, no videos or anything else
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Couldn't load photo", Toast.LENGTH_LONG).show();
                }
            }
        });

        BtnSentImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToMaximo();
            }
        });
        reported.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccidentActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        dateText.setOnClickListener(new View.OnClickListener() {
            private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
                String monthOfYearT;
                String dayOfMonthT;
                if(monthOfYear<10){
                    monthOfYearT="0"+monthOfYear+1;
                }else{
                    monthOfYearT=String.valueOf(monthOfYear+1);
                }
                if(dayOfMonth<10){
                    dayOfMonthT="0"+dayOfMonth;
                }else {
                    dayOfMonthT=String.valueOf(dayOfMonth);
                }
                return String.valueOf(year) + "-"
                        + monthOfYearT + "-"
                        + dayOfMonthT;
            }
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(AccidentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        final String format = setDateFormat(year,month,day);
                        dateText.setText(format);
                        date=format;

                    }

                }, mYear,mMonth, mDay).show();
            }

        });

        timetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int date = c.get(Calendar.DATE);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                // Create a new instance of TimePickerDialog and return it
                new TimePickerDialog(AccidentActivity.this, new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(minute<10){
                            String minute1="0"+minute;
                            timetext.setText(hourOfDay + ":" + minute1+":00");
                            time=hourOfDay + ":" + minute1+":00";
                        }else{
                            timetext.setText(hourOfDay + ":" + minute+":00");
                            time=hourOfDay + ":" + minute+":00";
                        }

                    }
                }, hour, minute, false).show();

            }
        });


    }

    public void onSelect(View view){
        switch(view.getId()){
            case R.id.HH:
//                Toast.makeText(MainActivity.this, "安安, 程式設計師!", Toast.LENGTH_SHORT).show();
                level="HH";
                break;
            case R.id.H:
                level="H";
                break;
            case R.id.M:
                level="M";

                break;
            case R.id.L:
                level="L";

                break;
            case R.id.LL:
                level="LL";

                break;
        }
    }

    public void showLoginDialog(View view)
    {
        AccidentCategoryFragment dialog = new AccidentCategoryFragment();
        dialog.show(getFragmentManager(), "loginDialog");
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        try {
            switch (requestCode) {
                case CAMERA_PIC_REQUEST:
                    if (resultCode == RESULT_OK) {
                        try {
                            Bitmap photo = (Bitmap) data.getExtras().get("data");
                            ImgPhoto.setImageBitmap(photo);
                        } catch (Exception e) {
                            Toast.makeText(this, "Couldn't load photo", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                case PICK_IMAGE_REQUEST:
                    if (resultCode == RESULT_OK) {
                        Uri uri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            // Log.d(TAG, String.valueOf(bitmap));

                            ImgPhoto.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
//                case QuestionEntryView.RESULT_GALLERY :
//                    if (null != data) {
//                        imageUri = data.getData();
//                        //Do whatever that you desire here. or leave this blank
//
//                    }
//                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    private void saveToMaximo(){
        if(checkNetworkConnection()) {


            final Map<String, String> map = new HashMap<String, String>();
            final int min = 100000000;
            final int max = 999999999;
            final String random = Integer.toString(new Random().nextInt((max - min) + 1));
            final String id = "AC" + random;
            map.put("accidentid", id);
            map.put("createdate", date+"T"+time);
            map.put("description", accidentType.getSelectedItem().toString());
            map.put("location", location.getSelectedItem().toString());
            map.put("remdy", remedy.getText().toString());
            map.put("insurance_description", level);


            final JSONObject jsonObject = new JSONObject(map);

            JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST,"http://nickall.asuscomm.com:9080/maximo/oslc/os/ZZ_ACCIDENTREP?lean=1", jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("sss", "response -> " + response.toString());

                            try {
                                String zz_accidentid=response.getString("zz_accidentid");
                                Log.d("sss",zz_accidentid);
                                map.put("zz_accidentid",zz_accidentid);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            final JSONObject jsonObjectReport = new JSONObject(map);
                            Log.d("sss", "responsessss -> " + jsonObjectReport.toString());


                            JsonRequest<JSONObject> jsonRequestNode = new JsonObjectRequest(Request.Method.POST,"http://nickall.asuscomm.com:1880/cpcapi", jsonObjectReport,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.d("sss", "responseNode -> " + response.toString());
                                            Log.d("sss", "response -> " + id);
                                            AlertDialog.Builder adb = new AlertDialog.Builder(AccidentActivity.this)
                                                    .setTitle("儲存成功")
                                                    .setMessage("儲存成功，回主畫面！！")
                                                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent= new Intent(AccidentActivity.this,MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    });
                                            AlertDialog alertDialog = adb.create();
                                            alertDialog.show();

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("sss", error.getMessage(), error);
                                }
                            })
                            {
                                @Override
                                public Map<String, String> getHeaders() {
                                    HashMap<String, String> headers = new HashMap<String, String>();
                                    headers.put("Accept", "application/json");
                                    headers.put("Content-Type", "application/json;charset=UTF-8");
                                    return headers;
                                }
                            };

                            jsonRequestNode.setRetryPolicy(new DefaultRetryPolicy(
                                    10000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            MySingleTon.getmInstance(AccidentActivity.this).addToRequestque(jsonRequestNode);



                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("sss", error.getMessage(), error);
                }
            })
            {
                @Override
                public Map<String, String> getHeaders() {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "application/json");
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("maxauth", "bWF4YWRtaW46emFxMXhzVzI=");
                    headers.put("properties", "*");
                    return headers;
                }
            };

            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleTon.getmInstance(this).addToRequestque(jsonRequest);
//            Volley.newRequestQueue(this).add(jsonRequest);




    }else{
            Log.d("error","connect error");
        }


    }
    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }


}
