public class Main {

    static State InitialState;

    static Agent agent;

    static void solve(String initalState, String strategy, boolean visualize) {
        InitialState = new State(initalState);
        agent = new Agent(InitialState);
        System.out.println(InitialState);
        GenericSearch.generalSearch(strategy);
        Node goalNode = GenericSearch.generalSearch(strategy);
        if (goalNode != null) {
            System.out.println("Solution Found!");
            System.out.println("Path Cost: " + goalNode.pathCost);
            System.out.println("Nodes Expanded: " + GenericSearch.nodesExpanded);
        } else {
            System.out.println("NoSolution!");
        }
    }

    public static void main(String[] args) {
        solve(
                "50;" +
                        "22,22,22;" +
                        "50,60,70;" +
                        "30,2;" +
                        "19,1;" +
                        "15,1;" +
                        "300,5,7,3,20;" +
                        "500,8,6,3,40;",
                "BF", true);
    }
}