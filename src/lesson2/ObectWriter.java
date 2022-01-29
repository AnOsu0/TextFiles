package lesson2;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObectWriter {
    public static void main(String[] args) {
        String fileName = "person.obj";
        Person p1 = new Person("Jan", "Kowalski");

        try(
                var fs = new FileOutputStream(fileName);
                var os = new ObjectOutputStream(fs);
        ) {
            os.writeObject(p1);
            System.out.println("Zapisano obiekt do pliku");

        } catch (IOException e) {
            System.err.println("Bład zapisu pliku " + fileName);
            e.printStackTrace();
        }
    }
}
