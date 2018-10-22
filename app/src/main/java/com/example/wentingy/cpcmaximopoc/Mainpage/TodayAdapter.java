package com.example.wentingy.cpcmaximopoc.Mainpage;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wentingy.cpcmaximopoc.AccidentActivity.AccidentActivity;
import com.example.wentingy.cpcmaximopoc.Model.AccidentList;
import com.example.wentingy.cpcmaximopoc.Model.WorkOrder;
import com.example.wentingy.cpcmaximopoc.R;

import java.util.List;


/**
 * Created by Yehwenting on 2018/7/30.
 */

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.TodayViewHolder>{

    private List<WorkOrder> workOrders;
    private List<AccidentList> accidentLists;
    Context context;


    public TodayAdapter(List<AccidentList> accidentLists){
        this.accidentLists=accidentLists;

    }

    @NonNull
    @Override
    public TodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_mainpage_item,null);

        return new TodayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayViewHolder holder, int position) {
        Log.d("test", String.valueOf(position));
//        final WorkOrder workOrder=workOrders.get(position);
        final AccidentList accidentList=accidentLists.get(position);

        holder.accidnetId.setText(accidentList.getAccidentId());
        holder.department.setText(accidentList.getDept());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, AccidentActivity.class);
                context.startActivity(intent);

            }
        });

//        TodayChildAdapter competitionGroupAdapter = new TodayChildAdapter(accidentList);
//        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
//        holder.recyclerView.setHasFixedSize(true);
//        holder.recyclerView.setAdapter(competitionGroupAdapter);
//        holder.recyclerView.getParent().requestDisallowInterceptTouchEvent(true);

    }

    @Override
    public int getItemCount() {
        return accidentLists.size();

    }

    public class TodayViewHolder extends RecyclerView.ViewHolder {

        TextView accidnetId,description,reporter,department,location,time,category,handle;
        RecyclerView recyclerView;
        CardView cardView;



        public TodayViewHolder(View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.cGroup_rv);
            cardView=itemView.findViewById(R.id.cardView2);


            accidnetId = itemView.findViewById(R.id.accidentId);
            description = itemView.findViewById(R.id.condition);
            reporter = itemView.findViewById(R.id.reporter);
            department = itemView.findViewById(R.id.department);
            location = itemView.findViewById(R.id.place);
            time = itemView.findViewById(R.id.time);
            category = itemView.findViewById(R.id.catagory);
            handle = itemView.findViewById(R.id.handle);
        }
    }
}
