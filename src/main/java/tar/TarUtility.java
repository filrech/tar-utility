package tar;

import org.kohsuke.args4j.CmdLineException;

import java.io.IOException;

public class TarUtility {
    public static void main(String[] args) throws CmdLineException, IOException {
        CommandLineValues parser = new CommandLineValues(args);
        if (parser.errors) return;
        if (parser.u == null) {
            TarArchiver.archiver(parser.files, parser.out, parser.path);
        } else {
            TarDecompressor.decompress(parser.u, parser.path);
        }
    }
}