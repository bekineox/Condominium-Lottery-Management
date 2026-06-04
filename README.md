# Condominium Lottery Registration System

> A console-based Java application for managing condominium unit type lotteries — from applicant registration through randomized draws to winner announcement.

---

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Project Structure](#project-structure)
4. [Architecture & Design](#architecture--design)
5. [OOP Concepts Demonstrated](#oop-concepts-demonstrated)
6. [Package Documentation](#package-documentation)
   - [Enums](#1-enums-comcondolotteryenums)
   - [Exceptions](#2-exceptions-comcondolotteryexception)
   - [Models](#3-models-comcondolotterymodel)
   - [Services](#4-services-comcondolotteryservice)
   - [Main](#5-main-comcondolotterymain)
7. [Data Persistence](#data-persistence)
8. [Application Flow](#application-flow)
9. [How to Compile & Run](#how-to-compile--run)
10. [Usage Guide](#usage-guide)
11. [Sample Workflow](#sample-workflow)

---

## Overview

The **Condominium Lottery Registration System** is a Java SE application designed to facilitate fair, lottery-based allocation of condominium unit type options to applicants. It uses a menu-driven console interface and persists all data to pipe-delimited text files in the `data/` directory.

**Primary use case:** A housing authority registers applicants, processes registrations linking applicants to desired unit type options, and then conducts randomized lottery draws to select winners.

---

## Features

| Feature                    | Description                                                        |
|----------------------------|--------------------------------------------------------------------|
| **Applicant Management**   | Core operations — add, view, search, and delete applicants         |
| **Registration System**    | Link applicants to unit types (registered as PENDING status)       |
| **Lottery Draw**           | Random winner selection from registered applicants per unit type   |
| **Result Tracking**        | View all lottery draw outcomes (winners and losers)                |
| **Data Persistence**       | Automatic file-based storage using pipe-delimited `.txt` files     |
| **Polymorphism Demo**      | Built-in demonstration of OOP polymorphism and dynamic binding     |

---

## Project Structure

```
Condominium Lottery Registration System/
├── .classpath
├── .project
├── README.md
├── sources.txt                            # File list for compilation
├── data/                                  # Persistent data storage
│   ├── applicants.txt                     # Applicant records
│   └── registrations.txt                  # Registration records
├── src/
│   └── com/
│       └── condolottery/
│           ├── enums/                     # Enumeration types
│           │   ├── RegistrationStatus.java
│           │   └── UnitType.java
│           ├── exception/                 # Custom exceptions
│           │   └── RegistrationException.java
│           ├── main/                      # Application entry point
│           │   └── Main.java
│           ├── model/                     # Data model classes
│           │   ├── Person.java            # Base class
│           │   ├── Admin.java             # Extends Person
│           │   ├── Applicant.java         # Extends Person
│           │   └── Registration.java
│           └── service/                   # Business logic layer
│               ├── Manageable.java        # Generic interface
│               ├── ApplicantService.java
│               └── RegistrationService.java
└── bin/                                   # Compiled .class files
```

---

## Architecture & Design

The application follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                │
│                      Main.java                       │
│          (Console menus, user I/O, flow control)     │
├─────────────────────────────────────────────────────┤
│                   SERVICE / BUSINESS LAYER           │
│      ApplicantService    │   RegistrationService     │
│       (CRUD logic, validation, file persistence)     │
├─────────────────────────────────────────────────────┤
│                      MODEL LAYER                     │
│  Person (base) → Applicant, Admin                    │
│  Registration                                        │
│            (Data structures / entities)               │
├─────────────────────────────────────────────────────┤
│               SUPPORTING TYPES                       │
│   Enums: RegistrationStatus, UnitType                │
│   Exceptions: RegistrationException                  │
├─────────────────────────────────────────────────────┤
│                  PERSISTENCE LAYER                   │
│              data/*.txt (pipe-delimited files)        │
└─────────────────────────────────────────────────────┘
```

---

## OOP Concepts Demonstrated

This project was built as a comprehensive demonstration of core Object-Oriented Programming principles in Java:

### 1. Abstraction
- The **`Manageable<T>`** interface abstracts CRUD operations, providing a consistent contract without dictating implementation details.
- Service Layer Abstraction hides complex file I/O from the Presentation layer.

### 2. Encapsulation
- All model fields are declared `private`.
- Access is controlled through `public` getter and setter methods.
- Internal file I/O logic is encapsulated as `private` methods within service classes.

### 3. Inheritance
- **`Applicant extends Person`** — inherits `id`, `fullName`, and `phone`; adds `email`, `address`, `registrationDate`.
- **`Admin extends Person`** — inherits `id`, `fullName`, and `phone`; adds `role`, `department`.

### 4. Polymorphism & Dynamic Binding
- Both `Applicant` and `Admin` override `displayInfo()` from `Person`.
- A `List<Person>` can hold both types; calling `displayInfo()` on each element invokes the correct subclass method at runtime.
- The **Demonstrate Polymorphism** menu option (option 5) showcases this explicitly.

### 5. Interfaces & Generics
- **`Manageable<T>`** — a generic interface defining `add(T)`, `displayAll()`, `searchById(String)`, `update(T)`, and `delete(String)`.
- `ApplicantService` and `RegistrationService` implement `Manageable<T>` with their respective type parameter.

### 6. Enumerations
- `UnitType` and `RegistrationStatus` are `enum` types.

### 7. Exception Handling
- **`RegistrationException`** (checked / `Exception`) — for business-rule violations during registration and lottery draws.
- All user-facing operations are wrapped in `try-catch` blocks.

### 8. File Handling
- Service classes read from and write to text files using Scanner and BufferedWriter.

---

## Package Documentation

### 1. Enums (`com.condolottery.enums`)

#### `UnitType`
Defines the types of condominium units available in the system.

- `STUDIO`
- `ONE_BEDROOM`
- `TWO_BEDROOM`
- `THREE_BEDROOM`

#### `RegistrationStatus`
Tracks the lifecycle of an applicant's registration.

- `PENDING`
- `APPROVED`
- `WON`
- `LOST`

---

### 2. Exceptions (`com.condolottery.exception`)

#### `RegistrationException` (extends `Exception`)
Thrown when a registration or lottery operation violates business rules.

**Common triggers:**
- Registering for a non-existent applicant
- Duplicate applicant registrations
- Conducting a draw with zero registrations

---

### 3. Models (`com.condolottery.model`)

#### `Person`
Base class for all people in the system.

- `id`, `fullName`, `phone`

#### `Applicant` (extends `Person`)
Represents a person who registers for the condominium lottery.

- `email`, `address`, `registrationDate`

#### `Admin` (extends `Person`)
Represents an administrator who manages the lottery system.

- `role`, `department`

#### `Registration`
Links an applicant to a desired UnitType.

- `registrationId`, `applicantId`, `unitType`, `registrationDate`, `status`

---

### 4. Services (`com.condolottery.service`)

#### `Manageable<T>` (Interface)
A generic interface that standardizes CRUD operations across all service classes.

- `add(T item)`
- `displayAll()`
- `searchById(String id)`
- `update(T item)`
- `delete(String id)`

#### `ApplicantService` (implements `Manageable<Applicant>`)
Manages all applicant CRUD operations. Data is persisted to `data/applicants.txt`.

#### `RegistrationService` (implements `Manageable<Registration>`)
Manages registration CRUD and filtering. Data is persisted to `data/registrations.txt`.

---

### 5. Main (`com.condolottery.main`)

#### `Main`
The application entry point. Provides a console-based, menu-driven interface.

---

## Data Persistence

All data is stored in pipe-delimited (`|`) text files under the `data/` directory. Files are created automatically if they don't exist.

---

## Application Flow

```
┌──────────────────┐
│1. Add Applicants │  Register people who want to participate
└────────┬─────────┘
         ▼
┌──────────────────────────┐
│ 2. Create Registrations  │  Link applicants to their desired UnitType
│    (Status: PENDING)     │
└────────┬─────────────────┘
         ▼
┌──────────────────────────┐
│ 3. Conduct Lottery Draw  │  Random selection from pending registrations
│    Winner → WON          │
│    Others → LOST         │
└────────┬─────────────────┘
         ▼
┌──────────────────────────┐
│  4. View Winners         │  See all results showing winners
└──────────────────────────┘
```

---

## How to Compile & Run

### Prerequisites
- **Java JDK 8** or later installed
- Terminal / Command Prompt access

### Compile

```bash
# Navigate to the project root directory
cd "Condominium Lottery Registration System"

# Compile all source files
javac -d bin "@sources.txt"
```

### Run

```bash
# Run from the project root (important for data/ file paths)
java -cp bin com.condolottery.main.Main
```

---

## Usage Guide

### Managing Applicants
1. Select **option 1** from the Main Menu
2. Choose from the Applicant sub-menu to Add, Display, Search, or Delete.

### Managing Registrations
1. Select **option 2** from the Main Menu
2. Choose from the Registration sub-menu to register an applicant.

### Conducting a Lottery Draw
1. Select **option 3** from the Main Menu
2. Select a UnitType to draw for.

---

## Sample Workflow

```
Step 1: Add Applicants
  → APP001 — Abebe Kebede (0911223344, abebe@email.com, Addis Ababa)
  → APP002 — Tigist Hailu (0922334455, tigist@email.com, Dire Dawa)

Step 2: Create Registrations
  → REG001 — APP001 → STUDIO (Status: PENDING)
  → REG002 — APP002 → STUDIO

Step 3: Conduct Lottery Draw for STUDIO
  → System randomly picks winner (e.g., APP002)
  → REG002 → WON, REG001 → LOST

Step 4: View Winners
  → Shows REG002 (Tigist Hailu) won
```