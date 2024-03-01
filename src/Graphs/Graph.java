package Graphs;

import java.util.LinkedList;
import java.util.Scanner;

public class Graph{

    int [][] adjacency_matrix;

    LinkedList<Integer> [] adjacency_list;

    int numVertices;

    Graph(int vertices)
    {
        this.numVertices = vertices;
        adjacency_matrix = new int[vertices][vertices];
        adjacency_list = new LinkedList[numVertices];
    }

    public void add_edge(int from,int to)
    {
        if(from >= numVertices || from < 0 || to >= numVertices || to < 0)
        {
            System.out.println("Invalid vertices, vertices should be between "+0+" and "+(numVertices-1));
        }
        else if(adjacency_matrix[from][to]==1)
        {
            System.out.println("Edge exists between vertices "+from+" and "+to);
        }
        else
        {
            adjacency_matrix[from][to] = 1;
            adjacency_matrix[to][from] = 1;
            System.out.println("Edge added successfully between "+from+" and "+to);



        }
    }

    public void remove_edge(int from,int to)
    {
        if(from >= numVertices || from < 0 || to >= numVertices || to < 0)
        {
            System.out.println("Invalid vertices, vertices should be between "+0+" and "+(numVertices-1));
        }
        else if(adjacency_matrix[from][to] != 1)
        {
            System.out.println("Edge does not exists");
        }
        else
        {
            adjacency_matrix[from][to] = 0;
            adjacency_matrix[to][from] = 0;
            System.out.println("Edge removed successfully between "+from+" and "+to);
        }
    }

    public void print_graph()
    {
        for(int i=0;i<numVertices;i++)
        {
            for(int j=0;j<numVertices;j++)
            {
                System.out.print(adjacency_matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int choice = 0,vertices = 0,from=0,to=0;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of vertices in the graph");
        vertices = s.nextInt();
        Graph g = new Graph(vertices);
        while(true) {
            System.out.println("Press 1 to add edge in graph");
            System.out.println("Press 2 to remove edge in graph");
            System.out.println("Press 3 to show the graph");
            System.out.println("Press 4 to exit graph");
            System.out.println("Enter your choice: ");
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter starting and ending vertices in graph : ");
                    from = s.nextInt();
                    to = s.nextInt();
                    g.add_edge(from,to);
                    break;
                case 2:
                    System.out.println("Enter starting and ending vertices in graph : ");
                    from = s.nextInt();
                    to = s.nextInt();
                    g.remove_edge(from,to);
                    break;
                case 3:
                    System.out.println("Graph is:");
                    g.print_graph();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
