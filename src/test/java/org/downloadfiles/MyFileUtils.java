package org.downloadfiles;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.lang.reflect.Field;
import java.time.Duration;

public class MyFileUtils {

    public static void createDownloadDirectory() {
        createDirectory(DirectoryFor.DOWNLOAD);
    }

    public static void createDirectory(DirectoryFor directoryFor) {
        File directory = new File(directoryFor.getDirName());
        if (directory.exists()) {
            try {
                FileUtils.cleanDirectory(directory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            directory.mkdir();
        }
    }

    public enum DirectoryFor {
        DOWNLOAD("download");

        private String dirName;

        DirectoryFor(String dirName) {
            this.dirName = dirName;
        }

        public String getDirName() {
            return dirName;
        }
    }

    public static String readFileInString(String dataFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dataFile));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    public static File waitTillFileDownloaded(File file) {
        WebDriverWait wait = new WebDriverWait(DownloadFiles.driver, Duration.ofSeconds(20));
        wait.until((ExpectedCondition<Boolean>) p -> file.exists());
        wait.until((ExpectedCondition<Boolean>) p -> {
            long sizeBefore = file.length();
            sleep(1000);
            long sizeAfter = file.length();
            return sizeAfter == sizeBefore;
        });
        return file;
    }

    private static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}