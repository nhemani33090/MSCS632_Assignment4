import java.util.*;

public class ShiftScheduler {

    static final String[] DAYS = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    static final String[] SHIFTS = {"morning", "afternoon", "evening"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, String>> preferences = new HashMap<>();
        Map<String, Integer> workdays = new HashMap<>();

        System.out.println("Welcome to the Employee Shift Scheduler!");

        // Get number of employees
        System.out.print("Enter number of employees: ");
        int numEmployees = Integer.parseInt(scanner.nextLine());

        System.out.println("\nShift Options:");
        for (int i = 0; i < SHIFTS.length; i++) {
            System.out.printf("%d. %s%n", i + 1, capitalize(SHIFTS[i]));
        }

        // Collect employee preferences
        for (int i = 0; i < numEmployees; i++) {
            System.out.print("\nEnter employee name: ");
            String name = scanner.nextLine().trim();
            Map<String, String> dailyPrefs = new HashMap<>();

            for (String day : DAYS) {
                int shiftNum;
                while (true) {
                    System.out.printf("%s's preferred shift on %s (1-Morning, 2-Afternoon, 3-Evening): ", name, day);
                    try {
                        shiftNum = Integer.parseInt(scanner.nextLine());
                        if (shiftNum >= 1 && shiftNum <= 3) {
                            break;
                        }
                        System.out.println("Please enter a number between 1 and 3.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                }
                dailyPrefs.put(day, SHIFTS[shiftNum - 1]);
            }
            preferences.put(name, dailyPrefs);
            workdays.put(name, 0);
        }

        // Initialize empty schedule
        Map<String, Map<String, List<String>>> schedule = new LinkedHashMap<>();
        for (String day : DAYS) {
            Map<String, List<String>> shiftMap = new HashMap<>();
            for (String shift : SHIFTS) {
                shiftMap.put(shift, new ArrayList<>());
            }
            schedule.put(day, shiftMap);
        }

        // First pass: Assign based on preferences
        for (Map.Entry<String, Map<String, String>> entry : preferences.entrySet()) {
            String name = entry.getKey();
            Map<String, String> dailyPrefs = entry.getValue();

            for (String day : DAYS) {
                if (workdays.get(name) >= 5) break;
                String preferredShift = dailyPrefs.get(day);
                List<String> shiftList = schedule.get(day).get(preferredShift);

                if (shiftList.size() < 2 && !shiftList.contains(name)) {
                    shiftList.add(name);
                    workdays.put(name, workdays.get(name) + 1);
                }
            }
        }

        // Second pass: Fill under-staffed shifts
        Random random = new Random();
        for (String day : DAYS) {
            for (String shift : SHIFTS) {
                List<String> shiftList = schedule.get(day).get(shift);

                while (shiftList.size() < 2) {
                    List<String> available = new ArrayList<>();
                    for (String name : preferences.keySet()) {
                        if (workdays.get(name) < 5) {
                            boolean alreadyScheduled = false;
                            for (String s : SHIFTS) {
                                if (schedule.get(day).get(s).contains(name)) {
                                    alreadyScheduled = true;
                                    break;
                                }
                            }
                            if (!alreadyScheduled) {
                                available.add(name);
                            }
                        }
                    }
                    if (available.isEmpty()) break;
                    String chosen = available.get(random.nextInt(available.size()));
                    shiftList.add(chosen);
                    workdays.put(chosen, workdays.get(chosen) + 1);
                }
            }
        }

        // Output final schedule
        System.out.println("\n📅 Final Weekly Schedule:");
        for (String day : DAYS) {
            System.out.println("\n" + day + ":");
            for (String shift : SHIFTS) {
                List<String> employees = schedule.get(day).get(shift);
                System.out.println("  " + capitalize(shift) + ": " + 
                    (employees.isEmpty() ? "Unassigned" : String.join(", ", employees)));
            }
        }

        scanner.close();
    }

    private static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
