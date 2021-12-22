package hw11;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PhoneBook {
    private static final DateTimeFormatter CHANGE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String DATA_FILE_PATH = "files/phone-book.json";

    private final Scanner scan = new Scanner(System.in);
    private final List<Contact> contacts = new ArrayList<>();
    private final ObjectMapper objectMapper;

    public PhoneBook() {
        objectMapper = new ObjectMapper();
        // pretty printing (json с отступами)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // для работы с полями типа LocalDate и LocalDateTime
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(module);
    }

    public void addContact() {
        System.out.println("Введите ФИО: ");
        String name = scan.nextLine();
        System.out.println("Введите дату рождения (пример 2007-12-03): ");
        String dateOfBirthStr = scan.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
        System.out.println("Добавление телефонных номеров");
        List<String> numbers = inputPhoneNumbers();
        System.out.println("Введите адрес: ");
        String address = scan.nextLine();
        int id = getNextId();
        Contact contact = new Contact(id, name, dateOfBirth, numbers, address);
        contacts.add(contact);
    }

    private List<String> inputPhoneNumbers() {
        List<String> numbers = new ArrayList<>();
        while (true) {
            System.out.println("Введите номер телефона (0 - для выхода): ");
            String number = scan.nextLine();
            if ("0".equals(number)) {
                return numbers;
            }
            numbers.add(number);
        }
    }

    private int getNextId() {
        if (contacts.isEmpty()) {
            return 1;
        }
        Contact maxIdContact = Collections.max(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return Integer.compare(c1.getId(), c2.getId());
            }
        });
        return maxIdContact.getId() + 1;
    }

    public void deleteContact() {
        System.out.println("Введите id контакта для удаления: ");
        int id = Integer.parseInt(scan.nextLine());
        Contact contact = findContactById(id);
        if (contact == null) {
            System.out.println("Контакт не найден");
            return;
        }
        contacts.remove(contact);
        System.out.println("Контакт удален");
    }

    private Contact findContactById(int id) {
        for (Contact contact : contacts) {
            if (id == contact.getId()) {
                return contact;
            }
        }
        return null;
    }

    private void printContacts(List<Contact> contacts) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("| id |       ФИО       | Дата рождения |    Телефоны    |        Адрес         |   Время изменения   |");
        System.out.println("------------------------------------------------------------------------------------------------------");
        for (Contact c : contacts) {
            System.out.printf(
                    "| %2d | %15s | %13s | %14s | %20s | %19s |%n",
                    c.getId(), c.getName(), c.getDateOfBirth().toString(),
                    c.getPhoneNumbers().isEmpty() ? "" : c.getPhoneNumbers().get(0),
                    c.getAddress(),
                    c.getChangeTime().format(CHANGE_TIME_FORMAT)
            );
            if (c.getPhoneNumbers().size() > 1) {
                for (int i = 1; i < c.getPhoneNumbers().size(); i++) {
                    System.out.printf(
                            "|    |                 |               | %14s |                      |                     |%n",
                            c.getPhoneNumbers().get(i)
                    );
                }
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
        }
    }

    public void findContactsByName() {
        System.out.println("Введите часть ФИО: ");
        String name = scan.nextLine();
        List<Contact> foundContacts = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getName().contains(name)) {
                foundContacts.add(c);
            }
        }
        printContacts(foundContacts);
    }

    public void findContactsByPhone() {
        System.out.println("Введите часть номера телефона: ");
        String phonePart = scan.nextLine();
        List<Contact> foundContacts = new ArrayList<>();
        for (Contact c : contacts) {
            for (String number : c.getPhoneNumbers()) {
                if (number.contains(phonePart)) {
                    foundContacts.add(c);
                    break;
                }
            }
        }
        printContacts(foundContacts);
    }

    public void sortByIdAndPrint() {
        contacts.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return Integer.compare(c1.getId(), c2.getId());
            }
        });
        printContacts(contacts);
    }

    public void sortByNameAndPrint() {
        contacts.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        printContacts(contacts);
    }

    public void sortByChangeDateAndPrint() {
        contacts.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getChangeTime().compareTo(c2.getChangeTime());
            }
        }.reversed());
        printContacts(contacts);
    }

    public void editContact() {
        System.out.println("Введите id контакта для редактирования: ");
        int id = Integer.parseInt(scan.nextLine());
        Contact contact = findContactById(id);
        if (contact == null) {
            System.out.println("Контакт не найден");
            return;
        }
        while (true) {
            printContacts(Collections.singletonList(contact));
            System.out.println("Редактировать");
            System.out.println("1. ФИО");
            System.out.println("2. Дату рождения");
            System.out.println("3. Телефоны");
            System.out.println("4. Адрес");
            System.out.println("5. Выход");
            String input = scan.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Введите ФИО: ");
                    String name = scan.nextLine();
                    contact.setName(name);
                    contact.setChangeTime(LocalDateTime.now());
                    break;
                case "2":
                    System.out.println("Введите дату рождения (пример 2007-12-03): ");
                    String date = scan.nextLine();
                    contact.setDateOfBirth(LocalDate.parse(date));
                    contact.setChangeTime(LocalDateTime.now());
                    break;
                case "3":
                    System.out.println("Редактирования телефонов");
                    editPhoneNumbers(contact);
                    break;
                case "4":
                    System.out.println("Введите адрес: ");
                    String address = scan.nextLine();
                    contact.setAddress(address);
                    contact.setChangeTime(LocalDateTime.now());
                    break;
                default:
                    return;
            }
        }


    }

    private void editPhoneNumbers(Contact contact) {
        List<String> phoneNumbers = contact.getPhoneNumbers();
        while (true) {
            System.out.println("Телефоны:");
            for (int i = 0; i < phoneNumbers.size(); i++) {
                System.out.println((i + 1) + ". " + phoneNumbers.get(i));
            }
            System.out.println("Выберите действие");
            System.out.println("1. Добавить телефоны");
            System.out.println("2. Удалить телефон");
            System.out.println("3. Выход");
            System.out.print("Ввод: ");
            String input = scan.nextLine();
            switch (input) {
                case "1":
                    List<String> newNumbers = inputPhoneNumbers();
                    phoneNumbers.addAll(newNumbers);
                    contact.setChangeTime(LocalDateTime.now());
                    break;
                case "2":
                    System.out.println("Введите номер по порядку телефона для удаления (-1 для выход): ");
                    int num = Integer.parseInt(scan.nextLine());
                    if (num == -1) {
                        break;
                    }
                    phoneNumbers.remove(num - 1);
                    contact.setChangeTime(LocalDateTime.now());
                    break;
                default:
                    return;
            }
        }
    }

    public void saveToFile() {
        try {
            String json = objectMapper.writeValueAsString(contacts);
            Files.write(Paths.get(DATA_FILE_PATH), json.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            System.out.println("Ошибка при сохранении файла: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void loadFromFile() {
        try {
            byte[] bytesData = Files.readAllBytes(Paths.get(DATA_FILE_PATH));
            List<Contact> contacts = objectMapper.readValue(bytesData, new TypeReference<List<Contact>>() {});
            this.contacts.clear();
            this.contacts.addAll(contacts);
        } catch (Exception ex) {
            System.out.println("Ошибка при загрузки из файла: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
