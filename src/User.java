import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class User {

	String username;
	int reviews; 
	int multiplier;
	HashMap<String,Integer> moviesReviewed;// hashmap to store movies reviewed by a user. key is movie name and value is score
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public User() {
		
	}
	public User(String username) {
		super();
		this.username = username;
		//this.mode = mode;
//		if(mode == 1) this.multiplier = 1;
//		else this.multiplier = 2;
		this.multiplier = 1;
		this.reviews = 0;
		moviesReviewed = new HashMap<>();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getReviews() {
		return reviews;
	}
	public void setReviews(int reviews) {
		this.reviews = reviews;
	}
	public int getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	public HashMap<String, Integer> getMap() {
		return moviesReviewed;
	}
	public void setMap(HashMap<String, Integer> map) {
		this.moviesReviewed = map;
	}
	
	//method to add review
	public int addReview(String movieName,int score) {
		reviews++;
		if(reviews > 3) multiplier = 2;
		int newScore = score * multiplier;
		moviesReviewed.put(movieName, newScore);
		if(reviews > 3)System.out.println("user now critic");
		return newScore;
	}
	
	// method to add user
	public  User addUser() throws IOException {
		System.out.println("Enter Username:");
		String username = br.readLine();
		User user = new User(username);
		return user;
	}
}
