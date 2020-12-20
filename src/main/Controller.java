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
                textField.clear();
                textField.setPromptText("Es muss zuerst eine Zahl eingegeben werden.");
        }
        else if (s.equals("Enter")){
            index++;
            isdot=false;
            isnewitem=true;
        }
        else {
            stack.pop();
            textField.clear();
            index--;
            if (s.equals("C")){
                do {
                stack.pop();
                } while (!stack.empty());
                index=0;
            }

        }
        showList();
    }

    @FXML
    private void clickOperatorblock(MouseEvent event) {
        Object node = event.getSource();
        Button btn = (Button)node;
        String s = btn.getText();
        Double popx;
        Double popy;
        try {
            popx = Double.parseDouble(stack.pop());
            popy = Double.parseDouble(stack.pop());

            if (s.equals("1/x") && !stack.empty()){
                stack.push(popy.toString());
                popx = 1/popx;
                stack.push(popx.toString());
            }
            else if (stack.size()<2){
                Double erg;

                if (s.equals("x<–>y")) {
                    stack.push(popx.toString());
                    erg = popy;
                }
                else if (s.equals("*")) {
                    erg = popx * popy;
                    index--;
                }
                else if (s.equals("/")) {
                    erg = popx / popy;
                    index--;
                }
                else if (s.equals("+")) {
                    erg = popx + popy;
                    index--;
                }
                else {
                    erg = popx - popy;
                    index--;
                }
                stack.push(erg.toString());
            }
        }
        catch (Exception ex){
            textField.clear();
            textField.setPromptText("Es sind nicht genügend Zahlen vorhanden!");
        }
        showList();
    }

    private String stacktoString(){
        StringBuilder sb =new StringBuilder();
        for (int i=stack.size()-1; i>-1;i--){
            if (stack.size()-1==i){
                sb.append("x:"+stack.get(i)+"\n");
            }
            else {
                sb.append(i+":"+stack.get(i)+"\n");
            }
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
