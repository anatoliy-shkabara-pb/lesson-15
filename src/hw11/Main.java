package hw11;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();

        System.out.println("Телефонная книга");

        while (true) {
            System.out.println("1. Вывод контактов");
            System.out.println("2. Добавление контакта");
            System.out.println("3. Удаление контакта");
            System.out.println("4. Поиск контактов");
            System.out.println("5. Редактирование контакта");
            System.out.println("6. Сохранить данные в файл");
            System.out.println("7. Загрузить данные из файла");
            System.out.println("8. Выход");
            System.out.print("Выбор: ");

            String input = scan.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Сортировка");
                    System.out.println("1. По id");
                    System.out.println("2. По ФИО");
                    System.out.println("3. По дате изменения");
                    System.out.print("Выбор: ");
                    input = scan.nextLine();
                    switch (input) {
                        case "1":
                            phoneBook.sortByIdAndPrint();
                            break;
                        case "2":
                            phoneBook.sortByNameAndPrint();
                            break;
                        case "3":
                            phoneBook.sortByChangeDateAndPrint();
                    }
                    break;
                case "2":
                    phoneBook.addContact();
                    break;
                case "3":
                    phoneBook.deleteContact();
                    break;
                case "4":
                    System.out.println("1. Поиск по ФИО");
                    System.out.println("2. Поиск по телефону");
                    System.out.print("Выбор: ");
                    input = scan.nextLine();
                    switch (input) {
                        case "1":
                            phoneBook.findContactsByName();
                            break;
                        case "2":
                            phoneBook.findContactsByPhone();
                    }
                    break;
                case "5":
                    phoneBook.editContact();
                    break;
                case "6":
                    phoneBook.saveToFile();
                    break;
                case "7":
                    phoneBook.loadFromFile();
                    break;
                default:
                    return;
            }
        }


    }
}
