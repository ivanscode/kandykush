package ivan.is.awesome.kandykrush.utils;

        import android.app.Activity;
        import android.app.Fragment;
        import android.content.Context;
        import android.media.MediaPlayer;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;

        import ivan.is.awesome.kandykrush.R;

public class ListAdapter extends BaseAdapter {

    private Activity activity;
    private MediaPlayer data[];
    private static LayoutInflater inflater=null;
    private ArrayList<String> titles = new ArrayList<>();


    public ListAdapter(Activity a, MediaPlayer[] d, ArrayList<String> t){
        activity = a;
        data=d;
        titles = t;
        inflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.drawer_layout, null);
            TextView title = (TextView)convertView.findViewById(R.id.title); // title
            title.setText(titles.get(position));
        return convertView;
    }
}
