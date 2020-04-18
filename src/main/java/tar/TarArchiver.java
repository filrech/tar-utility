package tar;

import java.io.*;
import java.util.*;

public class TarArchiver {
    static void archiver(List<File> input, String output, String path) throws IOException {
        File tmp = new File (path + "tmp");
        try (PrintWriter writer = new PrintWriter(tmp)) {
            Map<String, Integer> info = new HashMap<>();
            for (File files : input) {
                BufferedReader br = new BufferedReader(new FileReader(files));
                String line = br.readLine();
                int count = 0;
                while (line != null) {
                    writer.println(line);
                    line = br.readLine();
                    count++;
                }
                writer.flush();
                info.put(files.toString(), count);
            }
            info(tmp, output, path, info);
            writer.close();
            tmp.delete(); // Почему не удаляется? (Я тупой)
        }
    }
    static void info(File input, String output, String path, Map<String,Integer> info) throws IOException {
        try (PrintWriter writer = new PrintWriter(new File(path + output))) {
            writer.println("This file was archived:");
            for (Map.Entry<String, Integer> pair: info.entrySet()) {
                writer.println(pair);
            }
            writer.println("************\n");
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line = br.readLine();
            while (line != null) {
                writer.println(line);
                line = br.readLine();
            }
        }
    }
}
