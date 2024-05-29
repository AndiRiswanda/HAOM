package haom;

import javafx.scene.image.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ImageLoader {

    public static Image loadProfileImage(String imagePath) {
        File file = new File(imagePath);
        if (file.exists() && file.canRead()) {
            try {
                return new Image(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        // Load a default image if the user-specific image is not found
        InputStream defaultImageStream = ImageLoader.class.getResourceAsStream("/PicAsset/userProfile.png");
        if (defaultImageStream != null) {
            return new Image(defaultImageStream);
        }
        return null; // or some default placeholder image
    }

    public static void saveProfileImage(File file, String destinationPath) {
        // Ensure the destination directory exists and is writable
        File destFile = new File(destinationPath);
        File destDir = destFile.getParentFile();
        if (destDir.exists() || destDir.mkdirs()) {
            try (FileInputStream fis = new FileInputStream(file);
                 FileOutputStream fos = new FileOutputStream(destFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to create directory: " + destDir.getAbsolutePath());
        }

    }


    public static String getUserImagePathFromDatabase(String username) {
        // Replace with actual database call to get user-specific image path
        // For example: "C:/Path/To/PicAsset/" + username + "_profile.png"
        return "C:/Path/To/PicAsset/" + username + "_profile.png";
    }
}
