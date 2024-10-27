# Banking-system

A secure and efficient web-based Banking System designed to manage essential banking operations, such as account creation, balance inquiries, deposits, withdrawals, money transfers, and account deactivation. Built with Java Servlets and MySQL, this project streamlines banking functionality while ensuring data integrity and security.

# Table of Contents

Features

Technologies

Usage

Database Structure

# Features

**Account Management**: Create new accounts, view balance, and manage funds with ease.

**Transactions:** Perform deposits, withdrawals, and transfers between accounts securely.

**Account Deactivation:** Temporarily disable accounts with the option to reactivate them later.

**User Authentication:** Secure login with account verification and data protection.

**Error Handling:** Clear messaging for insufficient funds, incorrect credentials, etc.

# Technologies

**Frontend:** HTML, CSS

**Backend:** Java ,Servlets

**Database:** MySQL

# Usage

**Launch the Server:** Start the Apache Tomcat server and open the application in a web browser.

**Navigate:** Use the menu options to create accounts, view balances, make deposits/withdrawals, transfer funds, or deactivate accounts.

**Reactivation:** If attempting to create an account with deactivated credentials, users will be prompted to reactivate or proceed with a new account.

# Database Structure

**accountInfo** (Database table name)

acc_num: INT, Primary Key

name: VARCHAR

password: VARCHAR

amount: DOUBLE

status: VARCHAR (active/deactivate)

# vedio Demo

[Watch the Demo Video](https://drive.google.com/file/d/1dTnk6KH0QUfbNEq_3tA0TAyPxFWDfR2G/view?usp=sharing)
