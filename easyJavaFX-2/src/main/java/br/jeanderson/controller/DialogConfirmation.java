package br.jeanderson.controller;
import java.net.URL;
import java.util.ResourceBundle;

import br.jeanderson.animations.RotateInTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jeand
 */
public class DialogConfirmation implements Initializable {

    @FXML
    private ImageView ivImagem;
    @FXML
    private Label txtMensagem;
    private boolean resultado;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultado = false;
    }    

    public void prepara(String msg){
        this.txtMensagem.setText(msg);
    }
    
    public void exibirAnimacao(){
        new RotateInTransition(ivImagem).play();
    }
    
    @FXML
    private void actionSim(ActionEvent event) {
        this.resultado = true;
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void actionNao(ActionEvent event) {
        this.resultado = false;
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    public boolean getResultado(){
        return this.resultado;
    }
}
