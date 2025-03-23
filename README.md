
# 🧑‍💼 Employee Shift Scheduler

This project implements a weekly employee scheduling application in **two programming languages**: Python and Java.

## 📋 Assignment Overview

The goal of this project is to demonstrate mastery of **control structures** — such as conditionals, loops, and branching — in multiple languages by building an application that:

- Collects employee names and shift preferences
- Assigns employees to morning, afternoon, or evening shifts
- Ensures all scheduling constraints are met
- Outputs a clean and readable weekly schedule

## 🚦 Features

- Manual input of employee names and shift preferences
- Employees can work **a maximum of 5 days** per week
- Each employee can work **only one shift per day**
- Each shift requires **at least 2 employees**; if fewer, the system auto-assigns available employees
- Conflict resolution when preferred shifts are full
- Friendly and readable final schedule output
- Runs in both **Python** and **Java**

## 🖥️ Technologies Used

- Java 17 or above (for the Java implementation)
- Python 3.x (for the Python implementation)
- Console-based 

## ▶️ How to Run

### 🐍 Python

```bash
python3 shift_scheduler.py
```

### ☕ Java

**Important:** Make sure your runtime supports Java 17 or higher.

```bash
javac ShiftScheduler.java

# Run
java ShiftScheduler
```

## 📌 Example Output

```
Monday:
  Morning: Alice, Bob  
  Afternoon: Carla, Dave  
  Evening: Grace, Sarah  

Tuesday:
  Morning: Emma, John  
  Afternoon: Alice, Frank  
  Evening: Carla, Dave  
...
Sunday:
  Morning: Bob, Carla  
  Afternoon: Grace, Sarah  
  Evening: Alice, Dave  

```

## ✅ Control Structures Demonstrated

- `if` / `else` conditionals
- `while`, `for` loops
- `break`, `continue`, and branching logic
- HashMaps / dictionaries for structured data
- Input validation and user interaction

## 📂 Project Structure

```
/MSCS632_Assignment4
│
├── ShiftScheduler.java   # Java version
├── shift_scheduler.py     # Python version
└── README.md                # This file
```


