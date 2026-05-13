# ✴️⚓ Black Battleship

---

# Table of Contents
- [Identificação do Grupo](#indetificacao-do-grupo)
- [Scrum Product Backlog](#-scrum-product-backlog--battleship-online)
- [Notas a considerar](#notas-a-considerar)

---

# Identificação do grupo:
- Dinis Oliveira, nº 110764
- João Jesus, nº 123025
- Ricardo Cabrito, nº 122999
- Tiago Reinolds, nº 123011

Como referido no repositório anterior, alguns membros têm contas anteriores à cadeira que ficam associadas a alguns *commits*, as quais são identificadas de seguida:
- Aluno: 123011, Conta associada: [Reynolds2005](https://github.com/Reynolds2005)
- Aluno: 123025, Conta associada: [jcjesus45](https://github.com/jcjesus45)

# 📋 Scrum Product Backlog – Battleship Online

Este backlog descreve as User Stories para o desenvolvimento de um jogo de Batalha Naval online, seguindo a framework Scrum.

---

## 🎯 Epic 1 – Acesso e Autenticação

### US01 – Jogar como convidado
Como utilizador, quero entrar no jogo sem registo para começar a jogar rapidamente.

### US02 – Registo de utilizador
Como jogador, quero criar uma conta para guardar o meu progresso e estatísticas.

### US03 – Login de utilizador
Como jogador, quero iniciar sessão para aceder ao meu perfil.

---

## 🎯 Epic 2 – Sistema de Salas (Lobby)

### US04 – Criar sala privada
Como jogador, quero criar uma sala privada para jogar com amigos.

### US05 – Entrar em sala pública
Como jogador, quero entrar automaticamente numa partida contra outro jogador online.

### US06 – Entrar por convite
Como jogador, quero entrar numa sala através de um link ou código de convite.

### US07 – Estado de prontidão
Como jogador, quero indicar quando estou pronto para iniciar o jogo.

---

## 🎯 Epic 3 – Preparação do Jogo

### US08 – Grelha de jogo 10x10
Como jogador, quero um tabuleiro 10x10 para posicionar a minha frota.

### US09 – Frota de navios
Como sistema, quero disponibilizar diferentes tipos de navios com tamanhos variados.

### US10 – Colocação de navios
Como jogador, quero posicionar os meus navios no tabuleiro antes do jogo começar.

### US11 – Rotação de navios
Como jogador, quero rodar os navios entre horizontal e vertical.

### US12 – Validação de colocação
Como sistema, quero impedir posições inválidas ou sobreposição de navios.

---

## 🎯 Epic 4 – Mecânicas de Jogo

### US13 – Sistema de turnos
Como sistema, quero alternar turnos entre jogadores.

### US14 – Disparo em coordenadas
Como jogador, quero selecionar uma célula para atacar o adversário.

### US15 – Resultado do disparo
Como jogador, quero saber se o tiro foi acerto ou falha.

### US16 – Histórico de jogadas
Como jogador, quero visualizar os meus disparos anteriores no tabuleiro.

### US31 - Abortar Jogo
Como jogador, quero abortar um jogo após este ter sido iniciado.

### US32 - Alterar Cor da Página "Dark Mode"
Como jogador, quero poder alterar o tipo de fundo da minha home page, escolhendo ativar ou não o "Dark Mode"

---

## 🎯 Epic 5 – Regras de Vitória

### US17 – Deteção de navio afundado
Como sistema, quero identificar quando um navio foi completamente destruído.

### US18 – Condição de fim de jogo
Como sistema, quero terminar o jogo quando todos os navios de um jogador forem destruídos.

### US19 – Declaração de vencedor
Como jogador, quero ver o resultado final da partida.

---

## 🎯 Epic 6 – Multiplayer em Tempo Real

### US20 – Sincronização em tempo real
Como sistema, quero atualizar o estado do jogo em tempo real entre jogadores.

### US21 – Notificação de jogada adversária
Como jogador, quero ser notificado quando o adversário joga.

### US22 – Reconexão ao jogo
Como jogador, quero retomar o jogo caso perca ligação.

---

## 🎯 Epic 7 – Comunicação

### US23 – Chat entre jogadores
Como jogador, quero comunicar com o adversário durante a partida.

### US24 – Emojis/reacções
Como jogador, quero reagir às jogadas com emojis.

---

## 🎯 Epic 8 – Ranking e Estatísticas

### US25 – Sistema de ranking
Como jogador, quero subir ou descer no ranking conforme os resultados.

### US26 – Estatísticas pessoais
Como jogador, quero ver vitórias, derrotas e precisão de tiros.

### US27 – Leaderboard
Como jogador, quero comparar o meu ranking com outros jogadores.

---

## 🎯 Epic 9 – Modos de Jogo

### US28 – Jogador vs Jogador (PvP)
Como jogador, quero jogar contra outros jogadores reais.

### US29 – Jogador vs IA
Como jogador, quero jogar contra um bot para treinar.

### US30 – Jogo rápido
Como jogador, quero entrar rapidamente numa partida automática.

---

## 🎯 Epic 10 – Funcionalidades Extra

### US31 – Personalização de perfil
Como jogador, quero personalizar o meu avatar e nome.

### US32 – Variações de regras
Como jogador, quero jogar modos alternativos com regras diferentes.

### US33 – Sistema de torneios
Como jogador, quero participar em torneios competitivos.

---

# Notas a considerar

### Organização das classes do projeto
As classes *Page Object Class* não foram colocadas no *package* `src/main/java/`, pois os objetos de classe `SelenideElement` não eram processados no mesmo pacote. Assim sendo, só foi possível utilizar a funcionalidade mantendo as classes referidas no na diretoria `src/test/java/`.
