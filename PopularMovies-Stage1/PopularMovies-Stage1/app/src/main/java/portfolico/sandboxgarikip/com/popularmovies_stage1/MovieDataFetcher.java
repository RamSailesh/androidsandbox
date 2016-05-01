package portfolico.sandboxgarikip.com.popularmovies_stage1;

/**
 * Created by No1 on 29-04-2016.
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.squareup.picasso.*;

public class MovieDataFetcher extends AsyncTask<String, Integer, ArrayList<Movie>> {
    public Activity detailActivity = null;

    public ImageAdapter im = null;

    @Override
    protected ArrayList<Movie> doInBackground(String... params) {
        if(im != null) {
            String movieDataLink = im.getContext().getString(R.string.movie_url) + params[0] + "?api_key="+ im.getContext().getString(R.string.API_KEY) + "&sort_by=created_at";
            return processJSONString(getData(movieDataLink));
        }

        return null;
    }

    private String getData(String path) {
        String result = "";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(path);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                result = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                result = null;
            }
            result = buffer.toString();
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            result = null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        return result;
    }

    private ArrayList<Movie> processJSONString(String jsonString) {
        ArrayList<Movie> moviesList = new ArrayList<Movie>();

        if(jsonString == null || jsonString == "") {
            return moviesList;
        }

        JSONObject rootObj = null;

        try {
            rootObj = new JSONObject(jsonString);
            JSONArray moviesArray = rootObj.getJSONArray("results");

            for(int i =0;i < moviesArray.length(); ++i) {
                Movie mv = convertJSONObj2Movie(moviesArray.getJSONObject(i));
                //Log.e("getView",mv.getTitle());
                if(mv != null) {
                    moviesList.add(mv);
                }
            }
        }
        catch (JSONException e) {
            Log.e("processJSONString", e.getMessage());
        }

        return moviesList;
    }

    private Movie convertJSONObj2Movie(JSONObject obj) {
        Movie mv = null;

        try {
            mv = new Movie(obj.getString("original_title"),
                    obj.getString("id"),
                    obj.getString("overview"),
                    obj.getString("poster_path"),
                    obj.getString("vote_average"),
                    obj.getString("release_date"));
        }
        catch (Exception e) {
            Log.e("@processJSONString",e.getMessage());
        }
        return mv;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> model) {
        if(im != null) {
        //    Log.e("getV", String.valueOf(model.size()));
            im.setMovieDataModel(model);
        }
    }
}
