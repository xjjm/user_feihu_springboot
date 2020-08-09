package com.fh.mapper;

import com.fh.model.Area;

import java.util.List;

public interface AreaMapper {
    List<Area> selectArea();

    String selectAreaNameByAreaId(Integer areaId);
}
