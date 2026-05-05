public class Cradle extends Tool{

    public Cradle() {
        super(100);
    }

    @Override
    public int useTool() {
        if (getRnd().nextInt(100) < 20) {
            setDurability(0);
            System.out.println("Cradle broke");
            return 0;
        }

        int earnings = getRnd().nextInt(31);
        System.out.println("Cradle earned: $" + earnings);
        return earnings;
    }
}
