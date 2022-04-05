package data;


import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao {

    private Connection connection = null;

    public MySqlMoviesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());

            this.connection = DriverManager.getConnection(
                    config.getUrl(), // <-- WHERE IS THE DB?
                    config.getUser(), // <-- WHO IS ACCESSING?
                    config.getPassword() // <-- WHAT IS THEIR PASSWORD?
            );

        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Movie> all() throws SQLException {
        // TODO: Get ALL the movies
    }

    @Override
    public Movie findOne(int id) {
        // TODO: Get one movie by id
    }

    @Override
    public void insert(Movie movie) {
        // TODO: Insert one movie
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
            statement.setString((counter * 12) + 5, Integer.toString(movie.getYear()));
            statement.setString((counter * 12) + 6, Double.toString(movie.getRating()));
            statement.setString((counter * 12) + 7, movie.getPlot());
            statement.setString((counter * 12) + 8, movie.getPoster());
            statement.setString((counter * 12) + 9, movie.getTrailerURL());
            statement.setString((counter * 12) + 10, movie.getRuntime());
            statement.setString((counter * 12) + 11, movie.getMPAA());
            statement.setString((counter * 12) + 12, Boolean.toString(movie.isFavorite()));
            counter++;
        }
        statement.executeUpdate();
    }


    @Override
    public void update(Movie movie) throws SQLException {

        StringBuilder sql = new StringBuilder("UPDATE movies SET title = ?, director = ?, actors = ?, genre = ?, year = ?, rating = ?, plot = ?, poster = ?, trailerURL = ?, runtime = ?, MPAA = ?, favorite = ?) " +
                "WHERE id = ?");
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));
        PreparedStatement statement = connection.prepareStatement(sql.toString());
        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getDirector());
        statement.setString(3, movie.getActors());
        statement.setString(4, movie.getGenre());
        statement.setString(5, Integer.toString(movie.getYear()));
        statement.setString(6, Double.toString(movie.getRating()));
        statement.setString(7, movie.getPlot());
        statement.setString(8, movie.getPoster());
        statement.setString(9, movie.getTrailerURL());
        statement.setString(10, movie.getRuntime());
        statement.setString(11, movie.getMPAA());
        statement.setString(12, Boolean.toString(movie.isFavorite()));
    }

    @Override
    public void delete(int id) throws SQLException {
        //TODO: Annihilate a movie
    }









}


