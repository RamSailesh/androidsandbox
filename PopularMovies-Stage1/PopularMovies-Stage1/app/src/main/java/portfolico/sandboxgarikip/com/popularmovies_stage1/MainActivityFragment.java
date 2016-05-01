package portfolico.sandboxgarikip.com.popularmovies_stage1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.content.Intent;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ImageAdapter im = null;
    static private String dbExecString = "popular";
    private MovieDataFetcher mv = null;

    public MainActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(isNetworkPresent(getContext(), getActivity())) {
            if (item.getItemId() == R.id.most_popular) {
                if (dbExecString != "popular") {
                    dbExecString = "popular";
                    if(mv != null) {
                        mv.cancel(true);
                    }
                    mv = new MovieDataFetcher();
                    mv.im = im;
                    mv.execute(dbExecString);
                }
            } else {
                if (dbExecString != "top_rated") {
                    dbExecString = "top_rated";
                    if(mv != null) {
                        mv.cancel(true);
                    }
                    mv = new MovieDataFetcher();
                    mv.im = im;
                    mv.execute(dbExecString);
                }
            }
            item.setChecked(true);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        im = new ImageAdapter(getActivity().getBaseContext());
        GridView gv = (GridView)(getActivity().findViewById(R.id.movies_gridview));
        gv.setNumColumns(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2);
        gv.setAdapter(im);
        if(isNetworkPresent(getContext(), getActivity())) {
            mv = new MovieDataFetcher();
            mv.im = im;
            mv.execute(dbExecString);
        }

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isNetworkPresent(getContext(), getActivity())) {
                    return;
                }
                Intent intent = new Intent(getContext(), MovieDetail.class);
                Movie mv = im.getMovie(position);
                intent.putExtra("SELECTED_MOVIE", mv);
                startActivity(intent);
            }
        });
    }

    public static boolean isNetworkPresent(Context ctx, Activity activity) {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Please Connect to Internet and try again")
                    .setTitle("Network Error")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
//            dialog.setTitle("Network Error");
//            dialog.setMessage("Please Connect to Internet and try again");
           // Toast.makeText(ctx, "Please connect to Internet", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return info.isConnected();
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        GridView gv = (GridView)(getActivity().findViewById(R.id.movies_gridview));
        gv.setNumColumns(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2);
        if(gv.getCount() == 0) {
            if(isNetworkPresent(getContext(), getActivity())) {
                mv = new MovieDataFetcher();
                mv.im = im;
                mv.execute(dbExecString);
            }
        }
        super.onConfigurationChanged(newConfig);
    }


}
