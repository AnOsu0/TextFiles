package example1;

import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Company implements Serializable {
    Employee[] employees = new Employee[3];
    private final int EXIT = 0;
    private final int ADD = 1;
    private final int PRINT = 2;
    private final int FROM_FILE = 1;
    private final int FROM_USER = 2;
    Scanner sc = new Scanner(System.in);
    String fileName = "Employees.obj";

    private void addEmployee() {
        System.out.println("Wybierz czy chcesz wczytać dane z pliku czy podać je ręcznie?");
        System.out.println("1 - wczytanie danych z pliku");
        System.out.println("2 - podanie danych ręcznie");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case FROM_FILE:
                fromFile();
                break;
            case FROM_USER:
               fromUser();
               break;
        }
    }

    private Employee readData() {
        System.out.println("Podaj Imię");
        String firstName = sc.nextLine();
        System.out.println("Podaj Nazwisko");
        String lastName = sc.nextLine();
        System.out.println("Podaj wypłatę");
        double salary = sc.nextDouble();
        sc.nextLine();
        return new Employee(firstName, lastName, salary);
    }

    private void fromFile() {
        try (
                var fis = new FileInputStream(fileName);
                var ois = new ObjectInputStream(fis);
        ) {
            for (int i = 0; i < employees.length; i++) {
                employees[i] = (Employee) ois.readObject();
            }

        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Nie udało się odczytać pliku");
            e.printStackTrace();
        }
    }

    private void fromUser(){
        try (
                var fs = new FileOutputStream(fileName);
                var os = new ObjectOutputStream(fs);
        ) {
            for (int i = 0; i < employees.length; i++) {
                employees[i] = readData();
                os.writeObject(employees[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printInfo() {
        System.out.println("Zatrudnieni pracownicy to: ");
        for (Employee temp : employees) {
            System.out.println(temp.getFirstName() + " " + temp.getLastName() + ", pensja " +
                    temp.getSalary() + " zł");
        }
    }

    public void actions() {
        int option = -1;
        addEmployee();
        do {
            System.out.println("Wybierz opcje:");
            System.out.println("0 - wyjscie z programu");
            System.out.println("1 - dodanie praocwników");
            System.out.println("2 - wyświetlenie pracowników");
            option = sc.nextInt();
            switch (option){
                case ADD -> addEmployee();
                case PRINT -> printInfo();
            }

        } while (option != EXIT);
    }
}
