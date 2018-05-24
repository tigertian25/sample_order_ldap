package com.artwell.sampleordersheet.web.sampleOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import com.artwell.sampleordersheet.entity.EtStyleSize;
import com.artwell.sampleordersheet.entity.SeZu;
import com.artwell.sampleordersheet.entity.Style;
import com.artwell.sampleordersheet.service.StyleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.chile.core.model.MetaPropertyPath;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.KeyValueEntity;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.components.DataGrid.FooterRow;
import com.haulmont.cuba.gui.components.DataGrid.HeaderCell;
import com.haulmont.cuba.gui.components.DataGrid.HeaderRow;
import com.haulmont.cuba.gui.components.Frame;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.Table.Column;
import com.haulmont.cuba.gui.components.Table.ColumnAlignment;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.config.WindowInfo;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.data.impl.ValueCollectionDatasourceImpl;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.web.app.ui.jmxcontrol.inspect.operation.OperationResultWindow;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Accordion.Tab;
import com.haulmont.cuba.gui.components.Component;

public class Sampleorder extends AbstractLookup {
	@Inject
	StyleService styleService;

	Style style = new Style();
	List<SeZu> seZuList = new ArrayList<SeZu>();
	@Inject
	VBoxLayout labelVbox;

	@Override
	public void init(Map<String, Object> params) {
		super.init(params);
		Integer styleId = 531411520;
		// Integer styleId = 531412099;
		if (!params.isEmpty() && null != params.get("styleId")) {
			styleId = Integer.parseInt(params.get("styleId").toString());
		}
		initLabelValue(styleId);// 设置所有的label标签
		initSeZuTable(styleId);// 设置色组/色号表
		initPaiSeYongLiaoTable(styleId);// 设置配色用料表
		initDataGridTable(styleId);// 设置尺寸表
		initBom();
	}
	@Inject
	TabSheet tabSheet;
	/*@Inject
	Frame frame;*/
	
	public void initBom() {
		//TODO 初始化BOM1
		Map<String, Object> params = new HashMap<>();
		params.put("style", style);
		AbstractFrame frame = openFrame(tabSheet.getTabComponent("tab1"), "bom", params);
		
//		OperationFrame  frame = openFrame(tabSheet, "bom", params);
//		openEditor("sampleordersheet$SeZu.edit",seZuList.get(0) , WindowManager.OpenType.THIS_TAB, params);
//		com.haulmont.cuba.gui.components.TabSheet.Tab tab=tabSheet.getTab("bom1");
//		getWindowManager().openFrame(getFrame(), tabSheet, windowInfo)
//		getWindowManager().openFrame(getFrame(), tab, "");
		
	}
	//动态添加tab
	public void onAddBomButtonClick() {
		System.out.println("onAddBomButtonClick:"+tabSheet.getTabs().size());
		Map<String, Object> params = new HashMap<>();
		params.put("style", style);
		AbstractFrame frame = openFrame(null, "bom", params);
		 TabSheet.Tab tab = tabSheet.addTab("tab"+(tabSheet.getTabs().size()+1),frame);
		 
		 tab.setCaption("BOM"+(tabSheet.getTabs().size()));
		 tabSheet.setSelectedTab(tab);
    }
	//删除tab
	public void onDeleteBomButtonClick() {
		System.out.println(tabSheet.getSelectedTab().getName());
		tabSheet.removeTab(tabSheet.getSelectedTab().getName());
		//TODO 删除后台数据
		
    }
	@Inject
	TextField label_2, label_4, label_5, label_6, label_7, label_8, label_9, label_10, label_11, label_12, label_13,
			label_14, label_15, label_16, label_17, label_19;
	@Inject
	Label label_1, label_3, label_18;
	@Inject
	Datasource<Style> styleDs;

	private void initLabelValue(Integer styleId) {
		style = styleService.getStyleById(styleId);
		styleDs.setItem(style);
		// SimpleDateFormat format0 = new SimpleDateFormat("yyyy/MM/dd");
		//
		// label_2.setValue(style.getSNumber());
		// label_3.setValue(Integer.toString(style.getSID()));
		// label_4.setValue(format0.format(style.getXDate2()));
		// label_5.setValue(style.getXSampleType());
		// label_6.setValue(format0.format(style.getXDate1()));
		// label_7.setValue(style.getRCust());
		// label_8.setValue(style.getXStyleNo());
		// label_9.setValue(style.getXCustStyleNo());
		// label_10.setValue(style.getXWGT());
		// label_11.setValue(style.getXSeason());
		// label_12.setValue(style.getXGauge());
		// label_13.setValue(style.getXYSpec());
		// label_14.setValue(style.getXStyle());
		// label_15.setValue(style.getXWeaveDesc());
		// label_16.setValue(style.getXTeam());
		// label_17.setValue(style.getSOwner());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		label_18.setValue(format1.format(new Date()));
		// label_19.setValue(style.getXKindName());
		// label_20.setValue(style.getXNote());
	}
	Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	@Inject
	GroupDatasource<SeZu, UUID> seZusDs;
	@Inject
	DataGrid<SeZu> seZusTable;
	@Named("seZusTable.create")
	private CreateAction seZusTableCreate;

	private void initSeZuTable(Integer styleId) {
		Map<String, Object> params = new HashMap<>();
		params.put("isEdit", false);
		params.put("style", style);
		seZusTableCreate.setWindowParams(params);
		seZusTableCreate.setOpenType(WindowManager.OpenType.DIALOG);
		seZusTableCreate.setAfterWindowClosedHandler((window, closeActionId) -> {
			styleDs.refresh();
			seZusDs.refresh();
			seZusTable.setHeight(((seZusDs.getItemIds().size()+2)*31)+"px");
			setheader();

		});
		
		seZusTable.addEditorCloseListener(event ->{
		
        showNotification("Editor closed", NotificationType.TRAY);
        });

		seZusTable.addEditorPreCommitListener(event ->{
        showNotification("Pre Commit");
		});

		seZusTable.addEditorPostCommitListener(event ->{
			System.out.println(gson.toJson(seZusTable.getSingleSelected()));
        showNotification("Post Commit");
		});
		//设置ds，计算sum
		seZuList = styleService.getSeZuListById(styleId);
		if (null != seZuList && seZuList.size() > 0 && null != seZuList.get(0).getXQty1()) {
			Integer sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0, sum7 = 0, sum8 = 0, sum9 = 0, sum10 = 0,
					sum11 = 0, sum12 = 0, sum13 = 0;
			for (int i = 0; i < seZuList.size(); i++) {
				Integer sumQty=0;
				sumQty=seZuList.get(i).getXQty1()+seZuList.get(i).getXQty2()+seZuList.get(i).getXQty3()+seZuList.get(i).getXQty4()+seZuList.get(i).getXQty5()+seZuList.get(i).getXQty6()+seZuList.get(i).getXQty7()+seZuList.get(i).getXQty8()+seZuList.get(i).getXQty9()+seZuList.get(i).getXQty10()+seZuList.get(i).getXQty11()+seZuList.get(i).getXQty12();
				seZuList.get(i).setSumQty(sumQty);
				seZusDs.addItem(seZuList.get(i));
				sum1 += seZuList.get(i).getXQty1();
				sum2 += seZuList.get(i).getXQty2();
				sum3 += seZuList.get(i).getXQty3();
				sum4 += seZuList.get(i).getXQty4();
				sum5 += seZuList.get(i).getXQty5();
				sum6 += seZuList.get(i).getXQty6();
				sum7 += seZuList.get(i).getXQty7();
				sum8 += seZuList.get(i).getXQty8();
				sum9 += seZuList.get(i).getXQty9();
				sum10 += seZuList.get(i).getXQty10();
				sum11 += seZuList.get(i).getXQty11();
				sum12 += seZuList.get(i).getXQty12();
				sum13 += seZuList.get(i).getSumQty();
			}
			FooterRow footerRow = seZusTable.appendFooterRow();
			footerRow.getCell("seZuName")
					.setHtml("<div style=\"text-align: right;\">Tol: &nbsp;&nbsp;&nbsp;&nbsp;</div>");
			footerRow.getCell("xQty1").setText(sum1 != 0 ? sum1.toString() : "");
			footerRow.getCell("xQty2").setText(sum2 != 0 ? sum2.toString() : "");
			footerRow.getCell("xQty3").setText(sum3 != 0 ? sum3.toString() : "");
			footerRow.getCell("xQty4").setText(sum4 != 0 ? sum4.toString() : "");
			footerRow.getCell("xQty5").setText(sum5 != 0 ? sum5.toString() : "");
			footerRow.getCell("xQty6").setText(sum6 != 0 ? sum6.toString() : "");
			footerRow.getCell("xQty7").setText(sum7 != 0 ? sum7.toString() : "");
			footerRow.getCell("xQty8").setText(sum8 != 0 ? sum8.toString() : "");
			footerRow.getCell("xQty9").setText(sum9 != 0 ? sum9.toString() : "");
			footerRow.getCell("xQty10").setText(sum10 != 0 ? sum10.toString() : "");
			footerRow.getCell("xQty11").setText(sum11 != 0 ? sum11.toString() : "");
			footerRow.getCell("xQty12").setText(sum12 != 0 ? sum12.toString() : "");
			footerRow.getCell("sumQty").setText(sum13 != 0 ? sum13.toString() : "");
			seZusTable.setHeight(((seZuList.size()+2)*31)+"px");
		}
		setheader();
//		seZusTable.addCellStyleProvider((entity, property) -> {
//			return "center-size12";
//
//		});
		
		seZusDs.refresh();

	}

	public void setheader() {

		// 设置标题
		HeaderRow headerRow = seZusTable.getHeaderRow(0);
		headerRow.getCell("seZuName").setHtml("<div style=\"text-align: center;font-size: 12px;\">色  组/色  号</div>");
		// 移除空的列
		if (null == style.getXSize1() || "".equals(style.getXSize1())) {
			seZusTable.getColumn("xQty1").setVisible(false);
		} else {
			seZusTable.getColumn("xQty1").setVisible(true);
			headerRow.getCell("xQty1")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize1() + "</div>");
		}
		if (null == style.getXSize2() || "".equals(style.getXSize2())) {
			seZusTable.getColumn("xQty2").setVisible(false);
		} else {
			seZusTable.getColumn("xQty2").setVisible(true);
			headerRow.getCell("xQty2")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize2() + "</div>");
		}
		if (null == style.getXSize3() || "".equals(style.getXSize3())) {
			seZusTable.getColumn("xQty3").setVisible(false);
		} else {
			seZusTable.getColumn("xQty3").setVisible(true);
			headerRow.getCell("xQty3")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize3() + "</div>");
		}
		if (null == style.getXSize4() || "".equals(style.getXSize4())) {
			seZusTable.getColumn("xQty4").setVisible(false);
		} else {
			seZusTable.getColumn("xQty4").setVisible(true);
			headerRow.getCell("xQty4")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize4() + "</div>");
		}
		if (null == style.getXSize5() || "".equals(style.getXSize5())) {
			seZusTable.getColumn("xQty5").setVisible(false);
		} else {
			seZusTable.getColumn("xQty5").setVisible(true);
			headerRow.getCell("xQty5")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize5() + "</div>");
		}
		if (null == style.getXSize6() || "".equals(style.getXSize6())) {
			seZusTable.getColumn("xQty6").setVisible(false);
		} else {
			seZusTable.getColumn("xQty6").setVisible(true);
			headerRow.getCell("xQty6")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize6() + "</div>");
		}
		if (null == style.getXSize7() || "".equals(style.getXSize7())) {
			seZusTable.getColumn("xQty7").setVisible(false);
		} else {
			seZusTable.getColumn("xQty7").setVisible(true);
			headerRow.getCell("xQty7")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize7() + "</div>");
		}
		if (null == style.getXSize8() || "".equals(style.getXSize8())) {
			seZusTable.getColumn("xQty8").setVisible(false);
		} else {
			seZusTable.getColumn("xQty8").setVisible(true);
			headerRow.getCell("xQty8")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize8() + "</div>");
		}
		if (null == style.getXSize9() || "".equals(style.getXSize9())) {
			seZusTable.getColumn("xQty9").setVisible(false);
		} else {
			seZusTable.getColumn("xQty9").setVisible(true);
			headerRow.getCell("xQty9")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize9() + "</div>");
		}
		if (null == style.getXSize10() || "".equals(style.getXSize10())) {
			seZusTable.getColumn("xQty10").setVisible(false);
		} else {
			seZusTable.getColumn("xQty10").setVisible(true);
			headerRow.getCell("xQty10")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize10() + "</div>");
		}
		if (null == style.getXSize11() || "".equals(style.getXSize11())) {
			seZusTable.getColumn("xQty11").setVisible(false);
		} else {
			seZusTable.getColumn("xQty11").setVisible(true);
			headerRow.getCell("xQty11")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize11() + "</div>");
		}
		if (null == style.getXSize12() || "".equals(style.getXSize12())) {
			seZusTable.getColumn("xQty12").setVisible(false);
		} else {
			seZusTable.getColumn("xQty12").setVisible(true);
			headerRow.getCell("xQty12")
					.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize12() + "</div>");
		}
		headerRow.getCell("sumQty").setHtml("<div style=\"text-align: center;font-size: 12px;\">合计</div>");
	}

	@Inject
	Table<KeyValueEntity> paiSeYongLiaoTable;
	@Inject
	ValueCollectionDatasourceImpl paiSeYongLiaoDs;

	private void initPaiSeYongLiaoTable(Integer styleId) {
		paiSeYongLiaoDs.addProperty("seWei");
		paiSeYongLiaoDs.addProperty("yongLiao");
		for (int i = 0; i < seZuList.size(); i++) {
			paiSeYongLiaoDs.addProperty("sezu" + i);
		}
		List<Map<String, Object>> pamers = styleService.getPaiSeYongLiaoById(styleId);
		for (Map<String, Object> map : pamers) {
			paiSeYongLiaoDs.includeItem(item(map));
		}

		MetaClass metaClass = paiSeYongLiaoDs.getMetaClass();
		MetaPropertyPath seWeiColumnId = metaClass.getPropertyPath("seWei");
		Column seWeiColumn = new Column(seWeiColumnId, "色位");
		seWeiColumn.setType(seWeiColumnId.getRangeJavaClass());
		// seWeiColumn.setEditable(true);
		seWeiColumn.setAlignment(ColumnAlignment.LEFT);
		paiSeYongLiaoTable.addColumn(seWeiColumn);

		MetaPropertyPath yongLiaoColumnId = metaClass.getPropertyPath("yongLiao");
		Column yongLiaoColumn = new Column(yongLiaoColumnId, "排色用料");
		yongLiaoColumn.setType(yongLiaoColumnId.getRangeJavaClass());
		// yongLiaoColumn.setEditable(true);
		yongLiaoColumn.setAlignment(ColumnAlignment.CENTER);
		paiSeYongLiaoTable.addColumn(yongLiaoColumn);
		for (int i = 0; i < seZuList.size(); i++) {

			MetaPropertyPath columnId = metaClass.getPropertyPath("sezu" + i);
			Column sezuColumn = new Column(columnId,
					"<label style=\"font-size: 12px;\">" + seZuList.get(i).getXMColor() + "</label>");
			sezuColumn.setType(columnId.getRangeJavaClass());
			// sezuColumn.setEditable(true);
			sezuColumn.setAlignment(ColumnAlignment.CENTER);
			paiSeYongLiaoTable.addColumn(sezuColumn);
		}
		paiSeYongLiaoTable.setDatasource(paiSeYongLiaoDs);
		paiSeYongLiaoTable.addStyleProvider((entity, property) -> {

			return "table-size12";

		});

	}

	@Inject
	private Metadata metadata;

	public KeyValueEntity item(Map<String, Object> map) {
		KeyValueEntity entity = metadata.create(KeyValueEntity.class);
		for (Map.Entry<String, Object> m : map.entrySet()) {
			entity.setValue(m.getKey(), m.getValue());
		}
		return entity;
	}

	@Inject
	GroupDatasource<EtStyleSize, UUID> etStyleSizesDs;
	@Inject
	private DataGrid<EtStyleSize> etStyleSizesDataGrid;

	private void initDataGridTable(Integer styleId) {
		etStyleSizesDataGrid.addEditorCloseListener(event ->
        showNotification("Editor closed", NotificationType.TRAY));

		etStyleSizesDataGrid.addEditorPreCommitListener(event ->
        showNotification("Pre Commit"));

		etStyleSizesDataGrid.addEditorPostCommitListener(event ->
        showNotification("Post Commit"));
		// 合并列
		HeaderRow headerRow = etStyleSizesDataGrid.getHeaderRow(0);
		HeaderCell headerCell = headerRow.join("sIndex", "xPart", "xRule");
		headerCell.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + "MEASUREMENT 度法\\尺寸("
				+ style.getXUnit() + ")\\尺码" + "</div>");
		HeaderCell headerCell2 = headerRow.join("xLoss", "xLoss2");

		headerCell2.setHtml("<div style=\"text-align: center;\">" + "+/-" + "</div>");
		headerRow.getCell("xLen1")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize1() + "</div>");
		headerRow.getCell("xLen2")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize2() + "</div>");
		headerRow.getCell("xLen3")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize3() + "</div>");
		headerRow.getCell("xLen4")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize4() + "</div>");
		headerRow.getCell("xLen5")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize5() + "</div>");
		headerRow.getCell("xLen6")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize6() + "</div>");
		headerRow.getCell("xLen7")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize7() + "</div>");
		headerRow.getCell("xLen8")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize8() + "</div>");
		headerRow.getCell("xLen9")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize9() + "</div>");
		headerRow.getCell("xLen10")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize10() + "</div>");
		headerRow.getCell("xLen11")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize11() + "</div>");
		headerRow.getCell("xLen12")
				.setHtml("<div style=\"text-align: center;font-size: 12px;\">" + style.getXSize12() + "</div>");
		headerCell.setStyleName("center-size12");
		headerCell2.setStyleName("center-size12");
		headerRow.getCell("xLen1").setStyleName("center-size12");
		headerRow.getCell("xLen2").setStyleName("center-size12");
		headerRow.getCell("xLen3").setStyleName("center-size12");
		headerRow.getCell("xLen4").setStyleName("center-size12");
		headerRow.getCell("xLen5").setStyleName("center-size12");
		headerRow.getCell("xLen6").setStyleName("center-size12");
		headerRow.getCell("xLen7").setStyleName("center-size12");
		headerRow.getCell("xLen8").setStyleName("center-size12");
		headerRow.getCell("xLen9").setStyleName("center-size12");
		headerRow.getCell("xLen10").setStyleName("center-size12");
		headerRow.getCell("xLen11").setStyleName("center-size12");
		headerRow.getCell("xLen12").setStyleName("center-size12");

		// 移除空的列
		if (null == style.getXSize1() || "".equals(style.getXSize1())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen1"));
		}
		if (null == style.getXSize2() || "".equals(style.getXSize2())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen2"));
		}
		if (null == style.getXSize3() || "".equals(style.getXSize3())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen3"));
		}
		if (null == style.getXSize4() || "".equals(style.getXSize4())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen4"));
		}
		if (null == style.getXSize5() || "".equals(style.getXSize5())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen5"));
		}
		if (null == style.getXSize6() || "".equals(style.getXSize6())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen6"));
		}
		if (null == style.getXSize7() || "".equals(style.getXSize7())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen7"));
		}
		if (null == style.getXSize8() || "".equals(style.getXSize8())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen8"));
		}
		if (null == style.getXSize9() || "".equals(style.getXSize9())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen9"));
		}
		if (null == style.getXSize10() || "".equals(style.getXSize10())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen10"));
		}
		if (null == style.getXSize11() || "".equals(style.getXSize11())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen11"));
		}
		if (null == style.getXSize12() || "".equals(style.getXSize12())) {
			etStyleSizesDataGrid.removeColumn(etStyleSizesDataGrid.getColumn("xLen12"));
		}
		List<EtStyleSize> etStyleSizeList = styleService.getEtStyleSizeById(styleId);
		// 设置表高度
		// etStyleSizesDataGrid.setHeight(((etStyleSizeList.size()+1) * 27) + "px");
		// 设置列样式
		etStyleSizesDataGrid.addCellStyleProvider((entity, property) -> {
			if ("xRule".equals(property)) {
				return "left-size12";
			}
			return "center-size12";

		});
		// 设置数据源
		for (int i = 0; i < etStyleSizeList.size(); i++) {
			etStyleSizesDs.addItem(etStyleSizeList.get(i));
			
		}
		
		etStyleSizesDs.addItemPropertyChangeListener(e ->{
			
			System.out.println("PropertyChangeListener:"+e.getItem());
			System.out.println("PropertyChangeListener:"+e.getValue());
		});
		etStyleSizesDs.refresh();

	}

	public void onEditSeZu(Component source) {
		Map<String, Object> params = new HashMap<>();
		params.put("isEdit", true);
		params.put("style", style);
		openEditor("sampleordersheet$SeZu.edit", seZusTable.getSingleSelected(), WindowManager.OpenType.DIALOG, params);
		System.out.println("click onEditColor");
	}

	public void onDeleteSeZu(Component source) {
		seZusDs.removeItem(seZusTable.getSingleSelected());

		seZusDs.refresh();
		System.out.println("click onDeleteColor");
	}

	public void onDeleteSize(Component source) {
		etStyleSizesDs.removeItem(etStyleSizesDataGrid.getSingleSelected());
		etStyleSizesDs.refresh();
		System.out.println("click onDeleteSize");
	}


    public void onEditUnit(Component source) {
    }

    public void onAddSize(Component source) {
    	EtStyleSize addItem=new EtStyleSize();
    	etStyleSizesDs.addItem(addItem);
    }
}