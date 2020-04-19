package tar;

import org.kohsuke.args4j.*;

import java.io.File;
import java.util.ArrayList;

public class CommandLineValues {
    @Option(name = "-out", usage = "new archive", forbids = "-u")
    String out;

    @Option(name = "-u", usage = "unzip", forbids = "-out")
    String u;

    boolean errors;

    @Argument(metaVar = "tar", usage = "Input Files", required = true)
    public String tar;

    @Argument(metaVar = "inputFiles", usage = "Input Files", index = 1)
    public ArrayList<File> files;

    public String path = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator;

    public CommandLineValues(String... args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (!tar.equals("tar")) {
                throw new CmdLineException("Starts with \"tar\"");
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Wrong input format");
            System.err.println("Input: tar filename.txt filename.txt -out filename.txt OR tar -u filename.txt");
            parser.printUsage(System.err);
            errors = true;
        }
    }
}
