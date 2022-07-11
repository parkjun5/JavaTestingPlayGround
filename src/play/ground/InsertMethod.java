package play.ground;


import play.ground.objects.Lightning;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static play.ground.objects.Lightning.createLightning;

public class InsertMethod {

    public static void main(String[] args) {
        Set<String> fileList = new HashSet<>();
        Set<String> fileNames = listFilesUsingDirectoryStream("D:\\kma_2022\\낙뢰관련\\temp", fileList);

        StringBuilder results = new StringBuilder();

        for (String fileName : fileNames) {
            List<Lightning> lightnings =
                    readAndCreateLightnings("D:\\kma_2022\\낙뢰관련\\202204\\" + fileName);
            for (Lightning lightning : lightnings) {
                results.append(lightning.toInsertQuery());
            }
        }
        writeSqlAsFile(results.toString());
    }

    public static Set<String> listFilesUsingDirectoryStream(String dir, Set<String> fileList) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    listFilesUsingDirectoryStream(path.toString(), fileList);
                } else if (Files.isDirectory(path)) {
                    fileList.add(path.getFileName()
                            .toString());
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fileList;
    }

    private static List<Lightning> readAndCreateLightnings(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return readAndConvert(br);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static List<Lightning> readAndConvert(BufferedReader br) throws IOException {
        String line;
        List<Lightning> lightnings = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(" ");
            Lightning lightning = createLightning(values);
            lightnings.add(lightning);
        }
        return lightnings;
    }

    private static void writeSqlAsFile(String sql) {
        String resultSql = "D:\\data\\lightningSaver.sql";
        File file = new File(resultSql);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(sql);
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
