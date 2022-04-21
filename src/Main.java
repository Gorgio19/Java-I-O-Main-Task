import java.io.*;
import java.util.Scanner;

public class Main {
    static int amountOfDirectories = 0;
    static int amountOfFiles = 0;
    static int sumOfFilesNameLength = 0;

    public static void main(String[] args) {
        File myFile = new File("data/output.txt");
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the directory");
        String dir = scan.nextLine();
        File directory = new File(dir);
        if (directory.isDirectory()){
      makeTree(myFile,directory, 1);
    } else if (directory.isFile()){
            try {getInfo(directory);} catch (IOException e) {e.printStackTrace();}
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
                writer.write(preStr + file.getName() + "\n");
                System.out.println(preStr + file.getName());
                if (file.isDirectory()) {
                    makeTree(myFile, file, level + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getInfo(File directory) throws IOException {
        FileReader fr;
        BufferedReader br = null;
        try{
            fr = new FileReader(directory);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                if (line.contains(".txt")){
                    amountOfFiles++;
                    sumOfFilesNameLength+=getNameLength(line);
                } else amountOfDirectories++;
                line = br.readLine();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally {
            assert br != null;
            br.close();
        }
        System.out.println("Amount of directories = " + amountOfDirectories);
        System.out.println("Amount of files = " + amountOfFiles);
        System.out.println("Average amount of files in the directory = " + amountOfFiles/amountOfDirectories);
        System.out.println("Average length of files' name = " +sumOfFilesNameLength/amountOfFiles);
    }

    public static int getNameLength(String line){
        int dashCount = 0;
        char dash = '-';
        for (int i = 0; i < line.length(); i++) {
            if((line.charAt(i)) == dash) {
                dashCount++;
            }
        }
        return line.length() - 3 - dashCount;
    }
}
