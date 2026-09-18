import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {

    private Connection connection;
    private Scanner scanner;
    public AccountManager(Connection connection, Scanner scanner) {
            this.connection=connection;
            this.scanner=scanner;
    }

    public void debitMoney(long account_number) throws SQLException {
        scanner.nextLine();
        System.out.println("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Security Pin: ");
        String security_pin =scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (account_number!=0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Accounts WHERE account_number = ? AND security_pin = ? ");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();


                if (resultSet.next()){
                    double current_balance = resultSet.getDouble("balance");
                    if (amount<=current_balance){
                        String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(debit_query);
                        preparedStatement1.setDouble(1,amount);
                        preparedStatement1.setLong(2,account_number);
                        int rowsAffected = preparedStatement1.executeUpdate();
                        if (rowsAffected>0){
                            System.out.println("Rs."+amount+" debited Successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        }
                        else {
                            System.out.println("Transaction Failed!");
                            connection.rollback();
                            connection.setAutoCommit(true);
                             }
                    }
                    else{
                        System.out.println("Insufficient Balance!");
                    }
                }
                else {
                    System.out.println("Invalid pin");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connection.setAutoCommit(true);
    }



    public void creditMoney(long account_number) throws SQLException {
        scanner.nextLine();
        System.out.println("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Security Pin: ");
        String security_pin =scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (account_number!=0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Accounts WHERE account_number = ? AND security_pin = ? ");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();


                if (resultSet.next()){
                        String debit_query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(debit_query);
                        preparedStatement1.setDouble(1,amount);
                        preparedStatement1.setLong(2,account_number);
                        int rowsAffected = preparedStatement1.executeUpdate();
                        if (rowsAffected>0){
                            System.out.println("Rs."+amount+" Credited Successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        }
                        else {
                            System.out.println("Transaction Failed!");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }
                    else{
                        System.out.println("Insufficient Balance!");
                    }
                }
                else {
                    System.out.println("Invalid pin");
                }
            }
         catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connection.setAutoCommit(true);
    }


    public void getBalance(long account_number){
        scanner.nextLine();
        System.out.println("Enter Security Pin: ");
        String security_pin = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT balance FROM Accounts WHERE account_number = ? AND security_pin = ?");
            preparedStatement.setLong(1,account_number);
            preparedStatement.setString(2,security_pin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                double balance = resultSet.getDouble("balance");
                System.out.println("Balance: "+balance);
            }
            else {
                System.out.println("Invalid Pin!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void TransferMoney(long sender_account_number) throws SQLException{
        scanner.nextLine();
        System.out.println("Enter Receiver Account Number: ");
        long receiver_account_number = scanner.nextLong();
        System.out.println("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Security Pin: ");
        String Security_pin = scanner.nextLine();
        try{
            connection.setAutoCommit(false);
            if (sender_account_number!=0 && receiver_account_number!=0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number =? AND security_pin = ?");
                preparedStatement.setLong(1,sender_account_number);
                preparedStatement.setString(2,Security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    double current_balance = resultSet.getDouble("balance");
                    if (amount<=current_balance){
                        String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
                        String credit_query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
                        PreparedStatement DebitpreparedStatement = connection.prepareStatement(debit_query);
                        PreparedStatement CreditpreparedStatement = connection.prepareStatement(credit_query);
                        DebitpreparedStatement.setDouble(1,amount);
                        DebitpreparedStatement.setLong(2,sender_account_number);
                        CreditpreparedStatement.setDouble(1,amount);
                        CreditpreparedStatement.setLong(2,receiver_account_number);
                        int rowsAffected1 = DebitpreparedStatement.executeUpdate();
                        int rowsAffected2 = CreditpreparedStatement.executeUpdate();
                        if (rowsAffected1>0 && rowsAffected2>0){
                            System.out.println("Transaction Successful!");
                            connection.commit();
                            connection.setAutoCommit(true);
                        }
                        else {
                            System.out.println("Insufficient Balance!");
                        }

                    }
                    else {
                        System.out.println("Invalid Security Pin!");
                    }
                }
                else {
                    System.out.println("Invalid Account Number!");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    connection.setAutoCommit(true);
    }

}


