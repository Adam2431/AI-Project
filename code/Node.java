package code;

public class Node implements Comparable<Node> {
    State state;
    Node parent;
    String action;
    int depth;
    int heuristicOne;
    int heuristicTwo;

    public Node(State state, Node parent, String action, int depth,
            int heuristicOne,
            int heuristicTwo) {
        super();
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.depth = depth;
        this.heuristicOne = heuristicOne;
        this.heuristicTwo = heuristicTwo;
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

    public boolean equals(Node node) {
        if (this.state.equals(node.state))
            return true;
        else
            return false;
    }

    @Override
    public int compareTo(Node node) {
        // TODO Auto-generated method stub

        if (GenericSearch.staticStrategy.equals("DF")) {
            return Integer.compare(this.depth, node.depth);
        }
        if (GenericSearch.staticStrategy.equals("GR1")) {
            return Integer.compare(node.heuristicOne, this.heuristicOne);
        } else if (GenericSearch.staticStrategy.equals("GR2")) {
            return Integer.compare(node.heuristicTwo, this.heuristicTwo);
        } else if (GenericSearch.staticStrategy.equals("AS1")) {
            return Integer.compare(node.heuristicOne + node.state.moneySpent,
                    this.heuristicOne + this.state.moneySpent);
        } else if (GenericSearch.staticStrategy.equals("AS2")) {
            return Integer.compare(node.heuristicTwo + node.state.moneySpent,
                    this.heuristicTwo + this.state.moneySpent);
        } else if (GenericSearch.staticStrategy.equals("UC")) {
            return Integer.compare(node.state.moneySpent, this.state.moneySpent);
        } else {
            return 0;
        }
    }
}
