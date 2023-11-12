package code;

public class Solution {
    Node node;
    String expansionSequence;
    int nodesExpanded;
    int currlevel = 0;

    public Solution(Node node, String expansionSequence, int nodesExpanded, int currlevel) {
        super();
        this.node = node;
        this.expansionSequence = expansionSequence;
        this.nodesExpanded = nodesExpanded;
        this.currlevel = currlevel;
    }
}
