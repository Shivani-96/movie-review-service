# movie-review-service

This project contains backend code of movie review service.

Following functionalities have been implemented :
1. Adding users
    Only users with unique usernames are allowed. If usernames are repeated a message is shown on screen that username already exists. A user is by default a viewer. If he posts 
    more than 3 reviews then he is promoted to critic. After that his score has double weightage.
2. Adding movies
    Only movies with unique names are allowed. If names are repeated a message is shown on screen that movie already exists.
3. Add reviews
    A user can add review for a movie. He is not allowed to review a movie that has not released yet. In this application, upcoming movies are checked by year of release for simplicity.
    Also, a user cannot review the same movie more than once.
4. List top n movies by total review score by ‘critics’ in a particular genre
    Top n movies of a particular genre can be viewed. If there are no movies of that genre then a message is shown stating that no movie of that genre exists.
5. Average review score in a particular year of release
    Average review score in a particular year of release can be viewed after entering the year.
6. Average review score for a particular movie
    Average review score of a movie can be seen after entering the movie name.
    
    
 Steps to execute the code :
 1. Clone the project using: git clone -b master https://github.com/Shivani-96/movie-review-service.git
 2. Run AssignmentMain.java
