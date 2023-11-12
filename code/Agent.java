package code;

import java.util.ArrayList;
import java.util.HashMap;

public class Agent {
    State state;
    ArrayList<String> actions;
    HashMap<String, State> states;

    public Agent(State state) {
        this.state = state;
        actions = new ArrayList<String>();
        actions.add("RequestFood");
        actions.add("RequestMaterials");
        actions.add("RequestEnergy");
        actions.add("WAIT");
        actions.add("BUILD1");
        actions.add("BUILD2");
        states = new HashMap<String, State>();
    }

    public boolean doAction(State state, String action) {
        switch (action) {
            case "RequestFood":
                return RequestFood(Main.InitialState.unitPriceFood, Main.InitialState.unitPriceMaterials,
                        Main.InitialState.unitPriceEnergy, Main.InitialState.amountRequestFood,
                        Main.InitialState.delayRequestFood);
            case "RequestMaterials":
                return RequestMaterials(Main.InitialState.unitPriceFood, Main.InitialState.unitPriceMaterials,
                        Main.InitialState.unitPriceEnergy, Main.InitialState.amountRequestMaterials,
                        Main.InitialState.delayRequestMaterials);
            case "RequestEnergy":
                return RequestEnergy(Main.InitialState.unitPriceFood, Main.InitialState.unitPriceMaterials,
                        Main.InitialState.unitPriceEnergy, Main.InitialState.amountRequestEnergy,
                        Main.InitialState.delayRequestEnergy);
            case "WAIT":
                return WAIT(Main.InitialState.unitPriceFood, Main.InitialState.unitPriceMaterials,
                        Main.InitialState.unitPriceEnergy);
            case "BUILD1":
                return BUILD(Main.InitialState.foodUseBUILD1, Main.InitialState.materialsUseBUILD1,
                        Main.InitialState.energyUseBUILD1, Main.InitialState.unitPriceFood,
                        Main.InitialState.unitPriceMaterials, Main.InitialState.unitPriceEnergy,
                        Main.InitialState.priceBUILD1,
                        Main.InitialState.prosperityBUILD1);
            case "BUILD2":
                return BUILD(Main.InitialState.foodUseBUILD2, Main.InitialState.materialsUseBUILD2,
                        Main.InitialState.energyUseBUILD2, Main.InitialState.unitPriceFood,
                        Main.InitialState.unitPriceMaterials, Main.InitialState.unitPriceEnergy,
                        Main.InitialState.priceBUILD2,
                        Main.InitialState.prosperityBUILD2);
            default:
                return false;
        }
    }

    public boolean RequestFood(int priceFood, int priceMaterials, int priceEnergy, int amount, int delay) {

        if (state.food >= 50) {
            return false;
        }

        if (state.food > 0 && state.materials > 0 && state.energy > 0 && state.request.delay <= 1) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += priceFood;
            state.moneySpent += priceMaterials;
            state.moneySpent += priceEnergy;

            state.request.type = "food";
            state.request.amount = amount;
            state.request.delay = delay;
            handleRequest();
            return true;
        } else
            return false;
    }

    public boolean RequestMaterials(int priceFood, int priceMaterials, int priceEnergy, int amount, int delay) {
        if (state.materials >= 50) {
            return false;
        }

        if (state.food > 0 && state.materials > 0 && state.energy > 0 && state.request.delay <= 1) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += priceFood;
            state.moneySpent += priceMaterials;
            state.moneySpent += priceEnergy;

            state.request.type = "materials";
            state.request.amount = amount;
            state.request.delay = delay;
            handleRequest();
            return true;
        } else
            return false;
    }

    public boolean RequestEnergy(int priceFood, int priceMaterials, int priceEnergy, int amount, int delay) {

        if (state.energy >= 50) {
            return false;
        }

        if (state.food > 0 && state.materials > 0 && state.energy > 0 && state.request.delay <= 1) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += priceFood;
            state.moneySpent += priceMaterials;
            state.moneySpent += priceEnergy;

            state.request.type = "energy";
            state.request.amount = amount;
            state.request.delay = delay;
            handleRequest();
            return true;
        } else
            return false;
    }

    public boolean WAIT(int priceFood, int priceMaterials, int priceEnergy) {
        handleRequest();
        if (state.food > 0 && state.materials > 0 && state.energy > 0) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += priceFood;
            state.moneySpent += priceMaterials;
            state.moneySpent += priceEnergy;
            return true;
        } else
            return false;
    }

    public boolean BUILD(int food, int materials, int energy, int priceFood, int priceMaterials, int priceEnergy,
            int priceBuild,
            int prosperity) {
        if (state.food >= food && state.materials >= materials
                && state.energy >= energy) {
            state.food -= food;
            state.materials -= materials;
            state.energy -= energy;
            state.prosperity += prosperity;

            state.moneySpent += priceFood * food;
            state.moneySpent += priceMaterials * materials;
            state.moneySpent += priceEnergy * energy;
            state.moneySpent += priceBuild;
            return true;
        } else
            return false;
    }

    public void handleRequest() {
        if (state.request.delay == 1) {
            if (state.request.type.equals("food"))
                state.food += state.request.amount;
            else if (state.request.type.equals("materials"))
                state.materials += state.request.amount;
            else if (state.request.type.equals("energy"))
                state.energy += state.request.amount;

            if (state.food > 50)
                state.food = 50;
            if (state.materials > 50)
                state.materials = 50;
            if (state.energy > 50)
                state.energy = 50;

            state.request.type = "";
            state.request.delay = 0;
            state.request.amount = 0;
        } else {
            state.request.delay--;
        }
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
