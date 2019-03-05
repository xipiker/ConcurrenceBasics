package pers.xipiker.v23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 加锁list和没加锁list
 */
public class SynChronizedList {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        List<String> strSync = Collections.synchronizedList(strs); //加锁list
    }
}
