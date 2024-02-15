import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieCollection {
    ArrayList<Movie> movieCollection;
    Scanner scanner;

    public MovieCollection() {
        scanner = new Scanner(System.in);
        movieCollection = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File("src\\movies_data.csv"));
            while (input.hasNext()) {
                String[] movie = input.next().split(",");
                movieCollection.add(new Movie(movie[0], movie[1], movie[2], movie[3], movie[4], movie[5]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mainMenu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void searchTitles() {
        System.out.print("Enter a title search term: ");
        String search = scanner.nextLine();

        ArrayList<Movie> matches = new ArrayList<>();

        for (Movie movie : movieCollection) {
            if (movie.getTitle().contains(search)) {
                matches.add(movie);
            }
        }

        for (int i = 0; i < matches.size(); i++) {
            System.out.println(i + 1 + ": " + matches.get(i).getTitle());
        }
        System.out.println("Which movie would you like to learn more about? ");
        System.out.print("Enter a number: ");
        matches.get(scanner.nextInt()).printInfo();
    }

    public Movie searchCast() {
        System.out.print("Enter a person to search for(first or last name): ");
        String search = scanner.nextLine();

        ArrayList<Movie> movieMatches = new ArrayList<>();
        ArrayList<ArrayList<String>> castMatches = new ArrayList<>();

        for (int i = 0; i < movieCollection.size(); i++) {
            if (Arrays.stream(movieCollection.get(i).getCast()).anyMatch(a -> a.contains(search))) {
                movieMatches.add(movieCollection.get(i));
                castMatches.add(new ArrayList<>());
                for (int j = 0; j < movieCollection.get(i).getCast().length; j++) {
                    if (movieCollection.get(i).getCast()[j].equals(search)) {
                        castMatches.get(i).add(movieCollection.get(j).getCast()[j]);
                    }
                }

            }

//        for (int i = 0; i < matches.size(); i++) {
//            System.out.println(i + 1 + ": " + matches.get(i).getCast().);
//        }
//        System.out.println("Which movie would you like to learn more about? ");
//        System.out.print("Enter a number: ");
//        matches.get(scanner.nextInt()).printInfo();
        }
        return null;
    }
}