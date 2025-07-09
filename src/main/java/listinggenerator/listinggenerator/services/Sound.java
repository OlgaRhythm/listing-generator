package listinggenerator.listinggenerator.services;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Sound {
    private Clip clip;
    private final boolean loop;

    public Sound(String fileName, boolean loop) {
        this.loop = loop;
        loadSound(fileName);
    }

    public void loadSound(String fileName){
        try {
            InputStream inputStream = Sound.class.getResourceAsStream("/music/" + fileName);

            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + inputStream);
            }

            // Чтение данных из InputStream в байтовый массив
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            // Преобразование байтового массива в ByteArrayInputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());


            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public void playSound() {
        if (loop) {
            clip.loop(-1);
        } else {
            clip.start();
        }
    }

    public void stopSound() {
        clip.stop();
        clip.setFramePosition(0);
    }
}
