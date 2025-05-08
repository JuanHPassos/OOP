import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArvBpb extends ArvBin {
    // Construtores herdados da superclasse
    public ArvBpb (int len) {
        super(len);
    }

    public ArvBpb () {
        super();
    }

    @Override
    // Funçao de inserção de um elemento na árvore
    public void insert (String value) {
        if (value == null) throw new IllegalArgumentException("Null values not allowed");

        // Caso já exista o elemento
        if (super.find(value)) return;

        // Faz a inserção
        super.insert(value);
        
        // Rebalanceia caso esteja desbalanceado
        if (!isBalanced())
            rebuild(value);
    }

    @Override
    public boolean remove(String value) {
        if (this.size == 0) return false;
        if (value == null) return false;

        // Verifica se o valor existe na árvore
        boolean exists = false;
        for (String node : this.tree) {
            if (node != null && node.split(" ")[1].equals(value)) {
                exists = true;
                break;
            }
        }
        
        if (!exists) return false;

        // Cria uma lista com todos os valores exceto o que será removido
        String[] values = new String[this.size-1];
        int i = 0;
        for (String node : this.tree) {
            if (node != null) {
                String nodeValue = node.split(" ")[1];
                if (!nodeValue.equals(value)) {
                    values[i++] = nodeValue;
                }
            }
        }

        // Limpa a árvore completamente
        Arrays.fill(this.tree, null);
        this.size = 0;

        // Reconstrói a árvore balanceada com os valores restantes
        if (values.length > 0) {
            String[] valuesArray = values;
            Arrays.sort(valuesArray);
            build(valuesArray, 0, valuesArray.length - 1, 0);
            this.size = valuesArray.length;
        }

        return true;
    }

    @Override
    public String toString () {
        return super.toString();
    }

    // Função que remonta a árvore para manter o balanceamento
    private void rebuild(String value) {
    // Coleta todos os valores únicos (set)
    Set<String> uniqueValues = new HashSet<>();
    for (String node : tree) {
        if (node != null) {
            uniqueValues.add(node.split(" ")[1]);
        }
    }
    
    // Adiciona o novo valor se necessário
    if (value != null) uniqueValues.add(value);
    
    // Converte o set para array e ordena
    String[] sortedValues = uniqueValues.toArray(new String[0]);
    Arrays.sort(sortedValues);
    
    // Limpa e reconstrói a árvore
    Arrays.fill(tree, null);
    this.size = 0;
    build(sortedValues, 0, sortedValues.length - 1, 0);
    this.size = sortedValues.length;
    }

    // Função de construção, recursiva, de uma árvore binária perfeitamente balanceada 
    private void build (String[] values, int start, int end, int index) {
        // Verifica os limites
        if (start > end) return;

        // Calcula a posição do meio para manter o balancemento perfeito
        int mid = (start + end) / 2;
        // Verifica se necessita de expansão do array
        if (index >= this.tree.length) super.expandArray();

        // Insere o valor do meio na posição atual (index)
        this.tree[index] = index + " " + values[mid];
        
        // Constroi as subárvores:
        
        // Esquerda
        build(values, start, mid - 1, 2 * index + 1);
        // Direita
        build(values, mid + 1, end, 2 * index + 2);        
    }

    public boolean isBalanced () {
        // Árvore vazia é balanceada
        if (this.size == 0) return true;

        return checkBalance(0);
    }

    private boolean checkBalance(int index) {
        // Se não está na árvore (filho de folha) está balanceada
        if (index >= this.tree.length || this.tree[index] == null) return true;
        
        // Conta o numero de nós das subárvores 
        int leftNodes = super.countNodes(super.nodeLeft(index));
        int rightNodes = super.countNodes(super.nodeRight(index));

        // Retorna se a diferença entre o número de nós das subárvores esquerda e direita é menor ou igual a 1, recursivamente
        return (Math.abs(leftNodes - rightNodes) <= 1 && checkBalance(super.nodeLeft(index)) && checkBalance(super.nodeRight(index)));
    }
}