package com.artwell.sampleordersheet.web.style;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import com.artwell.sampleordersheet.entity.Style;
import com.artwell.sampleordersheet.service.StyleService;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.GroupDatasource;

public class StyleBrowse extends AbstractLookup {
	@Inject
	GroupDatasource<Style, UUID> stylesDs;
	@Inject
	StyleService styleService;
	@Inject
	GroupTable<Style> stylesTable;
	@Override
	public void init(Map<String, Object> params) {
		super.init(params);
		List<Style> styleList = styleService.getStyleList();
		for (Style style : styleList) {
			stylesDs.includeItem(style);
		}
		stylesDs.refresh();
		stylesTable.setItemClickAction(new BaseAction("itemClickAction") {
		    @Override
		    public void actionPerform(Component component) {
		    	Style selected = stylesTable.getSingleSelected();
		        if (selected != null) {
		        	Map<String, Object> param=new HashMap<>();
		        	param.put("styleId", selected.getSID());
		        	Window window =openWindow("sampleOrder",  WindowManager.OpenType.NEW_TAB, param);
		        	
		        }
		    }
		});
	}
}