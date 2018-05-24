package com.artwell.sampleordersheet.web.formatter;

import com.haulmont.cuba.gui.components.Formatter;

public class QtyFormatter implements Formatter<Object> {

	@Override
	public String format(Object value) {
		if (null != value) {
			int val = Integer.parseInt(value.toString());
			if(val==0) {
				return "";
			}else {
				return value.toString();
			}
		} else {
			return "";
		}
	}

}
