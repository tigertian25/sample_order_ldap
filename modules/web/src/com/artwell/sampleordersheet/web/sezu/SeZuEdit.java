package com.artwell.sampleordersheet.web.sezu;

import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import com.artwell.sampleordersheet.entity.SeZu;
import com.artwell.sampleordersheet.entity.Style;
import com.artwell.sampleordersheet.service.StyleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haulmont.cuba.gui.components.Component;

public class SeZuEdit extends AbstractEditor<SeZu> {
	Style style = new Style();
	@Inject
	StyleService styleService;

	private Boolean isEdit = true;

	@Override
	public void init(Map<String, Object> params) {
		Map<String, Object> styleMap = new HashMap<>();
		styleMap.put("ITEM", params.get("style"));
		style = (Style) WindowParams.ITEM.getEntity(styleMap);
		isEdit = Boolean.parseBoolean(params.get("isEdit").toString());
		System.out.println(style.getSID());
		initSeZuTable(style.getSID());
		if (isEdit) {// edit
			System.out.println("enter SeZuEdit!");

		} else {// create

			System.out.println("enter SeZuCreate!");
		}
	}

	@Inject
	Table<SeZu> seZusTable;

	@Inject
	CollectionDatasource<SeZu, UUID> seZusDs;

	List<SeZu> seZuList = new ArrayList<SeZu>();

	private void initSeZuTable(Integer styleId) {
		// 设置ds
		seZuList = styleService.getSeZuListById(styleId);
		if (null != seZuList && seZuList.size() > 0 && null != seZuList.get(0).getXQty1()) {
			for (int i = 0; i < seZuList.size(); i++) {

				seZusDs.addItem(seZuList.get(i));
			}
			seZusTable.setHeight((seZuList.size()  * 36 + 31) + "px");
		}
		seZusDs.addItemPropertyChangeListener(e -> {
			
			showNotification("修改已保存："+e.getProperty()+":"+e.getValue());
		});
		// 移除空的列
		if (null == style.getXSize1() || "".equals(style.getXSize1())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty1"));
		} else {
			seZusTable.getColumn("xQty1").setCaption(style.getXSize1());
		}
		if (null == style.getXSize2() || "".equals(style.getXSize2())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty2"));
		} else {
			seZusTable.getColumn("xQty2").setCaption(style.getXSize2());
		}
		if (null == style.getXSize3() || "".equals(style.getXSize3())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty3"));
		} else {
			seZusTable.getColumn("xQty3").setCaption(style.getXSize3());
		}
		if (null == style.getXSize4() || "".equals(style.getXSize4())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty4"));
		} else {
			seZusTable.getColumn("xQty4").setCaption(style.getXSize4());
		}
		if (null == style.getXSize5() || "".equals(style.getXSize5())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty5"));
		} else {
			seZusTable.getColumn("xQty5").setCaption(style.getXSize5());
		}
		if (null == style.getXSize6() || "".equals(style.getXSize6())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty6"));
		} else {
			seZusTable.getColumn("xQty6").setCaption(style.getXSize6());
		}
		if (null == style.getXSize7() || "".equals(style.getXSize7())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty7"));
		} else {
			seZusTable.getColumn("xQty7").setCaption(style.getXSize7());
		}
		if (null == style.getXSize8() || "".equals(style.getXSize8())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty8"));
		} else {
			seZusTable.getColumn("xQty8").setCaption(style.getXSize8());
		}
		if (null == style.getXSize9() || "".equals(style.getXSize9())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty9"));
		} else {
			seZusTable.getColumn("xQty9").setCaption(style.getXSize9());
		}
		if (null == style.getXSize10() || "".equals(style.getXSize10())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty10"));
		} else {
			seZusTable.getColumn("xQty10").setCaption(style.getXSize10());
		}
		if (null == style.getXSize11() || "".equals(style.getXSize11())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty11"));
		} else {
			seZusTable.getColumn("xQty11").setCaption(style.getXSize11());
		}
		if (null == style.getXSize12() || "".equals(style.getXSize12())) {
			seZusTable.removeColumn(seZusTable.getColumn("xQty12"));
		} else {
			seZusTable.getColumn("xQty12").setCaption(style.getXSize12());
		}

		// seZusTable.addStyleProvider((entity, property) -> {
		// return "center-size12";
		//
		// });

		seZusDs.refresh();

	}
	SeZu addItem = new SeZu();
	@Inject
	Button AddButton;
	Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	public void onRemove(Component source) {
		if(addItem.getId()==seZusTable.getSingleSelected().getId()) {
			AddButton.setEnabled(true);
		}
		seZusDs.removeItem(seZusTable.getSingleSelected());
		seZusTable.setHeight((seZusTable.getHeight() - 35) + "px");
	}

	public void onCancel(Component source) {
		close(Window.CLOSE_ACTION_ID, true);
	}
	
	public void onAdd(Component source) {
		addItem = new SeZu();
		System.out.println("addId:"+addItem.getId());
		addItem.setStyleID(style.getSID());
		seZusDs.addItem(addItem);
		seZusTable.setHeight((seZusTable.getHeight() + 35) + "px");
		seZusTable.setSelected(addItem);
		AddButton.setEnabled(false);
		seZusDs.getItem(addItem.getId()).addPropertyChangeListener(e -> {
			
			if (e.getItem().getValue("xMColor") != null || e.getItem().getValue("xEColor") != null || e.getItem().getValue("xMColorNo") != null) {
				AddButton.setEnabled(true);
				showNotification("修改已保存");
			}else {
				AddButton.setEnabled(false);
			}
		});
		// close(Window.CLOSE_ACTION_ID, true);
	}

	@Override
	public boolean close(String actionId) {
		close(Window.CLOSE_ACTION_ID, true);
		return true;

	}
}