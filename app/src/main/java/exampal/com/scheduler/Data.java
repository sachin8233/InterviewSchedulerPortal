package exampal.com.scheduler;




import java.util.ArrayList;


public class Data {



    static ArrayList<interview_widget> interviewArray=new ArrayList<interview_widget>(){{
        new interview_widget("1","Interview 1", "14-10-2022", "10:12","11:10","abs12@gamil.com",
                " asc32@gmail.com");
        new interview_widget("2","Interview 2", "15-10-2022", "10:12","11:10","abs12@gamil.com",
                " asc32@gmail.com");
    }};


    static interview_widget getInterview(String uid){
        for(int i=0;i<interviewArray.size();i++){
            if(interviewArray.get(i).id.equals(uid)){
                return interviewArray.get(i);
            }
        }
        return null;
    }
    static void Add(interview_widget CurrentInterview){
        interviewArray.add(CurrentInterview);

     }
     static void Edit(String id,interview_widget new_interview){
        ArrayList<interview_widget>newInterViewArray=new ArrayList<>();
        for(int i=0;i<interviewArray.size();i++){
            if(interviewArray.get(i).id.equals(id)){

                newInterViewArray.add(new_interview);

            }
            else {
                newInterViewArray.add(interviewArray.get(i));
            }
        }
        interviewArray=newInterViewArray;

     }
     static void delete(String id){
        for(int i=0;i<interviewArray.size();i++){
            if(interviewArray.get(i).id==id){
                interviewArray.remove(i);
                break;
            }
        }

     }
}
