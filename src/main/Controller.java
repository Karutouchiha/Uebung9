/****************************
 * Datum      17.12.2020    *
 * @version   1             *
 * @author    Sarah Haboeck *
 ****************************/
package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;


public class Controller implements Initializable {

    @FXML private TextField textField;
    @FXML private TextArea textArea;
    private Stack<String> stack = new Stack();
    private int index = 0;
    private boolean isnewitem = false;
    private boolean isdot = false;

    @FXML
    private void clickNummberblock(MouseEvent event) {
        Object node = event.getSource();
        Button btn = (Button)node;
        String s = btn.getText();
        if (stack.empty()||isnewitem){
                stack.push(s);
                isnewitem=false;
        }
        else {
            String pop = stack.pop();

            if (s.equals("-"))
            {
                Double dpop = Double.parseDouble(pop)-2*Double.parseDouble(pop);
                stack.push(dpop.toString());
            }
            else if (s.equals(".") && isdot) {
                stack.push(pop);
            }
            else {
                if (s.equals("."))
                {
                    isdot=true;
                }
                stack.push(pop+s);
            }
        }

        showList();
        textField.setText(stack.get(index));
    }
    @FXML
    private void clickOptionblock(MouseEvent event) {
        Object node = event.getSource();
        Button btn = (Button)node;
        String s = btn.getText();
        if (stack.empty()) {
            try {
                stack.push(s);
            } catch (Exception ex) {
                textField.clear();
                textField.setPromptText("Es muss zuerst eine zahl eingegeben werden.");
            }
        }
        else if (s.equals("Enter")){
            index++;
            isdot=false;
            isnewitem=true;
        }
        else {
            stack.pop();
            textField.clear();
            if (s.equals("C")){
                do {
                stack.pop();
                } while (!stack.empty());
            }

        }
        showList();
    }

    @FXML
    private void clickOperatorblock(MouseEvent event) {
    }

    private String stacktoString(){
        StringBuilder sb =new StringBuilder();
        for (int i=0; i<stack.size();i++){
            sb.append(i+":"+stack.get(i)+"\n");
        }
        return sb.toString();
    }

    private void showList(){
            textArea.setText(stacktoString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setEditable(false);
    }
}
