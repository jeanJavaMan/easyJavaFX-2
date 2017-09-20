## A classe ControlWindow
A classe ControlWindow é a responsável para fazer a construção da sua janela e colocar ela para funcionar, então neste caso a classe **ControlWindow** possui todos os métodos necessários para você trabalhar. Veja alguns deles:

```java
@Override
public void start(Stage primaryStage) throws Exception {
		ControlWindow<JanelaController> janela = new ControlWindow<>(JanelaController.class);
		//instancia para outra janela.
		ControlWindow<Janela2> segundaJanela = new ControlWindow<>(Janela2.class);
		//exibi a segunda janela, tendo como dependência a primeira janela.
		segundaJanela.show(janela);
		//retorna a instancia da classe de controller.
		JanelaController janelaController = janela.getController();
		//retorna o stage
		Stage stage = janela.getStage();
		//retorna a scene
		Scene scene = janela.getScene();
		//retorna o root
		Parent root = janela.getRoot();
		//retorna as configurações que foram usadas na construção da Janela.
		DefineConfiguration configuracoesDefinidas = janela.getDefineConfiguration();
		//exibi a janela e aguarda ela ser fechada ou alguma ação acontencer.
		janela.showAndWait();		
}
```

Veja que você tem tudo que precisa para trabalhar com os componentes da sua Janela.
##### Observações importantes:
ao utilizar o método **show(janelaPai)** que recebe uma Janela Pai, quando você for utilizar a mesma instancia para ter outras janelas pai, um novo Stage será
**gerado automaticamente** para que a Janela possa ter várias janela pai, esse novo Stage terá as configurações que foram definidas na anotação **@DefineConfiguration** na classe de controller. então caso você tenha feito alguma alteração no seu Stage após a construção do objeto da classe ControlWindow você poderá perder.
Então o **recomendado** é que você utilize para somente uma Janela pai, mas caso você não tenha feito alterações no seu stage, poderá utilizar para ter várias janelas pai.

Veja que é bem tranquilo de se trabalhar. No próximo mostrarei a classe **EasyFXFunctions** que vai te dar uma ajudinha para você ter o controle da janela em sua própria classe de controller.