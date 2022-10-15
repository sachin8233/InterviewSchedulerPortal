package exampal.com.scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomeBasedAdapter extends BaseAdapter {
    Context context;
    String listInterview [];
    LayoutInflater layoutInflater;
    Data obj=new Data();
    List<interview_widget> interview=obj.interviewArray;
    public CustomeBasedAdapter(Context ctx , String [] interview){
        this.context=ctx;
        this.listInterview=interview;
        layoutInflater=LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return interview.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txtView =(TextView)convertView.findViewById(R.id.textView);
        txtView.setText(interview.get(position).getInterview_name());
        return  convertView;

    }
}
