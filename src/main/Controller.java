package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    private void clickButton(MouseEvent event) {
        Object node = event.getSource(); //returns the object that generated the event
        //since the returned object is a Button we can do a typecast
        Button btn = (Button)node;
        String s = btn.getText();
        System.out.println(s);
    }
}
