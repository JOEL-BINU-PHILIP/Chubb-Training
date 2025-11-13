import java.util.Scanner;
public class HotelManaged {
	

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String branch = safeStringInput(sc, "Enter the hotel branch name: ");
        String city = safeStringInput(sc, "Enter the city: ");
        String customer = safeStringInput(sc, "Enter customer name: ");

        int roomNo = safeIntInput(sc, "Enter room number: ");
        String roomType = safeRoomType(sc, "Enter room type (Standard/Deluxe/Suite): ");
        int nights = safeIntInput(sc, "Enter number of nights: ");

        Hotel hotel = new Hotel(branch, city);
        Booking booking = new Booking(hotel, customer, roomNo, roomType, nights);

        booking.displayBill();
    }


    private static String safeStringInput(Scanner sc, String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (!input.isEmpty() && input.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("⚠️ Invalid input! Please enter letters only.");
            }
        }
        return input;
    }

    private static int safeIntInput(Scanner sc, String message) {
        int num = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(message);
            try {
                num = Integer.parseInt(sc.nextLine().trim());
                if (num > 0)
                    valid = true;
                else
                    System.out.println("⚠️ Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input! Please enter a valid number.");
            }
        }
        return num;
    }

    private static String safeRoomType(Scanner sc, String message) {
        String type;
        while (true) {
            System.out.print(message);
            type = sc.nextLine().trim().toLowerCase();
            if (type.equals("standard") || type.equals("deluxe") || type.equals("suite")) {
                break;
            } else {
                System.out.println("⚠️ Invalid room type! Choose from Standard, Deluxe, or Suite.");
            }
        }
        return type.substring(0, 1).toUpperCase() + type.substring(1);
    }
}
