package core;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class FileUtils {

    public static List<String> readLines(String path) {
        FileInputStream stream = null;
        BufferedReader reader = null;
        try {
            stream = new FileInputStream(path);
            reader = new BufferedReader(new InputStreamReader(stream));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(reader);
            FileUtils.close(stream);
        }
        return new ArrayList<>();
    }

    public static void write(String path, String data, boolean append) {
        File file = new File(path);

        FileWriter writer = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(path, append);
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(writer);
        }
    }

    public static void write(String path, String[] data, boolean append) {
        StringBuilder builder = new StringBuilder();
        for (String line : data) {
            builder.append(line).append("\r\n");
        }
        FileUtils.write(path, builder.toString(), append);
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
