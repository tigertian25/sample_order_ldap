package com.artwell.sampleordersheet.web.screens;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow;

public class ExtAppMainWindow extends AppMainWindow {
	@Override
    public void ready() {
       // Wait for the super to be ready
        super.ready();

        // Then load the initial screen
        openWindow("sampleordersheet$Style.browse", WindowManager.OpenType.THIS_TAB);
    }
}