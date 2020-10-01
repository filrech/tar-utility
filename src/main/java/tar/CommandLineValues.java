package tar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CommandLineValues {
    private String out;

    private String u;

    private boolean errors;

    private String tar;

    private ArrayList<File> files = new ArrayList<>();

    private String path = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator;

    public CommandLineValues(String[] args) {
        if (args.length == 3 || args.length == 5) {
            if (!args[0].equals("tar")) {
                throw new IllegalArgumentException("Start with \"tar\"");
            }
            if (args[1].equals("-u")) {
                u = args[2];
            } else if (args[3].matches("-out") && args[1].endsWith(".txt") &&
                    args[2].endsWith(".txt") && args[4].endsWith(".txt")) {
                files.add(new File(args[1]));
                files.add(new File(args[2]));
                out = args[4];
            } else
                throw new IllegalArgumentException("Input: tar filename.txt filename.txt -out filename.txt OR tar -u filename.txt");
        }
    }

    public void launch(String[] args) throws IOException {
        if (errors) return;
        if (u == null) {
            TarArchiver.archiver(files, out, path);
        } else {
            TarUnpacking.decompress(u);
        }
    }
}
