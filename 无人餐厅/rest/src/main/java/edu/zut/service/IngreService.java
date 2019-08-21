package edu.zut.service;

import edu.zut.entity.Ingre;

import java.util.List;

public interface IngreService {
    public List<Ingre> getAll();

    public Ingre findById(Integer id);

    public void edit(Ingre ingre);

    public void deleteById(Integer id);

    public void add(Ingre ingre);

    public int getNum();
}
