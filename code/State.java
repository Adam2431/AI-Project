package code;

public class State {

    int food;
    int materials;
    int energy;
    int prosperity;
    int moneySpent;
    Request request;
    int heuristicOne;
    int heuristicTwo;

    public State(int food, int materials, int energy, int prosperity, int moneySpent) {
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.prosperity = prosperity;
        this.moneySpent = moneySpent;
        request = new Request("", 0, 0);
    }

    public State(State state) {
        food = state.food;
        materials = state.materials;
        energy = state.energy;
        prosperity = state.prosperity;
        moneySpent = state.moneySpent;
        heuristicOne = state.heuristicOne;
        heuristicTwo = state.heuristicTwo;
        request = new Request(state.request.type, state.request.amount, state.request.delay);
    }

    public String hashString() {
        String hash = "" + food + "," + materials + "," + energy + "," + prosperity + "," + moneySpent + ","
                + request.type + "," + request.delay;
        return hash;
    }

    public String toString() {
        String state = "";

        state += "Food: " + food + "\n";
        state += "Materials: " + materials + "\n";
        state += "Energy: " + energy + "\n";
        state += "Prosperity: " + prosperity + "\n";
        state += "Money Spent: " + moneySpent + "\n";
        state += "Heuristic One: " + heuristicOne + "\n";
        state += "Heuristic Two: " + heuristicTwo + "\n";
        state += "Request Type: " + request.type + "\n";
        state += "Request Amount: " + request.amount + "\n";
        state += "Request Delay: " + request.delay + "\n";

        return state;
    }

    public boolean equals(State state) {
        if (this.food == state.food && this.materials == state.materials && this.energy == state.energy
                && this.prosperity == state.prosperity && this.moneySpent == state.moneySpent
                && this.request.equals(state.request))
            return true;
        else
            return false;
    }
}
