package tar;

import java.io.IOException;

public class TarUtility {
    public static void main(String[] args) throws IOException {
        CommandLineValues parser = new CommandLineValues(args);
        if (parser.errors) return;
        if (parser.u == null) {
            TarArchiver.archiver(parser.files, parser.out, parser.path);
        } else {
            TarUnpacking.decompress(parser.u);
        }
    }
}