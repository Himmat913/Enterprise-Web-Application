package q2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Playlist {
    private LinkedList<Song> songs = new LinkedList<>();
    private LinkedList<Song> history = new LinkedList<>();
    private ListIterator<Song> iterator = songs.listIterator();
    private char lastMove = ' ';

    private void resetIterator() {
        iterator = songs.listIterator();
        lastMove = ' ';
    }

    public void addSong(Song song) {
        songs.addLast(song);
        resetIterator();
        System.out.println("Added at the end: " + song);
    }

    public void addToFront(Song song) {
        songs.addFirst(song);
        resetIterator();
        System.out.println("Added at the front: " + song);
    }

    public void addAtPosition(int position, Song song) {
        if (position < 1 || position > songs.size() + 1) {
            System.out.println("Invalid position. Choose between 1 and " + (songs.size() + 1));
            return;
        }
        songs.add(position - 1, song);
        resetIterator();
        System.out.println("Added at position " + position + ": " + song);
    }

    public void removeDuplicates() {
        HashSet<Song> seen = new HashSet<>();
        Iterator<Song> it = songs.iterator();
        int removed = 0;
        while (it.hasNext()) {
            Song s = it.next();
            if (!seen.add(s)) {
                it.remove();
                removed++;
            }
        }
        resetIterator();
        System.out.println("Duplicates removed: " + removed);
    }

    public void display() {
        if (songs.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }
        int number = 1;
        for (Song s : songs) {
            System.out.println(number + ". " + s);
            number++;
        }
    }

    private void play(Song song) {
        System.out.println("Now playing: " + song);
        history.addFirst(song);
        if (history.size() > 5) {
            history.removeLast();
        }
    }

    public void playNext() {
        if (songs.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }
        if (lastMove == 'P') {
            iterator.next();
        }
        if (iterator.hasNext()) {
            Song s = iterator.next();
            lastMove = 'N';
            play(s);
        } else {
            System.out.println("End of playlist.");
            if (lastMove == 'P') {
                iterator.previous();
            }
        }
    }

    public void playPrevious() {
        if (songs.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }
        if (lastMove == 'N') {
            iterator.previous();
        }
        if (iterator.hasPrevious()) {
            Song s = iterator.previous();
            lastMove = 'P';
            play(s);
        } else {
            System.out.println("Start of playlist.");
            if (lastMove == 'N') {
                iterator.next();
            }
        }
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No songs played yet.");
            return;
        }
        System.out.println("Recently played (latest first):");
        for (Song s : history) {
            System.out.println("- " + s);
        }
    }
}
