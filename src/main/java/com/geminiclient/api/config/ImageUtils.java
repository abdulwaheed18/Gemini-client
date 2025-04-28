package com.geminiclient.api.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ImageUtils {

    private ImageUtils() {
        // Private constructor to prevent instantiation
    }

    public static void saveBase64Image(String base64Data, String baseFileName) throws IOException {
        if (base64Data == null || base64Data.isEmpty()) {
            throw new IllegalArgumentException("Base64 data is empty or null");
        }
        byte[] imageBytes = Base64.getDecoder().decode(base64Data);
        File outputDir = new File("images");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String finalFileName = baseFileName.replace(".png", "_") + timestamp + ".png";
        File outputFile = Paths.get(outputDir.getPath(), finalFileName).toFile();
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(imageBytes);
        }
    }
}
