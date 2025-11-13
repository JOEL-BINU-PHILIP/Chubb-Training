class Booking {
    private Hotel hotel;
    private String customerName;
    private int roomNumber;
    private String roomType;
    private int nights;
    private double ratePerNight;
    private double totalAmount;

    public Booking(Hotel hotel, String customerName, int roomNumber, String roomType, int nights) {
        this.hotel = hotel;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.nights = nights;
        this.ratePerNight = getRate(roomType);
        this.totalAmount = ratePerNight * nights;
    }


	private double getRate(String type) {
        switch (type.toLowerCase()) {
            case "deluxe":
                return 5000;
            case "suite":
                return 8000;
            case "standard":
                return 3000;
            default:
                return 3000;
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void displayBill() {
        System.out.println("Customer Name: " + customerName);
        System.out.println("Hotel Branch:  " + hotel.getBranchName());
        System.out.println("City:          " + hotel.getCity());
        System.out.println("Room Number:   " + roomNumber);
        System.out.println("Room Type:     " + roomType);
        System.out.println("Nights:        " + nights);
        System.out.println("Rate/Night:    ₹" + ratePerNight);
        System.out.println("---------------------------------------");
        System.out.println("Total Amount:  ₹" + totalAmount);

    }
}