import java.util.Scanner;

public class Main {

    static Movie[] available = new Movie[6];
    static String[] snacks = new String[]{"Popcorn", "Ice Cream Bar", "M&M", "Nachos"};
    static String[] drinks = new String[]{"Fanta", "Water", "Sprite", "Coke", "Diet Coke"};
    static String[] cart = new String[30]; 
    static String currentItem = "";
    static boolean add = false;
    static String purple = "\033[35m";
    static String erase = "\033[0m";
    //tracks number of items in cart
    static int cartIndex = 0;
       public static void main(String[] args){

        //movies avaialble 
        available [0] = new Movie("Deadpool and Wolverine", "Action/Comedy", "R");
        available [1] = new Movie("Despicable Me 4", "Family/Comedy", "PG");
        available [2] = new Movie("Transformers One", "Action/Sci-fi", "PG");
        available [3] = new Movie("Twister", "Action/Adventure", "PG-13");
        available [4] = new Movie("Dune 2", "Sci-fi", "PG-13");
        available [5] = new Movie("Inside Out 2", "Family/Comedy", "PG");

        Scanner in = new Scanner(System.in);//Creates scanner object.
        System.out.println(purple + "Welcome to Cinema 201. I'm Tie. I can assist you in selecting a movie, snacks, and drinks. Type 'bye' if you wish to quit." + erase);

        //chatbot loop 
        while(true){
        String userResp = in.nextLine().toLowerCase(); 
        if(userResp.equalsIgnoreCase("bye") || userResp.equalsIgnoreCase("bai")){
                System.out.println(purple + "Thank you for visiting Cinema 201" + erase);
                break;
                }
        String reply = getResponse(userResp);
        System.out.println(purple + reply + erase); 

        String userResp2 = in.nextLine().toLowerCase();
        if(userResp2.equalsIgnoreCase("bye") || userResp2.equalsIgnoreCase("bai")){
                System.out.println(purple + "Thank you for visiting Cinema 201" + erase);
                break;
        }
        String reply2 = getResponse2(userResp2);
        System.out.println(purple + reply2 + erase);
        }
       }

        public static String getResponse(String statement){
                String response = "";

                //list of all movies
                String movielist = "";
                for(int i = 0; i < available.length; i++){
                        if(i == available.length - 1){
                        movielist+= available[i].getName() + ". ";
                        }
                        else{
                        movielist+= available[i].getName() + ", ";
                        }
                }

                //list of all drinks
                String drinklist = "";
                        for(int i = 0; i < drinks.length; i++){
                                if(i == drinks.length - 1){
                                        drinklist += drinks[i] + ".";
                                }
                                else{
                                        drinklist += drinks[i] + ", ";
                                }
                        }

                //list of all snacks
                String snacklist = ""; 
                        for(int i = 0; i < snacks.length; i++){
                               if(i == snacks.length - 1){
                                       snacklist += snacks[i] + ".";
                               }
                               else{
                                       snacklist += snacks[i] + ", ";
                               }
                        }

                //if user greets chatbot
                if(statement.contains("hi") || statement.contains("Hi") || statement.contains("Hello") || statement.contains("Hey") || statement.contains("hey")){
                        response = "Hi! Want to look at some movies? Ask me to pull up the catalog.";
                }

                //if user says no 
                else if (statement.contains("no") || statement.contains("No") || statement.contains("NO")){
                        response = "Alright then, would you like to look at some other movies? Snacks? Drinks? View cart? Check out?";
                }
                //if user wants to add an item 
                else if((statement.contains("add") || statement.contains("yes") || statement.contains("Yes") )&& !currentItem.equals("")){
                        addItem(currentItem);
                }

                //if user wants to add item, but item isn't specified
                else if(statement.contains("add") || statement.contains("yes") || statement.contains("Yes") ){
                        response = "Which item would you like to add?";
                        add = true;
                }

                //if user wants to hear about all the movies or wants to pull up the catalog
                else if (statement.contains("ovie") || statement.contains("catalog")){
                        response = "We currently show " + movielist + "Would you like to add one to cart? Or would you like to hear more about them?";
                }

                //if user wants to hear more about a specific movie or wants to continue browsing
                else if (statement.contains("hear") || statement.contains("browsing") || statement.contains("keep shopping")){
                        response = "Which movie would you like to hear about? Or would you like to hear about the snacks or drinks?";
                }

                //if user wants to add a specific movie 
                else if((statement.contains("pool") ||statement.contains("rine") || statement.contains("picab") || statement.contains("cipab") || statement.contains("rans") || statement.contains("wis") || statement.contains("une") || statement.contains("side")) && add == true){
                        addItem(getItem(statement));
                        add = false;
                }

                //if user asks about a specific movie 
                else if((statement.contains("pool") ||statement.contains("rine") || statement.contains("picab") || statement.contains("cipab") ||statement.contains("rans") || statement.contains("wis") || statement.contains("une") || statement.contains("side"))){
                        response = movieInfo(getItem(statement)) + ". Should I add to cart or would you like to continue browsing?";
                        currentItem = getItem(statement);

                }

                //if user asks about snacks 
                else if (statement.contains("nack") || statement.contains("nakc")){
                         response = "Right now, you can purchase these snacks. Note, each snack costs $3: " + snacklist;
                         response += " Would you like to add one of these to cart?";
                         currentItem = "";
                }

                //if user asks about a specific snack 
                else if(statement.contains("corn") ||statement.contains("ream") || statement.contains("M&M") || statement.contains("m&m")|| statement.contains("achos")){
                        response = "Would you like to purchase " + getItem(statement) + " for $2?";
                        currentItem = getItem(statement);
                }

                //if user asks about drinks
                else if (statement.contains("drink") || statement.contains("dirnk") || statement.contains("drnik")){
                        response = "You can purchase the following drinks. Note, each drink costs $2: " + drinklist;
                        response += " Would you like to add one of these to cart?";
                        currentItem = "";
                }

                //if user asks about specific drinks 
                else if(statement.contains("ater") ||statement.contains("anta") || statement.contains("prite") || statement.contains("oke") || statement.contains("iet")){
                        response = "Would you like to purchase ";
                        response += getItem(statement) + " for $2";
                        currentItem = getItem(statement);
                }   

                //if user wants to view cart
                else if(statement.contains("cart") || statement.contains("view")){
                        int movies = 0;
                        int snacks = 0; 
                        int drinks = 0;

                   
                        for(int i = 0; i < cartIndex; i++){
                                if((cart[i].contains("pool") || cart[i].contains("rine") || cart[i].contains("picab") || cart[i].contains("rans") || cart[i].contains("wis") || cart[i].contains("une") || cart[i].contains("side"))){
                                        movies++;
                                }
                                else if(cart[i].contains("corn") ||cart[i].contains("ream") || cart[i].contains("M&M") || cart[i].contains("m&m")|| cart[i].contains("achos")){
                                        snacks++;
                                }
                                else if(cart[i].contains("ater") ||cart[i].contains("anta") || cart[i].contains("prite") || cart[i].contains("oke") || cart[i].contains("iet")){
                                        drinks++;
                                }
                        }
                        
                        response = "The contents of your cart are: ";
                        for (int i = 0; i < cartIndex; i++) {
                                if (cartIndex == 0){
                                        response = "Your cart is empty";
                                }
                                else if (i == cartIndex - 1){
                                        response += cart[i] + ".";
                                }
                                else{
                                        response += cart[i] + ", ";
                                }
                        }
                        int total = (movies * 15) + (snacks * 3) + (drinks * 2);
                        response += " Your total is $" + total + ". Would you like to check out or keep shopping?";
                }
                //if user wants to check out 
                else if(statement.contains("check out") || statement.contains("checkout")){
                        int movies = 0;
                        int snacks = 0; 
                        int drinks = 0;

                   
                        for(int i = 0; i < cartIndex; i++){
                                if((cart[i].contains("pool") || cart[i].contains("rine") || cart[i].contains("picab") || cart[i].contains("rans") || cart[i].contains("wis") || cart[i].contains("une") || cart[i].contains("side"))){
                                        movies++;
                                }
                                else if(cart[i].contains("corn") ||cart[i].contains("ream") || cart[i].contains("M&M") || cart[i].contains("m&m")|| cart[i].contains("achos")){
                                        snacks++;
                                }
                                else if(cart[i].contains("ater") ||cart[i].contains("anta") || cart[i].contains("prite") || cart[i].contains("oke") || cart[i].contains("iet")){
                                        drinks++;
                                }
                        }
                        
                        response = "The contents of your cart are: ";
                        for (int i = 0; i < cartIndex; i++) {
                                if (cartIndex == 0){
                                        response = "Your cart is empty";
                                }
                                else if (i == cartIndex - 1){
                                        response += cart[i] + ".";
                                }
                                else{
                                        response += cart[i] + ", ";
                                }
                        }
                        int total = (movies * 15) + (snacks * 3) + (drinks * 2);
                        response += " Your total is $" + total + ". Thank you for shopping at Cinema 201. Enjoy your movie!";
                }
                else{
                        response = getRandomResponse();
                }
                return response;
        }

        public static String getResponse2(String statement2){
                String response = "";

                //list of all movies
                String movielist = "";
                for(int i = 0; i < available.length; i++){
                        if(i == available.length - 1){
                        movielist+= available[i].getName() + ". ";
                        }
                        else{
                        movielist+= available[i].getName() + ", ";
                        }
                }

                //list of all drinks
                String drinklist = "";
                        for(int i = 0; i < drinks.length; i++){
                                if(i == drinks.length - 1){
                                        drinklist += drinks[i] + ".";
                                }
                                else{
                                        drinklist += drinks[i] + ", ";
                                }
                        }

                //list of all snacks
                String snacklist = ""; 
                        for(int i = 0; i < snacks.length; i++){
                               if(i == snacks.length - 1){
                                       snacklist += snacks[i] + ".";
                               }
                               else{
                                       snacklist += snacks[i] + ", ";
                               }
                        }
                //if user greets chatbot
                if(statement2.contains("hi") || statement2.contains("Hi") || statement2.contains("Hello") || statement2.contains("Hey") || statement2.contains("hey")){
                        response = "Hi! Want to look at some movies? Ask me to pull up the catalog.";
                }
                //if user says no 
                else if (statement2.contains("no") || statement2.contains("No") || statement2.contains("NO")){
                        response = "Alright then, would you like to look at some other movies? Snacks? Drinks? View cart? Check out?";
                }
                //if user wants to add an item 
                else if((statement2.contains("add") || statement2.contains("yes") || statement2.contains("Yes") )&& !currentItem.equals("")){
                        addItem(currentItem);
                        add = true;
                }
                //if user wants to add item, but item isn't specified
                else if(statement2.contains("add") || statement2.contains("yes") || statement2.contains("Yes") ){
                        response = "Which item would you like to add?";
                }
                //if user wants to hear about all the movies available 
                else if (statement2.contains("ovie") || statement2.contains("catalog")){
                        response = "We currently show " + movielist + "Would you like to add one to cart? Or would you like to hear more about them?";
                }
                //if user wants to add a specific movie 
                else if((statement2.contains("pool") ||statement2.contains("rine") || statement2.contains("picab") || statement2.contains("cipab") || statement2.contains("rans") || statement2.contains("wis") || statement2.contains("une") || statement2.contains("side")) && add == true){
                        addItem(getItem(statement2));
                        add = false;
                }
                //if user wants to hear more about a specific movie 
                else if (statement2.contains("hear") || statement2.contains("browsing") || statement2.contains("keep shopping")){
                        response = "Would you like to hear about more movies? Or would you like to hear about the snacks or drinks?";
                }
                //if user asks about a specific movie 
                else if((statement2.contains("pool") ||statement2.contains("rine") || statement2.contains("picab") || statement2.contains("cipab") || statement2.contains("rans") || statement2.contains("wis") || statement2.contains("une") || statement2.contains("side"))){
                        response = movieInfo(getItem(statement2)) + ". Should I add to cart or would you like to continue browsing?";
                        currentItem = getItem(statement2);
                }
                //if user asks about snacks 
                else if (statement2.contains("nack") || statement2.contains("nakc")){
                         response = "Right now, you can purchase these snacks. Note, each snack costs $3: " + snacklist;
                         response += " Would you like to add one of these to cart?";
                         currentItem = "";
                }
                //if user asks about a specific snack 
                else if(statement2.contains("corn") ||statement2.contains("ream") || statement2.contains("M&M") || statement2.contains("m&m")|| statement2.contains("achos")){
                        response = "Would you like to purchase " + getItem(statement2) + " for $2?";
                        currentItem = getItem(statement2);
                }
                //if user asks about drinks
                else if (statement2.contains("drink") || statement2.contains("dirnk") || statement2.contains("drnik")){
                        response = "You can purchase the following drinks. Note, each drink costs $2: " + drinklist;
                        response += " Would you like to add one of these to cart?";
                        currentItem = "";
                }
                //if user asks about specific drinks 
                else if(statement2.contains("ater") ||statement2.contains("anta") || statement2.contains("prite") || statement2.contains("oke") || statement2.contains("iet")){
                        response = "Would you like to purchase ";
                        response += getItem(statement2) + " for $2";
                        currentItem = getItem(statement2);
        
                }
                //if user wants to view cart
                else if(statement2.contains("cart") || statement2.contains("view")){
                        int movies = 0;
                        int snacks = 0; 
                        int drinks = 0;

                   
                        for(int i = 0; i < cartIndex; i++){
                                if((cart[i].contains("pool") || cart[i].contains("rine") || cart[i].contains("picab") || cart[i].contains("rans") || cart[i].contains("wis") || cart[i].contains("une") || cart[i].contains("side"))){
                                        movies++;
                                }
                                else if(cart[i].contains("corn") ||cart[i].contains("ream") || cart[i].contains("M&M") || cart[i].contains("m&m")|| cart[i].contains("achos")){
                                        snacks++;
                                }
                                else if(cart[i].contains("ater") ||cart[i].contains("anta") || cart[i].contains("prite") || cart[i].contains("oke") || cart[i].contains("iet")){
                                        drinks++;
                                }
                        }
                        
                        response = "The contents of your cart are: ";
                        for (int i = 0; i < cartIndex; i++) {
                                if (cartIndex == 0){
                                        response = "Your cart is empty";
                                }
                                else if (i == cartIndex - 1){
                                        response += cart[i] + ".";
                                }
                                else{
                                        response += cart[i] + ", ";
                                }
                        }
                        int total = (movies * 15) + (snacks * 3) + (drinks * 2);
                        response += " Your total is $" + total + ". Would you like to check out or keep shopping?";
                }
                //if user wants to check out 
                else if(statement2.contains("check out") || statement2.contains("checkout")){
                        int movies = 0;
                        int snacks = 0; 
                        int drinks = 0;

                   
                        for(int i = 0; i < cartIndex; i++){
                                if((cart[i].contains("pool") || cart[i].contains("rine") || cart[i].contains("picab") || cart[i].contains("rans") || cart[i].contains("wis") || cart[i].contains("une") || cart[i].contains("side"))){
                                        movies++;
                                }
                                else if(cart[i].contains("corn") ||cart[i].contains("ream") || cart[i].contains("M&M") || cart[i].contains("m&m")|| cart[i].contains("achos")){
                                        snacks++;
                                }
                                else if(cart[i].contains("ater") ||cart[i].contains("anta") || cart[i].contains("prite") || cart[i].contains("oke") || cart[i].contains("iet")){
                                        drinks++;
                                }
                        }
                        
                        response = "The contents of your cart are: ";
                        for (int i = 0; i < cartIndex; i++) {
                                if (cartIndex == 0){
                                        response = "Your cart is empty";
                                }
                                else if (i == cartIndex - 1){
                                        response += cart[i] + ".";
                                }
                                else{
                                        response += cart[i] + ", ";
                                }
                        }
                        int total = (movies * 15) + (snacks * 3) + (drinks * 2);
                        response += " Your total is $" + total + ". Thank you for shopping at Cinema 201. Enjoy your experience!";
                }
                else{
                        response = getRandomResponse();
                }
                return response;
        }

        public static String getRandomResponse(){
                String [] randomReplies = new String[4];
                String a = "";
    
                for(int i = 0; i < available.length; i++){
                    if(i == available.length - 1){
                            a+= available[i].getName() + ". ";
                    }
                    else{
                            a += available[i].getName() + ", ";
                    }
                 }
    
                randomReplies[0] = "I'm not the right person to ask about that topic. Could you clarify? I can also guide you towards some movie options. Would you like to watch one of these: " + a;
                randomReplies[1] = "That sounds great, but I'm afraid I don't know what you're talking about. Perhaps, I'm not hearing you right? Fortunately, I am well versed in our theatres offerings. Would you like to check out some movies?";
                randomReplies[2] = "I wish I could help, but I'm unfamiliar with that. Are you interested in watching any of these movies: " + a + "Or would you like some snacks or drinks?";
                randomReplies[3] = "I'm sorry, I haven't heard of that. Do you want to check these movies out?: " + a;
    
                //change some of these to our snacks and drinks, there doesn't seem to be multiple random movie response methods in the rubric? 
    
                int index = (int)(Math.random()*randomReplies.length);
    
                return randomReplies[index];
        }

        public static void addItem(String item){
                String [] followups = new String[3];

                followups[0] = "Excellent choice! Would you like to view your cart? Or grab any snacks or drinks?";
                followups[1] = "A good drink can elevate your viewing experience. Would you like to see the drink options?";
                followups[2] = "Great! More Snacks? Drinks? Or do you want to go to cart?";

                int index = (int)(Math.random()*followups.length);

                if (cartIndex < cart.length){
                        System.out.println(purple + item + " has been added to cart. " + followups[index] + erase);
                        cart[cartIndex] = item; // set string to the index
                        cartIndex++;
                }
        }

        public static String getItem(String x){
                //movies!!!
                if(x.contains("pool") || x.contains("wolve")){
                        return available[0].getName();
                }
                else if(x.contains("picab") || x.contains("cipab")){
                        return available[1].getName();
                }
                else if(x.contains("rans")){
                        return available[2].getName();
                }
                else if(x.contains("wis")){
                        return available[3].getName();
                }
                else if(x.contains("une")){
                        return available[4].getName();
                }
                else if(x.contains("side")){
                        return available[5].getName();
                }

                //snacks!!
                else if(x.contains("corn")){
                        return snacks[0];
                }
                else if(x.contains("ream")){
                        return snacks[1];
                }
                else if(x.contains("m&m") || x.contains("M&M")){
                        return snacks[2];
                }
                else if(x.contains("acho")){
                        return snacks[3];
                }

                //drinks!!!
                else if(x.contains("anta")){
                        return drinks[0];
                }
                else if(x.contains("ater")){
                        return drinks[1];
                }
                else if(x.contains("prite")){
                        return drinks[2];
                }
                else if(x.contains("oke")){
                        return drinks[3];
                }
                else if(x.contains("iet")){
                        return drinks[4];
                }

                //unidentifiable items :(
                else{
                        return "N/A";
                }

        }

        public static String movieInfo(String movie){
                String info = "";

                //movie descriptions
                if(movie.equals(available[0].getName())){
                        info += available[0].getName() + " is a " + available[0].getGenre() + " movie rated " + available[0].getRating() + ". It stars Deadpool teaming up with Wolverine to save the multiverse";
                }
                if(movie.equals(available[1].getName())){
                        info += available[1].getName() + " is a " + available[1].getGenre() + " movie rated " + available[1].getRating() + ". It is a continuation of the Despicable Me series where Gru and his family are forced to go on the run due to the escape of a criminal who wants revenge on Gru";
                }
                if(movie.equals(available[2].getName())){
                        info += available[2].getName() + " is a " + available[2].getGenre() + " movie rated " + available[2].getRating() + ". This prequel features the origin story of transformers.";
                }
                if(movie.equals(available[3].getName())){
                        info += available[3].getName() + " is a " + available[3].getGenre() + " movie rated " + available[3].getRating() + ". It follows the story of a storm chaser and her crew";
                }
                if(movie.equals(available[4].getName())){
                        info += available[4].getName() + " is a " + available[4].getGenre() + " movie rated " + available[4].getRating() + "It picks back up from Dune, featuring Paul Atreides' quest for revenge";
                }
                if(movie.equals(available[5].getName())){
                        info += available[5].getName() + " is a " + available[5].getGenre() + " movie rated " + available[5].getRating() + ". In this sequel, Riley deals with new emotions as she becomes a teenager ";
                }
                
                return info;
        }
}