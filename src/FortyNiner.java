import java.util.*;

public class FortyNiner {

    private int endurance;
    private int money;
    private ArrayList<Tool> tools;
    private Random rnd = new Random();

    public FortyNiner(int endurance, int money) {
        this.endurance = endurance;
        this.money = money;
        this.tools = new ArrayList<>();

        tools.add(new Pan());
        tools.add(new Sluice());
    }

    public int getEndurance() {
        return endurance;
    }
    public int getMoney() {
        return money;
    }
    public ArrayList<Tool> getTools() {
        return tools;
    }
    public void setEndurance(int endurance) {
        this.endurance = Math.max(0, Math.min(100, endurance));
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public void useTools() {
        if (endurance == 0) {
            System.out.println("Too tired to work");
            return;
        }

        int total = 0;
        Iterator<Tool> it = tools.iterator();

        while (it.hasNext()) {
            Tool t = it.next();

            if (t.getDurability() > 0) {
                total += t.useTool();
            }

            if (t instanceof Cradle && t.getDurability() == 0) {
                it.remove();
                System.out.println("Removed broken cradle.");
            }
        }

        money += total;
        System.out.println("Total earnings this week: $" + total);
    }

    public void buyFood() {
        int cost = 30 + rnd.nextInt(21);
        money -= cost;
        System.out.println("Food cost: $" + cost + " | Money left: $" + money);
    }

    public void loseEndurance() {
        int loss = 10 + rnd.nextInt(16);
        setEndurance(endurance - loss);
        System.out.println("Endurance now: " + endurance + "%");
    }

    public void itIsSundayAgain(Scanner sc) {
        System.out.println("It's Sunday! Choose:");
        System.out.println("1. Do nothing");
        System.out.println("2. Fix sluice ($100)");
        System.out.println("3. Go to saloon");

        int choice = sc.nextInt();

        switch (choice) {
            case 2 -> fixSluice();
            case 3 -> goToSaloon();
            default -> System.out.println("Resting...");
        }
    }

    private void goToSaloon() {
        int cost = 50 + rnd.nextInt(151);
        int gain = 5 + rnd.nextInt(46);

        money -= cost;
        setEndurance(endurance + gain);

        System.out.println("Saloon: -" + cost + "$, +" + gain + "% endurance");
    }

    private void fixSluice() {
        for (Tool t : tools) {
            if (t instanceof Sluice) {
                ((Sluice) t).repair();
                money -= 100;
                System.out.println("Paid $100 for repair");
                return;
            }
        }
    }
}