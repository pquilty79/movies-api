package Dao;

import com.mysql.cj.jdbc.Driver;
import data.Config;
import data.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao {

    private final Connection connection;

    public MySqlMoviesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            this.connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }


    @Override
    public List<Movie> all() throws SQLException {

        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM movies";
        ResultSet rs = statement.executeQuery(sql);

        List<Movie> movies = new ArrayList<>();

        while (rs.next()) {
            movies.add(new Movie(
                    rs.getString("title"),
                    rs.getString("director"),
                    rs.getString("actors"),
                    rs.getString("genre"),
                    rs.getInt("year"),
                    rs.getDouble("rating"),
                    rs.getString("plot"),
                    rs.getString("poster"),
                    rs.getString("trailerURL"),
                    rs.getString("runtime"),
                    rs.getString("MPAA"),
                    rs.getBoolean("favorite"),
                    rs.getInt("id")
                        ));
        }
        return movies;
    }


    @Override
    public Movie findOne(int id) throws SQLException {
        String sql = "SELECT * FROM movies WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        rs.next();
        return new Movie(rs.getString("title"),
                rs.getString("director"),
                rs.getString("actors"),
                rs.getString("genre"),
                rs.getInt("year"),
                rs.getDouble("rating"),
                rs.getString("plot"),
                rs.getString("poster"),
                rs.getString("trailerURL"),
                rs.getString("runtime"),
                rs.getString("MPAA"),
                rs.getBoolean("favorite"),
                rs.getInt("id")
        );
    }

    @Override
    public void insert(Movie movie) throws SQLException {
        String sql = "INSERT INTO movies (title, director, actors, genre, year, rating, plot, poster, trailerURL, runtime, MPAA, favorite) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getDirector());
        statement.setString(3, movie.getActors());
        statement.setString(4, movie.getGenre());
        statement.setInt(5, movie.getYear());
        statement.setDouble(6, movie.getRating());
        statement.setString(7, movie.getPlot());
        statement.setString(8, movie.getPoster());
        statement.setString(9, movie.getTrailerURL());
        statement.setString(10, movie.getRuntime());
        statement.setString(11, movie.getMPAA());
        statement.setBoolean(12, movie.isFavorite());
        statement.executeUpdate();
    }


    public void insertAll(Movie[] movies) throws SQLException {

        StringBuilder sql = new StringBuilder("INSERT INTO movies (" +
                "title, director, actors, genre, year, rating, plot, poster, trailerURL, runtime, MPAA, favorite) " +
                "VALUES ");
        sql.append("(?,?,?,?,?,?,?,?,?,?,?,?), ".repeat(movies.length));
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));
        PreparedStatement statement = connection.prepareStatement(sql.toString());

        int counter = 0;
        for (Movie movie : movies) {
            statement.setString((counter * 12) + 1, movie.getTitle());
            statement.setString((counter * 12) + 2, movie.getDirector());
            statement.setString((counter * 12) + 3, movie.getActors());
            statement.setString((counter * 12) + 4, movie.getGenre());
            statement.setInt((counter * 12) + 5, movie.getYear());
            statement.setDouble((counter * 12) + 6, movie.getRating());
            statement.setString((counter * 12) + 7, movie.getPlot());
            statement.setString((counter * 12) + 8, movie.getPoster());
            statement.setString((counter * 12) + 9, movie.getTrailerURL());
            statement.setString((counter * 12) + 10, movie.getRuntime());
            statement.setString((counter * 12) + 11, movie.getMPAA());
            statement.setBoolean((counter * 12) + 12, movie.isFavorite());
            counter++;
        }
        statement.executeUpdate();
    }


    @Override
    public void update(Movie movie) throws SQLException {

        String sql = "UPDATE movies SET title = ?, director = ?, actors = ?, genre = ?, year = ?, rating = ?, plot = ?, poster = ?, trailerURL = ?, runtime = ?, MPAA = ?, favorite = ?" +
                " WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getDirector());
        statement.setString(3, movie.getActors());
        statement.setString(4, movie.getGenre());
        statement.setInt(5, movie.getYear());
        statement.setDouble(6, movie.getRating());
        statement.setString(7, movie.getPlot());
        statement.setString(8, movie.getPoster());
        statement.setString(9, movie.getTrailerURL());
        statement.setString(10, movie.getRuntime());
        statement.setString(11, movie.getMPAA());
        statement.setBoolean(12, movie.isFavorite());
        statement.setInt(13, movie.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
    }


}












