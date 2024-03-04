package Graphs;

import java.util.*;

public class Graph{

    int [][] adjacency_matrix;

    LinkedList<Integer> [] adjacency_list;
    int numVertices;
    Stack<Integer> s;
    boolean[] visited;
    ArrayDeque<Integer> queue;
    Graph(int vertices)
    {
        this.numVertices = vertices;
        adjacency_matrix = new int[vertices][vertices];
        adjacency_list = new LinkedList[numVertices];
        for(int i=0;i<vertices;i++)
        {
            adjacency_list[i] = new LinkedList<Integer>();
        }
        s = new Stack<Integer>();
        visited = new boolean[vertices];
        queue = new ArrayDeque<Integer>();
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

    public void add_edge_list(int from,int to)
    {
        if(from >= numVertices || from < 0 || to >= numVertices || to < 0)
        {
            System.out.println("Invalid vertices, vertices should be between "+0+" and "+(numVertices-1));
        }
        else if(adjacency_list[from].contains(to))
        {
            System.out.println("Edge exists between vertices "+from+" and "+to);
        }
        else
        {
            adjacency_list[from].add(to);
            adjacency_list[to].add(from);
            System.out.println("Edge added successfully between "+from+" and "+to);
        }
    }

    public void remove_edge_list(Integer from,Integer to)
    {
        if(from >= numVertices || from < 0 || to >= numVertices || to < 0)
        {
            System.out.println("Invalid vertices, vertices should be between "+0+" and "+(numVertices-1));
        }
        else if(!adjacency_list[from].contains(to))
        {
            System.out.println("Edge does not exists between "+from+" and "+to);
        }
        else
        {
            adjacency_list[from].remove(to);
            adjacency_list[to].remove(from);
            System.out.println("Edge removed successfully between "+from+" and "+to);
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

    public void print_graph_list()
    {
        for(int i=0;i<adjacency_list.length;i++)
        {
            System.out.print(i);
            for(Integer neighbor : adjacency_list[i])
            {
                System.out.print("->"+neighbor);
            }
            System.out.println();
        }
    }

    public void dfs_traverse(Integer node)
    {
        System.out.print(node+"->");
        visited[node] = true;
        s.push(node);
        for(Integer i :adjacency_list[node])
        {
            if(!visited[i])
            {
                visited[node]=true;
                dfs_traverse(i);
                s.pop();
            }
        }
    }

    public void bfs_traverse(Integer node)
    {
        visited[node] = true;
        queue.add(node);
        System.out.print(node);
        while(!queue.isEmpty())
        {
            Integer e = queue.pop();
            for(Integer t : adjacency_list[e])
            {
                if(!visited[t])
                {
                    System.out.print("->"+t);
                    visited[t] = true;
                    queue.add(t);
                }
            }
        }
    }

    public boolean detect_cycle_using_dfs(Integer node,Integer pnode)
    {
        visited[node] = true;
        for(Integer t : adjacency_list[node])
        {
            if(!visited[t])
            {
                if(detect_cycle_using_dfs(t,node))
                    return true;
            }
            else if(t != pnode)
                return true;
        }
        return false;
    }

    public boolean detect_cycle_using_bfs(Integer pnode)
    {
        Integer node = queue.remove();
        visited[node] = true;
        for(Integer t : adjacency_list[node])
        {
            if(!visited[t])
            {
                queue.add(t);
                
            }
        }
        return false;
    }



    public void clean()
    {
        System.out.println();
        Arrays.fill(visited,false);
        s.clear();
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
            System.out.println("Press 4 to do dfs traversing");
            System.out.println("Press 5 to do bfs traversing");
            System.out.println("Press 6 to detect cycle using dfs");
            System.out.println("Press 7 to exit graph");
            System.out.println("Enter your choice: ");
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter starting and ending vertices in graph : ");
                    from = s.nextInt();
                    to = s.nextInt();
                    g.add_edge_list(from,to);
                    break;
                case 2:
                    System.out.println("Enter starting and ending vertices in graph : ");
                    from = s.nextInt();
                    to = s.nextInt();
                    g.remove_edge_list(from,to);
                    break;
                case 3:
                    System.out.println("Graph is:");
                    g.print_graph_list();
                    break;
                case 4:
                    System.out.println("Enter the starting node to traverse the graph: ");
                    int start_node = s.nextInt();
                    g.dfs_traverse(start_node);
                    g.clean();
                    break;
                case 5:
                    System.out.println("Enter the starting node to traverse the graph: ");
                    int snode = s.nextInt();
                    g.bfs_traverse(snode);
                    g.clean();
                    break;
                    case 6:
                        System.out.println("Enter the starting node to detect cycle in the graph: ");
                        int start = s.nextInt();
                        System.out.println(g.detect_cycle_using_dfs(start,-1));
                        g.clean();
                        break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
