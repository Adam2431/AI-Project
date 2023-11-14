package code;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Deque;
import java.util.HashSet;

public class GenericSearch {

    HashSet<String> states;

    public static String staticStrategy;

    PriorityQueue<Node> pqNodes;
    Deque<Node> dequeNodes;

    public Solution generalSearch(String strategy, Boolean visualize, Agent agent, State InitialState) {

        Solution solution = new Solution(null, "", 0, 0);

        staticStrategy = strategy;

        pqNodes = new PriorityQueue<Node>();
        dequeNodes = new LinkedList<Node>();
        states = new HashSet<String>();

        switch (strategy) {
            case "BF":
                return BreadthFirstSearch(dequeNodes, states, strategy, visualize, agent, solution);
            case "DF":
                return DepthFirstSearch(dequeNodes, states, strategy, visualize, agent, solution);
            case "UC":
                return UniformCostSearch(pqNodes, states, strategy, visualize, agent, solution);
            case "ID":
                return HelperIterativeDeepening(solution, visualize, agent, InitialState);
            case "GR1":
                return UniformCostSearch(pqNodes, states, strategy, visualize, agent, solution);
            case "GR2":
                return UniformCostSearch(pqNodes, states, strategy, visualize, agent, solution);
            case "AS1":
                return UniformCostSearch(pqNodes, states, strategy, visualize, agent, solution);
            case "AS2":
                return UniformCostSearch(pqNodes, states, strategy, visualize, agent, solution);
            default:
                return solution;
        }
    }

    private void IterativeDeepening(Deque<Node> nodes, HashSet<String> states, int currentLevel,
            boolean visualize, Agent agent, Solution solution) {
        nodes.add(new Node(agent.state, null, null, 0));
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
                return;
            }
            if (stateRepeated(node.state, states)) {
                if (visualize) {
                    System.out.println("State Repeated!");
                }
                continue;
            }
            if (visualize) {
                System.out.println(solution.nodesExpanded);
                solution.expansionSequence += " --> ";
            }

            solution.nodesExpanded += 1;
            if (node.state.moneySpent > 100000) {
                if (visualize) {
                    System.out.println("Money Spent Exceeded!");
                }
            } else {
                for (int i = 0; i < agent.actions.size(); i++) {
                    State state = new State(node.state);
                    agent.state = state;
                    boolean actionDone = agent.doAction(node.state, agent.actions.get(i));
                    if (actionDone) {
                        Node addedNode = new Node(state, node, agent.actions.get(i),
                                node.depth + 1);
                        if (visualize) {
                            System.out.println(addedNode);
                            System.out.println(agent);
                        }
                        if (addedNode.depth <= currentLevel)
                            nodes.addFirst((addedNode));
                    } else {
                        if (visualize) {
                            System.out.println("Not Enough Resources or Invalid Action");
                        }
                    }
                }
            }
        }
    }

    private Solution HelperIterativeDeepening(Solution solution, boolean visualize, Agent agent, State InitialState) {
        for (int level = 0; level <= 1_000_000; level++) {
            agent.state = InitialState;
            IterativeDeepening(new LinkedList<Node>(), new HashSet<String>(), level, visualize, agent,
                    solution);
            if (solution.node != null) {
                return solution;
            }
        }
        return solution;
    }

    private Solution BreadthFirstSearch(Deque<Node> nodes, HashSet<String> states, String strategy, Boolean visualize,
            Agent agent,
            Solution solution) {

        nodes.add(new Node(agent.state, null, null, 0));
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

            solution.nodesExpanded += 1;
            if (node.state.moneySpent > 100000) {
                if (visualize) {
                    System.out.println("Money Spent Exceeded!");
                }
            } else {
                for (int i = 0; i < agent.actions.size(); i++) {
                    State state = new State(node.state);
                    agent.state = state;
                    boolean actionDone = agent.doAction(node.state, agent.actions.get(i));
                    if (actionDone) {
                        Node addedNode = new Node(state, node, agent.actions.get(i),
                                node.depth + 1);
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
        }
        return solution;
    }

    private Solution DepthFirstSearch(Deque<Node> nodes, HashSet<String> states, String strategy, Boolean visualize,
            Agent agent,
            Solution solution) {

        nodes.add(new Node(agent.state, null, null, 0));
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

            solution.nodesExpanded += 1;
            if (node.state.moneySpent > 100000) {
                if (visualize) {
                    System.out.println("Money Spent Exceeded!");
                }
            } else {
                for (int i = 0; i < agent.actions.size(); i++) {
                    State state = new State(node.state);
                    agent.state = state;
                    boolean actionDone = agent.doAction(node.state, agent.actions.get(i));
                    if (actionDone) {
                        Node addedNode = new Node(state, node, agent.actions.get(i),
                                node.depth + 1);
                        if (visualize) {
                            System.out.println(addedNode);
                            System.out.println(agent);
                        }
                        nodes.addFirst(addedNode);
                    } else {
                        if (visualize) {
                            System.out.println("Not Enough Resources or Invalid Action");
                        }
                    }
                }
            }
        }
        return solution;
    }

    private Solution UniformCostSearch(PriorityQueue<Node> nodes, HashSet<String> states, String strategy,
            Boolean visualize,
            Agent agent,
            Solution solution) {

        nodes.add(new Node(agent.state, null, null, 0));
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

            solution.nodesExpanded += 1;
            if (node.state.moneySpent > 100000) {
                if (visualize) {
                    System.out.println("Money Spent Exceeded!");
                }
            } else {
                for (int i = 0; i < agent.actions.size(); i++) {
                    State state = new State(node.state);
                    agent.state = state;
                    boolean actionDone = agent.doAction(node.state, agent.actions.get(i));
                    if (actionDone) {
                        Node addedNode = new Node(state, node, agent.actions.get(i),
                                node.depth + 1);
                        if (staticStrategy.equals("GR1") || staticStrategy.equals("AS1"))
                            state.heuristicOne = heuristicOne(state);
                        if (staticStrategy.equals("GR2") || staticStrategy.equals("AS2"))
                            state.heuristicTwo = heuristicTwo(state);
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
        }
        return solution;
    }

    // nodes = addToQueue(strategy, nodes, expandNode(node, solution, agent,
    // Input, visualize), solution,
    // agent, Input, visualize);

    public int heuristicOne(State state) {
        // double remainingProsperity = 100 - state.prosperity;
        // double maxProsperityGain = Math.max(Input.prosperityBUILD2,
        // Input.prosperityBUILD1);
        // int prosp = (int) Math.ceil(remainingProsperity / maxProsperityGain)
        // * Math.min(Input.priceBUILD1, Input.priceBUILD2);

        double properityRate = Math.max(Input.prosperityBUILD1 / (double) Input.priceBUILD1,
                Input.prosperityBUILD2 / (double) Input.priceBUILD2);

        // double resources = Math.sqrt(state.food) + Math.sqrt(state.materials) +
        // Math.sqrt(state.energy);
        double resources = (state.food / (double) Input.amountRequestFood * Input.unitPriceFood)
                + (state.materials / (double) Input.amountRequestMaterials * Input.unitPriceMaterials)
                + (state.energy / (double) Input.amountRequestEnergy * Input.unitPriceEnergy);

        double prosp = state.prosperity / properityRate;
        return (int) (1.5 * resources + prosp);
    }

    public int heuristicTwo(State state) {
        double properityRate = Math.max(Input.prosperityBUILD1 / (double) (Input.foodUseBUILD1 * Input.unitPriceFood +
                Input.materialsUseBUILD1 * Input.unitPriceMaterials +
                Input.energyUseBUILD1 * Input.unitPriceEnergy),
                Input.prosperityBUILD2 / (double) (Input.foodUseBUILD2 * Input.unitPriceFood +
                        Input.materialsUseBUILD2 * Input.unitPriceMaterials +
                        Input.energyUseBUILD2 * Input.unitPriceEnergy));

        double resources = ((state.food - Input.initialFood) / (double) Input.amountRequestFood * Input.unitPriceFood)
                + ((state.materials - Input.initialMaterials) / (double) Input.amountRequestMaterials
                        * Input.unitPriceMaterials)
                + ((state.energy + Input.initialEnergy) / (double) Input.amountRequestEnergy * Input.unitPriceEnergy);

        double prosp = (state.prosperity - Input.initialProsperity) / properityRate;
        return (int) (resources + prosp);
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
