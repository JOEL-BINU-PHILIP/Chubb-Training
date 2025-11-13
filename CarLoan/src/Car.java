class Car {
	private String car;
    private String model;
    private String color;
    private double price;
    private double baseprice;

    // Constructor
    public Car(String model, String color , double baseprice , String car) {
    	this.car = car;
        this.model = model;
        this.color = color;
        this.baseprice = baseprice;
        this.price = getPriceByModel(model , baseprice);
    }

    // Return price based on model
    private double getPriceByModel(String model, double baseprice) {
        switch (model.toLowerCase()) {
            case "delta":
                return baseprice + baseprice*0.5;
            case "beta":
                return baseprice + baseprice*0.8;
            case "alfa":
                return baseprice + baseprice*1.2;
            default:
                System.out.println("Invalid model!");
                return 0;
        }
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }
    public String getCar() {
    	return car;
    }
    public double getBasePrice() {
    	return baseprice;
    }
}