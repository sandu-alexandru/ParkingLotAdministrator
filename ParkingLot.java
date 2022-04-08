import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class ParkingLot {

  private String parkingLotConfigPath = "parkingLotInput.ini";
  private ArrayList<ParkingLotLevel> parkingLevels;
  private IniFile parkingLotConfig;
  
  public ParkingLot(ArrayList<ParkingLotLevel> parkingLevels) {
    this.parkingLevels = parkingLevels;
    try{
      this.parkingLotConfig = new IniFile(this.parkingLotConfigPath);
    } catch (IOException e) {
      this.parkingLotConfig = null;
    }
  }
  
  public ParkingLot() {
    try{
      this.parkingLotConfig = new IniFile(this.parkingLotConfigPath);
    } catch (IOException e) {
      this.parkingLotConfig = null;
    }
  }

  public ArrayList<ParkingLotLevel> getLevels() { return this.parkingLevels; }

  public ArrayList<ParkingSpot> getReservedSpots() {
    int spotIndex = 1;
    ArrayList<ParkingSpot> reservedSpots = new ArrayList<ParkingSpot>();
    
    while(spotIndex != -1) {
      String reserved = this.parkingLotConfig.getString(
        "ReservedParkingSpaces",
        String.format("reservedParkingSpot%d", spotIndex),
        "");
      //System.out.println(reserved);
      if(reserved == null || reserved.isEmpty()) break;

      String[] spotInfo = reserved.split(",");
      int spotLevel = Integer.parseInt(spotInfo[0]);
      int spotNumber = Integer.parseInt(spotInfo[1]);
      String spotReservedlicensePlate = spotInfo[2];
      ParkingSpot spot = new ParkingSpot(spotNumber, spotLevel);
      spot.setReservation(spotReservedlicensePlate);
      reservedSpots.add(spot);
      spotIndex++;
    }
    return reservedSpots;
  }
  
  public void populateParkingLot() {
    this.parkingLevels = new ArrayList<ParkingLotLevel>();
    //System.out.println(this.parkingLotConfig.getString("ReservedParkingSpaces", "reservedParkingSpot1", ""));
    ArrayList<ParkingSpot> reservedSpots = getReservedSpots();
    
    int level = 0;
    while(level != -1){
      String levelAccessSelector = this.parkingLotConfig.getString("ParkingLot", String.format("level%d", level), "");
      //System.out.println(levelAccessSelector);
      
      if(levelAccessSelector == null || levelAccessSelector.isEmpty()) break;
      
      ArrayList<ParkingSpot> reservedSpotsForLevel = new ArrayList<ParkingSpot>();
      
      for (ParkingSpot reservedSpot : reservedSpots) {
        if(reservedSpot.getParkingLevel() == level) reservedSpotsForLevel.add(reservedSpot);
      }

      this.parkingLevels.add(new ParkingLotLevel(level, levelAccessSelector, reservedSpotsForLevel));
      level++;
    }
    
  }

  public void accomodate(Car incomingCar){
    for (ParkingLotLevel level : this.parkingLevels){
      if (!incomingCar.getAccessSelector().contains(level.getAccessSelector()))
        continue;
      boolean successfulAccomodation = level.checkAccomodation(incomingCar);
      if (successfulAccomodation)
        break;
    }
  }
  
}