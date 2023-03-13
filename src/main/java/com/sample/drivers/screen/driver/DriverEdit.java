package com.sample.drivers.screen.driver;

import com.sample.drivers.entity.Document;
import io.jmix.ui.UiComponents;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.LinkButton;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.screen.*;
import com.sample.drivers.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Driver.edit")
@UiDescriptor("driver-edit.xml")
@EditedEntityContainer("driverDc")
public class DriverEdit extends StandardEditor<Driver> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Downloader downloader;

    @Install(to = "documentsTable.attachment", subject = "columnGenerator")
    private Component documentsTableAttachmentColumnGenerator(Document document) {
        if(document.getAttachment() != null) {
            LinkButton linkButton = uiComponents.create(LinkButton.class);

            linkButton.setCaption(document.getAttachment().getFileName());
            linkButton.setAction(new BaseAction("download").withHandler(actionPerformedEvent
                    -> downloader.download(document.getAttachment())));
            return linkButton;
        }
        return null;
    }
}