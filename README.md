# Usuarios Front

Frontend web para cadastro e gerenciamento de usuários.

A aplicação fornece a interface de usuário para manutenção dos dados cadastrais e comunicação com os serviços REST responsáveis pelas operações de negócio.

## 📋 Funcionalidades

- Cadastro de usuários
- Consulta de usuários
- Alteração de dados cadastrais
- Exclusão de usuários
- Cadastro de endereços
- Associação de endereços aos usuários
- Validação de dados
- Integração com APIs REST

## 🏗️ Arquitetura

A aplicação utiliza uma arquitetura web baseada em **Jakarta Faces (JSF)**, com **Apache MyFaces** como implementação de Jakarta Faces e **CDI** para gerenciamento de componentes e injeção de dependências.

A aplicação é empacotada como **WAR** e executada em um **Apache Tomcat**.

A comunicação com os serviços de backend é realizada através de APIs REST utilizando **OpenFeign**.

```text
┌───────────────────────────────┐
│           Navegador           │
└───────────────┬───────────────┘
                │
                │ HTTP
                ▼
┌───────────────────────────────┐
│        Usuarios Front         │
│                               │
│       Jakarta Faces 4.0       │
│          MyFaces 4.0          │
│          PrimeFaces           │
│              CDI              │
└───────────────┬───────────────┘
                │
                │ REST / HTTP
                ▼
┌───────────────────────────────┐
│          Backend / API        │
└───────────────────────────────┘
```

## 🛠️ Tecnologias

| Tecnologia | Versão |
|---|---:|
| Java | 25 |
| Maven | — |
| Jakarta Faces | 4.0 |
| Apache MyFaces | 4.0.0 |
| PrimeFaces | 16.0.0 |
| PrimeFaces Extensions | 16.0.0 |
| PrimeFaces Themes | 16.0.0 |
| CDI / Weld Servlet | 6.0.3.Final |
| OpenFeign | 13.15 |
| Lombok | 1.18.48 |
| Slf4j | 13.15 |
| JUnit | 4.13.1 |
| Jackson Datatype JSR-310 | 3.0.0-rc2 |
| Apache Tomcat | 26.7.0_3 |

## 📦 Requisitos

Para desenvolver e executar o projeto, são necessários:

- JDK 25
- Maven
- Apache Tomcat compatível com Jakarta Servlet (26.7.0_3)
- Git

Verifique as versões instaladas:

```bash
java -version
mvn -version
git --version
```

## 🚀 Build

Para compilar o projeto:

```bash
mvn clean package
```

O arquivo WAR será gerado em:

```text
target/usuarios.war
```

O nome do arquivo é definido pelo `finalName` do Maven:

```xml
<finalName>usuarios</finalName>
```

## 🌐 Deploy

A aplicação pode ser implantada no diretório `webapps` do Tomcat.

Exemplo:

```bash
cp target/usuarios.war $CATALINA_HOME/webapps/
```

Após iniciar o Tomcat:

```bash
$CATALINA_HOME/bin/startup.sh
```

a aplicação estará disponível no contexto:

```text
http://localhost:8080/usuarios/
```

O contexto pode ser alterado através da configuração de deploy do Tomcat.

## 🔌 Integração com APIs

A aplicação utiliza **OpenFeign** para realizar chamadas HTTP para serviços REST.

Arquitetura de integração:

```text
JSF / View
    │
    ▼
CDI Bean
    │
    ▼
Service
    │
    ▼
OpenFeign Client
    │
    ▼
HTTP / REST
    │
    ▼
Backend API
```

As bibliotecas Feign utilizadas são:

- `feign-okhttp`
- `feign-jackson`
- `feign-slf4j`

## 👤 Usuários

O sistema possui como principal domínio o cadastro de usuários.

Um usuário pode possuir um ou mais endereços.

Modelo conceitual:

```text
┌───────────────┐
│    Usuário    │
├───────────────┤
│ id            │
│ cpf           │
│ nome          │
│ dataNascimento│
└───────┬───────┘
        │
        │ 1:1
        ▼
┌───────────────┐
│   Endereço    │
├───────────────┤
│ id            │
│ CEP           │
│ logradouro    │
│ número        │
│ cidade        │
│ UF            │
└───────────────┘
```

O CPF é tratado como identificador de negócio único do usuário.

## 🏠 Endereços

Um usuário pode possuir somente um endereço.

## 🧩 CDI

O gerenciamento de dependências e componentes da aplicação é realizado através de **CDI**, utilizando **Weld Servlet** para execução no Tomcat.

Exemplo:

```java
@Named
@RequestScoped
public class UsuarioBean {

    @Inject
    private UsuarioService usuarioService;
}
```

## 🎨 Interface

A interface da aplicação utiliza:

- Jakarta Faces
- Apache MyFaces
- PrimeFaces
- PrimeFaces Extensions
- PrimeFaces Themes

Os componentes PrimeFaces são utilizados para construção dos formulários, tabelas, mensagens, diálogos e demais elementos da interface.

## 🧪 Testes

Para executar os testes:

```bash
mvn test
```

Para executar o ciclo completo de validação:

```bash
mvn clean verify
```

## 📁 Estrutura

Estrutura esperada do projeto:

```text
usuarios-front/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/velsis/...
│   │   │
│   │   ├── resources/
│   │   │
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── web.xml
│   │       │   └── faces-config.xml
│   │       │
│   │       ├── pages/
│   │       ├── resources/
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── images/
│   │       │
│   │       └── index.xhtml
│   │
│   └── test/
│
├── pom.xml
├── README.md
└── .gitignore
```

## 🔧 Configuração

Configurações específicas de ambiente devem ser mantidas fora do código-fonte sempre que possível.

Exemplos:

```text
URL da API
Endpoints
Credenciais
Tokens
Configurações específicas de ambiente
```

Os ambientes podem ser separados em:

```text
development
testing
production
```

## 🌿 Git

Sugestão de nomenclatura para branches:

```text
main
│
├── feature/cadastro-usuario
├── feature/cadastro-endereco
├── feature/consulta-usuario
├── fix/validacao-cpf
└── refactor/...
```

### Commits

Recomenda-se utilizar mensagens objetivas:

```text
feat: adiciona cadastro de usuário
feat: adiciona cadastro de endereço
feat: adiciona integração com API de usuários
fix: corrige validação de CPF
fix: corrige consulta de usuários
refactor: reorganiza camada de serviços
test: adiciona testes de usuário
docs: atualiza documentação
```

## 🔐 Segurança

Não versionar informações sensíveis no Git.

Nunca adicionar ao repositório:

```text
Senhas
Tokens
API Keys
Certificados privados
Credenciais de banco de dados
Credenciais de APIs externas
Arquivos de configuração contendo dados sensíveis
```

Utilize mecanismos de configuração apropriados para cada ambiente.

## 📄 Empacotamento

O projeto é empacotado como:

```text
WAR
```

O nome final do artefato é:

```text
usuarios.war
```

definido através de:

```xml
<build>
    <finalName>usuarios</finalName>
</build>
```

## 📌 Informações do projeto

**Group ID:** `com.velsis`

**Artifact ID:** `usuarios-front`

**Versão:** `0.0.1`

**Packaging:** `war`

**Nome:** `usuarios`

**Java:** `25`

**Context Path padrão:** `/usuarios`

---

## 👨‍💻 Desenvolvimento

Projeto frontend responsável pela interface de cadastro e gerenciamento de usuários, desenvolvido utilizando Jakarta Faces, PrimeFaces, CDI e integração com APIs REST.

**Status:** Em desenvolvimento.