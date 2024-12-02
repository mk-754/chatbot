public class Movie {
    //A new class must be made. You should change this to match what your class should be named.
   
    private String name;
    private String genre; 
    private String ageRating;

    public Movie(String n, String g, String a){ 
        name = n;
        genre = g;
        ageRating = a;
    }

    //Create methods that get and set for each attribute 
    public String getName(){
        return name;
    }
    
    public void setName(String x){
        name = x;
    }

    public String getGenre(){
        return genre;
    }
    
    public void setGenre(String y){
        genre = y;
    }

    public String getRating(){
        return ageRating;
    }
    
    public void setRating(String z){
        name = z;
    }

    public String toString(){
        return this.name + " ";
    }

    }

  