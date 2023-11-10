package code;
import java.util.ArrayList;

public class Agent {
    State state;
    ArrayList<String> actions;

    public Agent(State state) {
        this.state = state;
        actions = new ArrayList<String>();
        actions.add("RequestFood");
        actions.add("RequestMaterials");
        actions.add("RequestEnergy");
        actions.add("WAIT");
        actions.add("BUILD1");
        actions.add("BUILD2");
    }

    public ArrayList<String> doAction(State state, String action) {
        switch (action) {
            case "RequestFood":
                return RequestFood(Main.InitialState.amountRequestFood, Main.InitialState.delayRequestFood);
            case "RequestMaterials":
                return RequestMaterials(Main.InitialState.amountRequestMaterials,
                        Main.InitialState.delayRequestMaterials);
            case "RequestEnergy":
                return RequestEnergy(Main.InitialState.amountRequestEnergy, Main.InitialState.delayRequestEnergy);
            case "WAIT":
                return WAIT();
            case "BUILD1":
                return BUILD1(Main.InitialState.foodUseBUILD1, Main.InitialState.materialsUseBUILD1,
                        Main.InitialState.energyUseBUILD1, Main.InitialState.prosperityBUILD1);
            case "BUILD2":
                return BUILD2(Main.InitialState.foodUseBUILD2, Main.InitialState.materialsUseBUILD2,
                        Main.InitialState.energyUseBUILD2, Main.InitialState.prosperityBUILD2);
            default:
                return null;
        }
    }

    public ArrayList<String> RequestFood(int amount, int delay) {
        if (state.food > 0 & state.materials > 0 & state.energy > 0) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += Main.InitialState.unitPriceFood;
            state.moneySpent += Main.InitialState.unitPriceMaterials;
            state.moneySpent += Main.InitialState.unitPriceEnergy;

            ArrayList<String> request = new ArrayList<String>();
            request.add("food");
            request.add(Integer.toString(amount));
            request.add(Integer.toString(delay));
            return request;
        } else
            return null;
    }

    public ArrayList<String> RequestMaterials(int amount, int delay) {

        if (state.food > 0 & state.materials > 0 & state.energy > 0) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += Main.InitialState.unitPriceFood;
            state.moneySpent += Main.InitialState.unitPriceMaterials;
            state.moneySpent += Main.InitialState.unitPriceEnergy;

            ArrayList<String> request = new ArrayList<String>();
            request.add("materials");
            request.add(Integer.toString(amount));
            request.add(Integer.toString(delay));
            return request;
        } else
            return null;

    }

    public ArrayList<String> RequestEnergy(int amount, int delay) {

        if (state.food > 0 & state.materials > 0 & state.energy > 0) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += Main.InitialState.unitPriceFood;
            state.moneySpent += Main.InitialState.unitPriceMaterials;
            state.moneySpent += Main.InitialState.unitPriceEnergy;

            ArrayList<String> request = new ArrayList<String>();
            request.add("energy");
            request.add(Integer.toString(amount));
            request.add(Integer.toString(delay));
            return request;
        } else
            return null;
    }

    public ArrayList<String> WAIT() {

        if (state.food > 0 && state.materials > 0 && state.energy > 0) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += Main.InitialState.unitPriceFood;
            state.moneySpent += Main.InitialState.unitPriceMaterials;
            state.moneySpent += Main.InitialState.unitPriceEnergy;
            return new ArrayList<>();
        } else
            return null;
    }

    public ArrayList<String> BUILD1(int food, int materials, int energy, int prosperity) {
        if (state.food > Main.InitialState.foodUseBUILD1 && state.materials > Main.InitialState.materialsUseBUILD1
                && state.energy > Main.InitialState.energyUseBUILD1) {
            state.food -= Main.InitialState.foodUseBUILD1;
            state.materials -= Main.InitialState.materialsUseBUILD1;
            state.energy -= Main.InitialState.energyUseBUILD1;
            state.prosperity += Main.InitialState.prosperityBUILD1;

            state.moneySpent += Main.InitialState.unitPriceFood * Main.InitialState.foodUseBUILD1;
            state.moneySpent += Main.InitialState.unitPriceMaterials * Main.InitialState.materialsUseBUILD1;
            state.moneySpent += Main.InitialState.unitPriceEnergy * Main.InitialState.energyUseBUILD1;
            state.moneySpent += Main.InitialState.priceBUILD1;
            return new ArrayList<>();
        } else
            return null;
    }

    public ArrayList<String> BUILD2(int food, int materials, int energy, int prosperity) {
        if (state.food > Main.InitialState.foodUseBUILD2 && state.materials > Main.InitialState.materialsUseBUILD2
                && state.energy > Main.InitialState.energyUseBUILD2) {
            state.food -= Main.InitialState.foodUseBUILD2;
            state.materials -= Main.InitialState.materialsUseBUILD2;
            state.energy -= Main.InitialState.energyUseBUILD2;
            state.prosperity += Main.InitialState.prosperityBUILD2;
            state.moneySpent += Main.InitialState.unitPriceFood * Main.InitialState.foodUseBUILD2;
            state.moneySpent += Main.InitialState.unitPriceMaterials * Main.InitialState.materialsUseBUILD2;
            state.moneySpent += Main.InitialState.unitPriceEnergy * Main.InitialState.energyUseBUILD2;
            state.moneySpent += Main.InitialState.priceBUILD2;
            return new ArrayList<>();
        } else
            return null;
    }

    public String toString() {
        String currentState = "";
        currentState += "prosperity: " + state.prosperity + "\n";
        currentState += "food: " + state.food + "\n";
        currentState += "materials: " + state.materials + "\n";
        currentState += "energy: " + state.energy + "\n";
        currentState += "moneySpent: " + state.moneySpent + "\n";
        return currentState;
    }
}
