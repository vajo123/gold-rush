import java.util.Random;

public abstract class Tool {

    protected int durability;
    protected Random rnd = new Random();

    public Tool(int durability) {
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        if (durability < 0) {
            this.durability = 0;
        }
        else if (durability > 100) {
            this.durability = 100;
        }
        else {
            this.durability = durability;
        }
    }

    public abstract int useTool();
}