---

# 💳 Banking Management System (JDBC + MySQL)

A **Java-based console application** to simulate core banking operations such as account creation, deposits, withdrawals, transfers, and user authentication.
This project uses **JDBC** for database connectivity and **MySQL** for persistent data storage.

---

## 📌 Features

* **User Registration & Login**
* **Account Creation** (Savings, Current, etc.)
* **Deposit & Withdraw Funds**
* **Money Transfer Between Accounts**
* **Check Account Balance**
* **Secure Transactions** with Security PIN
* **Persistent Storage** using MySQL Database
* **Modular Java Classes** for better maintainability

---

## 🛠️ Technologies Used

| Technology        | Purpose                                |
| ----------------- | -------------------------------------- |
| **Java (JDK 24)** | Core application logic                 |
| **JDBC**          | Database connectivity layer            |
| **MySQL**         | Backend database for storing user data |
| **SQL**           | Queries for CRUD operations            |

---

## 📂 Project Structure

```
Banking-Management-System-JDBC/
│── src/
│   └── com/
│       └── banking/
│           ├── BankingApp.java      # Main entry point
│           ├── User.java            # User details & authentication
│           ├── AccountManager.java  # Handles banking operations
│           └── Accounts.java        # Account entity class
│── banking_system.sql               # MySQL database schema & sample data
│── README.md                        # Project documentation
```

---

## ⚙️ Database Setup

1. **Create Database & Tables**
   Run the provided [`banking_system.sql`](banking_system.sql) file in MySQL Workbench or CLI:

   ```sql
   SOURCE /path/to/banking_system.sql;
   ```

2. **Update Database Credentials**
   In each Java file where JDBC connection is made, update:

   ```java
   private static final String url = "jdbc:mysql://localhost:3306/banking_system";
   private static final String username = "root";
   private static final String password = "your_password";
   ```

---

## 🚀 How to Run the Project

1. **Clone the Repository**

   ```bash
   git clone https://github.com/Naved-Sheikh/Banking-Management-System-JDBC.git
   ```

2. **Open in Your IDE** (Eclipse / IntelliJ IDEA / VS Code)

3. **Compile & Run**

   ```bash
   javac -d bin src/com/banking/*.java
   java -cp bin com.banking.BankingApp
   ```

---

## 📈 Future Improvements

* GUI-based frontend with JavaFX or Swing
* Transaction history tracking
* Password encryption for better security
* Admin dashboard for managing all accounts

---

## 👨‍💻 Author

**Naved Sheikh**
📧 Email: [navedsheikh7983@gmail.com](mailto:navedsheikh7983@gmail.com)
💻 GitHub: [Naved-Sheikh](https://github.com/Naved-Sheikh)
🔗 LinkedIn: [Mohd Naved](https://www.linkedin.com/in/mohd-naved-82757a2aa)

---

## 📜 License

This project is licensed under the **MIT License** – you are free to use, modify, and distribute it with attribution.

---
