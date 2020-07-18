package client.newViewNedaei;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class SoundPlayer {
    private static final SoundPlayer instance = new SoundPlayer();
    private static MediaPlayer mediaPlayer;

    private SoundPlayer() {
//        Media media = new Media(new File("src/main/java/client.sounds/1.mp3").toURI().toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setOnEndOfMedia(() -> {
//            mediaPlayer.seek(Duration.ZERO);
//        });
//        mediaPlayer.play();
    }

    public static SoundPlayer getInstance() {
        return instance;
    }

    public static void playClickEffect() {
//        (new Thread(() -> {
//            MediaPlayer temp;
//            Media media = new Media(new File("src/main/java/client.sounds/click.mp3").toURI().toString());
//            temp = new MediaPlayer(media);
//            temp.play();
//        })).start();
    }

    public static void playBackground(int n) {
//        Media media = new Media(new File("src/main/java/client.sounds/" + n + ".mp3").toURI().toString());
//        mediaPlayer.stop();
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setOnEndOfMedia(() -> {
//            mediaPlayer.seek(Duration.ZERO);
//        });
//        mediaPlayer.play();
    }
}
