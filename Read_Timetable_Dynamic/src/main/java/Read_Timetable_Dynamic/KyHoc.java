package Read_Timetable_Dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KyHoc {
	private Map<Integer, Tuan> dsTuan = new HashMap<>();

	public KyHoc() {
		
	}
	
    public void addTuan(Integer tuanThu,Tuan tuan) {
        dsTuan.put(tuanThu, tuan);
    }
	
	public Map<Integer, Tuan> getDsTuan() {
		return dsTuan;
	}
	
	private Tuan getTuan(Integer tuanthu) {
		// TODO Auto-generated method stub
			return dsTuan.get(tuanthu);
	}
}