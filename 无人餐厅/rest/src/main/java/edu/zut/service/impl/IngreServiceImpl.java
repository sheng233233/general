package edu.zut.service.impl;

import edu.zut.entity.Ingre;
import edu.zut.mapper.IngreMapper;
import edu.zut.service.IngreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngreServiceImpl implements IngreService {


    @Autowired
    IngreMapper im;


    @Override
    public List<Ingre> getAll() {
        return im.getAll();
    }

    @Override
    public Ingre findById(Integer id) {
        return im.findById(id);
    }

    @Override
    public void edit(Ingre ingre) {
        im.update(ingre);
    }

    @Override
    public void deleteById(Integer id) {
        im.delete(id);
    }

    @Override
    public void add(Ingre ingre) {
        im.insert(ingre);
    }

    @Override
    public int getNum() {
        return im.getNum();
    }


}
