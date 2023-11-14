package code;

import java.util.ArrayList;

public class Agent {
    State state;
    ArrayList<String> actions;

    public Agent(State state) {
        this.state = state;
        actions = new ArrayList<String>();
        actions.add("BUILD1");
        actions.add("BUILD2");
        actions.add("RequestFood");
        actions.add("RequestMaterials");
        actions.add("RequestEnergy");
        actions.add("WAIT");
    }

    public boolean doAction(State state, String action) {
        switch (action) {
            case "RequestFood":
                return RequestFood(Input.unitPriceFood, Input.unitPriceMaterials,
                        Input.unitPriceEnergy, Input.amountRequestFood,
                        Input.delayRequestFood);
            case "RequestMaterials":
                return RequestMaterials(Input.unitPriceFood, Input.unitPriceMaterials,
                        Input.unitPriceEnergy, Input.amountRequestMaterials,
                        Input.delayRequestMaterials);
            case "RequestEnergy":
                return RequestEnergy(Input.unitPriceFood, Input.unitPriceMaterials,
                        Input.unitPriceEnergy, Input.amountRequestEnergy,
                        Input.delayRequestEnergy);
            case "WAIT":
                return WAIT(Input.unitPriceFood, Input.unitPriceMaterials,
                        Input.unitPriceEnergy);
            case "BUILD1":
                return BUILD(Input.foodUseBUILD1, Input.materialsUseBUILD1,
                        Input.energyUseBUILD1, Input.unitPriceFood,
                        Input.unitPriceMaterials, Input.unitPriceEnergy,
                        Input.priceBUILD1,
                        Input.prosperityBUILD1);
            case "BUILD2":
                return BUILD(Input.foodUseBUILD2, Input.materialsUseBUILD2,
                        Input.energyUseBUILD2, Input.unitPriceFood,
                        Input.unitPriceMaterials, Input.unitPriceEnergy,
                        Input.priceBUILD2,
                        Input.prosperityBUILD2);
            default:
                return false;
        }
    }

    public boolean RequestFood(int priceFood, int priceMaterials, int priceEnergy, int amount, int delay) {

        if (state.food >= 49) {
            return false;
        }

        if (state.food > 0 && state.materials > 0 && state.energy > 0 && state.request.delay < 1) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += priceFood;
            state.moneySpent += priceMaterials;
            state.moneySpent += priceEnergy;

            state.request.type = "food";
            state.request.amount = amount;
            state.request.delay = delay;
            return true;
        } else
            return false;
    }

    public boolean RequestMaterials(int priceFood, int priceMaterials, int priceEnergy, int amount, int delay) {
        if (state.materials >= 49) {
            return false;
        }

        if (state.food > 0 && state.materials > 0 && state.energy > 0 && state.request.delay < 1) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += priceFood;
            state.moneySpent += priceMaterials;
            state.moneySpent += priceEnergy;

            state.request.type = "materials";
            state.request.amount = amount;
            state.request.delay = delay;
            return true;
        } else
            return false;
    }

    public boolean RequestEnergy(int priceFood, int priceMaterials, int priceEnergy, int amount, int delay) {

        if (state.energy >= 49) {
            return false;
        }

        if (state.food > 0 && state.materials > 0 && state.energy > 0 && state.request.delay < 1) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += priceFood;
            state.moneySpent += priceMaterials;
            state.moneySpent += priceEnergy;

            state.request.type = "energy";
            state.request.amount = amount;
            state.request.delay = delay;
            return true;
        } else
            return false;
    }

    public boolean WAIT(int priceFood, int priceMaterials, int priceEnergy) {
        if (state.food > 0 && state.materials > 0 && state.energy > 0 && state.request.delay > 0 && state.food < 50
                && state.materials < 50 && state.energy < 50) {
            state.food--;
            state.materials--;
            state.energy--;
            state.moneySpent += priceFood;
            state.moneySpent += priceMaterials;
            state.moneySpent += priceEnergy;
            handleRequest();
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

            handleRequest();
            return true;
        } else
            return false;
    }

    public void handleRequest() {
        state.request.delay--;
        if (state.request.delay == 0) {
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
        } else if (state.request.delay < 0) {
            state.request.delay = 0;
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
