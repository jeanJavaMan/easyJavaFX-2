package br.jeanderson.controller;
import java.net.URL;
import java.util.ResourceBundle;

import br.jeanderson.animations.BounceInDownTransition;
import br.jeanderson.enums.DialogType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jeand
 */
public class DialogMessage implements Initializable {

    @FXML
    private ImageView ivImagem;
    @FXML
    private Label txtMensagem;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void prepara(Stage stage, String message, DialogType type) {
        this.stage = stage;
        txtMensagem.setText(message);
        switch (type) {
            case INFORMATION:
                setImageAndIcon("information-icon.png", "information-new.png");
                break;
            case WARNING:
                setImageAndIcon("warning-icon.png", "warning-new.png");
                break;
            case SUCESS:
                setImageAndIcon("sucess.png", "success-new.png");
                break;
            case ERRO:
                setImageAndIcon("error-icon.png", "erro.png");
                break;
        }
    }

    private void setImageAndIcon(String iconName, String imageName) {
        stage.getIcons().add(new Image("/br/jeanderson/img/" + iconName));
        ivImagem.setImage(new Image("/br/jeanderson/img/" + imageName));
    }

    public void exibirAnimacao(){
        new BounceInDownTransition(ivImagem).play();
    }
    
    public ImageView getImagem(){
        return ivImagem;
    }
    
    @FXML
    private void actionOk(ActionEvent event) {
        this.stage.close();
    }

}
