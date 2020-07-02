
package proje;


public class DataEntryOperator {
    private int ID;
    private String adSoyad;
    private String Departman;

    public DataEntryOperator() {
        this.ID = 0;
        this.Departman = null;
        this.adSoyad = null;
        
    }

    public DataEntryOperator(int ID, String adSoyad, String Departman) {
        this.ID = ID;
        this.adSoyad = adSoyad;
        this.Departman = Departman;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getDepartman() {
        return Departman;
    }

    public void setDepartman(String Departman) {
        this.Departman = Departman;
    }

    
    @Override
    public String toString() {
        return " ID :"+ID+"\n Ad ve Soyad :"+adSoyad+"\n Departman :"+Departman;
    
    }
    
}

