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
                String[] movie = input.nextLine().split(",");
                movieCollection.add(new Movie(movie[0], movie[1], movie[2], movie[3], movie[4], movie[5]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        mainMenu();
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

            switch (menuOption) {
                case "t" -> searchTitles();
                case "c" -> searchCast();
                case "q" -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public void searchTitles() {
        System.out.print("Enter a title search term: ");
        String search = scanner.nextLine();

        ArrayList<Movie> matches = new ArrayList<>();

        for (Movie movie : movieCollection) {
            if (movie.getTitle().toLowerCase().contains(search.toLowerCase())) {
                matches.add(movie);
            }
        }
        if(matches.isEmpty()) {
            System.out.println("No movie titles match that search term!");
        }
        else {
            matches.sort((a, b) -> a.getTitle().equals(b.getTitle()) ? 0 : a.getTitle().compareTo(b.getTitle()) > 0 ? 1 : -1);

            for (int i = 0; i < matches.size(); i++) {
                System.out.println(i + 1 + ": " + matches.get(i).getTitle());
            }

            System.out.println("Which movie would you like to learn more about? ");
            System.out.print("Enter a number: ");
            matches.get(scanner.nextInt() - 1).printInfo();

            System.out.println("** Press Enter to Return to Main Menu **");
            scanner.nextLine();
        }
    }

    public void searchCast() {
        System.out.print("Enter a person to search for (first or last name): ");
        String search = scanner.nextLine();

        ArrayList<String> matches = new ArrayList<>();

        for (Movie movie : movieCollection) {
            for (int i = 0; i < movie.getCast().length; i++) {
                if (movie.getCast()[i].toLowerCase().contains(search.toLowerCase()) && !matches.contains(movie.getCast()[i])) {
                    matches.add(movie.getCast()[i]);
                }
            }
        }

        if(matches.isEmpty()) {
            System.out.println("No results match your search");
        }
        else {
            matches.sort((a, b) -> a.equals(b) ? 0 : a.compareTo(b) > 0 ? 1 : -1);
            for (int i = 0; i < matches.size(); i++) {
                System.out.println(i + 1 + ": " + matches.get(i));
            }

            System.out.println("Which would you like to see all movies for? ");
            System.out.print("Enter number: ");
            String actor = matches.get(scanner.nextInt() - 1);
            ArrayList<Movie> movieMatches = new ArrayList<>();

            for (Movie movie : movieCollection) {
                if (Arrays.asList(movie.getCast()).contains(actor)) {
                    movieMatches.add(movie);
                }
            }

            if(movieMatches.isEmpty()) {
                System.out.println("No movie titles match that search term!");
            }

            else {
                movieMatches.sort((a, b) -> a.getTitle().equals(b.getTitle()) ? 0 : a.getTitle().compareTo(b.getTitle()) > 0 ? 1 : -1);

                for (int i = 0; i < movieMatches.size(); i++) {
                    System.out.println(i + 1 + ": " + movieMatches.get(i).getTitle());
                }

                System.out.println("Which movie would you like to learn more about?");
                System.out.print("Enter number: ");
                movieMatches.get(scanner.nextInt() - 1).printInfo();

                System.out.println("** Press Enter to Return to Main Menu **");
                scanner.nextLine();
            }
        }
    }
}