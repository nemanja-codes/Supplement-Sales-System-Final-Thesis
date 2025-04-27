# Supplement Sales System - Final Thesis Project

## Overview

This project represents a desktop application developed for supplement sales management as part of a university final thesis.  
It showcases the use of Java technologies (Swing for GUI, sockets for communication, threads for concurrency, and database operations) along with the application of a simplified Larman's software development method.

## Features

- Manage inventory and sales of supplements
- User-friendly desktop graphical interface (Swing)
- Socket-based server-client communication
- Multithreaded processing for handling multiple client requests
- Database operations for persistent data storage
- JUnit tests for verifying system operations

## Project Structure

- `SuplementiKlijent` — Client-side desktop application
- `SuplementiServer` — Server-side application managing requests
- `SuplementiZajednicki` — Shared domain classes and system operations
- `suplemeti.sql` — SQL script for database creation
- `mysql-connector.jar` — JDBC driver for MySQL database connection
- `flatlaf.jar` — Library for modernizing the Swing UI look and feel

## Database Setup

- Use the provided `suplemeti.sql` file to create the database structure.
- Execute the script in your database management system (e.g., MySQL).
- Ensure the database connection details in the project match your environment settings.

## How to Run

1. Set up the database using `suplemeti.sql`.
2. Start the `SuplementiServer` application to initialize the server.
3. Run the `SuplementiKlijent` application to connect and manage supplements.

## Technologies Used

- Java SE
- Swing
- Sockets (Server-Client communication)
- Multithreading
- MySQL
- JUnit
- FlatLaf (for enhanced GUI appearance)

## Final Note

This project was developed as part of the final thesis, highlighting the practical application of structured software development approaches in Java.

