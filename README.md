
# Reverso Application

## Overview

The **Reverso Application** is a Java-based solution designed for managing clients, contracts, and related business entities within a database. Built on robust object-oriented principles, it provides functionalities such as database connectivity, CRUD operations, and structured exception handling.

---

## Features

### Client Management
- Perform CRUD operations (Create, Read, Update, Delete) on client entities.
- Validate inputs such as company name, email, turnover (`chiffreAffaires`), and more.
- Establish relationships between clients and contracts.

### Contract Management
- Add and manage contracts for each client using the `ContratDao` class.

### Database Connection
- Singleton `ConnectionManager` ensures a single database connection is active throughout the application.
- Uses a `database.Properties` file for seamless configuration of database connection parameters.

### Error Handling
- Custom exceptions (`ReversoException`, `ReversoDaoException`) for handling application-specific and database errors.
- Detailed error messages displayed via `JOptionPane` dialogs and logged for debugging purposes.

### Logging
- Centralized logging system using `MyLoggersClass`.
- Logs both errors and events for better monitoring and debugging.

---

## Setup Instructions

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or later
- **MySQL Database**
- **JDBC Driver**: MySQL Connector (`mysql-connector-java-5.1.49.jar`)

### Database Configuration
1. Create a `database.Properties` file in the project root directory with the following content:
   ```properties
   jdbc.driver.class=com.mysql.cj.jdbc.Driver
   url=jdbc:mysql://localhost:3306/reverso_db
   login=root
   password=your_password
   ```

2. Ensure the database `reverso_db` exists and contains necessary tables like `Client` and `Contract`.

### Running the Application
1. Clone the repository or download the project files.
2. Include `mysql-connector-java-5.1.49.jar` in the `lib/` folder.
3. Compile and run the application using:
   ```bash
   javac -cp .;lib/mysql-connector-java-5.1.49.jar com/bouchaib/Main.java
   java -cp .;lib/mysql-connector-java-5.1.49.jar com.bouchaib.Main
   ```

---

## Project Structure

```
root/
├── lib/
│   └── mysql-connector-java-5.1.49.jar
├── src/
│   ├── com/
│   │   ├── bouchaib/
│   │   │   ├── dao/daoSql/
│   │   │   │   ├── ClientDao.java
│   │   │   │   ├── ConnectionManager.java
│   │   │   │   └── ContratDao.java
│   │   │   ├── entity/
│   │   │   │   ├── Client.java
│   │   │   │   ├── Contrat.java
│   │   │   │   ├── Societe.java
│   │   │   │   ├── Prospect.java
│   │   │   │   └── loggers/
│   │   │   │       └── MyLoggersClass.java
│   │   │   ├── utilities/
│   │   │   │   ├── CrudEnum.java
│   │   │   │   └── TypeSocieteEnum.java
│   │   │   └── Main.java
├── database.Properties
├── app.log
├── reverso.iml
├── .gitignore
└── out/
```

---

## Key Components

### ConnectionManager
- Ensures a single connection to the database is maintained.
- Provides methods to establish and close the database connection.
- Handles errors such as invalid credentials or database unavailability with custom exceptions.

### ClientDao
- Handles database interactions for `Client` objects.
- Key Methods:
  - `findAll()`: Retrieves all clients.
  - `find(int id)`: Retrieves a client by its ID.
  - `save(Client client, CrudEnum operation)`: Creates or updates a client in the database.
  - `delete(Client client)`: Deletes a client from the database.

### Client
- Represents a client entity with attributes like:
  - Company name (`raisonSociale`)
  - Street address (`numRue`, `nomRue`)
  - Turnover (`chiffreAffaires`)
  - Number of employees (`nbEmployes`)

### Logging
- Logs critical events and errors to the `app.log` file.
- Uses the `MyLoggersClass` for centralized logging.

---

## Example Logs

```
INFO: Connection established successfully.
SEVERE: Error while saving client data.
java.sql.SQLException: Duplicate entry '1' for key 'PRIMARY'
    at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:953)
    ...


## Author

**Khribech Bouchaib**  
Date: 2023-03-25 
Version: 1.0

---
