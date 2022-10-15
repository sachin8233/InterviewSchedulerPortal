package exampal.com.scheduler;



import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView userList;
     Interview_Adapter interview_adapter;
     Button button3;
    FirebaseDatabase firebaseDatabase;
    ArrayList<interview_widget> userArray = new ArrayList<interview_widget>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();
        button3=findViewById(R.id.addBtn);

        /**
         * add item in arraylist
         */
        userArray=Data.interviewArray;


        /**
         * set item into adapter
         */
        interview_adapter= new Interview_Adapter(MainActivity.this, R.layout.activity_custom_list_view,
                userArray);
        userList = (ListView) findViewById(R.id.listView);
        userList.setItemsCanFocus(false);
        userList.setAdapter(interview_adapter);

        /**
         * get on item click listener
         */
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Log.i("List View Clicked", "**********");
                Toast.makeText(MainActivity.this,
                        "List View Clicked:" + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this , Poratl.class);

                startActivity(intent);
            }
        });

    }

public void setData(){
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("Interview");




        // calling add value event listener method
        // for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
            // interview_widget interviews = snapshot.getValue(interview_widget.class);
                HashMap map= new HashMap<>();
                ArrayList<interview_widget>interviewFromDB=new ArrayList<interview_widget>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    System.out.println(postSnapshot.getValue());
                    System.out.println("------------");
                    map=(HashMap)postSnapshot.getValue();
                    //interview_widget newinterview=new interview_widget()


                    System.out.println(postSnapshot.getKey());

                }
                //System.out.println(snapshot.getValue().toString());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });





}


    }
