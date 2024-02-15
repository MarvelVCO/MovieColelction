public class Movie {
    private final String title;
    private final String[] cast;
    private final String director;
    private final String overview;
    private final String runtime;
    private final String userRating;

    public Movie(String title, String cast, String director, String overview, String runtime, String userRating) {
        this.title = title;
        this.cast = cast.split("\\|");
        this.director = director;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }

    public void printInfo() {
        System.out.println("Title: " + title + "\n" +
                           "Runtime: " + runtime + "\n" +
                           "Directed by: " + director + "\n" +
                           "Cast: " + cast + "\n" +
                           "Overview: " + overview + "\n" +
                           "User Rating: " + userRating);
    }

    public String getTitle() {
        return title;
    }

    public String[] getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public String getOverview() {
        return overview;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getUserRating() {
        return userRating;
    }
}
