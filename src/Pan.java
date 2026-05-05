public class Pan extends Tool{

    public Pan() {
        super(100);
    }

    @Override
    public int useTool() {
        int earnings = getRnd().nextInt(61);
        System.out.println("Pan earned: $" + earnings);
        return earnings;
    }
}
