
import java.util.*;
public class EmployeeDirectory {

    public static void main(String[] args) {

        // 1. Create map where values are a List of Employee OBJECTS
        Map<String, List<Employee>> companyMap = new HashMap<>();

        // 2. Make entry for 5 companies
        companyMap.put("Google", Arrays.asList(
            new Employee(101, "Sundar", "CEO"),
            new Employee(102, "Jeff", "AI"),
            new Employee(103, "Susan", "YouTube")
        ));

        companyMap.put("Microsoft", Arrays.asList(
            new Employee(201, "Satya", "CEO"),
            new Employee(202, "Brad", "President"),
            new Employee(203, "Phil", "Xbox")
        ));
        
        companyMap.put("Apple", Arrays.asList(
            new Employee(301, "Tim", "CEO"),
            new Employee(302, "Jony", "Design")
        ));
        
        companyMap.put("Amazon", Arrays.asList(
            new Employee(401, "Andy", "CEO"),
            new Employee(402, "Werner", "CTO")
        ));
        
        companyMap.put("Tesla", Arrays.asList(
            new Employee(501, "Elon", "CEO"),
            new Employee(502, "Robyn", "Chair")
        ));

        System.out.println("--- Initial Company Map ---");
        companyMap.forEach((company, employees) -> {
            System.out.println(company + " ==> " + employees);
        });
        System.out.println("---------------------------\n");


        System.out.println("--- Scenario 1: Checking for 'Cognizant' and 'Ram' ---");
        findEmployeeByName(companyMap, "Cognizant", "Ram");
        
        System.out.println("\n--- Map After Scenario 1 ---");
        companyMap.forEach((company, employees) -> {
            System.out.println(company + " ==> " + employees);
        });
        System.out.println("----------------------------\n");
\
        System.out.println("--- Scenario 2: Checking for 'Apple' and 'Ram' ---");
        findEmployeeByName(companyMap, "Apple", "Ram");
        
        System.out.println("\n--- Scenario 3: Company and Employee both exist ---");
        findEmployeeByName(companyMap, "Microsoft", "Satya");

    }

    public static void findEmployeeByName(Map<String, List<Employee>> map, String companyName, String empName) {
        
        // 4. Find if the company exists
        if (map.containsKey(companyName)) {
            System.out.println("Found company: " + companyName);
            List<Employee> employees = map.get(companyName);

            Optional<Employee> foundEmployee = employees.stream()
                .filter(emp -> emp.getName().equalsIgnoreCase(empName))
                .findFirst();

            if (foundEmployee.isPresent()) {
                System.out.println("SUCCESS: " + empName + " is working in " + companyName);
                System.out.println("   Details: " + foundEmployee.get()); 
            } else {
                System.out.println("INFO: " + empName + " is NOT found in " + companyName);
            }
            
        } else {
            System.out.println("INFO: Company '" + companyName + "' not found.");
            
            String newCompany = "Cgnizant";
            List<Employee> newEmpList = Arrays.asList(
                new Employee(601, "Mahesh", "HR"),
                new Employee(602, "Priya", "Java")
            );
            
            map.put(newCompany, newEmpList);
            System.out.println("ACTION: Added new company '" + newCompany + "' with employees: " + newEmpList);
        }
    }
}
