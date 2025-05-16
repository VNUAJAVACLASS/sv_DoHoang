package Read_Timetable_Dynamic;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ReadHtmlWithJsoup {
    public static KyHoc parseTKB(String url) throws Exception {
        InputStream inputStream = ReadHtmlWithJsoup.class.getClassLoader().getResourceAsStream(url);
        Document doc = Jsoup.parse(inputStream, String.valueOf(StandardCharsets.UTF_8), "");
        KyHoc tkb = new KyHoc();
        Element table = doc.select("table").first();
        Elements rows = table.select("tr");
        LichHoc lastMonHoc = null;
        for (Element row : rows) {
            Elements cols = row.select("td");
            if (!cols.isEmpty() && !cols.get(0).attr("rowspan").isEmpty()) {
                String maMonHoc = cols.get(0).text();
                String tenMonHoc = cols.get(1).text();
                String nhomTo = cols.get(2).text();
                int soTinChi = Integer.parseInt(cols.get(3).text());
                int soTietHocLT = cols.get(4).text().isEmpty() ? 0 :Integer.parseInt(cols.get(4).text());
                int soTietHocTH = cols.get(5).text().isEmpty() ? 0 : Integer.parseInt(cols.get(5).text());
                String lopHoc = cols.get(6).text();
                int thu = cols.get(8).text().equals("CN") ? 8 : Integer.parseInt(cols.get(8).text());
                int tietBD = Integer.parseInt(cols.get(9).text());
                int soTiet = Integer.parseInt(cols.get(10).text());
                String phong = cols.get(11).text();
                String gv = cols.get(12).text();
                List<Integer> tuan = extractWeeks(cols.get(13).text());
                lastMonHoc = new LichHoc(maMonHoc, tenMonHoc, nhomTo,soTinChi,soTietHocLT,soTietHocTH,lopHoc);
                addMonHoc(tkb, lastMonHoc, thu, tietBD, soTiet, phong, gv, tuan);
            } else if (!cols.isEmpty()) {
                int thu = cols.get(0).text().equals("CN") ? 8 : Integer.parseInt(cols.get(0).text());
                int tietBD = Integer.parseInt(cols.get(1).text());
                int soTiet = Integer.parseInt(cols.get(2).text());
                String phong = cols.get(3).text();
                String gv = cols.get(4).text();
                List<Integer> tuan = extractWeeks(cols.get(5).text());
                addMonHoc(tkb, lastMonHoc, thu, tietBD, soTiet, phong, gv, tuan);
            }
        }
        return tkb;
    }

    private static void addMonHoc(KyHoc tkb, LichHoc lastMonHoc, int thu, int tietBD, int soTiet, String phong, String gv, List<Integer> tuan) {
        for (Integer s : tuan) {
            LichHoc mh = new LichHoc(lastMonHoc);
            if (tkb.getDsTuan().containsKey(s)){
                Tuan t = tkb.getDsTuan().get(s);
                Thu n = t.getThu(thu);
                if (n == null) {
                    n = new Thu();
                    t.addThu(thu, n);
                }
                mh.setTietBD(tietBD);
                mh.setSoTiet(soTiet);
                mh.setPhong(phong);
                mh.setGv(gv);
                n.themLichHoc(mh);
            }else {
                Tuan t = new Tuan();
                Thu n = new Thu();
                mh.setTietBD(tietBD);
                mh.setSoTiet(soTiet);
                mh.setPhong(phong);
                mh.setGv(gv);
                n.themLichHoc(mh);
                t.addThu(thu,n);
                tkb.addTuan(s,t);
            }
        }
    }

    public static List<Integer> extractWeeks(String text) {
        List<Integer> tuanThu = new ArrayList<>();

        int currentWeek = 1; // Bắt đầu từ tuần 1
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != '-') {  // Nếu không phải là dấu '-'
                tuanThu.add(currentWeek);
            }
            currentWeek++;
        }

        return tuanThu;
    }
}