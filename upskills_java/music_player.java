import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MusicPlayer {
    private List<String> playlist;
    private int currentSongIndex;
    private boolean isPlaying;

    public MusicPlayer() {
        playlist = new ArrayList<>();
        currentSongIndex = -1;
        isPlaying = false;
    }

    public void addSong(String song) {
        playlist.add(song);
    }

    public void play() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty. Add some songs.");
            return;
        }

        if (isPlaying) {
            System.out.println("Music is already playing.");
            return;
        }

        if (currentSongIndex == -1) {
            currentSongIndex = 0;
            System.out.println("Playing: " + playlist.get(currentSongIndex));
        } else {
            System.out.println("Resuming playback: " + playlist.get(currentSongIndex));
        }

        isPlaying = true;
    }

    public void pause() {
        if (currentSongIndex == -1) {
            System.out.println("No song is currently playing.");
            return;
        }

        if (!isPlaying) {
            System.out.println("Music is already paused.");
            return;
        }

        System.out.println("Pausing: " + playlist.get(currentSongIndex));
        isPlaying = false;
    }

    public void next() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty. Add some songs.");
            return;
        }

        if (currentSongIndex == -1) {
            currentSongIndex = 0;
        } else if (currentSongIndex == playlist.size() - 1) {
            currentSongIndex = 0;
        } else {
            currentSongIndex++;
        }

        System.out.println("Playing next song: " + playlist.get(currentSongIndex));
        isPlaying = true;
    }
}

public class music_player {
    public static void main(String[] args) {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.addSong("Song 1");
        musicPlayer.addSong("Song 2");
        musicPlayer.addSong("Song 3");
        musicPlayer.addSong("Song 4");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Music Player Menu ---");
            System.out.println("1. Play");
            System.out.println("2. Pause");
            System.out.println("3. Next");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    musicPlayer.play();
                    break;
                case 2:
                    musicPlayer.pause();
                    break;
                case 3:
                    musicPlayer.next();
                    break;
                case 4:
                    System.out.println("Exiting Music Player...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
