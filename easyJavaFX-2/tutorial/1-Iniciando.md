## Primeiros passos
Vamos dar inicio aos tutoriais de como utilizar está biblioteca, cujo o objetivo é acelerar o desenvolvimento de sistema feito em JavaFX.

#### 1º Passo
Como qualquer aplicação **JavaFX** o main do seu projeto deve estender a classe **Application** para que venha funcionar. ex:

```java
public class MeuMain extends Application {}
```

Agora você deverá já ter feito seu FXML e criado sua classe de controller, e neste ponto que vamos começar!
Na **sua classe de controller** você vai utilizar a anotação **@DefineConfiguration** para definir as configurações da sua janela, conforme o exemplo abaixo:

```java
@DefineConfiguration(url_fxml = "/br/jeanderson/Janela.fxml", title = "Titulo da Janela")
public class Janela implements Initializable {}
```
Você poderá fazer mais configurações atráves dessa anotação, no exemplo acima: informamos onde está o arquivo FXML e o titulo da janela.

#### 2º Passo - Exibindo a Janela
Agora que já usamos a anotação para as configurações inicias da Janela, vamos utilizar a classe **ControlWindow** para carregar essa Janela e
fazer a exibição da mesma, vejo como deve proceder:

```java
public class MeuMain extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {		
		ControlWindow<Janela> janela = new ControlWindow<>(Janela.class);
		//fazemos a exibição da janela.
		janela.show();
	}
}
```

Veja que fazemos uma instancia da classe ControlWindow informando o seu tipo, que deve ser do tipo **da sua classe de controller**, e no seu construtor informamos a **Classe de controller que ele deve pegar as configurações atráves da anotação @DefineConfiguration**!
Após isso podemos apenas chamar a janela para exibição com tudo ja pronto! Veja como foi simples.

Na proxima parte irei mostrar alguns métodos da Classe **ControlWindow**.