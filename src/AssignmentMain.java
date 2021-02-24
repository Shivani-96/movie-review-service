import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AssignmentMain {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("This assignment is made by SHIVANI SAH - Shivani.Sah@iiitb.org - IIIT Bangalore - MTech CSE");
		System.out.println("--------------------------------------------------------------------------------------------");
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, User> users = new HashMap<>(); // Hashmap to store all the users created. Key is username and value is object created with that username.
		HashMap<String, Movie> movies = new HashMap<>();// Hashmap to store all the movies added. Key is movie name and value is object created with that movie name.
		
		int flag = 0;
		while(flag == 0) {
			System.out.println("Choose one of the following options:");
			System.out.println("1.Add User");
			System.out.println("2.Add Movie");
			System.out.println("3.Add Review");
			System.out.println("4.List Top n Movies Reviwed by Critic");
			System.out.println("5.Average Review Score in a particular Year of Release");
			System.out.println("6.Average Score of a Particular Movie");  
			System.out.println("7.Exit");
			int option = Integer.parseInt(br.readLine());
			
			
			switch(option) {
			case 1: // case to add user 
				User user = new User();
				user = user.addUser(); // calling the method in User class to add user
				if(users.containsKey(user.getUsername())) // check to see if username already exists
					System.out.println("User already exists!!!");
				else {
					users.put(user.getUsername(), user); // if username is unique then only user is added
					System.out.println("User added");
				}
				break;
			
			case 2: //case to add movie
				Movie movie = new Movie();
				movie = movie.addMovie();// calling the method in Movie class to add movie
				if(movies.containsKey(movie.getName()))// check to see if movie already exits
					System.out.println("Movie already exists!!!");
				else {
					movies.put(movie.getName(), movie);// if movie name is unique then only it is added
					System.out.println("Movie added");
				}
				break;
			
			case 3: // case to add review for movies
				System.out.println("Enter Username:");
				String userName = br.readLine().trim();
				if(users.containsKey(userName)) { // check to see if user is valid or not
					int i = 1;
					boolean moviesToReview = false;//flag to check if there are any movies to review
					System.out.println("List of movies to review");
					System.out.println("------------------------");
					//only those movies which are released on or before 2021 are to be reviewed.
					//for simplicity comparison is done using year of release which is taken as int
					for(String key:movies.keySet()) { // loop to display al movies eligible for review
						if(movies.get(key).getReleaseYear() <= 2021) {
							moviesToReview = true;
							System.out.println(i+" "+key);
							i++;
						}
					}
					if(!moviesToReview) System.out.println("No movies to review!!!");  
					else {
						System.out.println("Enter Movie to Review:");
						String movieToReview = br.readLine().trim();
						User reviewer = users.get(userName);
						if(reviewer.getMap().containsKey(movieToReview)) System.out.println("Movie already reviewed!!!"); // if a user has already reviewed a movie then movie review of that movie not allowed
						else {
							System.out.println("Enter score:(between 1 to 10)");
							int score = Integer.parseInt(br.readLine().trim());
							if(score > 10 || score < 1) System.out.println("Please enter valid score!!!");
							else {
								score = reviewer.addReview(movieToReview, score);// calling the method in User class to add review
								Movie movieReviewed = movies.get(movieToReview); 
								movieReviewed.update(reviewer.getUsername(), score); // calling the method in Movie class to update total review score and total reviews of that movie
								System.out.println("Movie Reviewed");
							}
						}
					}
				}
				else System.out.println("Enter valid username!!!");// if user is not valid then he is not allowed to review
				break;
			
			case 4: // case to get top N movies based on critic reviews
				Movie topNMovies = new Movie();
				topNMovies.getTopNMovies(users, movies); 
				break;
			
			case 5: // case to get average review score in a particular year of release
				Movie avgReviewScore = new Movie();
				avgReviewScore.avgReviewScore(movies); 
				break;
				
			case 6: // case to get average score of a particular movie
				System.out.println("Enter a Movie name:");
				String reqMovie = br.readLine().trim();
				// check to see if movie name is valid
				if(!movies.containsKey(reqMovie)) System.out.println("Enter a valid movie name!!!");
				else { // if valid get the average review score
					Movie m = movies.get(reqMovie);
					m.average(); // calling method to display average
				}
				break;
				
			case 7:// case to exit the program
				System.out.println("Are you sure you want to exit?");
				System.out.println("Type 1 for YES and 0 for NO");
				flag = Integer.parseInt(br.readLine());
				break;
				
			default: // default case to handle wrong input
				System.out.println("Wrong Input"); 
				break;
			}
			
			if(flag == 1) {
				System.out.println("Exited");
				break;
			}
		}
		 
	}

}
