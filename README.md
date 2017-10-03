# ufjf-dcc171-2017-3-trb1-GabrielMS97
ufjf-dcc171-2017-3-trb1-GabrielMS97 created by GitHub Classroom

<h2> Identificação </h2>

Nome: Gabriel Martins Santana <br/>
Matrícula: 201576002 <br/>
Curso: Sistemas de Informação <br/>

<h2> Cenário do Sistema </h2>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   O sistema foi desenvolvido como trabalho para a disciplina DCC171 - Laboratório de Programação 3. Neste contexto procurei desenvolvê-lo
   baseado em uma lanchonete que costumo frequentar.
</p>
<p>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   O sistema apresenta funcionalidades para que o mantenedor ou responsável da lanchonete possa manter um controle de pedidos durante um
   dia de funcionamento da mesma. Portanto é possível: <br/>
   <ul>
      <li> Incluir e excluir mesas do sistema, de acordo com as necessidades do estabelecimento; </li> 
      <li> Incluir e excluir pedidos associados às mesas, de acordo com os pedidos feitos pelos clientes; </li> 
      <li> Visualizar a listagem de mesas; </li>
      <li> Visualizar a listagem de pedidos feitos por cada mesa; </li> 
      <li> Fechar a conta das mesas, calculando o valor total a ser pago. </li>
   </ul>
</p>

<h2> Modelo de Dados </h2>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   Segue um link para o Modelo de Dados do sistema:
</p>
<p>
  (https://drive.google.com/file/d/0Bw0hauFVk1JrV3ZENjNWWXBkbW8/view?usp=sharing)
</p>

<h2> Levantamento dos Campos Necessários </h2>
<p>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  O sistema possui uma janela principal e outra para a realização dos pedidos. Na janela principal temos:
  <ul>
    <li>Um painel que mostra a lista de Mesas;</li>
    <li>Um painel que mostra a lista de Pedidos feitos por cada mesa;</li>
    <li>O botão "Adicionar Mesa" que possibilita a adição de uma nova mesa no sistema;</li>
    <li>O botão "Excluir Mesa" que possibilita a exclusão de uma mesa que já esteja na lista;</li>
    <li>O botão "Fazer Pedido" que chama a janela de pedidos;</li>
    <li>O botão "Excluir Item do Pedido" que permite a exclusão de um ou mais itens em um pedido;</li>
    <li>O botão "Fechar Conta" que finaliza a conta de uma mesa, soma os valores de todos os itens pedidos por ela e informa
        o valor a pagar.</li>
  </ul>
</p>
<p>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Já na janela de pedidos temos:
  <ul>
    <li>Um ComboBox com os itens que o cliente pode comprar;</li>
    <li>Um campo de texto, no qual deve ser informada a quantidade desejada do item selecionado;</li>
    <li>Um botão que conclui a realização do pedido.</li>
  </ul>
</p>


<h2> Pontos importantes para o funcionamento da interface </h2>
<p>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  A interface se comporta da maneira como foi idealizada. Procurei tratar todas as possibilidades de erro tanto externo ao sistema (como
  uma entrada de valor inválido) quanto interno (como o cliente realizar o pedido de um item e o sistema computar o valor de outro).
  Além disso não é possível acessar a janela de pedidos se ela já estiver aberta, evitando assim que várias dessas janelas fossem abertas
  ao mesmo tempo, o que poderia ocasinar alguns problemas.
</p>

<h2> Pontos de maior dificuldade </h2>
<p>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  O ponto no qual tive maior dificuldade foi ao fazer com que os dados gerados na Janela de Pedidos passassem para a Janela Principal
  da aplicação. Alguns erros de semântica que ocorreram durante o desenvolvimento também trouxeram bastante dificuldade, pois apesar de
  na maioria das vezes ser simples de se corrigir, era difícil encontrá-los, ainda mais quando o código já havia tomado um tamanho
  maior.
</p>
<p>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Apesar das dificuldades, foi muito interessante desenvolver este sistema. A partir dele pude fixar o que foi visto em sala de aula
  e também me surgiram ideias de novos sistemas e de melhorias para este que podem ser realizadas futuramente.
</p>

<h2> Melhorias Futuras </h2>
<p>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Algumas funcionalidades podem ser implementadas para melhorar este sistema como:
  <ul>
    <li>Permitir que sejam adicionados novos itens ao cardápio da lanchonete, além dos itens padrão;</li>
    <li>Permitir a persistência dos dados de pedidos feitos no sistema;</li>
    <li>Adicionar uma funcionalidade para gerir pedidos de delivery;</li>
    <li>Adicionar uma funcionalidade para gerir o estoque de mercadorias e ingredientes.</li>
  </ul>
</p>
