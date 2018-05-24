package com.artwell.sampleordersheet.service;

import java.util.List;
import java.util.Map;

import com.artwell.sampleordersheet.entity.EtStyleSize;
import com.artwell.sampleordersheet.entity.SeZu;
import com.artwell.sampleordersheet.entity.Style;

public interface StyleService {
    String NAME = "sampleordersheet_StyleService";
    public List<Style> getStyleList();
    public Style getStyleById(Integer styleId);
    public List<SeZu> getSeZuListById(Integer styleId);
    public List<Map<String,Object>> getPaiSeYongLiaoById(Integer styleId);
    public List<EtStyleSize> getEtStyleSizeById(Integer styleId);
    
    public void upStyle(Style style);
}