package Read_Timetable_Dynamic;

import java.util.HashMap;
import java.util.Map;

public class Tuan {
    private Map<Integer,Thu> dsThu = new HashMap<>();

    public void addThu(Integer id, Thu thu) {
        dsThu.put(id,thu);
    }

    public Map<Integer, Thu> getDsThu() {
		return dsThu;
	}

    public Thu getThu(Integer id) {
        return dsThu.get(id);
    }
}