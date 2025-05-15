package QLLH;


import java.util.*;

public class Tuan {
    private int soTuan;
    private HashMap<Integer, List<LichHoc>> dsThu;

    public Tuan(int soTuan) {
        this.soTuan = soTuan;
        this.dsThu = new HashMap<>();
    }

    public void themLichHoc(int thu, LichHoc lichHoc) {
        dsThu.computeIfAbsent(thu, k -> new ArrayList<>()).add(lichHoc);
    }

    public List<LichHoc> getDsLichHoc(int thu) {
        return dsThu.getOrDefault(thu, new ArrayList<>());
    }

    public int getSoTuan() {
        return soTuan;
    }
}