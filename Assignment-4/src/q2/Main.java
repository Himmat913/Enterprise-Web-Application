// Name: Himantveer Kaur
// Roll No: 1024170271
package q2;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static Song readSong() {
        System.out.print("Song title: ");
        String title = sc.nextLine().trim();
        System.out.print("Artist: ");
        String artist = sc.nextLine().trim();
        return new Song(title, artist);
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Media Player Queue ---");
            System.out.println("1. Add song at the end");
            System.out.println("2. Add song at the front (priority)");
            System.out.println("3. Add song at a position");
            System.out.println("4. Remove duplicate songs");
            System.out.println("5. Show playlist");
            System.out.println("6. Play next song");
            System.out.println("7. Play previous song");
            System.out.println("8. Show recent history");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                choice = -1;
                continue;
            }

            if (choice == 1) {
                playlist.addSong(readSong());
            } else if (choice == 2) {
                playlist.addToFront(readSong());
            } else if (choice == 3) {
                System.out.print("Position: ");
                int position;
                try {
                    position = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Position must be a number.");
                    continue;
                }
                playlist.addAtPosition(position, readSong());
            } else if (choice == 4) {
                playlist.removeDuplicates();
            } else if (choice == 5) {
                playlist.display();
            } else if (choice == 6) {
                playlist.playNext();
            } else if (choice == 7) {
                playlist.playPrevious();
            } else if (choice == 8) {
                playlist.showHistory();
            } else if (choice == 0) {
                System.out.println("Exiting player.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
