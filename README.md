# Conversor
# Currency Converter

Este é um projeto Java que implementa um conversor de moedas, permitindo obter taxas de câmbio entre diferentes moedas e realizar a conversão de valores. O sistema utiliza a API `ExchangeRate-API` para buscar as taxas de conversão em tempo real.

## Funcionalidades

- Conversão de valores entre diferentes moedas.
- Integração com a API `ExchangeRate-API` para obter taxas de câmbio atualizadas.
- Menu interativo para seleção das moedas e entrada do valor a ser convertido.

## Tecnologias Utilizadas

- **Java 11** ou superior
- **HttpClient** para realizar requisições HTTP
- **Gson** para manipulação de dados JSON

## Requisitos

- JDK 11 ou superior
- Dependência Gson (versão 2.10.1)

### Como adicionar Gson ao projeto

#### Usando Maven
Adicione a seguinte dependência ao arquivo `pom.xml`:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```

#### Usando Gradle
Adicione a seguinte dependência ao arquivo `build.gradle`:

```gradle
dependencies {
    implementation 'com.google.code.gson:gson:2.10.1'
}
```

#### Manualmente
Baixe o JAR do Gson em [Maven Central](https://search.maven.org/artifact/com.google.code.gson/gson/2.10.1) e inclua-o no classpath do seu projeto.

## Como Executar o Projeto

1. Clone ou baixe este repositório.
2. Configure o ambiente de desenvolvimento com as dependências necessárias (Gson).
3. Compile o projeto:
   ```bash
   javac -cp gson-2.10.1.jar CurrencyConverter.java
   ```
4. Execute o programa:
   ```bash
   java -cp .:gson-2.10.1.jar CurrencyConverter
   ```
