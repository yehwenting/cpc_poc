package com.example.wentingy.cpcmaximopoc.AccidentActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wentingy.cpcmaximopoc.Mainpage.MainActivity;
import com.example.wentingy.cpcmaximopoc.Model.AccidentCategoryFragment;
import com.example.wentingy.cpcmaximopoc.Model.AccidentList;
import com.example.wentingy.cpcmaximopoc.R;

import java.util.Calendar;

public class ViewAccidentActivity extends AppCompatActivity implements AccidentCategoryFragment.OnInputSelected{
    private static final int CAMERA_PIC_REQUEST = 22;

    Uri cameraUri;

    private ImageView back;
    private Button BtnSelectImage,BtnSentImg;
    private ImageView ImgPhoto;
    private String Camerapath ;
    public TextView remedy,reported;
    private TextView dateText;
    private Spinner accidentType,location;

    @Override
    public void sendInput(String input) {
//        accident_category.setText(input);
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
        back=findViewById(R.id.back);
        ImgPhoto = findViewById(R.id.ImgPhoto);
        BtnSelectImage = findViewById(R.id.BtnSelectImg);
        dateText = findViewById(R.id.datetext);
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
        AccidentList accidentList = null;
        accidentList = (AccidentList) getIntent().getSerializableExtra("accidentList ");
        for(int i= 0; i < location.getAdapter().getCount(); i++)
        {
            if(location.getAdapter().getItem(i).toString().contains(accidentList.getLocation()))
            {
                location.setSelection(i);
            }
        }
        for(int i= 0; i < accidentType.getAdapter().getCount(); i++)
        {
            if(accidentType.getAdapter().getItem(i).toString().contains(accidentList.getType()))
            {
                accidentType.setSelection(i);
            }
        }
        dateText.setText(accidentList.getCreatedate());
        remedy.setText(accidentList.getRemdy());



    }
    private void initListerer(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewAccidentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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

        BtnSentImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToMaximo();
            }
        });
        reported.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewAccidentActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int date = c.get(Calendar.DATE);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                // Create a new instance of TimePickerDialog and return it
                new TimePickerDialog(ViewAccidentActivity.this, new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dateText.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false).show();

            }
        });

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
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    private void saveToMaximo(){
        AlertDialog.Builder adb = new AlertDialog.Builder(ViewAccidentActivity.this)
                .setTitle("儲存成功")
                .setMessage("儲存成功，回主畫面！！")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent= new Intent(ViewAccidentActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alertDialog = adb.create();
        alertDialog.show();


//        if(checkNetworkConnection()) {
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("name1", "value1");
//            map.put("name2", "value2");
//
//            JSONObject jsonObject = new JSONObject(params);
//            JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST,httpurl, jsonObject,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            Log.d(TAG, "response -> " + response.toString());
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e(TAG, error.getMessage(), error);
//                }
//            })
//            {
//                @Override
//                public Map<String, String> getHeaders() {
//                    HashMap<String, String> headers = new HashMap<String, String>();
//                    headers.put("Accept", "application/json");
//                    headers.put("Content-Type", "application/json; charset=UTF-8");
//
//                    return headers;
//                }
//            };
//            requestQueue.add(jsonRequest);
//
//
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://nickall.asuscomm.com:9080/maximo/oslc/os/ZZ_ACCIDENTREP",
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            Log.d("uuuuu", response);
//                            AlertDialog.Builder adb = new AlertDialog.Builder(AccidentActivity.this)
//                                    .setTitle("儲存成功")
//                                    .setMessage("儲存成功，回主畫面！！")
//                                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialogInterface, int i) {
//                                            Intent intent= new Intent(AccidentActivity.this,MainActivity.class);
//                                            startActivity(intent);
//                                        }
//                                    });
//                            AlertDialog alertDialog = adb.create();
//                            alertDialog.show();
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                    Log.d("error", "do not save to mysql 2");
//
//                }
//            }) {
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("maxauth", "bWF4YWRtaW46emFxMXhzVzI=");
//                    params.put("Content-Type","application/json");
//                    return params;
//                }
//            };
//
//        }else{
//            Log.d("error","connect error");
//        }


    }
    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }


}
