package newViewNedaei;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer {
    private static final SoundPlayer instance = new SoundPlayer();
    private static MediaPlayer mediaPlayer;

    private SoundPlayer() {

    }

    public static SoundPlayer getInstance() {
        return instance;
    }

    public static void playClickEffect() {
        Media media = new Media(new File("src/main/java/sounds/click.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
