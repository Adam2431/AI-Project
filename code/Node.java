package code;

public class Node implements Comparable<Node> {
    State state;
    Node parent;
    String action;
    int depth;

    public Node(State state, Node parent, String action, int depth) {
        super();
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.depth = depth;
    }

    public boolean equals(Node node) {
        if (this.state.equals(node.state))
            return true;
        else
            return false;
    }

    @Override
    public int compareTo(Node node) {
        // TODO Auto-generated method stub

        if (GenericSearch.staticStrategy.equals("GR1")) {
            return Integer.compare(node.state.heuristicOne, this.state.heuristicOne);
        } else if (GenericSearch.staticStrategy.equals("GR2")) {
            return Integer.compare(node.state.heuristicTwo, this.state.heuristicTwo);
        } else if (GenericSearch.staticStrategy.equals("AS1")) {
            return Integer.compare(node.state.heuristicOne + node.state.moneySpent,
                    this.state.heuristicOne + this.state.moneySpent);
        } else if (GenericSearch.staticStrategy.equals("AS2")) {
            return Integer.compare(node.state.heuristicTwo + node.state.moneySpent,
                    this.state.heuristicTwo + this.state.moneySpent);
        } else if (GenericSearch.staticStrategy.equals("UC")) {
            return Integer.compare(node.state.moneySpent, this.state.moneySpent);
        } else {
            return 0;
        }
    }

    public boolean goalTest() {
        if (this.state.prosperity >= 100) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Action: " + action + "\n" + "Depth: " + depth;
    }
}
