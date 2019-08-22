package edu.zut.service;

import edu.zut.entity.Ingre;

import java.util.List;

public interface IngreService {
    /**
     * 获得所有食材
     * @return
     */
    public List<Ingre> getAll();
    /**
     * 根据id返回食材记录
     * @param id
     * @return
     */
    public Ingre findById(Integer id);

    /**
     * 根据id修改食材记录(剩余量)
     * @param ingre 封装的食材记录
     */
    public void edit(Ingre ingre);

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(Integer id);

    /**
     * 添加新的食材
     * @param ingre
     */
    public void add(Ingre ingre);

    /**
     * 获得记录条数
     * @return
     */
    public int getNum();
}
