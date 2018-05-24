package com.artwell.sampleordersheet.web.sampleOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import com.artwell.sampleordersheet.entity.MtYarn;
import com.artwell.sampleordersheet.entity.SeWei;
import com.artwell.sampleordersheet.entity.SeZu;
import com.artwell.sampleordersheet.entity.Style;
import com.artwell.sampleordersheet.entity.YongLiang;
import com.artwell.sampleordersheet.service.BomService;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.sun.mail.imap.YoungerTerm;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.PickerField;

public class Bom extends AbstractFrame {
	Style style = new Style();
	@Inject
	BomService bomService;
	@Inject
	GroupDatasource<SeWei, UUID> seWeisDs;

	List<SeWei> seWeiList = new ArrayList<SeWei>();

	@Override
	public void init(Map<String, Object> params) {
		Map<String, Object> styleMap = new HashMap<>();
		styleMap.put("ITEM", params.get("style"));
		style = (Style) WindowParams.ITEM.getEntity(styleMap);

		initYongLiang();
		initSeWei();
	}

	public void initSeWei() {
		seWeiList = bomService.getSeWeiListById(style.getSID());
		System.out.println(seWeiList.size());
		for (SeWei seWei : seWeiList) {
			seWeisDs.addItem(seWei);
		}
		seWeisDs.refresh();
	}
	@Inject
	GroupTable<YongLiang> groupTable_1;
	@Inject
	GroupDatasource<YongLiang, UUID> yongLiangsDs;
	public void initYongLiang() {
		
	}

	@Inject
	protected ComponentsFactory componentsFactory;
	@Inject
	GroupTable<SeWei> groupTable;

	public Component generateYarnCell(SeWei entity) {
		Map<String, Object> lookupScreenParams = new HashMap<>();
		lookupScreenParams.put("id", entity.getMID());
		PickerField colourField = (PickerField) componentsFactory.createComponent(PickerField.NAME);
		colourField.setDatasource(groupTable.getItemDatasource(entity), "yarn");
		colourField.addLookupAction();
		colourField.getLookupAction().setLookupScreen("sampleordersheet$MtYarn.browse");
		colourField.getLookupAction().setLookupScreenOpenType(WindowManager.OpenType.DIALOG);
		colourField.getLookupAction().setLookupScreenParams(lookupScreenParams);

		colourField.getLookupAction().setAfterLookupSelectionHandler(iteams -> {
			// MtYarn yarn=(MtYarn) e;
			for (Object object : iteams) {
				MtYarn yarn = (MtYarn) object;
				System.out.println(yarn.getXCode());
			}
		});
		return colourField;
	}

}