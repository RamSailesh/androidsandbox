package portfolico.sandboxgarikip.com.popularmovies_stage1;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.*;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }

    @Override
    public void onStart() {
        super.onStart();
        Movie mv = (Movie)(getIntent().getSerializableExtra("SELECTED_MOVIE"));
        updateMovieDetailContents(mv);
    }

    private void updateMovieDetailContents(Movie mv) {
        if(mv == null) {
            return;
        }

        ImageView im = (ImageView)findViewById(R.id.movieposter_imageview);
        String s = getBaseContext().getString(R.string.movie_pic_url) + mv.getPosterPath();
        Picasso.with(getBaseContext()).load(s).into(im);

        TextView releasedate_tv = (TextView)(findViewById(R.id.releasedate_textview));
//        Html.fromHtml("<b>Release Date</b> <br>" + mv.getReleaseDate())
        //releasedate_tv.setText("<b>Release Date</b>\n" + mv.getReleaseDate());
        releasedate_tv.setText(Html.fromHtml("<b>Release Date</b> <br>" + mv.getReleaseDate()));

        TextView userrating_tv = (TextView)(findViewById(R.id.avgrating_textview));
//        userrating_tv.setText("User Rating\n"+mv.getUserRating() + "/10");
        userrating_tv.setText(Html.fromHtml("<b>User Rating</b> <br>" + mv.getUserRating() + "/10"));

        TextView plot_tv = (TextView)(findViewById(R.id.plot_textview));
        //plot_tv.setText("Plot\n"+mv.getPlot());
        plot_tv.setText(Html.fromHtml("<b>Plot</b> <br>" + mv.getPlot()));

        setTitle(mv.getTitle());
    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//
//        }
//        else {
//
//        }
//    }
}
