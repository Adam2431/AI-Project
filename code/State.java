package code;

public class State {
    int initialProsperity;
    int initialFood;
    int initialMaterials;
    int initialEnergy;
    int unitPriceFood;
    int unitPriceMaterials;
    int unitPriceEnergy;
    int amountRequestFood;
    int delayRequestFood;
    int amountRequestMaterials;
    int delayRequestMaterials;
    int amountRequestEnergy;
    int delayRequestEnergy;
    int priceBUILD1;
    int foodUseBUILD1;
    int materialsUseBUILD1;
    int energyUseBUILD1;
    int prosperityBUILD1;
    int priceBUILD2;
    int foodUseBUILD2;
    int materialsUseBUILD2;
    int energyUseBUILD2;
    int prosperityBUILD2;

    int food;
    int materials;
    int energy;
    int prosperity;
    int moneySpent;
    Request request;
    int heuristicOne;
    int heuristicTwo;

    public State(String initalState) {

        char[] stateArray = initalState.toCharArray();
        int parsedSoFar = 0;
        int innerParsedSoFar = 0;
        String innerParsed = "";
        for (char character : stateArray) {
            if (character == ',' || character == ';') {
                switch (innerParsedSoFar) {
                    case 0:
                        if (parsedSoFar == 0)
                            initialProsperity = Integer.parseInt(innerParsed);
                        if (parsedSoFar == 1)
                            initialFood = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 2)
                            unitPriceFood = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 3)
                            amountRequestFood = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 4)
                            amountRequestMaterials = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 5)
                            amountRequestEnergy = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 6)
                            priceBUILD1 = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 7)
                            priceBUILD2 = Integer.parseInt(innerParsed);
                        break;
                    case 1:
                        if (parsedSoFar == 1)
                            initialMaterials = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 2)
                            unitPriceMaterials = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 3)
                            delayRequestFood = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 4)
                            delayRequestMaterials = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 5)
                            delayRequestEnergy = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 6)
                            foodUseBUILD1 = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 7)
                            foodUseBUILD2 = Integer.parseInt(innerParsed);
                        break;
                    case 2:
                        if (parsedSoFar == 1)
                            initialEnergy = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 2)
                            unitPriceEnergy = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 6)
                            materialsUseBUILD1 = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 7)
                            materialsUseBUILD2 = Integer.parseInt(innerParsed);
                        break;
                    case 3:
                        if (parsedSoFar == 6)
                            energyUseBUILD1 = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 7)
                            energyUseBUILD2 = Integer.parseInt(innerParsed);
                        break;
                    case 4:
                        if (parsedSoFar == 6)
                            prosperityBUILD1 = Integer.parseInt(innerParsed);
                        else if (parsedSoFar == 7)
                            prosperityBUILD2 = Integer.parseInt(innerParsed);
                        break;
                }
                innerParsedSoFar++;
                innerParsed = "";
            } else {
                innerParsed += character;
            }
            if (character == ';') {
                parsedSoFar++;
                innerParsedSoFar = 0;
                innerParsed = "";
            }
        }

        food = initialFood;
        materials = initialMaterials;
        energy = initialEnergy;
        prosperity = initialProsperity;
        moneySpent = 0;
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

    public String toString2() {
        String state = "";

        state += "Initial Prosperity: " + initialProsperity + "\n";
        state += "Initial Food: " + initialFood + "\n";
        state += "Initial Materials: " + initialMaterials + "\n";
        state += "Initial Energy: " + initialEnergy + "\n";
        state += "Unit Price Food: " + unitPriceFood + "\n";
        state += "Unit Price Materials: " + unitPriceMaterials + "\n";
        state += "Unit Price Energy: " + unitPriceEnergy + "\n";
        state += "Amount Request Food: " + amountRequestFood + "\n";
        state += "Delay Request Food: " + delayRequestFood + "\n";
        state += "Amount Request Materials: " + amountRequestMaterials + "\n";
        state += "Delay Request Materials: " + delayRequestMaterials + "\n";
        state += "Amount Request Energy: " + amountRequestEnergy + "\n";
        state += "Delay Request Energy: " + delayRequestEnergy + "\n";
        state += "Price BUILD1: " + priceBUILD1 + "\n";
        state += "Food Use BUILD1: " + foodUseBUILD1 + "\n";
        state += "Materials Use BUILD1: " + materialsUseBUILD1 + "\n";
        state += "Energy Use BUILD1: " + energyUseBUILD1 + "\n";
        state += "Prosperity BUILD1: " + prosperityBUILD1 + "\n";
        state += "Price BUILD2: " + priceBUILD2 + "\n";
        state += "Food Use BUILD2: " + foodUseBUILD2 + "\n";
        state += "Materials Use BUILD2: " + materialsUseBUILD2 + "\n";
        state += "Energy Use BUILD2: " + energyUseBUILD2 + "\n";
        state += "Prosperity BUILD2: " + prosperityBUILD2 + "\n";

        return state;
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
