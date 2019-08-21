package edu.zut.mapper;

import edu.zut.entity.Ingre;

import java.util.List;

public interface IngreMapper {

    public List<Ingre> getAll();

    public Ingre findById(Integer id);

    public void update(Ingre ingre);

    public void delete(Integer id);

    public void insert(Ingre ingre);

    public int getNum();
}