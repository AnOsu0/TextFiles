package lesson1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileCreator {
    public static void main(String[] args) throws FileNotFoundException {
//        String fileName = "C:\\Users\\LNV\\javastart\\Pliki tekstowe\\ testFile.txt";
        // tworzenie pliku w danje lokalizacji

        String fileName = "C:\\Users\\LNV\\Desktop\\Ania.txt";
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        int lines = 0;
        while (sc.hasNextLine()){
            System.out.println(sc.nextLine());
            lines++;
        }
        System.out.println("liczba wierszy w pliku: " + lines);
    }
}



