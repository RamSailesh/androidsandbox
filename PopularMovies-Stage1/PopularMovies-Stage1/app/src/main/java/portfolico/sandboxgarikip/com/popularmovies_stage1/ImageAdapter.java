package portfolico.sandboxgarikip.com.popularmovies_stage1;

import com.squareup.picasso.*;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import 	android.widget.BaseAdapter;
import 	android.content.Context;
import  android.view.View;
import  android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by No1 on 26-04-2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private String imageLinkPrefix = "http://image.tmdb.org/t/p/w185";
    private ArrayList<Movie> model = null;

    public void setMovieDataModel(ArrayList<Movie> m) {
        model = m;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public ImageAdapter(Context c) {
        context = c;
    }

    public Movie getMovie(int index) {
        if(model == null || index >= model.size()) {
            return null;
        }

        return model.get(index);
    }

    public int getCount() {
        if(model == null) {
            return 0;
        }
        return model.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }

        try {
            String s = imageLinkPrefix + model.get(position).getPosterPath();
            Picasso.with(context).load(imageLinkPrefix+s).into(imageView);
        }
        catch (Exception e) {
            Log.e("getView"+position, e.getMessage());
        }
        return imageView;
    }
}

