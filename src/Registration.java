import java.io.*;
import java.util.Scanner;

public class Registration {

    private static final String USERS_FILE = "users.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите действие: 1 - регистрация, 2 - авторизация");
        int choice = scanner.nextInt();
        if (choice == 1) {
            register();
        } else if (choice == 2) {
            login();
        } else {
            System.out.println("Некорректный выбор");
        }
    }


        private static void register () throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите логин:");
            String username = scanner.nextLine();

            File usersFile = new File(USERS_FILE);
            if (usersFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts[0].equals(username)) {
                        System.out.println("Пользователь с таким именем уже существует");
                        return;
                    }
                }
                reader.close();
            }

            System.out.println("Введите пароль:");
            String password = scanner.nextLine();

            BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true));
            writer.write(username + ":" + password + "\n");
            writer.close();

            System.out.println("Пользователь успешно зарегистрирован");
        }

        private static void login () throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите логин:");
            String username = scanner.nextLine();

            System.out.println("Введите пароль:");
            String password = scanner.nextLine();

            BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(username)) {
                    if (parts[1].equals(password)) {
                        System.out.println("Авторизация успешна");
                        return;
                    } else {
                        System.out.println("Неверный пароль");
                        return;
                    }
                }
            }
            reader.close();
            System.out.println("Пользователь с таким именем не найден");
        }
    }

