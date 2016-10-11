package ivan.is.awesome.kandykrush;

        import android.app.Activity;
        import android.content.Context;
        import android.media.MediaPlayer;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

class ListAdapter extends BaseAdapter {

    private Activity activity;
    private MediaPlayer data[];
    private static LayoutInflater inflater=null;

    ListAdapter(Activity a, MediaPlayer[] d){
        activity = a;
        data=d;
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
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.drawer_layout, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        String array[] = activity.getResources().getStringArray(R.array.titles);
        title.setText(array[position]);
        // Setting all values in listview

        return vi;
    }
}
