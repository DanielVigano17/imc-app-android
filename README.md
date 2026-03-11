# IMC App

Um aplicativo Android simples e intuitivo para cálculo de Índice de Massa Corporal (IMC), desenvolvido como parte de um projeto acadêmico na FATEC.

## 🚀 Funcionalidades

- **Cálculo de IMC:** Calcula o índice com base no peso (kg) e altura (m) informados.
- **Identificação por Nome:** Permite inserir o nome do usuário para um resultado personalizado.
- **Categorização Visual:** Direciona o usuário para uma tela específica baseada no seu resultado, com cores que facilitam a interpretação:
    - **Magreza:** Azul
    - **Peso Normal:** Verde
    - **Sobrepeso:** Amarelo
    - **Obesidade:** Laranja
    - **Obesidade Grave:** Vermelho
- **Navegação Simples:** Botão de retorno em cada tela de resultado para realizar novas consultas.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Plataforma:** Android Studio
- **Componentes de UI:** Material Design, ConstraintLayout, TextInputEditText.

## 📋 Como usar

1.  Abra o aplicativo.
2.  Insira seu nome no campo correspondente.
3.  Digite seu peso em quilogramas (ex: 75.5).
4.  Digite sua altura em metros (ex: 1.75).
5.  Clique no botão para ver seu resultado.
6.  Uma nova tela será aberta com seu IMC calculado e a classificação correspondente.

## 📁 Estrutura do Projeto

- `MainActivity.java`: Controla a lógica principal e o cálculo do IMC.
- `Magreza.java`, `Normal.java`, etc.: Classes que gerenciam as telas de resultado.
- `res/layout/`: Contém os arquivos XML que definem a interface de cada tela.

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais.
