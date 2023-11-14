package code;

public class LLAPSearch extends GenericSearch {

    public static String solve(String initalState, String strategy, Boolean visualize) {

        Input.initalizeInputs(initalState);

        State InitialState = new State(Input.initialFood, Input.initialMaterials, Input.initialEnergy,
                Input.initialProsperity, 0);
        Agent agent = new Agent(InitialState);
        LLAPSearch llapSearch = new LLAPSearch();

        Solution solution = llapSearch.generalSearch(strategy, visualize, agent, InitialState);
        Node goalNode = solution.node;
        if (visualize) {
            System.out.println("Initial State: " + InitialState);
            System.out.println("Expansion Sequence: ");
            System.out.println(solution.expansionSequence);
        }
        if (goalNode != null) {
            if (visualize) {
                System.out.println("Solution Found!");
                System.out.println("Money Spent: " + goalNode.state.moneySpent);
                System.out.println("Nodes Expanded: " + solution.nodesExpanded);
                System.out.println();
                System.out.println();
            }
            if (visualize) {
                llapSearch.recursivePrintVisualize(goalNode);
                System.out.println();
                System.out.println();
            }
            return llapSearch.recursivePrint(goalNode) + ";" + goalNode.state.moneySpent + ";"
                    + solution.nodesExpanded;
        } else {
            if (visualize)
                System.out.println("NOSOLUTION");
            return "NOSOLUTION";
        }
    }

    String recursivePrint(Node node) {
        if (node.parent.parent == null) {
            return node.action;
        }
        return (recursivePrint(node.parent) + "," + node.action);
    }

    void recursivePrintVisualize(Node node) {
        if (node.parent.parent == null) {
            System.out.print(node.action + " Depth " + node.depth);
            return;
        }
        recursivePrintVisualize(node.parent);
        System.out.print(" --> " + node.action + " Depth " + node.depth);
    }
}
