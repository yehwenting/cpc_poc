package com.example.wentingy.cpcmaximopoc.AccidentActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wentingy.cpcmaximopoc.Mainpage.MainActivity;
import com.example.wentingy.cpcmaximopoc.Model.AccidentCategoryFragment;
import com.example.wentingy.cpcmaximopoc.R;

import java.util.Calendar;

public class AccidentActivity extends AppCompatActivity implements AccidentCategoryFragment.OnInputSelected{

    private static final int CAMERA_PIC_REQUEST = 22;

    Uri cameraUri;

    private ImageView back;
    private Button BtnSelectImage,accident_categoryBtn;
    private ImageView ImgPhoto;
    private String Camerapath ;
    public TextView accident_category;
    private TextView dateText;
    private TextView save;

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
        back=findViewById(R.id.back);
        ImgPhoto = findViewById(R.id.ImgPhoto);
        BtnSelectImage = findViewById(R.id.BtnSelectImg);
        accident_categoryBtn = findViewById(R.id.accident_categoryBtn);
        accident_category = findViewById(R.id.accident_category);
        save = findViewById(R.id.save);
        dateText = findViewById(R.id.datetext);


    }
    private void initDate(){

    }
    private void initListerer(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccidentActivity.this, MainActivity.class);
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
        accident_categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog(v);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        dateText.setOnClickListener(new View.OnClickListener() {
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


}
