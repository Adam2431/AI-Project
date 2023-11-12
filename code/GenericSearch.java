package code;

import java.util.ArrayList;
import java.util.Comparator;

public class GenericSearch {

    public static int nodesExpanded = 0;

    public static String expansionSequence = "";

    public static Node generalSearch(String strategy) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(Main.agent.state, null, null, 0, 0, 0, 0));
        while (!nodes.isEmpty()) {
            Node node = nodes.remove(0);

            expansionSequence += node.action + " Depth " + node.depth;
            if (node.goalTest()) {
                return node;
            }
            expansionSequence += " --> ";
            if (Main.visualize) {
                System.out.println();
            }
            nodes = addToQueue(strategy, nodes, expandNode(node));
        }
        return null;
    }

    public static ArrayList<Node> addToQueue(String strategy, ArrayList<Node> oldNodes, ArrayList<Node> newNodes) {
        switch (strategy) {
            case "BF": {
                oldNodes.addAll(newNodes);
                return oldNodes;
            }
            case "DF": {
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
                if (Main.visualize) {
                    System.out.println("Invalid Strategy");
                }
                return oldNodes;

        }

    }

    public static ArrayList<Node> expandNode(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodesExpanded += 1;
        if (node.state.moneySpent > 100000) {
            if (Main.visualize) {
                System.out.println("Money Spent Exceeded!");
            }
        } else if (stateRepeated(node.state)) {
            if (Main.visualize) {
                System.out.println("State Repeated!");
            }
        } else {
            for (int i = 0; i < Main.agent.actions.size(); i++) {
                State state = new State(node.state);
                Main.agent.state = state;
                boolean actionDone = Main.agent.doAction(node.state, Main.agent.actions.get(i));
                if (actionDone) {
                    Node addedNode = new Node(state, node, Main.agent.actions.get(i),
                            node.depth + 1,
                            Main.agent.state.pathCost,
                            Main.agent.state.heuristicOne, Main.agent.state.heuristicTwo);
                    if (Main.visualize) {
                        System.out.println(addedNode);
                        System.out.println(Main.agent);
                    }
                    nodes.add(addedNode);
                } else {
                    if (Main.visualize) {
                        System.out.println("Not Enough Resources or Invalid Action");
                    }
                }
            }
        }
        return nodes;

    }

    public static boolean stateRepeated(State state) {
        if (Main.agent.states.containsKey(state.hashString())) {
            return true;
        }
        Main.agent.states.put(state.hashString(), state);
        return false;
    }

    public static ArrayList<Node> sort(ArrayList<Node> nodes) {

        nodes.sort(Comparator.comparing(node -> node.state.moneySpent));
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
