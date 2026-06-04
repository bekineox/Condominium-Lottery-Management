# UML Diagram & Report Guide

## 📊 UML Class Diagram Files

### File: `UML_CLASS_DIAGRAM.puml`
**Format:** PlantUML source code

PlantUML is a simple syntax for creating diagrams from text. You have multiple options to view/render it:

### Option 1: Online Renderer (Easiest - No Installation)
1. Go to: https://www.plantuml.com/plantuml/uml/
2. Copy entire contents of `UML_CLASS_DIAGRAM.puml`
3. Paste into the text area
4. View or export as PNG/PDF

### Option 2: VS Code Extension
1. Install PlantUML extension: `jebbs.plantuml`
2. Open `UML_CLASS_DIAGRAM.puml` in VS Code
3. Press `Alt+D` to preview
4. Right-click → Export to PNG

### Option 3: Command Line (Requires Installation)
```bash
# Install PlantUML (requires Java)
# macOS: brew install plantuml
# Linux: sudo apt-get install plantuml
# Windows: Download from http://plantuml.com

# Render to PNG
plantuml UML_CLASS_DIAGRAM.puml

# Output: UML_CLASS_DIAGRAM.png
```

### Option 4: IDE Integration
- **IntelliJ IDEA:** Install PlantUML plugin from marketplace
- **Eclipse:** Install PlantUML Eclipse plugin
- **NetBeans:** Available through plugin manager

---

## 📄 Project Report

### File: `PROJECT_REPORT.txt`
**Format:** Plain text (readable in any editor)
**Length:** ~3 pages
**Contains:**
- Project title & description
- System architecture diagram
- Complete class descriptions with roles
- OOP concepts applied (8 main concepts)
- Data persistence format
- Sample workflow with actual output
- Conclusion

**To View:**
- Open in any text editor (Notepad, VS Code, IDE, etc.)
- Or read in terminal: `cat PROJECT_REPORT.txt` (Linux/Mac) or `type PROJECT_REPORT.txt` (Windows)

---

## 📋 UML Diagram Structure

The PlantUML diagram includes:

### Enums (Yellow boxes)
```
┌──────────────────────┐
│ UnitType     <<enum>>│
├──────────────────────┤
│ STUDIO               │
│ ONE_BEDROOM          │
│ TWO_BEDROOM          │
│ THREE_BEDROOM        │
└──────────────────────┘
```
- **UnitType** — apartment size constant
- **RegistrationStatus** — workflow state (PENDING, APPROVED, WON, LOST)

### Exceptions (Red/Pink boxes)
```
└── RegistrationException (checked exception)
```

### Base Class (Gray box)
```
┌──────────────────────────┐
│ Person                   │
├──────────────────────────┤
│ - id: String             │
│ - fullName: String       │
│ - phone: String          │
├──────────────────────────┤
│ + displayInfo(): String  │
└──────────────────────────┘
```

### Models (Gray boxes)
```
Applicant (extends Person)
├── email, address, registrationDate
├── displayInfo() → applicant format
└── Inherits: id, fullName, phone

Admin (extends Person)
├── role, department
├── displayInfo() → admin format
└── Inherits: id, fullName, phone

Registration
├── registrationId, applicantId, unitType
├── status: RegistrationStatus (enum)
└── lifecycle: PENDING → WON/LOST
```

### Services (Green boxes)
```
Manageable<T> (Interface)
├── add(T)
├── displayAll()
├── searchById(id)
├── update(T)
└── delete(id)

Implementations:
├── ApplicantService implements Manageable<Applicant>
└── RegistrationService implements Manageable<Registration>

Each service:
├── Private list of entities
├── loadFromFile()
├── saveToFile()
└── File persistence
```

### Main Application (Blue box)
```
Main
├── Static services
├── Scanner for input
├── displayMainMenu()
├── CRUD sub-menus for applicant/registration
└── conductLotteryDraw()
```

### Relationships
- **→** (extends) — Inheritance
- **-|** (implements) — Interface implementation
- **→** (uses) — Association/Dependency
- **..→** (throws) — Exception throwing

---

## 🔄 Workflow Diagram (Text Format)

```
User starts application (Main.java)
         ↓
    MAIN MENU
    1. Manage Applicants
    2. Manage Registrations
    3. Conduct Lottery Draw
    4. View Lottery Winners
    5. Demonstrate Polymorphism
    6. Exit
         ↓
    User selects option
         ↓
    ┌─────────────────────────────────────────┐
    │         SERVICE LAYER                   │
    │                                          │
    │  ApplicantService                       │
    │  ├── load from data/applicants.txt      │
    │  ├── add/update in memory               │
    │  └── save to data/applicants.txt        │
    │                                          │
    │  RegistrationService                    │
    │  ├── load from data/registrations.txt   │
    │  ├── persistence operations             │
    │  └── save to file                       │
    │                                          │
    └─────────────────────────────────────────┘
         ↓
    Data persisted to disk
         ↓
    Results shown to user
         ↓
    Return to MAIN MENU
         ↓
    Repeat until user selects Exit (6)
```

---

## 📊 Data Flow Example: Adding an Applicant

```
User Input (Main.java)
    ↓
    Name? Address? Email?
    ↓
Create Applicant object
    ↓
    ApplicantService.add(applicant)
    ↓
    1. Validate (check duplicate ID)
    2. Load existing data from file
    3. Add to in-memory list
    4. Save all to file
    ↓
File updated: data/applicants.txt
    ↓
    Success message to user
    ↓
    Ready for next operation
```

---

## 🎯 Key Design Patterns Used

### 1. **Layered Architecture**
- Presentation (Main) → Service → Model → Data
- Clear separation of concerns

### 2. **Service Pattern**
- Each service manages one entity type
- Encapsulates business logic

### 3. **Template Method** (in Manageable interface)
- All services follow CRUD pattern
- Consistent interface across different types

### 4. **Polymorphism Pattern**
- Person → Applicant, Admin
- List<Person> can hold either type
- displayInfo() method dispatches to correct implementation

### 5. **Repository Pattern**
- Services act as repositories
- Load/save from file
- Query and filtering methods

---

## 📈 Relationship Summary

```
INHERITANCE HIERARCHY:
    Person
    ├── Applicant
    └── Admin

INTERFACE IMPLEMENTATIONS:
    Manageable<T> (interface)
    ├── ApplicantService
    └── RegistrationService

ENUM USAGE:
    UnitType ← used by Registration.unitType
    RegistrationStatus ← used by Registration.status

DEPENDENCIES:
    Main depends on services
    Services depend on models
    Models depend on enums and exceptions
```

---

## ✅ Checklist for Report Submission

- [x] **Project Title** — Condominium Lottery Registration System
- [x] **Description** — System architecture and purpose
- [x] **Classes & Roles** — All 11 classes documented
- [x] **OOP Concepts** — 8 main concepts explained with examples
- [x] **UML Diagram** — Complete class diagram in PlantUML format
- [x] **Sample Outputs** — Full workflow walkthrough with actual output
- [x] **Data Persistence** — File formats and examples
- [x] **Conclusion** — Summary of achievements

---

## 🎓 Learning Outcomes Demonstrated

By studying this project, students learn:

1. **Abstraction** — Interfaces
2. **Encapsulation** — Data hiding and controlled access
3. **Inheritance** — Code reuse through class hierarchies
4. **Polymorphism** — Runtime method dispatch
5. **Generics** — Type-safe parameterized types
6. **Interfaces** — Contract-based design
7. **Enums** — Type-safe constants
8. **Exceptions** — Structured error handling
9. **File I/O** — Persistence and serialization
10. **Design Patterns** — Layered architecture, services, repositories

---

## 📞 Document Quick Reference

| Document | Purpose | Read Time |
|----------|---------|-----------|
| `PROJECT_REPORT.txt` | Complete analysis & sample output | 15-20 min |
| `QUICK_REFERENCE.txt` | Fast overview and compilation guide | 5 min |
| `UML_CLASS_DIAGRAM.puml` | Visual class structure | 10 min |
| `UML_AND_REPORT_GUIDE.md` | UML viewing instructions | 5 min |

---

**All documents are in the project root directory.**
**Start with: PROJECT_REPORT.txt + UML_CLASS_DIAGRAM.puml**
