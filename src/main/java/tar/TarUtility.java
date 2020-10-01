package tar;

import java.io.IOException;

public class TarUtility {
    public static void main(String[] args) throws IOException {
        CommandLineValues parser = new CommandLineValues(args);
        parser.launch(args);
    }
}