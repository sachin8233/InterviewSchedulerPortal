package exampal.com.scheduler;

import android.app.Activity;

import android.content.Context;

import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.TextView;


import java.util.ArrayList;

public class Interview_Adapter extends ArrayAdapter<interview_widget> {
    Context context;
    int layoutResourceId;

    ArrayList<interview_widget> data = new ArrayList<interview_widget>();

    public Interview_Adapter(Context context, int layoutResourceId,
                             ArrayList<interview_widget> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.interviewName = (TextView) row.findViewById(R.id.InterviewText);
            holder.date = (TextView) row.findViewById(R.id.textView2);
            holder.startTime = (TextView) row.findViewById(R.id.textView3);
            holder.endTime= (TextView) row.findViewById(R.id.textView4);
            holder.candidate= (TextView) row.findViewById(R.id.textView5);
            holder.interviewer = (TextView) row.findViewById(R.id.textView6);
            holder.button1 = (Button) row.findViewById(R.id.edtBtn);
            holder.button2 = (Button) row.findViewById(R.id.deleteBtn);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        interview_widget user = data.get(position);
        holder.interviewName.setText(user.interview_name);
        holder.date.setText(user.date);
        holder.startTime.setText(user.start_time);
        holder.endTime.setText(user.end_time);
        holder.candidate.setText(user.candidate);
        holder.interviewer.setText(user.interviewer);


        holder.button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

              Intent intent= new Intent(parent.getContext(),Poratl.class);
                System.out.println("userrrrr");
                System.out.println(data.get(position).id);
                System.out.println(user.id);
              intent.putExtra("id",user.id);
              parent.getContext().startActivity(intent);

            }
        });
        holder.button2.setTag(position);
        holder.button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              //  System.out.println(user.id);
            //  Data.delete(user.id);
               int pos= (int)v.getTag();
               data.remove(pos);
               Interview_Adapter.this.notifyDataSetChanged();
            }
        });

        return row;

    }

    static class UserHolder {
        TextView interviewName;
        TextView date;
        TextView startTime;
        TextView endTime;
        TextView candidate;
        TextView interviewer;
        Button button1;
        Button button2;

    }
}