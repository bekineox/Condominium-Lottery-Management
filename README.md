# Condominium Lottery Registration System

> A console-based Java application for managing condominium unit lotteries — from applicant registration through randomized draws to winner announcement.

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

The **Condominium Lottery Registration System** is a Java SE application designed to facilitate fair, lottery-based allocation of condominium units to applicants. It uses a menu-driven console interface and persists all data to pipe-delimited text files in the `data/` directory.

**Primary use case:** A housing authority registers applicants, adds condominium buildings and units, processes registrations linking applicants to desired units, and then conducts randomized lottery draws to select winners.

---

## Features

| Feature                    | Description                                                        |
|----------------------------|--------------------------------------------------------------------|
| **Applicant Management**   | Core operations — add, view, and search applicants                 |
| **Condominium Management** | Core operations — add, view, and search condominium buildings      |
| **Unit Management**        | Core operations — add, view, and search individual units           |
| **Registration System**    | Link applicants to units (automatically registered as APPROVED)    |
| **Lottery Draw**           | Random winner selection from registered applicants per unit        |
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
├── data/                                  # Persistent data storage
│   ├── applicants.txt                     # Applicant records
│   ├── condominiums.txt                   # Condominium records
│   ├── units.txt                          # Unit records
│   ├── registrations.txt                  # Registration records
│   └── results.txt                        # Lottery result records
├── src/
│   └── com/
│       └── condolottery/
│           ├── enums/                     # Enumeration types
│           │   ├── RegistrationStatus.java
│           │   ├── UnitStatus.java
│           │   └── UnitType.java
│           ├── exception/                 # Custom exceptions
│           │   ├── InvalidDataException.java
│           │   └── RegistrationException.java
│           ├── main/                      # Application entry point
│           │   └── Main.java
│           ├── model/                     # Data model classes
│           │   ├── Person.java            # Abstract base class
│           │   ├── Admin.java             # Extends Person
│           │   ├── Applicant.java         # Extends Person
│           │   ├── Condominium.java
│           │   ├── Unit.java
│           │   ├── Registration.java
│           │   └── LotteryResult.java
│           └── service/                   # Business logic layer
│               ├── Manageable.java        # Generic interface
│               ├── ApplicantService.java
│               ├── CondominiumService.java
│               ├── UnitService.java
│               ├── RegistrationService.java
│               └── LotteryService.java
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
│   ApplicantService │ CondominiumService │ UnitService │
│         RegistrationService │ LotteryService         │
│       (CRUD logic, validation, file persistence)     │
├─────────────────────────────────────────────────────┤
│                      MODEL LAYER                     │
│  Person (abstract) → Applicant, Admin                │
│  Condominium │ Unit │ Registration │ LotteryResult    │
│            (Data structures / entities)               │
├─────────────────────────────────────────────────────┤
│               SUPPORTING TYPES                       │
│   Enums: RegistrationStatus, UnitStatus, UnitType    │
│   Exceptions: InvalidDataException,                  │
│               RegistrationException                  │
├─────────────────────────────────────────────────────┤
│                  PERSISTENCE LAYER                   │
│              data/*.txt (pipe-delimited files)        │
└─────────────────────────────────────────────────────┘
```

---

## OOP Concepts Demonstrated

This project was built as a comprehensive demonstration of core Object-Oriented Programming principles in Java:

### 1. Abstraction
- **`Person`** is an `abstract` class that cannot be instantiated directly.
- The abstract method `displayInfo()` forces all subclasses to provide their own implementation.

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
- The **Demonstrate Polymorphism** menu option (option 7) showcases this explicitly.

### 5. Constructor Chaining (`super`)
- `Applicant` and `Admin` constructors call `super(id, fullName, phone)` to initialize inherited fields via the `Person` constructor.

### 6. Interfaces & Generics
- **`Manageable<T>`** — a generic interface defining `add(T)`, `displayAll()`, `searchById(String)`, and `update(T)`.
- `ApplicantService`, `CondominiumService`, `UnitService`, and `RegistrationService` all implement `Manageable<T>` with their respective type parameter.

### 7. Enumerations
- `UnitType`, `UnitStatus`, and `RegistrationStatus` are `enum` types with descriptive string fields and `getDescription()` methods.

### 8. Exception Handling
- **`InvalidDataException`** (unchecked / `RuntimeException`) — for validation errors like duplicate IDs or missing records.
- **`RegistrationException`** (checked / `Exception`) — for business-rule violations during registration and lottery draws.
- All user-facing operations are wrapped in `try-catch` blocks.

### 9. File Handling
- Each service class reads from and writes to a dedicated text file using `BufferedReader` / `BufferedWriter`.
- The `finally` block ensures resources are closed even if exceptions occur.

### 10. `this` Keyword
- Used throughout constructors to disambiguate between parameters and instance fields (e.g., `this.id = id`).

---

## Package Documentation

### 1. Enums (`com.condolottery.enums`)

#### `UnitType`
Defines the types of condominium units available in the system.

| Constant         | Description               |
|------------------|---------------------------|
| `STUDIO`         | Studio Apartment          |
| `ONE_BEDROOM`    | One Bedroom Apartment     |
| `TWO_BEDROOM`    | Two Bedroom Apartment     |
| `THREE_BEDROOM`  | Three Bedroom Apartment   |

#### `UnitStatus`
Tracks the availability lifecycle of a condominium unit.

| Constant     | Description | Meaning                              |
|--------------|-------------|--------------------------------------|
| `AVAILABLE`  | Available   | Open for registration                |
| `RESERVED`   | Reserved    | Has at least one pending registration |
| `SOLD`       | Sold        | Lottery draw completed; unit awarded |

#### `RegistrationStatus`
Tracks the lifecycle of an applicant's registration.

| Constant    | Description        | Meaning                                        |
|-------------|--------------------|-------------------------------------------------|
| `APPROVED`  | Approved for Draw  | Active registration, eligible for lottery draw  |
| `WON`       | Won the Lottery    | Selected as winner in the draw                  |
| `LOST`      | Did Not Win        | Not selected in the draw                        |

---

### 2. Exceptions (`com.condolottery.exception`)

#### `InvalidDataException` (extends `RuntimeException`)
Thrown when input data fails validation checks.

**Common triggers:**
- Attempting to update a record that doesn't exist
- Empty or malformed required fields

#### `RegistrationException` (extends `Exception`)
Thrown when a registration or lottery operation violates business rules.

**Common triggers:**
- Registering for a non-existent applicant or unit
- Registering for a unit that is not `AVAILABLE`
- Duplicate applicant–unit registration
- Conducting a draw with zero registrations

---

### 3. Models (`com.condolottery.model`)

#### `Person` (abstract)
Base class for all people in the system.

| Field       | Type     | Access  | Description              |
|-------------|----------|---------|--------------------------|
| `id`        | `String` | private | Unique identifier        |
| `fullName`  | `String` | private | Full name of the person  |
| `phone`     | `String` | private | Phone number             |

**Abstract method:** `String displayInfo()` — must be overridden by subclasses.

---

#### `Applicant` (extends `Person`)
Represents a person who registers for the condominium lottery.

| Field              | Type     | Access  | Description                      |
|--------------------|----------|---------|----------------------------------|
| `email`            | `String` | private | Email address                    |
| `address`          | `String` | private | Residential address              |
| `registrationDate` | `String` | private | Date of registration (yyyy-MM-dd) |

*Inherited from Person:* `id`, `fullName`, `phone`

---

#### `Admin` (extends `Person`)
Represents an administrator who manages the lottery system.

| Field        | Type     | Access  | Description                          |
|--------------|----------|---------|--------------------------------------|
| `role`       | `String` | private | Admin role (e.g., "System Manager")  |
| `department` | `String` | private | Department (e.g., "IT Department")   |

*Inherited from Person:* `id`, `fullName`, `phone`

---

#### `Condominium`
Represents a condominium building.

| Field         | Type     | Access  | Description              |
|---------------|----------|---------|--------------------------|
| `condoId`     | `String` | private | Unique condominium ID    |
| `name`        | `String` | private | Building name            |
| `location`    | `String` | private | Address / location       |
| `totalFloors` | `int`    | private | Number of floors         |
| `totalUnits`  | `int`    | private | Total number of units    |

---

#### `Unit`
Represents an individual condominium unit within a building.

| Field         | Type         | Access  | Description                        |
|---------------|--------------|---------|------------------------------------|
| `unitId`      | `String`     | private | Unique unit ID                     |
| `condoId`     | `String`     | private | Parent condominium ID              |
| `floorNumber` | `int`        | private | Floor number                       |
| `unitNumber`  | `String`     | private | Unit number / label                |
| `area`        | `double`     | private | Area in square meters              |
| `price`       | `double`     | private | Price in ETB (Ethiopian Birr)      |
| `unitType`    | `UnitType`   | private | Type of unit (enum)                |
| `status`      | `UnitStatus` | private | Availability status (enum)         |

---

#### `Registration`
Links an applicant to a desired condominium unit.

| Field              | Type                 | Access  | Description                  |
|--------------------|----------------------|---------|------------------------------|
| `registrationId`   | `String`             | private | Unique registration ID       |
| `applicantId`      | `String`             | private | ID of the applicant          |
| `unitId`           | `String`             | private | ID of the desired unit       |
| `registrationDate` | `String`             | private | Date of registration         |
| `status`           | `RegistrationStatus` | private | Current status (enum)        |

---

#### `LotteryResult`
Stores the outcome of a lottery draw for each participant.

| Field            | Type      | Access  | Description                              |
|------------------|-----------|---------|------------------------------------------|
| `resultId`       | `String`  | private | Unique result ID (e.g., `RES0001`)       |
| `registrationId` | `String`  | private | Associated registration ID               |
| `applicantId`    | `String`  | private | Applicant who participated               |
| `unitId`         | `String`  | private | Unit the draw was conducted for          |
| `drawDate`       | `String`  | private | Date of the draw (yyyy-MM-dd)            |
| `winner`         | `boolean` | private | `true` if this applicant won             |

---

### 4. Services (`com.condolottery.service`)

#### `Manageable<T>` (Interface)
A generic interface that standardizes CRUD operations across all service classes.

| Method                     | Return Type | Description                        |
|----------------------------|-------------|------------------------------------|
| `add(T item)`              | `void`      | Adds a new item and persists it    |
| `displayAll()`             | `void`      | Prints all items to the console    |
| `searchById(String id)`    | `T`         | Finds an item by ID (or `null`)    |
| `update(T item)`           | `void`      | Updates an existing item           |

---

#### `ApplicantService` (implements `Manageable<Applicant>`)
Manages all applicant CRUD operations. Data is persisted to `data/applicants.txt`.

**Key behaviors:**
- Duplicate ID check on `add()`
- Case-insensitive ID search
- Auto-save to file after every mutation

---

#### `CondominiumService` (implements `Manageable<Condominium>`)
Manages all condominium building CRUD operations. Data is persisted to `data/condominiums.txt`.

**Key behaviors:**
- Duplicate ID check on `add()`
- Case-insensitive ID search
- Auto-save to file after every mutation

---

#### `UnitService` (implements `Manageable<Unit>`)
Manages condominium unit CRUD operations. Data is persisted to `data/units.txt`.

**Key behaviors:**
- Duplicate ID check on `add()`
- Enum types (`UnitType`, `UnitStatus`) stored by their `.name()` and restored via `valueOf()`
- Auto-save to file after every mutation

---

#### `RegistrationService` (implements `Manageable<Registration>`)
Manages registration CRUD and provides filtering capabilities. Data is persisted to `data/registrations.txt`.

**Key behaviors:**
- Duplicate ID check on `add()`
- `getApprovedRegistrationsByUnit(unitId)` — filters registrations with `APPROVED` status for a specific unit
- Auto-save to file after every mutation

---

#### `LotteryService`
Conducts lottery draws and manages draw results. Data is persisted to `data/results.txt`.

**Key behaviors:**
- Uses `java.util.Random` for fair winner selection
- Creates a `LotteryResult` for every participant (winner and losers)
- Updates registration statuses (`WON` / `LOST`) via `RegistrationService`
- Updates unit status to `SOLD` via `UnitService`
- Provides `displayAllResults()` view

---

### 5. Main (`com.condolottery.main`)

#### `Main`
The application entry point. Provides a console-based, menu-driven interface.

**Main menu options:**

| # | Option                    | Description                                       |
|---|---------------------------|---------------------------------------------------|
| 1 | Manage Applicants         | Operations: add, display all, search by ID        |
| 2 | Manage Condominiums       | Operations: add, display all, search by ID        |
| 3 | Manage Condominium Units  | Operations: add, display all, search by ID        |
| 4 | Manage Registrations      | Operations: add, display all, search by ID        |
| 5 | Conduct Lottery Draw      | Select a unit and randomly pick a winner           |
| 6 | View Lottery Results      | Displays all lottery draw results directly        |
| 7 | Demonstrate Polymorphism  | Live OOP polymorphism & dynamic binding demo       |
| 8 | Exit                      | Terminates the application                        |

**Utility methods:**
- `readStringInput(prompt)` — reads non-empty string input
- `readOptionalInput(prompt, default)` — reads input with a default fallback
- `readIntInput(prompt)` — reads and validates integer input
- `readDoubleInput(prompt)` — reads and validates double input

---

## Data Persistence

All data is stored in pipe-delimited (`|`) text files under the `data/` directory. Files are created automatically if they don't exist.

### File Formats

#### `data/applicants.txt`
```
{id}|{fullName}|{phone}|{email}|{address}|{registrationDate}
```
**Example:** `APP001|Abebe Kebede|0911223344|abebe@email.com|Addis Ababa|2025-01-15`

#### `data/condominiums.txt`
```
{condoId}|{name}|{location}|{totalFloors}|{totalUnits}
```
**Example:** `C001|Sunshine Residences|Bole, Addis Ababa|19|40`

#### `data/units.txt`
```
{unitId}|{condoId}|{floorNumber}|{unitNumber}|{area}|{price}|{unitType}|{unitStatus}
```
**Example:** `U001|C001|5|A-501|75.0|2500000.0|TWO_BEDROOM|AVAILABLE`

#### `data/registrations.txt`
```
{registrationId}|{applicantId}|{unitId}|{registrationDate}|{status}
```
**Example:** `REG001|APP001|U001|2025-03-10|APPROVED`

#### `data/results.txt`
```
{resultId}|{registrationId}|{applicantId}|{unitId}|{drawDate}|{winner}
```
**Example:** `RES0001|REG001|APP001|U001|2025-04-01|true`

---

## Application Flow

The typical end-to-end workflow follows this sequence:

```
┌──────────────────┐
│  1. Add Condo    │  Create a condominium building
└────────┬─────────┘
         ▼
┌──────────────────┐
│  2. Add Units    │  Add units to the building (type, floor, price, etc.)
└────────┬─────────┘
         ▼
┌──────────────────┐
│ 3. Add Applicants│  Register people who want to participate
└────────┬─────────┘
         ▼
┌──────────────────────────┐
│ 4. Create Registrations  │  Link applicants to their desired unit
│    (Status: APPROVED)    │  Unit status changes to RESERVED
└────────┬─────────────────┘
         ▼
┌──────────────────────────┐
│ 5. Conduct Lottery Draw  │  Random selection from registered applicants
│    Winner → WON          │  Unit status changes to SOLD
│    Others → LOST         │
└────────┬─────────────────┘
         ▼
┌──────────────────────────┐
│  6. View Results         │  See all results showing winners and losers
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
javac -d bin src/com/condolottery/enums/*.java \
              src/com/condolottery/exception/*.java \
              src/com/condolottery/model/*.java \
              src/com/condolottery/service/*.java \
              src/com/condolottery/main/*.java
```

### Run

```bash
# Run from the project root (important for data/ file paths)
java -cp bin com.condolottery.main.Main
```

> **Note:** The application must be run from the project root directory so that the relative `data/` path resolves correctly.

---

## Usage Guide

### Managing Applicants
1. Select **option 1** from the Main Menu
2. Choose from the Applicant sub-menu:
   - **Add** — enter ID, name, phone, email, and address (date is auto-generated)
   - **Display All** — lists all registered applicants
   - **Search** — find by applicant ID

### Managing Condominiums
1. Select **option 2** from the Main Menu
2. Enter condominium ID, name, location, floors, and total units

### Managing Units
1. Select **option 3** from the Main Menu
2. Enter unit ID, parent condo ID, floor, unit number, area (sqm), price (ETB), and select a unit type

### Managing Registrations
1. Select **option 4** from the Main Menu
2. **Add Registration** — enter registration ID, applicant ID, and unit ID
   - The system validates that both the applicant and unit exist
   - The unit must be `AVAILABLE` — its status changes to `RESERVED`
   - Duplicate applicant–unit pairs are rejected
   - The registration is automatically set to `APPROVED` status

### Conducting a Lottery Draw
1. Select **option 5** from the Main Menu
2. All units are displayed; enter the unit ID to draw for
3. The system randomly selects one winner from all registrations for that unit
4. The winner's registration is updated to `WON`, others to `LOST`
5. The unit status changes to `SOLD`

### Viewing Results
1. Select **option 6** from the Main Menu
2. The system displays all draw outcomes directly, showing both winners and losers

---

## Sample Workflow

```
Step 1: Add Condominium
  → ID: C001, Name: Sunshine Residences, Location: Bole, Floors: 19, Units: 40

Step 2: Add Unit
  → ID: U001, Condo: C001, Floor: 5, Unit: A-501, Area: 75sqm, Price: 2,500,000 ETB, Type: Two Bedroom

Step 3: Add Applicants
  → APP001 — Abebe Kebede (0911223344, abebe@email.com, Addis Ababa)
  → APP002 — Tigist Hailu (0922334455, tigist@email.com, Dire Dawa)

Step 4: Create Registrations
  → REG001 — APP001 → U001 (Status: APPROVED, Unit changes to RESERVED)
  → REG002 — APP002 → U001

Step 5: Conduct Lottery Draw for U001
  → System randomly picks winner (e.g., APP002)
  → REG002 → WON, REG001 → LOST
  → Unit U001 → SOLD

Step 6: View Results
  → Result ID: RES0002, Status: WON ✓
  → Result ID: RES0001, Status: LOST ✗
```

---

## Class Diagram

```
                    ┌─────────────────────┐
                    │   «abstract»        │
                    │     Person          │
                    ├─────────────────────┤
                    │ - id: String        │
                    │ - fullName: String  │
                    │ - phone: String     │
                    ├─────────────────────┤
                    │ + displayInfo()*    │
                    │ + getters/setters   │
                    └──────────┬──────────┘
                               │
                ┌──────────────┴──────────────┐
                │                             │
    ┌───────────▼───────────┐     ┌───────────▼───────────┐
    │     Applicant         │     │       Admin           │
    ├───────────────────────┤     ├───────────────────────┤
    │ - email: String       │     │ - role: String        │
    │ - address: String     │     │ - department: String  │
    │ - registrationDate    │     ├───────────────────────┤
    ├───────────────────────┤     │ + displayInfo()       │
    │ + displayInfo()       │     └───────────────────────┘
    └───────────────────────┘

    ┌───────────────────────┐     ┌───────────────────────┐
    │    Condominium        │     │        Unit           │
    ├───────────────────────┤     ├───────────────────────┤
    │ - condoId: String     │     │ - unitId: String      │
    │ - name: String        │     │ - condoId: String     │
    │ - location: String    │     │ - floorNumber: int    │
    │ - totalFloors: int    │     │ - unitNumber: String  │
    │ - totalUnits: int     │     │ - area: double        │
    ├───────────────────────┤     │ - price: double       │
    │ + displayInfo()       │     │ - unitType: UnitType  │
    └───────────────────────┘     │ - status: UnitStatus  │
                                  ├───────────────────────┤
                                  │ + displayInfo()       │
                                  └───────────────────────┘

    ┌───────────────────────┐     ┌───────────────────────┐
    │    Registration       │     │    LotteryResult      │
    ├───────────────────────┤     ├───────────────────────┤
    │ - registrationId      │     │ - resultId: String    │
    │ - applicantId         │     │ - registrationId      │
    │ - unitId              │     │ - applicantId         │
    │ - registrationDate    │     │ - unitId              │
    │ - status              │     │ - drawDate: String    │
    │   RegistrationStatus  │     │ - winner: boolean     │
    ├───────────────────────┤     ├───────────────────────┤
    │ + displayInfo()       │     │ + displayInfo()       │
    └───────────────────────┘     └───────────────────────┘

    ┌────────────────────────────────────────────┐
    │         «interface» Manageable<T>          │
    ├────────────────────────────────────────────┤
    │ + add(T item): void                        │
    │ + displayAll(): void                       │
    │ + searchById(String id): T                 │
    │ + update(T item): void                     │
    └──────────┬─────────────────────────────────┘
               │ implements
    ┌──────────┼──────────────┬─────────────────┐
    │          │              │                 │
    ▼          ▼              ▼                 ▼
Applicant  Condominium     Unit          Registration
Service     Service       Service          Service
```

---

## Enums State Diagram

### Unit Status Lifecycle
```
  AVAILABLE ──(registration created)──► RESERVED ──(lottery drawn)──► SOLD
```

### Registration Status Lifecycle
```
  APPROVED ──(lottery drawn)──┬──► WON
                              └──► LOST
```

---

> **Developed with Java OOP Principles** — Encapsulation, Inheritance, Polymorphism, Abstraction, Interfaces, Generics, Enums, Exception Handling, and File I/O.
#   C o n d o m i n i u m - L o t t e r y - M a n a g e m e n t  
 