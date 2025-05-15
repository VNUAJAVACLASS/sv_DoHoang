package QLLH;


public enum thu {
    TIET_1(1, "07:00 - 07:50"),
    TIET_2(2, "07:55 - 08:45"),
    TIET_3(3, "08:50 - 09:40"),
    TIET_4(4, "09:45 - 10:35"),
    TIET_5(5, "10:40 - 11:30"),
    TIET_6(6, "12:30 - 13:20"),
    TIET_7(7, "13:25 - 14:15"),
    TIET_8(8, "14:20 - 15:10"),
    TIET_9(9, "15:15 - 16:05"),
    TIET_10(10, "16:10 - 17:00");

    private final int tiet;
    private final String gioHoc;

    thu(int tiet, String gioHoc) {
        this.tiet = tiet;
        this.gioHoc = gioHoc;
    }

    public static String getGioHoc(int tiet) {
        for (thu t : values()) {
            if (t.tiet == tiet) return t.gioHoc;
        }
        return "Không xác định";
    }
}
