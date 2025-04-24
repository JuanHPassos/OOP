# Herança

A herança é um princípio da POO que permite a criação de novas classes a partir de outras previamente criadas. Essas novas classes são chamadas de `subclasses`, ou classes `derivadas`; e as classes já existentes, que deram origem às subclasses, são chamadas de `superclasses`, ou `classes base`. Deste modo é possível criar uma hierarquia dessas classes, tornando, assim, classes mais amplas e classes mais específicas. Uma subclasse **herda métodos e atributos de sua superclasse**; apesar disso, pode escrevê-los novamente para uma forma mais específica de representar o comportamento do método herdado.

## Exemplo usando classe Pessoa
Pega-se como exemplo o universo academico com alunos, professores e funcionários, todos esses indivíduos podem ter características em comum, pois são todos pessoas, logo podemos fazer uma abstração de suas características comuns de diferentes objetos para criar uma classe base Pessoa.

```java
import java.util.Date;
public class Pessoa {
	public String nome;
	public String cpf;
	public Date data_nascimento;

	public Pessoa(String _nome, String _cpf, Date _data) {
		this.nome = _nome;
		this.cpf = _cpf;
		this.data_nascimento = _data;
	}
}
```
A partir da classes base Pessoa, são criadas as três subsclasses, usando a palavra extends seguida do nome da superclasse.

```java
import java.util.Date;
public class Aluno extends Pessoa {
	public Aluno(String _nome, String _cpf, Date _data) {
		super(_nome, _cpf, _data);
	}
	public String matricula;
}
public class Professor extends Pessoa {
	public Professor(String _nome, String _cpf, Date _data) {
		super(_nome, _cpf, _data);
	}
	public double salario;
	public String disciplina;
}
public class Funcionario extends Pessoa {
	public Funcionario(String _nome, String _cpf, Date _data) {
		super(_nome, _cpf, _data);
	}
	public double salario;
	public Date data_admissao;
	public String cargo;
}
```
Observa-se que no construtor das novas classes é usado uma chamada super(_nome, _cpf, _data), tal palavra `super` representa uma chamada de método ou acesso a um atributo da superclasse, nesse caso, o super é usado para **invocar o construtor da superclasse Pessoa**, que recebe os três parâmetros e preenche os atributos do objeto.

## Modificadores(Como se aplica aos filhos?)

Todos os métodos e atributos públicos(public) e protegidos(protected) da superclasse serão herdados, caso utilize-se o extends Classe na definição de uma nova classe. Portanto, os métodos e atributos privados (private) não serão herdados, e não teremos acesso a eles nem com a utilização da palavra especial super.

## É possível re-implementar algo já feito?
Apesar de a classe derivada herdar os comportamentos públicos da classe base, nada (ou quase nada) impede que implementemos de novo os métodos e atributos que quisermos na subclasse. Podemos sobrescrever métodos das superclasses, criando assim um novo comportamento para funções específicas. Vejamos um exemplo da sobrescrita de método.
```java
import java.util.Date;
public class Pessoa {
	public String nome;
	public String cpf;
	public Date data_nascimento;
	public Pessoa(String _nome, String _cpf, Date _data) {
		this.nome = _nome;
		this.cpf = _cpf;
		this.data_nascimento = _data;
	}
	public double tirarCopias(int qtd) { //Retorna o preço para tirar copias
		return 0.10 * (double) qtd;
	}
}

public class Aluno extends Pessoa {
	public Aluno(String _nome, String _cpf, Date _data) {
		super(_nome, _cpf, _data);
	}
	public String matricula;
	public double tirarCopias(int qtd) { //Preço para tirar copias para alunos
		return 0.07 * (double) qtd;
	}
}
```
**OBS:** a palavra especial final, utilizada na declaração de métodos e atributos, que só permite uma declaração da variável ou comportamento em questão(Proibido reescreve-la).

## Sobre o @override
A notação `@override` basicamente tem duas funções, uma é visualmente tornar mais fácil para o programador identificar que tal método está sobrescrevendo um método da super classe.
A segunda, é uma forma de dizer ao compilador que aquele método vai sobrescrever um método da super classe. Ao fazer isso, o compilador vai testar se realmente pode sobrescrever. Mas o compilador sem esse 'aviso', baseado na anotação, já consegue fazer essa verificação. Então, isso torna a anotação um tanto dispensável.

## Classes abstratas
Classes abstratas são classes que, basicamente, são apenas um tipo abstrato de como as classes que a herdarem devem se comportar.

Classes abstratas não podem ser instanciadas, como já dito, elas servem apenas para que outras classes usem-na como modelo (herdem os atributos/propriedades e métodos delas).

Elas podem ter métodos abstratos ou não abstratos.
Os métodos abstratos não podem ter corpo, ou seja, deve-se declarar apenas a assinatura do método e eles obrigatoriamente terão que ser implementados na classe filha (a classe que herda), já os métodos que não forem assinados como abstract devem ter corpo e podem ou não ser sobrescritos na classe filha.
```java
public abstract class Animal{        
    abstract String getHabitat();

    public String getRaca(){
        return "Raça indefinida";
    }
}

class Cachorro extends Animal{
    public String getHabitat(){
        return "";
    }
}

class Gato extends Animal{
    public String getHabitat(){
        return "indefinido";
    }

    public String getRaca(){
        return "Munchkin";
    }
}
```

### Java tem herança simples(só herda de uma classe).

## Em python
Em Python, a herança é implementada através da declaração da classe filha com a classe pai entre parênteses. Essa declaração estabelece uma relação entre as classes, indicando que a classe filha herda as características da classe pai. Por exemplo:
```py
class Pai:
    def __init__(self, nome):
        self.nome = nome

    def saudacao(self):
        return f"Olá, {self.nome}!"

class Filha(Pai):
    def __init__(self, nome, idade):
        super().__init__(nome)
        self.idade = idade

    def saudacao(self):
        return f"Oi, {self.nome}! Você tem {self.idade} anos."
```
A classe Filha herda os atributos e métodos da classe Pai. Cabe destacar que o objeto filha herda o método saudacao e o modifica para exibir idade.

## Python possui herança múltipla
A herança múltipla, como uma árvore genealógica, permite que uma classe filha herde de mais de uma classe pai, combinando atributos e comportamentos de cada uma delas. Isso pode levar a possíveis conflitos, que o Python resolve usando a ordem de resolução de método (MRO).
```py
class Person:
    def get_details(self):
        return "Details of a person."

class Athlete:
    def get_skill(self):
        return "Athletic skills."

class Student(Person, Athlete):
    pass

# Example usage
student = Student()
print(student.get_details())
print(student.get_skill())
```
Ordem de resolução de métodos determina a ordem em que as classes são pesquisadas em busca de métodos e atributos. O MRO segue uma abordagem de profundidade primeiro, da esquerda para a direita.