package edu.zut.service;

import java.util.Map;

public interface TableService {


    /**向管理员展示当前所有桌号,包括可用与不可用
     *
     * @return
     */
    public Map<Integer,Boolean> getAll();


    /**
     * 分配桌号
     * @return 桌号 若为0则说明暂无空位
     */
    public int distribute();

    /**
     * 回收桌号
     * @param num
     * @return
     */
    public boolean revoke(Integer num);


    /**
     * 设置桌子数量
     * @param num 目标数量
     */
    public void setNum(Integer num);



}
