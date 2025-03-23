import random
from collections import defaultdict

DAYS = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
SHIFTS = ["morning", "afternoon", "evening"]

# Step 1: Get employee input 
def get_employee_preferences():
    num_employees = int(input("Enter number of employees: "))
    preferences = defaultdict(dict)

    print("\nShift Options:")
    for i, shift in enumerate(SHIFTS, start=1):
        print(f"{i}. {shift.capitalize()}")

    for _ in range(num_employees):
        name = input("\nEnter employee name: ").strip()
        for day in DAYS:
            while True:
                try:
                    choice = int(input(f"{name}'s preferred shift on {day} (1-Morning, 2-Afternoon, 3-Evening): "))
                    if 1 <= choice <= 3:
                        preferences[name][day] = SHIFTS[choice - 1]
                        break
                    else:
                        print("Please enter a number between 1 and 3.")
                except ValueError:
                    print("Invalid input. Please enter a number.")
    
    return preferences

# Step 2: Schedule initialization
def initialize_schedule():
    return {day: {shift: [] for shift in SHIFTS} for day in DAYS}

# Step 3: Assign shifts based on preferences
def assign_shifts(preferences):
    schedule = initialize_schedule()
    workdays = {employee: 0 for employee in preferences}

    # First pass: assign based on preference
    for employee, prefs in preferences.items():
        for day, preferred_shift in prefs.items():
            if workdays[employee] >= 5:
                continue
            if len(schedule[day][preferred_shift]) < 2:
                schedule[day][preferred_shift].append(employee)
                workdays[employee] += 1

    # Second pass: fill in under-staffed shifts
    for day in DAYS:
        for shift in SHIFTS:
            while len(schedule[day][shift]) < 2:
                available = [
                    e for e in preferences 
                    if workdays[e] < 5 and not any(e in schedule[day][s] for s in SHIFTS)
                ]
                if not available:
                    break
                chosen = random.choice(available)
                schedule[day][shift].append(chosen)
                workdays[chosen] += 1

    return schedule

# Step 4: Output the schedule
def print_schedule(schedule):
    print("\n📅 Final Weekly Schedule:")
    for day in DAYS:
        print(f"\n{day}:")
        for shift in SHIFTS:
            employees = schedule[day][shift]
            print(f"  {shift.capitalize()}: {', '.join(employees) if employees else 'Unassigned'}")

# Main program
def main():
    print("Welcome to the Employee Shift Scheduler!")
    preferences = get_employee_preferences()
    schedule = assign_shifts(preferences)
    print_schedule(schedule)

if __name__ == "__main__":
    main()
