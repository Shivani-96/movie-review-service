import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Movie {
	
	String name;
	String genre;
	int releaseYear;
	HashMap<String,Integer> userScores = new HashMap<>(); // hashmap to store user scores given to a movie. key is user name and value is score given.
	int totalMovieScore;
	int totalReviews;
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public Movie() {
		
	}
	public Movie(String name, String genre, int releaseYear) {
		super();
		this.name = name;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.totalMovieScore = 0;
		this.totalReviews = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public HashMap<String, Integer> getMap() {
		return userScores;
	}
	public void setMap(HashMap<String, Integer> map) {
		this.userScores = map;
	}
	
	//method to update total score and total reviews
	public void update(String username,int score) {
		userScores.put(username, score);
		totalMovieScore = totalMovieScore + score;
		totalReviews++;
	}
	
	// method to print average review score
	public void average() {
		float avg = (float)totalMovieScore / totalReviews;
		System.out.println("Average is: "+avg); 
	}
	
	//method to add movie
	public Movie addMovie() throws IOException {
		System.out.println("Enter Movie Name:");
		String movieName = br.readLine();
		System.out.println("Enter Release Year:");
		int year = Integer.parseInt(br.readLine());
		System.out.println("Enter Genre:");
		String genre = br.readLine();
		Movie movie = new Movie(movieName, genre, year);
		return movie;
	}
	
	// method to print top N movies based on critic reviews
	public void getTopNMovies(HashMap<String, User> users, HashMap<String, Movie> movies) throws IOException {
		System.out.println("Enter Genre:");
		String reqGenre = br.readLine().trim();
		System.out.println("Enter n:");
		int n = Integer.parseInt(br.readLine().trim());
		ArrayList<Movie> list = new ArrayList<>();
		for(String key:movies.keySet()) {// loop to check which movies of given genre are reviewed by critics
			if(movies.get(key).getGenre().equalsIgnoreCase(reqGenre)) {
				HashMap<String,Integer> map = movies.get(key).getMap();
				for(String uKey:map.keySet()) {
					if(users.get(uKey).getReviews() > 3) {
						list.add(movies.get(key)); // movies reviewed by critics are added in a list
					}
				}
			}
		}
		// if a list is empty then no movies of given genre are reviewed by critics
		if(list.size() == 0) System.out.println("No movie of required genre!!!");
		else { // else sort the list in descending order based on total review score
			Collections.sort(
		            list, new Comparator<Movie>() {
		                @Override
		                public int compare(Movie m1, Movie m2)
		                {
		                    // Changing the order of the elements
		                    return m2.totalMovieScore - m1.totalMovieScore;
		                }
		            }); 
			if(n > list.size()) { // if N is greater than movies in list then a valid number should be entered
				System.out.println("Enter a valid number!!!");
			}
			else { // else display the list
				System.out.println("Top "+n+" movies");
				System.out.println("-----------------");
				for(int i=0;i<n;i++) {
					System.out.println(list.get(i).getName());
				}
			}
		}
	}
	
	// method to print average movie score in a particular year of release
	public void avgReviewScore(HashMap<String, Movie> movies) throws NumberFormatException, IOException {
		System.out.println("Enter a year:");
		int reqYear = Integer.parseInt(br.readLine().trim());
		// year should not be greater than 2021 as movies after 2021 are not eligible for being reviewed
		if(reqYear > 2021) System.out.println("Enter a year less than or equal to 2021");
		else {
			float count = 0, sum = 0;
			for(String key:movies.keySet()) { // loop to get movies belonging to given year
				if(movies.get(key).getReleaseYear() == reqYear) {
					sum = sum + movies.get(key).totalMovieScore;
					count++;
				}
			}
			// if count is 0 then no movies belonging to given year were reviewed
			if(count == 0) System.out.println("No reviews!!!");
			else { 
				System.out.println("Average Movie Review Score of "+reqYear+" "+(sum/count)); // print average
			} 
		}
	}
}
