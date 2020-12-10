package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.Stack;


public class Controller {

    private Stack<Double> stack = new Stack();

    @FXML
    private void clickNummberblock(MouseEvent event) {
        Object node = event.getSource(); //returns the object that generated the event
        //since the returned object is a Button we can do a typecast
        Button btn = (Button)node;
        String s = btn.getText();
        if (s.equals(".")){
            try {
                stack.push(Double.parseDouble(stack.pop() + "."));
            }
            catch (Exception ex){

            }
        }
        else if (s.equals("-")) {

        }
        stacktoString();
    }
    private void stacktoString(){
        System.out.println("Stack");
        for (int i=0; i<stack.size();i++){
            System.out.println(i+":"+stack.get(i));
        }
    }
}
