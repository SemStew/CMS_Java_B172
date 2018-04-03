package cz.cvut.fit.semstew;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import com.vaadin.ui.*;

import java.io.File;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");
        final Label info = new Label();

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            info.setValue("Thanks " + name.getValue()
                    + ", it works!");
        });

        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();
        //name.setValue(basepath);

        //FileResource res = new FileResource();

        ThemeResource res = new ThemeResource("testImg.jpg");

        Image image = new Image("Logo", res );

        
        layout.addComponents(name, button,info,image);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
