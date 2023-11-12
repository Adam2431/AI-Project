package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class GenericSearch {

    HashSet<String> states;

    public Solution generalSearch(String strategy, Boolean visualize, Agent agent, State InitialState) {

        Solution solution = new Solution(null, "", 0, 0);

        ArrayList<Node> nodes = new ArrayList<Node>();
        states = new HashSet<String>();
        nodes.add(new Node(agent.state, null, null, 0, 0, 0, 0));
        while (!nodes.isEmpty()) {
            Node node = nodes.remove(0);

            solution.expansionSequence += node.action + " Depth " + node.depth;
            if (node.goalTest()) {
                solution.node = node;
                return solution;
            }
            if (stateRepeated(node.state, states)) {
                if (visualize) {
                    System.out.println("State Repeated!");
                }
                continue;
            }
            System.out.println(solution.nodesExpanded);
            solution.expansionSequence += " --> ";
            if (visualize) {
                System.out.println();
            }
            nodes = addToQueue(strategy, nodes, expandNode(node, solution, agent, InitialState, visualize), solution,
                    agent, InitialState, visualize);
        }

        return null;
    }

    public ArrayList<Node> addToQueue(String strategy, ArrayList<Node> oldNodes, ArrayList<Node> newNodes,
            Solution solution, Agent agent, State InitialState, Boolean visualize) {
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

                if (!newNodes.isEmpty() && newNodes.get(0).depth <= solution.currlevel) {
                    newNodes.addAll(oldNodes);
                    return newNodes;
                } else if (oldNodes.isEmpty()) {
                    solution.currlevel += 1;
                    agent.state = InitialState;
                    oldNodes.add(new Node(agent.state, null, null, 0, 0, 0, 0));
                    // new queue with the
                    // root node only
                    return oldNodes;
                }
                return oldNodes;

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
                if (visualize) {
                    System.out.println("Invalid Strategy");
                }
                return oldNodes;

        }

    }

    public ArrayList<Node> expandNode(Node node, Solution solution, Agent agent, State InitialState,
            Boolean visualize) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        solution.nodesExpanded += 1;
        if (node.state.moneySpent > 100000) {
            if (visualize) {
                System.out.println("Money Spent Exceeded!");
            }
        } else {
            for (int i = 0; i < agent.actions.size(); i++) {
                State state = new State(node.state);
                agent.state = state;
                boolean actionDone = agent.doAction(node.state, agent.actions.get(i), InitialState);
                if (actionDone) {
                    Node addedNode = new Node(state, node, agent.actions.get(i),
                            node.depth + 1,
                            agent.state.pathCost,
                            agent.state.heuristicOne, agent.state.heuristicTwo);
                    if (visualize) {
                        System.out.println(addedNode);
                        System.out.println(agent);
                    }
                    nodes.add(addedNode);
                } else {
                    if (visualize) {
                        System.out.println("Not Enough Resources or Invalid Action");
                    }
                }
            }
        }
        return nodes;

    }

    public boolean stateRepeated(State state, HashSet<String> states) {
        if (states.contains(state.hashString())) {
            return true;
        }
        states.add(state.hashString());
        return false;
    }

    public ArrayList<Node> sort(ArrayList<Node> nodes) {

        nodes.sort(Comparator.comparing(node -> node.state.moneySpent));
        return nodes;
    }

    private ArrayList<Node> sortAHeuristicTwo(ArrayList<Node> oldNodes) {
        return null;
    }

    private ArrayList<Node> sortAHeuristicOne(ArrayList<Node> oldNodes) {
        return null;
    }

    private ArrayList<Node> sortHeuristicTwo(ArrayList<Node> oldNodes) {
        return null;
    }

    private ArrayList<Node> sortHeuristicOne(ArrayList<Node> oldNodes) {
        return null;
    }
}
