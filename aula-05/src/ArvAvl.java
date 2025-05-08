public class ArvAvl extends ArvBin{
    // Herda os contrutores da superclasse
    public ArvAvl (int len) {
        super(len);
    }

    public ArvAvl () {
        super();
    }

    @Override
    public void insert(String value){
        int i = 0;
        while (i < super.tree.length) {
            String n = super.getNode(i);
            if (n == null) {
                super.setNode(i, value);
                super.size++;
                rebalance(i);
                
                return;
            }

            int r = n.compareTo(value);
            if (r == 0) return;
            else if (r > 0) i = super.nodeLeft(i);
            else i = super.nodeRight(i);
        }

        super.expandArray();
        super.setNode(i, value);
        super.size++;
        rebalance(i);
    }

    @Override
    public boolean remove (String value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values not allowed");
        }
        
        int removedIndex = super.findIndex(value);
        if(removedIndex != -1){ 
            super.deleteNode(removedIndex);
            rebalance(removedIndex);
        }

        return true;
    }
    
    @Override
    public String toString () {
        return super.toString();
    }

    // Retorna a altura de um nó(index)
    protected int getHeight(int index) {
        return super.getHeight(index);
    }

    // Retorna se a árvore está balanceada
    public boolean isBalanced (int index) {
        return super.isBalanced(index);
    }

    // Retorna altura da árvore
    protected int getHeight() {
        return getHeight(0);
    }

    // Abre um espaço nulo entre o nó origem e o destino.
    private void shiftDown(int index, int towards) {
        if (index >= super.tree.length || super.getNode(index) == null) {
            return;
        }

        // increase size so we can finish shifting down
        while (towards >= super.tree.length) { // while in the case we don't make it big enough
            super.expandArray();
        }

        shiftDown(super.nodeLeft(index), super.nodeLeft(towards));
        shiftDown(super.nodeRight(index), super.nodeRight(towards));
        // super.tree[towards] = super.tree[index];
        setNode(towards, getNode(index));
        super.tree[index] = null;
    }

    // A partir de um nó(index) sobre rebalanceando a árvore
    private void rebalance(int index) {
        while (true) {
            int leftHeight = getHeight(super.nodeLeft(index));
            int rightHeight = getHeight(super.nodeRight(index));
            if (leftHeight - rightHeight >= 2) {
                int bfRight = getHeight(super.nodeLeft(nodeLeft(index))) - 
                             getHeight(nodeRight(nodeLeft(index)));

                if (bfRight >= 0) {
                    rotateRight(index);
                } else {
                    rotateLeftRight(index);
                }
            } else if(leftHeight - rightHeight <= -2){
                int bfLeft = getHeight(super.nodeLeft(super.nodeRight(index))) - 
                             getHeight(super.nodeRight(super.nodeRight(index)));
                
                if (bfLeft < 0) {
                    rotateLeft(index);
                } else {
                    rotateRightLeft(index);
                }                             

            }

            if (index == 0) break;
            index = parent(index);
        }
    }

    // Rotação direita
    private void rotateRight(int index) {
        // Desce a sub-arvore em um nivel, formando um no com filho esquerdo nulo
        // e filho direito igual a sub-arvore deslocada.
        shiftDown(super.nodeRight(index), super.nodeRight(super.nodeRight(index)));
        // Esse nó gerado ocupa a nova posição da raiz(deve-se atribui-la).
        super.setNode(super.nodeRight(index), super.getNode(index));

        // Move a sub-arvore para o espaço nulo liberado pela shift down.
        shiftUp(super.nodeRight(super.nodeLeft(index)), super.nodeLeft(super.nodeRight(index)));
        if(super.nodeRight(super.nodeLeft(index)) < super.tree.length) expandArray();
        
        // Seta para nulo a antiga posição da raiz da sub-arvore movida.
        super.tree[super.nodeRight(super.nodeLeft(index))] = null;
        
        // Ajusta a nova raiz.
        super.setNode(index, super.getNode(nodeLeft(index)));

        // Mantém ligação do filho esquerdo da nova raiz
        shiftUp(super.nodeLeft(nodeLeft(index)), super.nodeLeft(index));
    }

    // Rotação esquerda
    private void rotateLeft(int index) {   
        int pivotIndex = super.nodeRight(index);

        // Desce a sub-arvore em um nivel, formando um no com filho direito nulo
        // e filho esquerdo igual a sub-arvore deslocada.        
        shiftDown(super.nodeLeft(index), super.nodeLeft(super.nodeLeft(index)));
        // Esse nó gerado ocupa a nova posição da raiz(deve-se atribui-la).
        super.setNode(super.nodeLeft(index), super.getNode(index));

        // Move a sub-arvore para o espaço nulo liberado pela shift down.
        shiftUp(super.nodeLeft(pivotIndex), super.nodeRight(super.nodeLeft(index)));
        if(super.nodeLeft(pivotIndex) < super.tree.length) super.tree[super.nodeLeft(pivotIndex)] = null;

        // Ajusta a nova raiz.
        super.setNode(index, super.getNode(nodeRight(index)));
        
        // Mantém ligação do filho esquerdo da nova raiz
        shiftUp(super.nodeRight(pivotIndex), super.nodeRight(index));
    }

    // Rotação esquerda direita
    private void rotateLeftRight(int rootIndx) {
        int newRootIndx = super.nodeRight(super.nodeLeft(rootIndx));
    
        // Desloque a subárvore direita da raiz para a direita
        shiftDown(super.nodeRight(rootIndx), super.nodeRight(super.nodeRight(rootIndx)));
        setNode(super.nodeRight(rootIndx), getNode(rootIndx));
    
        // Mover o novo filho direito das raízes para o filho esquerdo do filho direito das raízes
        shiftUp(super.nodeRight(newRootIndx), super.nodeLeft(super.nodeRight(rootIndx)));
    
        setNode(rootIndx, getNode(newRootIndx));
        super.tree[newRootIndx] = null;
    
        // deslocar-se para onde a nova raiz estava, é filho esquerdo
        shiftUp(super.nodeLeft(newRootIndx), newRootIndx);
    }

    // Rotação direita esquerda
    private void rotateRightLeft(int rootIndx) {
        int newRootIndx = super.nodeLeft(super.nodeRight(rootIndx));
    
        // Deslocar a subárvore esquerda da raiz para a esquerda
        shiftDown(super.nodeLeft(rootIndx), super.nodeLeft(super.nodeLeft(rootIndx)));
        setNode(super.nodeLeft(rootIndx), getNode(rootIndx));
        
    
        // mover o novo filho esquerdo das raízes para o filho direito do filho esquerdo das raízes
        shiftUp(super.nodeLeft(newRootIndx), super.nodeRight(super.nodeLeft(rootIndx)));
    
        // Move o novo nó raiz para o nó raiz
        setNode(rootIndx, getNode(newRootIndx));
        super.tree[newRootIndx] = null;
    
        // mude para onde a nova raiz estava no filho certo
        shiftUp(super.nodeRight(newRootIndx), newRootIndx);
    }

    // Retorna pai do nó
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Root node has no parent");
        }
        return (index - 1) / 2;
    }

}
