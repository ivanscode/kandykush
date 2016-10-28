package ivan.is.awesome.kandykrush.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import ivan.is.awesome.kandykrush.R;
import ivan.is.awesome.kandykrush.utils.ListAdapter;

public class ListPlayback extends Fragment {

    public MediaPlayer[] mp;
    ArrayList<String> titles = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.meme_frag_layout, container, false);
        ListView mDrawerList = (ListView) rootView.findViewById(R.id.left_drawer);
        setupMedia();
        mDrawerList.setAdapter(new ListAdapter(getActivity(), mp, titles));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mp[position].start();

            }
        });
        return rootView;
    }
    public void setupMedia() {
        Field[] fields = R.raw.class.getFields();
        // loop for every file in raw folder
        mp = new MediaPlayer[fields.length];
        for(int x=0; x < fields.length; x++) {
            String filename = fields[x].getName();
            mp[x] = MediaPlayer.create(getActivity(), getResources().getIdentifier(filename, "raw", getActivity().getPackageName()));
            filename = filename.substring(0, 1).toUpperCase() + filename.substring(1);
            filename = filename.replace("_", " ");
            titles.add(filename);
        }
    }
}

