package com.artwell.sampleordersheet.service;

import java.util.List;

import com.artwell.sampleordersheet.entity.SeWei;
import com.artwell.sampleordersheet.entity.YongLiang;

public interface BomService {
    String NAME = "sampleordersheet_BomService";
    public List<SeWei> getSeWeiListById(Integer styleId);
    public List<YongLiang> getYongLiangListById(Integer styleId);
}