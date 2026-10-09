package Prim;

public class TesteGrafoMatriz {

	public static void main(String args[]) {
		// Cria um grafo com 9 vértices
		// Usando o exemplo do material da aula do arquivo
		TGrafoND g = new TGrafoND(9);

		// arestas (origem, destino, peso)
		g.insereAND(1, 2, 4.0f);
		g.insereAND(1, 6, 5.0f);
		g.insereAND(2, 3, 7.0f);
		g.insereAND(2, 6, 3.0f);
		g.insereAND(3, 4, 5.0f);
		g.insereAND(3, 7, 6.0f);
		g.insereAND(4, 5, 3.0f);
		g.insereAND(5, 7, 2.0f);
		g.insereAND(5, 8, 4.0f);
		g.insereAND(6, 7, 7.0f);
		g.insereAND(6, 9, 5.0f);
		g.insereAND(7, 8, 6.0f);
		g.insereAND(8, 9, 8.0f);

		System.out.println("Grafo original do primeiro exemplo");
		g.show();

		// Cria um grafo com 6 vértices
		// Usando o exemplo da atividade solicitada anteriormente do arquivo
		TGrafoND g2 = new TGrafoND(6);

		// arestas (origem, destino, peso)
		// acho que com letra não vai dar certo
		// então:
		/* 
		a = 1
		b = 2
		c = 3
		d = 4
		e = 5
		f = 6
		*/
		g2.insereAND(1, 2, 6.0f);
		g2.insereAND(1, 3, 15.0f);
		g2.insereAND(1, 5, 8.0f);
		g2.insereAND(2, 3, 20.0f);
		g2.insereAND(2, 5, 10.0f);
		g2.insereAND(2, 6, 8.0f);
		g2.insereAND(3, 4, 9.0f);
		g2.insereAND(4, 6, 7.0f);
		g2.insereAND(5, 6, 5.0f);
		
		System.out.println("\nGrafo original do segundo exemplo");
		g2.show();
	}
}