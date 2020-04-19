package tar;

import com.sun.tools.javac.util.Convert;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class TarUnpacking {
    static void decompress(String input) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            Map<String, Integer> info = new TreeMap<>();
            String line = br.readLine();
            if (!line.equals("This file was archived:")) {
                System.err.println("This file is not archive");
                throw new IOException();
            }
            line = br.readLine();
            while (!line.equals("************")) {
                String[] lineParse = line.split("="); //TODO лучший разделитель. (:
                String filename = lineParse[0];
                Integer stingCount = Convert.string2int(lineParse[1], 10);
                info.put(filename, stingCount);
                line = br.readLine();
            }
            line = br.readLine();
            System.out.println(line);
            for (String filename: info.keySet()) {
                try (PrintWriter writer = new PrintWriter(new File(filename))) {
                    int count = 0;
                    while (count < info.get(filename)) {
                        String lineToWrite = br.readLine();
                        writer.println(lineToWrite);
                        count++;
                    }
                    writer.flush();
                }
            }
        }
    }
}
