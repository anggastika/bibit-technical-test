package bukalapak.data;

public class SaldoBukalapakData {
    private static int saldoBukaDompet;

    public static void setSaldoBukaDompet(int saldoBukaDompet) {
        SaldoBukalapakData.saldoBukaDompet = saldoBukaDompet;
    }

    public static Integer getSaldoBukadompet() {
        return saldoBukaDompet;
    }
}
