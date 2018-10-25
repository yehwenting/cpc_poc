package com.example.wentingy.cpcmaximopoc.Model;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.wentingy.cpcmaximopoc.R;

import org.json.JSONException;
import org.json.JSONObject;


public class AccidentCategoryFragment extends DialogFragment {

    public interface OnInputSelected{
        void sendInput(String input);
    }

    public OnInputSelected mInputSelected;
    private Context context;
    private CheckBox all,injury1,injury2,injury3,injury4,accident1,accident2;
    private JSONObject detail;
    private String returnText;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        context=getActivity().getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_accident_category, null);
        initView(view);
        initDate();
        initListerer();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("確定",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                                returnText="";
                                if(injury1.isChecked()){
                                    try {
                                        returnText=returnText+detail.getString("injury1")+"\n";
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(injury2.isChecked()){
                                    try {
                                        returnText=returnText+detail.getString("injury2")+"\n";
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(injury3.isChecked()){
                                    try {
                                        returnText=returnText+detail.getString("injury3")+"\n";
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(injury4.isChecked()){
                                    try {
                                        returnText=returnText+detail.getString("injury4")+"\n";
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(accident1.isChecked()){
                                    try {
                                        returnText=returnText+detail.getString("accident1")+"\n";
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(accident2.isChecked()){
                                    try {
                                        returnText=returnText+detail.getString("accident2")+"\n";
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(all.isChecked()){
                                    try {
                                        returnText="";
                                        returnText=detail.getString("injury1")+"\n"+
                                                detail.getString("injury2")+"\n"+
                                                detail.getString("injury3")+"\n"+
                                                detail.getString("injury4")+"\n"+
                                                detail.getString("accident1")+"\n"+
                                                detail.getString("accident2");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                mInputSelected.sendInput(returnText);
                            }
                        }).setNegativeButton("取消", null);
        return builder.create();

    }

    private void initView(View view){
        all = view.findViewById(R.id.checkBox);
        all.setOnCheckedChangeListener(chklistener);
        injury1 = view.findViewById(R.id.injury1);
        injury1.setOnCheckedChangeListener(chklistener);
        injury2 = view.findViewById(R.id.injury2);
        injury2.setOnCheckedChangeListener(chklistener);
        injury3 = view.findViewById(R.id.injury3);
        injury3.setOnCheckedChangeListener(chklistener);
        injury4 = view.findViewById(R.id.injury4);
        injury4.setOnCheckedChangeListener(chklistener);
        accident1 = view.findViewById(R.id.accident1);
        accident1.setOnCheckedChangeListener(chklistener);
        accident2 = view.findViewById(R.id.accident2);
        accident2.setOnCheckedChangeListener(chklistener);
    }
    private void initDate(){
        detail = new JSONObject();
        try {
            detail.put("injury1", "職業災害:1.死亡事故或3人以上受傷");
            detail.put("injury2", "職業災害:2.受傷就醫需住院治療");
            detail.put("injury3", "職業災害:3.交通事故外造成受傷或請\n公傷假");
            detail.put("injury4", "職業災害:4.交通事故造成職業傷害需就醫\n或請公傷假");
            detail.put("accident1", "意外事故:1.發生火災有出動消防車或\n爆炸事故");
            detail.put("accident2", "意外事故:2.其他發生火災事故");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    private void initListerer(){

    }
    private CheckBox.OnCheckedChangeListener chklistener = new CheckBox.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            switch(buttonView.getId()) {
                case R.id.checkBox:
                    if(all.isChecked()){
                        injury1.setChecked(true);
                        injury2.setChecked(true);
                        injury3.setChecked(true);
                        injury4.setChecked(true);
                        accident1.setChecked(true);
                        accident2.setChecked(true);
                        all.setChecked(true);
                    }else{
                        injury1.setChecked(false);
                        injury2.setChecked(false);
                        injury3.setChecked(false);
                        injury4.setChecked(false);
                        accident1.setChecked(false);
                        accident2.setChecked(false);
                        all.setChecked(false);
                    }
                    break;

                // TODO: Veggie sandwich
            }

            if(all.isChecked()){
                if(!injury1.isChecked()||!injury2.isChecked()||!injury3.isChecked()||!injury4.isChecked()
                        ||!accident1.isChecked()||!accident2.isChecked()){
                    all.setChecked(false);
                }
            }



        }
    };

        @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mInputSelected=(OnInputSelected) getActivity();
        }catch (ClassCastException e){
            Log.d("rr","onAttach:"+e.getMessage());
        }
    }
}
