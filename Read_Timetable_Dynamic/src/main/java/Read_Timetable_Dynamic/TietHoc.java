package Read_Timetable_Dynamic;

public enum TietHoc {
    TIET_1(1, "07:00", "07:50"),
    TIET_2(2, "07:55", "08:45"),
    TIET_3(3, "08:50", "09:40"),
    TIET_4(4, "09:55", "10:45"),
    TIET_5(5, "10:50", "11:40"),
    TIET_6(6, "12:45", "13:35"),
    TIET_7(7, "13:40", "14:30"),
    TIET_8(8, "14:35", "15:25"),
    TIET_9(9, "15:40", "16:30"),
    TIET_10(10, "16:35", "17:25"),
    TIET_11(11, "18:00", "18:50"),
    TIET_12(12, "18:55", "19:45"),
    TIET_13(13, "19:50", "20:40");

    private final int soTiet;
    private final String gioBatDau;
    private final String gioKetThuc;

    TietHoc(int soTiet, String gioBatDau, String gioKetThuc) {
        this.soTiet = soTiet;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public String getGioKetThuc() {
        return gioKetThuc;
    }

}