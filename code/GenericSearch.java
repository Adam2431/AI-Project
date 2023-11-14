package code;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;

public class GenericSearch {

    HashSet<String> states;

    public static String staticStrategy;

    public Solution generalSearch(String strategy, Boolean visualize, Agent agent, State InitialState) {

        Solution solution = new Solution(null, "", 0, 0);

        staticStrategy = strategy;

        PriorityQueue<Node> nodes = new PriorityQueue<Node>();
        states = new HashSet<String>();
        nodes.add(new Node(agent.state, null, null, 0, 0, 0));
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            // if (visualize) {
            // solution.expansionSequence += node.action + " Depth " + node.depth;
            // }

            if (node.state.moneySpent > 100000) {
                if (visualize) {
                    System.out.println("Money Spent Exceeded!");
                }
                continue;
            }
            if (node.goalTest()) {
                solution.node = node;
                return solution;
            }
            if (stateRepeated(node.state, states) && !strategy.equals("ID")) {
                if (visualize) {
                    System.out.println("State Repeated!");
                }
                continue;
            }
            if (visualize) {
                System.out.println(solution.nodesExpanded);
                solution.expansionSequence += " --> ";
            }
            nodes = addToQueue(strategy, nodes, expandNode(node, solution, agent, InitialState, visualize), solution,
                    agent, InitialState, visualize);
        }

        return solution;
    }

    public PriorityQueue<Node> addToQueue(String strategy, PriorityQueue<Node> oldNodes, PriorityQueue<Node> newNodes,
            Solution solution, Agent agent, State InitialState, Boolean visualize) {
        switch (strategy) {
            case "BF": {
                oldNodes.addAll(newNodes);
                return oldNodes;
            }
            case "DF": {
                newNodes.addAll(oldNodes);
                // while (!newNodes.isEmpty()) {
                // oldNodes.addFirst(newNodes.removeLast());
                // }
                return newNodes;
            }
            case "ID": {

                if (!newNodes.isEmpty() && newNodes.peek().depth <= solution.currlevel) {
                    newNodes.addAll(oldNodes);
                    return newNodes;
                } else if (oldNodes.isEmpty()) {
                    solution.currlevel += 1;
                    agent.state = InitialState;
                    oldNodes.add(new Node(agent.state, null, null, 0, 0, 0));
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
                return (oldNodes);
            }

            case "GR2": {
                oldNodes.addAll(newNodes);
                return (oldNodes);
            }

            case "AS1": {
                oldNodes.addAll(newNodes);
                return (oldNodes);
            }

            case "AS2": {
                oldNodes.addAll(newNodes);
                return (oldNodes);
            }

            default:
                if (visualize) {
                    System.out.println("Invalid Strategy");
                }
                return oldNodes;

        }

    }

    public PriorityQueue<Node> expandNode(Node node, Solution solution, Agent agent, State InitialState,
            Boolean visualize) {
        PriorityQueue<Node> nodes = new PriorityQueue<Node>();
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
                            agent.state.heuristicOne, agent.state.heuristicTwo);
                    if (staticStrategy.equals("GR1") || staticStrategy.equals("AS1"))
                        state.heuristicOne = heuristicOne(state, InitialState);
                    if (staticStrategy.equals("GR2") || staticStrategy.equals("AS2"))
                        state.heuristicTwo = heuristicTwo(state, InitialState);
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

    public int heuristicOne(State state, State InitialState) {
        double remainingProsperity = 100 - state.prosperity;
        double maxProsperityGain = Math.max(InitialState.prosperityBUILD2, InitialState.prosperityBUILD1);
        return (int) Math.ceil(remainingProsperity / maxProsperityGain)
                * Math.min(InitialState.priceBUILD1, InitialState.priceBUILD2);
    }

    public int heuristicTwo(State state, State InitialState) {
        double remainingProsperity = 100 - state.prosperity;
        double maxProsperityGain = Math.max(InitialState.prosperityBUILD2, InitialState.prosperityBUILD1);
        return (int) Math.ceil(remainingProsperity / maxProsperityGain)
                * Math.min(
                        InitialState.unitPriceFood * InitialState.foodUseBUILD1
                                + InitialState.unitPriceMaterials * InitialState.materialsUseBUILD1
                                + InitialState.unitPriceEnergy * InitialState.energyUseBUILD1,
                        InitialState.unitPriceFood * InitialState.foodUseBUILD2
                                + InitialState.unitPriceMaterials * InitialState.materialsUseBUILD2
                                + InitialState.unitPriceEnergy * InitialState.energyUseBUILD2);
    }

    public boolean stateRepeated(State state, HashSet<String> states) {
        if (states.contains(state.hashString())) {
            return true;
        }
        states.add(state.hashString());
        return false;
    }

    public PriorityQueue<Node> sort(PriorityQueue<Node> nodes) {

        // nodes.sort(Comparator.comparing(node -> node.state.moneySpent));
        return nodes;
    }

}
