class ParkingSpot {
  private int parkingLevel;
  private int parkingSpotNumber;
  private String reservedLicensePlate;
  private boolean occupied;
  private String occupiedBy;
  private Car car;

  public ParkingSpot(int parkingSpotNumber, int parkingLevel){
    this.parkingSpotNumber = parkingSpotNumber;
    this.parkingLevel = parkingLevel;
    this.occupied = false;
  }

  public void setReservation(String licensePlate) { this.reservedLicensePlate = licensePlate; }

  public String getReservation() { return this.reservedLicensePlate; }
  
  public int getParkingLevel() { return this.parkingLevel; }
  
  public int getParkingSpotNumber() { return this.parkingSpotNumber; }

  public boolean isOccupied() { return this.occupied; }

  public void occupy(Car car) { 
    this.occupied = true;
    this.car = car;
    this.occupiedBy = car.getLicensePlate();
  }

  public void release() {
    this.occupied = false;
    this.car = null;
    this.occupiedBy = null;
  }

  public String ToString() {
    return String.format(
      "Parking Spot [ Level: %d, Spot: %d, ReservedTo: %s, \tOccupied: %b, \tOccupiedBy: %s, \tCar: %s ]",
      this.parkingLevel, this.parkingSpotNumber, this.reservedLicensePlate,
      this.occupied, this.occupiedBy, this.car
    );
  }
  
}