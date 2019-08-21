package edu.zut.service.impl;

import edu.zut.service.TableService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TableServiceImpl implements TableService {

    //默认桌位数量
    private static final int DEFAULT_NUM = 50;
    //桌子池,key=桌号   value=是否可用(true可用  false不可用)
    public static Map<Integer, Boolean> tables = new HashMap<>();

    //桌子是否可用  true可用  false使用使用中
    static {
        for (int i = 1; i <= DEFAULT_NUM; i++) {
            tables.put(i, true);
        }
    }

    @Override
    public Map<Integer, Boolean> getAll() {
        return tables;
    }

    public synchronized int distribute() {
        int num = 0;
        for (Integer i : tables.keySet()) {
            if (tables.get(i)) { //可用则分发
                tables.put(i, false);  //置为不可用
                num = i;
                break;
            }
        }
        return num;
    }

    public boolean revoke(Integer num) { //回收
        if (num <= 0 && num > tables.size()) {
            return false;
        }
        tables.put(num, true);  //置为可用
        return true;
    }

    public void setNum(Integer num) {  //设置桌子数量
        int d = num - DEFAULT_NUM;  //差值
        if (d >= 0) {
            for (int i = 1; i <= d; i++) {
                tables.put(DEFAULT_NUM + i, true);
            }
        } else {
            for (int i = 0; i < d * -1; i++) {
                tables.remove(DEFAULT_NUM - i);
            }
        }
    }

}