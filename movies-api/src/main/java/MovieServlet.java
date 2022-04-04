import com.google.gson.Gson;
import data.Movie;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies/*")
public class MovieServlet extends HttpServlet {

    int nextId = 1;
    ArrayList<Movie> movies = new ArrayList<>();
    Movie princessBride = new Movie("The Princess Bride", "Rob Reiner", "Cary Elwes, Mandy Patinkin, Robin Wright", "Adventure, Family, Fantasy", 1987, 8.1, "An elderly man reads the book \"The Princess Bride\" to his sick and thus currently bedridden adolescent grandson, the reading of the book which has been passed down within the family for generations. The grandson is sure he won't like the story, with a romance at its core, he prefers something with lots of action and \"no kissing\", but he lets grandfather continue, because he doesn't want to hurt his feelings. The story centers on Buttercup, a former farm girl who has been chosen as the princess bride to Prince Humperdinck of Florian. Buttercup does not love him, she who still laments the death of her one true love, Westley, five years ago. Westley was a hired hand on the farm, his stock answer of \"as you wish\" to any request she made of him which she came to understand was his way of saying that he loved her. But Westley went away to sea, only to be killed by the Dread Pirate Roberts. On a horse ride to clear her mind of her upcoming predicament of marriage, Buttercup is kidnapped by a band of bandits: Vizzini who works on his wits, and his two associates, a giant named Fezzik who works on his brawn, and a Spaniard named Inigo Montoya, who has trained himself his entire life to be an expert swordsman. They in turn are chased by the Dread Pirate Roberts himself. But chasing them all is the Prince, and his men led by Count Tyrone Rugen. What happens to these collectives is dependent partly on Buttercup, who does not want to marry the Prince, and may see other options as lesser evils, and partly on the other motives of individuals within the groups. But a larger question is what the grandson will think of the story as it proceeds and at its end, especially as he sees justice as high a priority as action.", "https://imdb-api.com/images/original/MV5BMGM4M2Q5N2MtNThkZS00NTc1LTk1NTItNWEyZjJjNDRmNDk5XkEyXkFqcGdeQXVyMjA0MDQ0Mjc@._V1_Ratio0.6762_AL_.jpg", "https://www.youtube.com/watch?v=tD3WKbQRvQw", "1h 38min", "PG", true, 309);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String movieString = new Gson().toJson(movies.toArray());
        try {
            PrintWriter out = response.getWriter();
            out.println(movieString);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        BufferedReader br = request.getReader();
        Movie[] newMovies = new Gson().fromJson(br, Movie[].class);
        for (Movie movie : newMovies) {
            movie.setId(nextId++);
            movies.add(movie);
        }
        try {
            PrintWriter out = response.getWriter();
            out.println("Movie(s) added");

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPut (HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            String [] uriParts = request.getRequestURI().split("/");
            int targetId = Integer.parseInt(uriParts[uriParts.length - 1]);
            for(Movie movie : movies) {
                if (targetId == movie.getId()){
                    movies.set(movies.indexOf(movie), movie);
                }
            }

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();


        } catch (IOException ex){
            ex.printStackTrace();
        }
    }



}