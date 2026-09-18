 import java.sql.*;
import java.util.Scanner;

public class Accounts {

    private Connection connection;
    private Scanner scanner;
    public Accounts(Connection connection, Scanner scanner) {
           this.scanner=scanner;
           this.connection=connection;
    }


    public long getAccountNumber(String email)  {
        String query = "SELECT account_number from Accounts WHERE email = ?";
       try{
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,email);
           ResultSet resultSet = preparedStatement.executeQuery();
           if (resultSet.next()){
               return resultSet.getLong("account_number");
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
       throw new RuntimeException("Account Number Does Not Exist !");
    }




    public long generateAccountNumber(){
        try{
            String query = "SELECT account_number from Accounts ORDER BY account_number DESC LIMIT 1";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                long last_account_number = resultSet.getLong("account_number");
                return last_account_number+1;
            }
            else {
                return 10000100;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 10000100;
    }



    public boolean account_exist(String email){
        String query = "SELECT account_number from accounts WHERE email = ?";
    try{
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()){
              return true;
        }
        else {
            return false;
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return false;
    }

    public long openAccount(String email){
             if (!account_exist(email)){
                 String AccOpenQuery = "INSERT INTO accounts(account_number, full_name, email,balance,security_pin) VALUES (?,?,?,?,?)";
                 scanner.nextLine();
                 System.out.println("Enter Full Name : ");
                 String full_name = scanner.nextLine();
                 System.out.println("Enter Initial Amount: ");
                 double balance = scanner.nextDouble();
                 scanner.nextLine();
                 System.out.println("Enter Security Pin: ");
                 String securityPin = scanner.nextLine();
           try {
               long account_number = generateAccountNumber();
               PreparedStatement preparedStatement = connection.prepareStatement(AccOpenQuery);
               preparedStatement.setLong(1, account_number);
               preparedStatement.setString(2,full_name);
               preparedStatement.setString(3,email);
               preparedStatement.setDouble(4,balance);
               preparedStatement.setString(5,securityPin);

               int rowsAffected = preparedStatement.executeUpdate();
               if (rowsAffected>0){
                   return account_number;
               }
               else {
                   throw new RuntimeException("Account Creation Failed");
               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
             }
             throw new RuntimeException("Account Already Exist");
    }
}


