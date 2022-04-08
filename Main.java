class Main {
  public static void main(String[] args) {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.populateParkingLot();
    
    showParkingLotInfo(parkingLot);

    Car incomingCar1 = new Car("B01BOS", "BBBCCC");

    parkingLot.accomodate(incomingCar1);
    
    showParkingLotInfo(parkingLot);

  }

  public static void showParkingLotInfo(ParkingLot parkingLot){
    System.out.println();
    for (int level = 0; level < parkingLot.getLevels().size(); level++)
      for (ParkingSpot spot : parkingLot.getLevels().get(level).getSpots()){
        System.out.println(spot.ToString());
      }
  }
  
}