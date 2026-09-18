import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {

    private static final String url = "jdbc:mysql://localhost:3306/banking_system";
    private static final String username = "root";
    private static final String password = "admin@123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in);

            User user = new User(connection, scanner);
            Accounts accounts = new Accounts(connection, scanner);
            AccountManager accountManager = new AccountManager(connection, scanner);

            while (true) {
                System.out.println("\n*** WELCOME TO BANKING SYSTEM ***");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter Your Choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        user.register();
                        break;

                    case 2:
                        String email = user.login();
                        if (email != null) {
                            System.out.println("\nUser Logged In!");

                            if (!accounts.account_exist(email)) {
                                // User does not have an account yet
                                System.out.println("\nYou do not have a bank account.");
                                System.out.println("1. Create a New Account");
                                System.out.println("2. Back to Main Menu");
                                System.out.print("Enter choice: ");
                                int accChoice = scanner.nextInt();

                                if (accChoice == 1) {
                                    long account_number = accounts.openAccount(email);
                                    System.out.println("Account Created Successfully! Your Account Number is: " + account_number);
                                }
                                break;
                            }

                            long account_number = accounts.getAccountNumber(email);
                            int choice2 = 0;
                            while (choice2 != 5) {
                                System.out.println("\n--- Banking Menu ---");
                                System.out.println("1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Transfer Money");
                                System.out.println("4. Check Balance");
                                System.out.println("5. Log Out");
                                System.out.print("Enter Your Choice: ");
                                choice2 = scanner.nextInt();

                                switch (choice2) {
                                    case 1:
                                        accountManager.debitMoney(account_number);
                                        break;
                                    case 2:
                                        accountManager.creditMoney(account_number);
                                        break;
                                    case 3:
                                        accountManager.TransferMoney(account_number);
                                        break;
                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;
                                    case 5:
                                        System.out.println("Logging out...");
                                        break;
                                    default:
                                        System.out.println("Enter Valid Choice!");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("Incorrect Email or Password!");
                        }
                        break;

                    case 3:
                        System.out.println("Thank You For Using Banking System!!!!");
                        System.out.println("Exiting System.....");
                        return;

                    default:
                        System.out.println("Enter Valid Choice!");
                        break;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
