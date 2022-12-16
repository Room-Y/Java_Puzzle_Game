package cmrUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author: Java_cmr
 * @Date: 2022/12/15 - 19:58
 */
public class CodeUtil {
    public static String getCode(){
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            list.add((char)('a' + i));
            list.add((char)('A' + i));
        }

        String ret = "";
        Random r = new Random();

        //随机数字出现的位置
        int tag = r.nextInt(5);

        for(int i = 0; i < 5; i++){
            if(i == tag){
                ret += r.nextInt(10);
            }else{
                ret += list.get(r.nextInt(list.size()));
            }
        }

        return ret;
    }
}
