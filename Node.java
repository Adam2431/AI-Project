public class Node {
    State state;
    Node parent;
    String action;
    int depth;
    int pathCost;
    int heuristicOne;
    int heuristicTwo;

    public Node(State state, Node parent, String action, int depth, int pathCost,
            int heuristicOne,
            int heuristicTwo) {
        super();
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.depth = depth;
        this.pathCost = pathCost;
        this.heuristicOne = heuristicOne;
        this.heuristicTwo = heuristicTwo;
    }

    public boolean goalTest() {
        if (this.state.prosperity == 100) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Action: " + action + "\n" + "Depth: " + depth;
    }
}
