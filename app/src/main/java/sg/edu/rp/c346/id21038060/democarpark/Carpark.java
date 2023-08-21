package sg.edu.rp.c346.id21038060.democarpark;

public class Carpark {
    private String carparkNo;
    private String lotsAvailable;

    public Carpark(String carparkNo, String lotsAvailable) {
        this.carparkNo = carparkNo;
        this.lotsAvailable = lotsAvailable;
    }

    public String getCarparkNo() {
        return carparkNo;
    }

    public void setCarparkNo(String carparkNo) {
        this.carparkNo = carparkNo;
    }

    public String getLotsAvailable() {
        return lotsAvailable;
    }

    public void setLotsAvailable(String lotsAvailable) {
        this.lotsAvailable = lotsAvailable;
    }

    @Override
    public String toString() {
        return "Carpark No: " + carparkNo + "\n" +
                "Lots Available: " + lotsAvailable;
    }
}
