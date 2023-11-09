import java.util.ArrayList;
import java.util.Comparator;

public class GenericSearch {

    public static int nodesExpanded = 0;

    public static Node generalSearch(String strategy) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(Main.agent.state, null, null, 0, 0, 0, 0));
        while (!nodes.isEmpty()) {
            Node node = nodes.remove(0);
            if (node.goalTest()) {
                return node;
            }
            nodes = addToQueue(strategy, nodes, expandNode(node));
        }
        return null;
    }

    public static ArrayList<Node> addToQueue(String strategy, ArrayList<Node> oldNodes, ArrayList<Node> newNodes) {
        switch (strategy) {
            case "BFS": {
                oldNodes.addAll(newNodes);
                return oldNodes;
            }
            case "DFS": {
                newNodes.addAll(oldNodes);
                return newNodes;
            }
            case "ID": {

                // if (!newNodes.isEmpty() && newNodes.get(0).depth <= currlevel) {
                // newNodes.addAll(oldNodes);
                // return newNodes;
                // } else if (oldNodes.isEmpty()) {
                // currlevel += 1;
                // states = new Hashtable<String, String>();
                // oldNodes.add(new SearchTreeNode(this.initialState, null, null, 0, 0, 0, 0));
                // // new queue with the
                // // root node only
                // return oldNodes;
                // }
                // return oldNodes;

            }

            case "UC": {
                oldNodes.addAll(newNodes);
                return sort(oldNodes);
            }

            case "GR1": {
                oldNodes.addAll(newNodes);
                return sortHeuristicOne(oldNodes);
            }

            case "GR2": {
                oldNodes.addAll(newNodes);
                return sortHeuristicTwo(oldNodes);
            }

            case "AS1": {
                oldNodes.addAll(newNodes);
                return sortAHeuristicOne(oldNodes);
            }

            case "AS2": {
                oldNodes.addAll(newNodes);
                return sortAHeuristicTwo(oldNodes);
            }

            default:
                return oldNodes;

        }

    }

    public static ArrayList<Node> expandNode(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodesExpanded += 1;
        handleRequests(node.state);
        for (int i = 0; i < Main.agent.actions.size(); i++) {
            State state = new State(node.state);
            Main.agent.state = state;
            ArrayList<String> newRequest = Main.agent.doAction(node.state, Main.agent.actions.get(i));
            if (newRequest != null) {
                if (!newRequest.isEmpty()) {
                    state.requests.add(newRequest);
                }
                Node addedNode = new Node(state, node, Main.agent.actions.get(i),
                        node.depth + 1,
                        Main.agent.state.pathCost,
                        Main.agent.state.heuristicOne, Main.agent.state.heuristicTwo);
                System.out.println(addedNode);
                System.out.println(Main.agent);
                nodes.add(addedNode);
            } else {
                System.out.println("Not Enough Resources");
            }
        }
        return nodes;

    }

    public static void handleRequests(State state) {
        int delay;
        int amount;
        for (int i = 0; i < state.requests.size(); i++) {
            delay = Integer.parseInt(state.requests.get(i).get(2));
            if (delay == 0) {
                amount = Integer.parseInt(state.requests.get(i).get(1));
                if (state.requests.get(i).get(0).equals("food")) {
                    state.food += amount;
                    state.requests.remove(i);
                    i--;
                } else if (state.requests.get(i).get(0).equals("materials")) {
                    state.materials += amount;
                    state.requests.remove(i);
                    i--;
                } else if (state.requests.get(i).get(0).equals("energy")) {
                    state.energy += amount;
                    state.requests.remove(i);
                    i--;
                }
            } else {
                delay--;
                state.requests.get(i).set(2, Integer.toString(delay));
            }
        }
    }

    public static ArrayList<Node> sort(ArrayList<Node> nodes) {

        nodes.sort(Comparator.comparing(node -> node.pathCost));
        return nodes;
    }

    private static ArrayList<Node> sortAHeuristicTwo(ArrayList<Node> oldNodes) {
        return null;
    }

    private static ArrayList<Node> sortAHeuristicOne(ArrayList<Node> oldNodes) {
        return null;
    }

    private static ArrayList<Node> sortHeuristicTwo(ArrayList<Node> oldNodes) {
        return null;
    }

    private static ArrayList<Node> sortHeuristicOne(ArrayList<Node> oldNodes) {
        return null;
    }
}
