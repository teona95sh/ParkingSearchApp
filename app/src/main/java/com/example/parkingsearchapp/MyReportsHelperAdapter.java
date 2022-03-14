package com.example.parkingsearchapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyReportsHelperAdapter  extends RecyclerView.Adapter {
    List<Reports> reportsList;
    DatabaseReference likes_ref;
    int countLikes;

    public MyReportsHelperAdapter(List<Reports> reportsList) {
        this.reportsList = reportsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_all_reports, parent, false);
        MyReportsViewHolderClass viewHolderClass = new MyReportsViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyReportsViewHolderClass viewHolderClass = (MyReportsViewHolderClass) holder;

        Reports reports_data = reportsList.get(position);

        viewHolderClass.username.setText(reports_data.getUser_name());
        viewHolderClass.addressText.setText(reports_data.getAddress());
        viewHolderClass.TextReportTime.setText(reports_data.getTime());
        viewHolderClass.FreeParkgAmnt.setText(reports_data.getFree_Parking_Amount());
        viewHolderClass.Text_is_Cost.setText(reports_data.getIsCost());
        viewHolderClass.Text_is_Indoor.setText(reports_data.getIsIndoor());
        viewHolderClass.Text_is_Disabled.setText(reports_data.getIsDisabled());
        viewHolderClass.Text_Disabled_Amnt.setText(reports_data.getIsDisabledAmount());
        viewHolderClass.total_likes(reports_data.getPost_id());
    }

    @Override
    public int getItemCount() {
        return reportsList.size();

    }
    public class MyReportsViewHolderClass extends RecyclerView.ViewHolder {
        TextView username, addressText, TextReportTime, FreeParkgAmnt, Text_is_Cost, Text_is_Indoor, Text_is_Disabled, Text_Disabled_Amnt, textDis;
        ImageButton dislike_button;
        TextView number_of_likes;


        public MyReportsViewHolderClass(@NonNull View reportView) {
            super(reportView);
            username = reportView.findViewById(R.id.ReportUserName);
            addressText = reportView.findViewById(R.id.TextAddress);
            TextReportTime = reportView.findViewById(R.id.ReportTime);
            FreeParkgAmnt = reportView.findViewById(R.id.TextFreeParkingAmnt);
            Text_is_Cost = reportView.findViewById(R.id.TextIsCost);
            Text_is_Indoor = reportView.findViewById(R.id.TextIsIndoor);
            Text_is_Disabled = reportView.findViewById(R.id.TextIsDisabled);
            Text_Disabled_Amnt = reportView.findViewById(R.id.TextIsDisabledAmnt);
            number_of_likes = reportView.findViewById(R.id.titleLikes);
            dislike_button = reportView.findViewById(R.id.imageLike);



        }

        public void total_likes(final String post_id){
            likes_ref = FirebaseDatabase.getInstance().getReference().child("likes").child(post_id);

            likes_ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int count_likes =(int) snapshot.getChildrenCount();
                    dislike_button.setImageResource(R.drawable.like);
                    number_of_likes.setText((Integer.toString(count_likes)+(" likes")));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }
}
