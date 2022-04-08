import java.util.ArrayList; 
import java.util.HashMap; 

class ParkingLotLevel {
  private int level;
  private String accessSelector;
  private ArrayList<ParkingSpot> reservedSpots;
  private ArrayList<ParkingSpot> spots;

  public ParkingLotLevel (int level, String accessSelector) {
    this.level = level;
    this.accessSelector = accessSelector;
  }

  public ParkingLotLevel (int level,
                          String accessSelector,
                          ArrayList<ParkingSpot> reservedSpots) {
    this.level = level;
    this.accessSelector = accessSelector;
    this.reservedSpots = reservedSpots;
    initializeParkingLevel();
  }

  public void initializeParkingLevel(){
    this.spots = new ArrayList<ParkingSpot>();
    HashMap<Integer, ParkingSpot> reservations = new HashMap<Integer, ParkingSpot>();
    
    for (ParkingSpot spot : this.reservedSpots){
      reservations.put(spot.getParkingSpotNumber(), spot);
    }

    for (int spotIndex = 1; spotIndex <= 5; spotIndex++){
      if (reservations.containsKey(spotIndex)) {
        spots.add(reservations.get(spotIndex));
        continue;
      }
      this.spots.add(new ParkingSpot(spotIndex, this.level));
    }
  }
  
  public int getLevel() { return this.level; }
  
  public String getAccessSelector() { return this.accessSelector; }

  public boolean isOnThisLevel(int level) { return this.level == level; }

  public boolean hasAccess(String accessSelector) { 
    return this.accessSelector.equals(accessSelector); 
  }

  public ArrayList<ParkingSpot> getReservedSpots() { return this.reservedSpots; }

  public ArrayList<ParkingSpot> getSpots() { return this.spots; }

  public boolean checkAccomodation(Car incomingCar){
    for (ParkingSpot spot : this.spots){
      if (spot.getReservation() != null)
        if (!spot.getReservation().equals(incomingCar.getLicensePlate()))
          continue;
      if (spot.isOccupied())
        continue;
      spot.occupy(incomingCar);
      return true;
    }
    return false;
  }
  
}