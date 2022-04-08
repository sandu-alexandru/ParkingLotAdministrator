class Car {
  private String licensePlate;
  private String accessSelector;
  private ParkingSpot reservedParkingSpot;
  
  
  public Car(String licensePlate, String accessSelector) {
    this.licensePlate = licensePlate;
    this.accessSelector = accessSelector;
  }
  
  public Car(String licensePlate, String accessSelector, ParkingSpot reservedParkingSpot) {
    // this.licensePlate = licensePlate;
    // this.accessSelector = accessSelector;
    this(licensePlate, accessSelector);
    this.reservedParkingSpot = reservedParkingSpot;
  }

  public String getLicensePlate() { return this.licensePlate; }
  
  public String getAccessSelector() { return this.accessSelector; }
  
  public ParkingSpot getReservedParkingSpot() { return this.reservedParkingSpot; }

}