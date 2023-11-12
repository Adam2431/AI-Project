package code;

public class LLAPSearch extends GenericSearch {
    public static String solve(String initalState, String strategy, Boolean visualize) {

        Main.visualize = visualize;

        Main.InitialState = new State(initalState);
        Main.agent = new Agent(Main.InitialState);
        Node goalNode = GenericSearch.generalSearch(strategy);
        if (visualize) {
            System.out.println("Initial State: " + Main.InitialState);
            System.out.println("Expansion Sequence: ");
            System.out.print(GenericSearch.expansionSequence);
        }
        if (goalNode != null) {
            if (visualize) {
                System.out.println("Solution Found!");
                System.out.println("Path Cost: " + goalNode.pathCost);
                System.out.println("Nodes Expanded: " + GenericSearch.nodesExpanded);
            }
            return recursivePrint(goalNode, visualize) + ";" + goalNode.state.moneySpent + ";"
                    + GenericSearch.nodesExpanded;
        } else {
            if (visualize)
                System.out.println("NOSOLUTION");
            return "NOSOLUTION";
        }
    }

    static String recursivePrint(Node node, Boolean visualize) {
        if (node.parent.parent == null) {
            if (visualize)
                System.out.print(node.action + " Depth " + node.depth);
            return node.action;
        }

        if (visualize)
            System.out.print(node.action + " Depth " + node.depth + " <-- ");
        return (recursivePrint(node.parent, visualize) + "," + node.action);
    }
}
