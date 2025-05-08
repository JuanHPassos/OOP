public class ArvBin {
  // Capacidade padrão para uma árvore com 4 níveis
  protected static final int DEFAULT_CAPACITY = 15;
  protected String[] tree;
  protected int size;

  // Contrutor gera uma árvore vazia
  public ArvBin(){
    this.tree = new String[DEFAULT_CAPACITY];
    this.size = 0;
  }

  // Contrutor gera uma árvore vazia
  public ArvBin(int len){
    this.tree = new String[(len > 0 ? len : DEFAULT_CAPACITY)];
    this.size = 0;
  }

  // Insere uma string na árvore
  public void insert(String value){
    if (value == null) {
      throw new IllegalArgumentException("Null values not allowed");
    }

    if (size == 0) {
      tree[0] = 0 + " " + value;
      size++;
      return;
    }

    int currentIndex = 0;
    while (currentIndex < tree.length) {
      if (tree[currentIndex] == null) {
        tree[currentIndex] = currentIndex + " " + value;
        size++;
        return;
      }

      // Retorna negativo se for menor, 0 se igual e positivo se for maior.
      int comparison = value.compareTo(getNode(currentIndex));
      if (comparison < 0) {
        currentIndex = 2 * currentIndex + 1; // Vai para o filho esquerdo
      } else if (comparison > 0) {
        currentIndex = 2 * currentIndex + 2; // Vai para o filho direito
      } else {
        // String já existe na árvore
        return;
      }
    }

    // Se chegou aqui, precisa expandir o array
    expandArray();
    insert(value);
  }

  // Retorna o número de elementos presentes na árvore
  public int len(){
    return this.size;
  }

  // Remove a element in the tree
  public boolean remove(String v){
    if (v == null) {
      throw new IllegalArgumentException("Null values not allowed");
    }

    int indexToRemove = findIndex(v);
    if (indexToRemove != -1) {
      deleteNode(indexToRemove);
      return true; // Valor removido da árvore.
    }

    // Valor não está na árvore.
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph {\n");
    if(size == 1) sb.append(String.format("\"%s\" ", tree[0]));
    else if(size > 1) buildGraphvizString(0, sb);
    sb.append("}\n");
    return sb.toString();
  }

  // Verifica se o elemento está presente na árvore
  protected boolean find (String v) {
    int currentIndex = 0;
    while (currentIndex < tree.length && tree[currentIndex] != null) {
      int comparison = v.compareTo(tree[currentIndex]);
      if (comparison == 0) {
        return true;
      } else if (comparison < 0) {
        currentIndex = 2 * currentIndex + 1;
      } else {
        currentIndex = 2 * currentIndex + 2;
      }
    }
    return false;
  }

  // Expande o array quando necessário
  protected void expandArray() {
    String[] newTree = new String[tree.length * 2 + 1];
    System.arraycopy(tree, 0, newTree, 0, tree.length);
    tree = newTree;
  }

  protected void deleteNode(int i) {
    int lChildIndx = nodeLeft(i);
    int rChildIndx = nodeRight(i);

    size--;
    // Se há filho esquerdo, o predecessor irá substituir o nó removido.
    if(getNode(lChildIndx) != null)
    {
      int predecessor = lChildIndx;
      while (nodeRight(predecessor) < tree.length && tree[nodeRight(predecessor)] != null) {
        predecessor = nodeRight(predecessor);
      }
      // Predecessor assume o lugar do nó removido
      setNode(i, getNode(predecessor));
      tree[predecessor] = null;
      // Por definição, o predessor não possui filho direito
      // logo a sub-arvore esquerda(se existir), assume seu lugar.
      shiftUp(nodeLeft(predecessor), predecessor);
    } 
    // Caso haja filho direito, sem esquerdo, o sucessor assume o lugar do nó removido.
    else if(getNode(rChildIndx) != null) 
    {
      int sucessor = rChildIndx;
      while (nodeLeft(sucessor) < tree.length && tree[nodeLeft(sucessor)] != null) {
        sucessor = nodeLeft(sucessor);
      }
      setNode(i, getNode(sucessor));
      // Sucessor assume o lugar do nó removido
      tree[sucessor] = null;
      // Por definição, o predessor não possui filho esquerdo
      // logo a sub-arvore direita(se existir), assume seu lugar.
      shiftUp(nodeRight(sucessor), sucessor);
    }
    // Se não há filho, apenas remove o nó.
    else 
    {
      tree[i] = null;
    }
  }
    

  // Substitui sub-arvore towards pela index
  protected  void shiftUp(int index, int towards) {
    if (index >= tree.length || getNode(index) == null) {    
        return;
    }
    setNode(towards, getNode(index)); 
    tree[index] = null;
    shiftUp(nodeRight(index), nodeRight(towards));
    shiftUp(nodeLeft(index), nodeLeft(towards));
  }

  // Retorna index do nó com o valor passado como parametro
  protected int findIndex(String value) {
    int currentIndex = 0;
    while (currentIndex < tree.length && tree[currentIndex] != null) {
      int comparison = value.compareTo(getNode(currentIndex));
      if (comparison == 0) {
        return currentIndex;
      } else if (comparison < 0) {
        currentIndex = 2 * currentIndex + 1;
      } else {
        currentIndex = 2 * currentIndex + 2;
      }
    }
    return -1;
  }

  // Conta o número de nós em uma subárvore
  protected int countNodes(int i){
    // Caso base: índice inválido ou nó nulo
    if (i >= tree.length || tree[i] == null) {
      return 0;
    }
    // Conta o nó atual (1) + nós na subárvore esquerda + nós na subárvore direita
    return 1 + countNodes(2*i + 1) + countNodes(2*i + 2);
  }

  // Retorna o valor de um dado nó
  protected String getNode (int i){
    if(i < this.tree.length && i >= 0){
      if(this.tree[i] == null) return null;
      // Dado String "num valor", pega-se o valor.
      return this.tree[i].split(" ")[1];
    }
    else return null;
  }

  // Indica qual é o número do filho à esquerda da posição i
  protected int nodeLeft(int i){
    return 2*i + 1;
  }

  // Indica qual é o número do filho à direita da posição i
  protected int nodeRight(int i){
    return 2*i + 2;
  }

  // Atribui valor para determinado nó
  protected void setNode(int i, String v){
    if(this.tree.length > i){
      this.tree[i] = i + " " + v;
    }
  }

  // Retorna a altura de um nó
  protected int getHeight(int index) {
    if (index >= this.tree.length || getNode(index) == null) {
        return -1;
    }
    return Math.max(
        getHeight(nodeLeft(index)) + 1,
        getHeight(nodeRight(index)) + 1
    );
  }

  // Retorna se uma árvore está balanceada
  public boolean isBalanced (int index) {
    if (this.tree.length == 0) return true;

    if (index >= this.tree.length || this.tree[index] == null) return true;

    int leftHeight = getHeight(nodeLeft(index));
    int rightHeight = getHeight(nodeRight(index));

    return (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(nodeLeft(index)) && isBalanced(nodeRight(index)));
  }

  private void buildGraphvizString(int index, StringBuilder sb) {
    if (index >= tree.length) {
      return;
    }

    if (tree[index] != null) {
      int leftChild = 2 * index + 1;
      int rightChild = 2 * index + 2;

      if (leftChild < tree.length && tree[leftChild] != null) {
        sb.append(String.format("\"%s\" ->\"%s\"\n", tree[index], tree[leftChild]));
      }

      if (rightChild < tree.length && tree[rightChild] != null) {
        sb.append(String.format("\"%s\" ->\"%s\"\n", tree[index], tree[rightChild]));
      }
    }

    buildGraphvizString(index + 1, sb);
  }

}
