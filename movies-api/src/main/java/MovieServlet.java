import com.google.gson.Gson;
import data.Movie;
import data.MoviesDao;
import data.MoviesDaoFactory;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import static data.MoviesDaoFactory.DAOType.IN_MEMORY;
import static data.MoviesDaoFactory.getMoviesDao;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies/*")
public class MovieServlet extends HttpServlet {

    ArrayList<Movie> movies = new ArrayList<>();
    MoviesDao moviesDao = getMoviesDao(IN_MEMORY);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            out.println(new Gson().toJson(
                    moviesDao.all()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        BufferedReader br = request.getReader();
        PrintWriter out = response.getWriter();
        Movie[] newMovies = new Gson().fromJson(br, Movie[].class);
        try {
            moviesDao.insertAll(newMovies);
        } catch (SQLException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        }
        out.println(new Gson().toJson("{message: \"Movies POST was successful\"}"));
        response.setStatus(200);
    }


    @Override
    protected void doPut (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        BufferedReader br = request.getReader();
        PrintWriter out = response.getWriter();
        try {
            Movie movie = new Gson().fromJson(request.getReader(), Movie.class);
            moviesDao.update(movie);
        } catch (SQLException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(400);
            e.printStackTrace();
            return;
        }

        out.println(new Gson().toJson("{message: \"Movie UPDATE was successful\"}"));
        response.setStatus(200);
    }


    @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            int id = new Gson().fromJson(request.getReader(), int.class);
            moviesDao.delete(id);
        } catch (SQLException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        }

        out.println(new Gson().toJson("{message: \"Movie DELETE was successful\"}"));
        response.setStatus(200);
    }


}