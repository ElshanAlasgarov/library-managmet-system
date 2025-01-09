# Library Management System (LMS)

The **Library Management System (LMS)** is a **console-based application** designed to efficiently manage library resources. The system tracks and manages books, readers, and loan operations.

## 📖 Project Description
Using LMS, users can:
- Add, update, and delete books.
- Register, update, and remove readers.
- Manage book rental and return operations.
- Calculate late return penalties.

## 🚀 Features
### Book Management
- Add new books.
- Update book information.
- Delete books.
- Search books by category or author.

### Reader Management
- Register new readers.
- Update reader information.
- Remove readers from the system.

### Book Rental
- Rent books to readers.
- Manage book returns.
- Calculate late return penalties.

## 🛠️ Technologies
- **Java SE**: Primary programming language.
- **PostgreSQL**: Database.
- **JDBC**: To connect Java with PostgreSQL.

## 📂 Project Structure
The project consists of the following modules:
1. **Book Module**:
    - Classes: `Book`, `Category`, `Author`.
    - Operations: Add, update, delete, and search.

2. **Reader Module**:
    - Classes: `Reader`.
    - Operations: Register, update, and delete.

3. **Loan Module**:
    - Classes: `Loan`, `Penalty`.
    - Operations: Rent, return, and calculate penalties.

4. **Database Module**:
    - Connection to PostgreSQL.
    - CRUD operations managed by a DAO (Data Access Object) layer.

## 🖥️ User Interface
The application operates through a **console-based interface**. Main menu options include:
1. **Books**
2. **Readers**
3. **Loan Operations**
4. **Exit**

