package code;
public class LLAPSearch extends GenericSearch{
    public static String solve(String initalState, String strategy, Boolean visualize){
        
        Main.InitialState = new State(initalState);
        Main.agent = new Agent(Main.InitialState);
        Node goalNode = GenericSearch.generalSearch(strategy);
        System.out.println("Expansion Sequence: ");
        System.out.print(GenericSearch.expansionSequence);
        if (goalNode != null) {
            System.out.println("Solution Found!");
            System.out.println("Path Cost: " + goalNode.pathCost);
            System.out.println("Nodes Expanded: " + GenericSearch.nodesExpanded);
            return recursivePrint(goalNode) + ";" + goalNode.state.moneySpent + ";" + GenericSearch.nodesExpanded;
        } else {
            if(visualize)
                System.out.println("NOSOLUTION");
            return "NOSOLUTION";
        }
    }

    static String recursivePrint(Node node){
        if(node.parent.parent == null){
            System.out.print(node.action + " Depth " + node.depth);
            return node.action;
        }
        System.out.print(" --> " + node.action + " Depth " + node.depth);
        return (recursivePrint(node.parent) + "," + node.action);
    }
}
