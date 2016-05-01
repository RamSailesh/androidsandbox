package portfolico.sandboxgarikip.com.popularmovies_stage1;

import java.io.Serializable;

/**
 * Created by No1 on 29-04-2016.
 */
public class Movie implements Serializable{

    private String title;
    private String id;
    private String plot;
    private String posterPath;
    private String userRating;
    private String releaseDate;

    public Movie(String title, String id,String plot,String posterPath, String userRating, String releaseDate) {
        this.title = title;
        this.id = id;
        this.plot = plot;
        this.posterPath = posterPath;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getPlot() { return plot; }

    public String getPosterPath() {
        return posterPath;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
