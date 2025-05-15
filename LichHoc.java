package QLLH;


public class LichHoc {
    private String maMH, tenMH, nhomTo, soTinChi, lop, phong, giangVien;
    private int thu, tietBatDau, soTiet;

    public LichHoc(String maMH, String tenMH, String nhomTo, String soTinChi, String lop,
                   int thu, int tietBatDau, int soTiet, String phong, String giangVien) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.nhomTo = nhomTo;
        this.soTinChi = soTinChi;
        this.lop = lop;
        this.setThu(thu);
        this.tietBatDau = tietBatDau;
        this.soTiet = soTiet;
        this.phong = phong;
        this.giangVien = giangVien;
    }

    @Override
    public String toString() {
        String gioBD = TietHoc.getGioHoc(tietBatDau).split(" - ")[0];
        String gioKT = TietHoc.getGioHoc(tietBatDau + soTiet - 1).split(" - ")[1];
        return String.format("%-10s | %-30s | Nhóm: %-5s | Tín chỉ: %-2s | Lớp: %-10s | Tiết: %d-%d (%s - %s) | Phòng: %-6s | GV: %-10s",
                maMH, tenMH, nhomTo, soTinChi, lop, tietBatDau, tietBatDau + soTiet - 1, gioBD, gioKT, phong, giangVien);
    }

	public int getThu() {
		return thu;
	}

	public void setThu(int thu) {
		this.thu = thu;
	}
}
