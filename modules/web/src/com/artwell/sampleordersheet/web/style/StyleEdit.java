package com.artwell.sampleordersheet.web.style;

import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.data.Datasource;

import java.util.Map;

import javax.inject.Inject;

import com.artwell.sampleordersheet.entity.Style;
import com.artwell.sampleordersheet.service.StyleService;

public class StyleEdit extends AbstractEditor<Style> {
	@Inject
	Datasource<Style> styleDs;
	@Inject
	StyleService styleService;
	
	Style style = new Style();
	@Override
	public void init(Map<String, Object> params) {
		style = (Style) WindowParams.ITEM.getEntity(params);
		setListener(1,textField_1);setListener(2,textField_2);
		setListener(3,textField_3);setListener(4,textField_4);
		setListener(5,textField_5);setListener(6,textField_6);
		setListener(7,textField_7);setListener(8,textField_8);
		setListener(9,textField_9);setListener(10,textField_10);
		setListener(11,textField_11);setListener(12,textField_12);
	}
	@Inject
	TextField textField_1,textField_2,textField_3,textField_4,textField_5,textField_6,
				textField_7,textField_8,textField_9,textField_10,textField_11,textField_12;
	public void setListener(int num,TextField textFieldStr) {
		textFieldStr.addValueChangeListener(event -> {
			String xSize="";
		  
		    switch (num) {
			case 1:
				style.setXSize1(xSize);
				break;
			case 2:
				style.setXSize2(xSize);
				break;
			case 3:
				style.setXSize3(xSize);
				break;
			case 4:
				style.setXSize4(xSize);
				break;
			case 5:
				style.setXSize5(xSize);
				break;
			case 6:
				style.setXSize6(xSize);
				break;
			case 7:
				style.setXSize7(xSize);
				break;
			case 8:
				style.setXSize8(xSize);
				break;
			case 9:
				style.setXSize9(xSize);
				break;
			case 10:
				style.setXSize10(xSize);
				break;
			case 11:
				style.setXSize11(xSize);
				break;
			case 12:
				style.setXSize12(xSize);
				break;

			default:
				break;
			}
		    
		    styleService.upStyle(style);
		});
	}
}