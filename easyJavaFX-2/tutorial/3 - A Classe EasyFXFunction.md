## A Classe EasyFXFunction
Esta classe foi criado para dar uma pequena ajuda, oferecendo funções para administrar sua janela em sua própria classe de controller.

#### O que a classe oferece?
atualmente o classe oferece os seguintes métodos:

- AfterConstruct() Com este método é possivel você receber sua Classe **ControlWindow** em sua classe de controller
- AfterShow() este método é sempre chamado depois de o objeto da classe **ControlWindow** utilizar o método show()

## Como utiliza-la

Bom para utilizar sua classe de controller deverá estender a classe **EasyFXFunction** da seguinte maneira:

```java
@DefineConfiguration(url_fxml = "/br/jeanderson/Janela.fxml", title = "Titulo da Janela")
public class Janela extends EasyFXFunction implements Initializable {}
```
Com isso você poderá utilizar os métodos citados anteriormente em sua classe de controller. No momento da construção do objeto da classe **ControlWindow** o método **afterConstruct()** é chamado e nele é passado o próprio objeto que acabou de ser construído.
Quando o objeto chamar o método **show()** então logo após será chamado o método **afterShow()** veja como funciona:

```java
@DefineConfiguration(url_fxml = "/br/jeanderson/Janela.fxml", title = "Titulo da Janela")
public class Janela extends EasyFXFunction implements Initializable {
	//para recebemos a instancia do objeto, temos que pegar atráves do método afterConstruct
	private ControlWindow<Janela> janela;
	
	@Override
	public void afterConstruct(ControlWindow control){
		//aqui recebemos a instancia que foi construido.
		this.janela = control;
	}
	
	@Override
	public void afterShow(){
		System.out.printLn("Exibindo uma mensagem após utilizar o método show()");
	}
}
```
No momento pode ser poucas funções, mas elas vão te quebrar um galho.

No próximo irei mostrar uma anotação responsável pela limpeza automática de todos os campos..