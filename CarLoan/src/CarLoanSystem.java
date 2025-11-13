import java.util.*;
public class CarLoanSystem {	

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 🔹 Input car details
        System.out.print("Enter Car Name: ");
        String carName = sc.next();
        
        System.out.print("Base price: ");
        double baseprice = sc.nextDouble();
        
        System.out.print("Enter Car Model (Delta/Beta/Alfa): ");
        String model = sc.next();

        System.out.print("Enter Car Color: ");
        String color = sc.next();

        Car car = new Car(model, color , baseprice, carName);

        // 🔹 Input loan details
        System.out.println("Car Price: ₹" + car.getPrice());
        System.out.print("Enter Down Payment: ");
        double downPayment = sc.nextDouble();

        double loanAmount = car.getPrice() - downPayment;

        System.out.print("Enter Loan Interest Rate (% per annum): ");
        double rate = sc.nextDouble();

        System.out.print("Enter Loan Tenure (3 or 5 years): ");
        int years = sc.nextInt();
        
        sc.close();
        double simpleInterest = LoanCalculator.calculateSimpleInterest(loanAmount, rate, years);
        double compoundInterest = LoanCalculator.calculateCompoundInterest(loanAmount, rate, years);
//        double totalSimple = loanAmount + simpleInterest;
//        double totalCompound = loanAmount + compoundInterest;
//        double emi = LoanCalculator.calculateEMI(loanAmount, rate, years);

        System.out.println("Simple Interest: ₹" + simpleInterest);
        System.out.println("Compound Interest: ₹" + compoundInterest);
//        System.out.println("Total (Simple Interest): ₹" + totalSimple);
//        System.out.println("Total (Compound Interest): ₹" + totalCompound);
//        System.out.println("Monthly EMI: ₹" + String.format("%.2f", emi));
    }
}