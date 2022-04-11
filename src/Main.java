import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File myFile = new File("data/output.txt");
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the directory");
        String dir = scan.nextLine();
        File directory = new File(dir);
        if (directory.isDirectory()){
      makeTree(myFile,directory, 1);
    } else if (directory.isFile()){

        } else System.out.println("check your input");
    }

    public static void makeTree(File myFile, File directory, int level) {
        StringBuilder preStr = new StringBuilder();

        preStr.append("--".repeat(Math.max(0, level)));
        try(FileWriter writer = new FileWriter(myFile,true))
        {
            File[] underFile = directory.listFiles();
            assert underFile != null;
            for (File file : underFile) {
                System.out.println(file.getName());
                writer.write(preStr + file.getName() + "\n");
                if (file.isDirectory()) {
                    makeTree(myFile, file, level + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
