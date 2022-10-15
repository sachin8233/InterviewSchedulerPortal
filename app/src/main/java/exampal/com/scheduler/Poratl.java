package exampal.com.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class Poratl extends AppCompatActivity {
    EditText date;
    DatePickerDialog datePickerDialog;
    EditText time;
    EditText endTime;
 //   TextView name;
    FirebaseDatabase database;
    DatabaseReference reference;
     Spinner s1 ;
     Spinner s2 ;
    String[] candidate_emails = new String[]{"No Selection","kumar12@gmail.com", "ram32@gmail.com", "mohan34@gmail.com"};
    String[] interviewer_emails = new String[]{"No Selection","sac23@gmail.com", "shikhar23@gmail.com", "home54@gmail.com"};
    private Button button;
//   LocalDate myObj = LocalDate.now();
//    LocalTime localTime = LocalTime.now();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_poratl);
        date = (EditText) findViewById(R.id.date);
        Bundle extras = getIntent().getExtras();
        s1=(Spinner) findViewById(R.id.candidate_email);
        s2 = (Spinner) findViewById(R.id.interviewer_email);
        time = (EditText) findViewById(R.id.time);
        endTime =(EditText) findViewById(R.id.endTime);

       // name=(TextView) findViewById(R.id.InterviewText);

        if(extras!=null){


            String id=extras.getString("id");
            System.out.println(id);
            interview_widget interview=Data.getInterview(id);
            if(interview!=null){
                date.setText(interview.date);
                time.setText(interview.start_time);
                endTime.setText(interview.end_time);
                s1.setSelection(getIndex(s1,interview.candidate,candidate_emails));
                s2.setSelection(getIndex(s2,interview.interviewer,interviewer_emails));



            }

        }
        button=findViewById(R.id.submit);


        // perform click event on edit text
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!checkAllFields())return ;

                database= FirebaseDatabase.getInstance();
                reference=database.getReference("Interview" );


                String n=date.getText().toString();
                String S=time.getText().toString();
                String E=endTime.getText().toString();
                String candidate_email=s1.getSelectedItem().toString();
                String interviewer_email=s2.getSelectedItem().toString();
                String id;


                //String I=name.getText().toString();
//

                if(extras!=null){
                    //editing
                    id=extras.getString("id");
                    interview_widget new_interview=new interview_widget(id,"Interview",n,S,E,candidate_email
                            ,interviewer_email);
                    Data.Edit(id,new_interview);
                    interview_widget widget=new interview_widget(id,"Interview" , n , S,E,candidate_email,interviewer_email );
                    reference.child(id).setValue(widget);
                }
                else{
                    //adding
                 //  System.out.println(localTime);
                    id=reference.push().getKey();
                    String newId=UUID.randomUUID().toString();
                    interview_widget new_interview=new interview_widget(newId,"Interview",n,S,E,candidate_email
                            ,interviewer_email);
                    Data.Add(new_interview);
                    interview_widget widget=new interview_widget(id,"Interview 1" , n , S,E,candidate_email,interviewer_email );
                    reference.child(id).setValue(widget);
                }
//



                Intent intent= new Intent(Poratl.this ,MainActivity.class);

                startActivity(intent);

            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Poratl.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Poratl.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");

                mTimePicker.show();

            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog TimePicker;
                TimePicker = new TimePickerDialog(Poratl.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                TimePicker.setTitle("Select Time");

                TimePicker.show();

            }
        });



        Spinner dropdown = findViewById(R.id.candidate_email);



//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, candidate_emails);
//set the spinners adapter to the previously created one.
        dropdown.setSelected(false);
        dropdown.setAdapter(adapter);

        Spinner dropdown2= findViewById(R.id.interviewer_email);



        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, interviewer_emails);
        dropdown2.setSelected(false);
        dropdown2.setAdapter(adapter2);


//       // Spinner dropdown3= findViewById(R.id.spinner3);
//
//        String[] items3 = new String[]{"1","2","3","4","5","6","7","8","9"};
//
//        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
//
//        dropdown3.setAdapter(adapter3);

    }
    private boolean checkAllFields(){
            if (date.length() == 0) {
                date.setError("Date field is required");
                return false;
            }

            if (time.length() == 0) {
                time.setError("Start Time field is required");
                return false;
            }

            if (endTime.length() == 0) {
                endTime.setError("End Time Field is required");
                return false;
            }

            if (s1.getSelectedItem().toString().equals("No Selection")) {
                ((TextView)s1.getSelectedView()).setError("Select a Candidate");
                return false;
            }
            if (s2.getSelectedItem().toString().equals("No Selection")) {
                ((TextView)s2.getSelectedView()).setError("Select an Interviewer");
                return false;
            }

        // after all validation return true.
        return true;
    }
    private int getIndex(Spinner spinner, String myString,String []emails){
        System.out.println(myString);
        System.out.println(spinner.getCount());
        System.out.println(emails.length);

        for(int i=0;i<emails.length;i++){
            if(emails[i].equals(myString)){
                System.out.println(i);
                return i;
            }
        }
//        for (int i=0;i<spinner.getCount();i++){
//            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
//                return i;
//            }
//        }

        return 0;
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}