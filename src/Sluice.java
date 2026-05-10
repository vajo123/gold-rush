public class Sluice extends Tool{

    public Sluice() {
        super(100);
    }

    @Override
    public int useTool() {
        if (getDurability() == 0) {
            System.out.println("Sluice is broken");
            return 0;
        }

        int currentDurability = getDurability();
        int earnings = getRnd().nextInt(501);
        int loss = 20 + getRnd().nextInt(31);

        if (loss > currentDurability) {
            earnings = (int) Math.round(earnings * (double) currentDurability / loss);
        }

        setDurability(currentDurability - loss);
        System.out.println("Sluice earned: $" + earnings + " ,durability now: " + getDurability() + "%");
        return earnings;
    }

    public void repair() {
        setDurability(100);
        System.out.println("Sluice durability repaired to 100%");
    }
}
