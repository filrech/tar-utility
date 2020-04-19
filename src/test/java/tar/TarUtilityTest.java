package tar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

class TarUtilityTest {
    private static String archiveTest2;
    private static String archiveTest1;
    private static String archiveOutput;
    private static String archiveExpected;
    private static String unpackerAchive;
    private static String unpackerResult1;
    private static String unpackerResult2;
    private static String unpackerResult1Expected;
    private static String unpackerResult2Expected;

    @BeforeAll
    static void start() {
        archiveTest1 = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator + "archiverTest1.txt";
        archiveTest2 = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator + "archiverTest2.txt";
        archiveOutput = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator + "archiveResult.txt";
        archiveExpected = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator + "archiveExpected.txt";
        unpackerAchive = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator + "unpackerArchive.txt";
        unpackerResult1 = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator + "unpackerResult1.txt";
        unpackerResult2 = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator + "unpackerResult2.txt";
        unpackerResult1Expected = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator + "unpackerResult1Expected.txt";
        unpackerResult2Expected = "src" + File.separator + "test" + File.separator + "java" + File.separator + "tar" + File.separator + "resources" + File.separator + "unpackerResult2Expected.txt";
    }
    @Test
    void archiver() throws IOException {
        TarUtility.main(new String[]{"tar", archiveTest1, archiveTest2, "-out", "archiveResult.txt"});
        fileCheck(new File(archiveExpected), new File(archiveOutput));
    }

    @Test
    void decompress() throws IOException {
        TarUtility.main(new String[]{"tar", "-u", unpackerAchive});
        fileCheck(new File(unpackerResult1Expected), new File(unpackerResult1));
        fileCheck(new File(unpackerResult2Expected), new File(unpackerResult2));
    }

    void fileCheck(File inputFile, File outputFile) throws FileNotFoundException {
        Scanner file = new Scanner(inputFile);
        Scanner expected = new Scanner(outputFile);
        while (file.hasNext() || expected.hasNext()) {
            Assertions.assertEquals(file.nextLine(), expected.nextLine());
        }
        file.close();
        expected.close();
    }
}