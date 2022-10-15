package exampal.com.scheduler;

public class interview_widget {
    String id;
    String interview_name;
    String date;
    String start_time;
    String end_time;
    String  candidate;
    String interviewer;
    public interview_widget(String id,String interview_name, String date, String start_time, String end_time, String candidate, String interviewer) {
        this.id=id;
        this.interview_name = interview_name;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.candidate = candidate;
        this.interviewer = interviewer;
    }

    public String getInterview_name() {
        return interview_name;
    }

    public void setInterview_name(String interview_name) {
        this.interview_name = interview_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }
}



