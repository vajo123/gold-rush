import java.util.Random;

public abstract class Tool {

    private int durability;
    private Random rnd;

    public Tool(int durability) {
        this.rnd = new Random();
        setDurability(durability);
    }

    public int getDurability() {
        return durability;
    }

    protected Random getRnd() {
        return rnd;
    }

    public void setDurability(int durability) {
        if (durability < 0) {
            this.durability = 0;
        } else if (durability > 100) {
            this.durability = 100;
        } else {
            this.durability = durability;
        }
    }

    public abstract int useTool();
}