package com.example.parkingsearchapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter {
    private DatabaseReference Database = FirebaseDatabase.getInstance().getReference();

    List<Reports> reportsList;
    Boolean LikeChecker=false;
    DatabaseReference likes_ref,reports_ref;
    private FirebaseAuth mAuth;

    public HelperAdapter(List<Reports> reportsList) {
        this.reportsList = reportsList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_all_reports, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass) holder;
        mAuth = FirebaseAuth.getInstance();
        String currentUserId;
        currentUserId =mAuth.getCurrentUser().getUid();

        Reports reports_data = reportsList.get(position);
        String post_id = reports_data.getPost_id();
        viewHolderClass.username.setText(reports_data.getUser_name());
        viewHolderClass.addressText.setText(reports_data.getAddress());
        viewHolderClass.TextReportTime.setText(reports_data.getTime());
        viewHolderClass.FreeParkgAmnt.setText(reports_data.getFree_Parking_Amount());
        viewHolderClass.Text_is_Cost.setText(reports_data.getIsCost());
        viewHolderClass.Text_is_Indoor.setText(reports_data.getIsIndoor());
        viewHolderClass.Text_is_Disabled.setText(reports_data.getIsDisabled());
        viewHolderClass.Text_Disabled_Amnt.setText(reports_data.getIsDisabledAmount());
        likes_ref = FirebaseDatabase.getInstance().getReference().child("likes");
        viewHolderClass.setLikeButtonStatus(post_id);

        viewHolderClass.dislike_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeChecker=true;
                likes_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(LikeChecker.equals(true)){
                            if(snapshot.child(post_id).hasChild(currentUserId)){
                                likes_ref.child(post_id).child(currentUserId).removeValue();
                                LikeChecker=false;
                            }
                            else
                            {
                                likes_ref.child(post_id).child(currentUserId).setValue(true);
                                LikeChecker=false;

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return reportsList.size();
    }





    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView username, addressText, TextReportTime, FreeParkgAmnt, Text_is_Cost, Text_is_Indoor, Text_is_Disabled, Text_Disabled_Amnt, textDis;
        ImageButton dislike_button,like_button;
        TextView number_of_likes;
        int countLikes;
        String currentUserId;
        DatabaseReference likes_ref;

        public ViewHolderClass(@NonNull View reportView) {
            super(reportView);
            username = reportView.findViewById(R.id.ReportUserName);
            addressText = reportView.findViewById(R.id.TextAddress);
            TextReportTime = reportView.findViewById(R.id.ReportTime);
            FreeParkgAmnt = reportView.findViewById(R.id.TextFreeParkingAmnt);
            Text_is_Cost = reportView.findViewById(R.id.TextIsCost);
            Text_is_Indoor = reportView.findViewById(R.id.TextIsIndoor);
            Text_is_Disabled = reportView.findViewById(R.id.TextIsDisabled);
            Text_Disabled_Amnt = reportView.findViewById(R.id.TextIsDisabledAmnt);
            dislike_button = reportView.findViewById(R.id.imageLike);
            number_of_likes = reportView.findViewById(R.id.titleLikes);

            likes_ref = FirebaseDatabase.getInstance().getReference().child("likes");
            currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        public void setLikeButtonStatus( final String post_id){
            likes_ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(post_id).hasChild(currentUserId)){
                        countLikes =(int) snapshot.child(post_id).getChildrenCount();
                        dislike_button.setImageResource(R.drawable.like);
                        number_of_likes.setText((Integer.toString(countLikes)+(" likes")));

                    }
                    else{
                        countLikes =(int) snapshot.child(post_id).getChildrenCount();
                        dislike_button.setImageResource(R.drawable.dislike);
                        number_of_likes.setText((Integer.toString(countLikes)+(" likes")));

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}
