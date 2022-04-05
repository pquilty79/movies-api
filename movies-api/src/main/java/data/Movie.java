package data;

public class Movie {

    private String title;
    private String director;
    private String actors;
    private String genre;
    private int year;
    private double rating;
    private String plot;
    private String poster;
    private String trailerURL;
    private String runtime;
    private String MPAA;
    private boolean favorite;
    private int id;

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getMPAA() {
        return MPAA;
    }

    public void setMPAA(String MPAA) {
        this.MPAA = MPAA;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Movie(String title, String director, String actors, String genre, int year, double rating, String plot, String poster, String trailerURL, String runtime, String MPAA, boolean favorite, int id) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.plot = plot;
        this.poster = poster;
        this.trailerURL = trailerURL;
        this.runtime = runtime;
        this.MPAA = MPAA;
        this.favorite = favorite;
        this.id = id;
    }



}
