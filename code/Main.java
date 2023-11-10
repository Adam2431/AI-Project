package code;
public class Main {

    static State InitialState;

    static Agent agent;

    public static void main(String[] args) {
        LLAPSearch.solve(
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