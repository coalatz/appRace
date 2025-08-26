# 🚀 Projeto App Run

## 📋 Pré-requisitos
Antes de rodar o projeto, certifique-se de ter instalado:
- **JDK 21** (versão utilizada no desenvolvimento)  
- **Maven** (para gerenciamento de dependências e build)

## ⚙️ Instalação
1. Clone este repositório:
   ```bash
   git clone <url-do-repositorio>
   cd Run
   ```

2. Compile o projeto com Maven (isso irá baixar e instalar todas as dependências definidas no `pom.xml`):
   ```bash
   mvn clean install
   ```

## ▶️ Execução
Você pode rodar o projeto de duas formas:

### 1. Pela IDE
- Importar o projeto como **Maven Project**.
- Rodar a aplicação diretamente pela sua IDE (Spring Boot Application).

### 2. Via terminal com o `.jar` gerado
Após o build, o Maven irá gerar o artefato `Run.jar` dentro da pasta `target`.  
Execute com o comando:
   ```bash
   java -jar target/Run.jar
   ```

## 🌐 Acesso à API
Após a execução, a API estará disponível em:
```
http://localhost:8009
```

A interface gráfica do **Swagger** pode ser acessada em:
```
http://localhost:8009/swagger-ui.html
```
