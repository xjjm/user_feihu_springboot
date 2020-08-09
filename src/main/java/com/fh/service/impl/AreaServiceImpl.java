package com.fh.service.impl;

import com.fh.mapper.AreaMapper;
import com.fh.model.Area;
import com.fh.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;


    @Override
    public List<Area> selectArea() {
        return areaMapper.selectArea();
    }
}
