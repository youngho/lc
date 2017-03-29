package kr.co.famos.not.control.main;

/**
 * <code>BetsSplit.java</code>
 * 
 * @company : FAMOS
 * @Description : BETS 에서 넘어온 데이터 Key, value 분리
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.02
 * @version : 1.0
 */

public class BetsSplit {

    public void Parse_Data(String allData) {

        String[] values = allData.split("\n");
        for (int x = 1; x < (values.length - 1); x++) {

            String StringNext = values[x].replaceAll("</[A-Za-z_0-9]*>", "").replaceAll(">", "=").replaceAll("<", "").replaceAll("=$", "=-");
            
            String[] info = StringNext.split("=");
            
            if (MainDual.main_radio_st1.isSelected()) {
                MainDual.info_map_st1.put(info[0], info[1]);
            } else {
                MainDual.info_map_st2.put(info[0], info[1]);
            }
        }
    }
}
