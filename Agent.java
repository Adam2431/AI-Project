public class Agent {
    int food;
    int materials;
    int energy;
    int prosperity;


    public void RequestFood(int amount, int delay){
        food--;
        materials--;
        energy--;
    }

    public void RequestMaterials(int amount, int delay){
        food--;
        materials--;
        energy--;
    }

    public void RequestEnergy(int amount, int delay){
        food--;
        materials--;
        energy--;
    }

    public void WAIT(){
        food--;
        materials--;
        energy--;
    }

    public void BUILD1(int food, int materials, int energy, int prosperity){
        
    }

    public void BUILD2(int food, int materials, int energy, int prosperity){
        
    }
}
