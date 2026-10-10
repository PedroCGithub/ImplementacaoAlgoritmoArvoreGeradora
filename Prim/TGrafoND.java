package Prim;


public class TGrafoND {
	// Atributos Privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	float adj[][]; //matriz de adjacência com float
	
	private float custo; // custo para o prim
	private int T[]; //array para guardar os vertices do prim
	private int qtdT; // auxiliar
	private int E[][]; //array para guarard arestas
	private int qtdE; // aurxiliar
	
	// Métodos Públicos
	public TGrafoND( int n) {  // construtor
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafo
	    this.adj = new float [n+1][n+1];

	    // Inicia a matriz com zeros
		for(int i = 1; i<= n; i++)
			for(int j = 1; j<= n; j++)
				this.adj[i][j]= Float.POSITIVE_INFINITY;	
	}

	// Insere uma aresta no Grafo Não Dirigido tal que
	// v é adjacente a w
	public void insereAND(int v, int w, float peso) {
		//testa se não temos a aresta
        if (adj[v][w] == Float.POSITIVE_INFINITY) {
            adj[v][w] = peso;
            adj[w][v] = peso;
            m++;
        }
    }
	
	// remove uma aresta v->w do Grafo Não Dirigido
	public void removeAND(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] == Float.POSITIVE_INFINITY ){
	        adj[v][w] = Float.POSITIVE_INFINITY;
			adj[w][v] = Float.POSITIVE_INFINITY;
	        m--; // atualiza qtd arestas
	    }
	}
	public void removeVND(int v) {
		if (v < 0 || v >= n) {
			System.out.println("Vértice inválido!");
			return;
		}

		int novoN = n - 1;
		float[][] novaAdj = new float[novoN][novoN];
		int novoM = 0;

		int novaLinha = 0;
		for (int i = 0; i < n; i++) {
			if (i == v) continue;
			int novaColuna = 0;
			for (int j = 0; j < n; j++) {
				if (j == v) continue;
				novaAdj[novaLinha][novaColuna] = adj[i][j];
				novaColuna++;
			}
			novaLinha++;
		}

		// recontar arestas: como o grafo é simétrico,
		// conto só a "metade de cima" da matriz para não contar cada aresta 2x
		for (int i = 0; i < novoN; i++) {
			for (int j = i + 1; j < novoN; j++) {
				if (novaAdj[i][j] == 1) {
					novoM++;
				}
			}
		}

		this.adj = novaAdj;
		this.n = novoN;
		this.m = novoM;

		System.out.println("Vértice " + v + " removido com sucesso.");
	}

	public void show() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        for (int i = 1; i <= n; i++) {
            System.out.println();
            for (int w = 1; w <= n; w++) {
                float peso = adj[i][w];
                if (peso != Float.POSITIVE_INFINITY)
                    System.out.print("Adj[" + i + "," + w + "]=" + peso + " ");
                else 
                    System.out.print("Adj[" + i + "," + w + "]=inf ");
            }
        }
        System.out.println("\n\nfim da impressao do grafo.");
    }

	/* Algoritmo do slide
	
	- Constrói uma subárvore que se inicia unindo um vértice qualquer (1) a seu vizinho mais próximo;
	- No início cada vértice é uma subárvore, escolhendo cada um deles para iniciar o processo;
	- Seja Ti  V o conjunto de vértices da subárvore na iteração i e seja (v, w) | v  Ti, w  V – Ti a aresta de menor valor entre V – Ti e Ti
	- Unem-se o vértice w e a aresta (v,w) à subárvore vigente, cujo conjunto de vértices se torna: Ti+1 = Ti  {w}
	- O processo termina quando atingimos todos os vértices (Tn = V) 

Algoritmo de PRIM
Início <dados: Grafo G = (V, E) >
valor = ∞; / / auxiliar na troca da melhor aresta
custo = 0; / / armazena o custo total da árvore
T = { 1 }; / / Começa a montar a árvore a partir do vértice 1
E = nulo / A árvore não possui nenhuma aresta
PRIM( T ); / / Inicia o algoritmo de Prim
Fim.


Procedimento PRIM (T);
para todo k e T faça / / para todo vértice k pertencente a árvore T
  para todo i e V - T faça / / procura a aresta menor aresta em V-T
  / / Se alguma aresta é menor do que a encontrada até o momento
   se vki < valor então
    // atualiza o valor, o vértice interno a árvore (vint) e externo (vext)
    valor  vki ; vint  k; vext  i;
   fim-se
  fim-para
fim-para / / atualiza o custo, insere o novo vértice vext em T
 custo ← custo + valor; T  T  { vext };
 E’  E’  { (vext, vint ) }; valor  ∞; / / insere a nova aresta
 se T  V então PRIM (T); / / se a árvore não está completa chama PRIM novamente
fim-procedimento
	*/
	public void prim() {
		// declaraçao de quase tudo que precisa
		custo = 0;
		T = new int[n];
		E = new int[n - 1][2];
		qtdT = 0;
		qtdE = 0;

		T[qtdT++] = 1; // T = { 1 }
		primLOOP(); // PRIM(T)

		// saida
		System.out.println("Custo total: " + custo);
		System.out.println("Arestas da árvore:");
		for (int j = 0; j < qtdE; j++) {
			System.out.println("(" + E[j][1] + ", " + E[j][0] + ")");
		}
	}

	private void primLOOP() {
		// declaracao do que faltava
		int vint = -1;
		int vext = -1;
		float valor = Float.POSITIVE_INFINITY;

		// para todo k em T
		for (int j = 0; j < qtdT; j++) {
			int k = T[j];

			// para todo i em V - T
			for (int i = 1; i <= n; i++) {

				// descobre se i já pertence a T
				boolean estaEmT = false;
				for (int a = 0; a < qtdT; a++) {
					if (T[a] == i) {
						estaEmT = true;
					}
				}

				// i está em V - T e a aresta é menor que a melhor até agora
				if (!estaEmT && adj[k][i] < valor) {
					valor = adj[k][i];
					vint = k;
					vext = i;
				}
			}
		}

		// não dá pra chegar nos vértices restantes
		if (vext == -1) {
			System.out.println("Grafo desconexo");
			return;
		}

		custo = custo + valor;
		T[qtdT++] = vext;   // T = T U {vext}
		E[qtdE][0] = vext;  // E' = E' U {(vext, vint)}
		E[qtdE][1] = vint;
		qtdE++;

		if (qtdT < n) {    // árvore incompleta, chama de novo
			primLOOP();
		}
	}
}
