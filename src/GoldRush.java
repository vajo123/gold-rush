import java.io.*;
import java.util.Scanner;

public class GoldRush {

    private FortyNiner fortyNiner;
    private File savedGame = new File("gold-rush.txt");
    private int currentWeek = 1;
    private boolean justLoaded = false;

    public void survive() {
        Scanner sc = new Scanner(System.in);

        if (fortyNiner == null) {
            fortyNiner = new FortyNiner(100, 100);
        }

        for (int i = currentWeek; i <= 20; i++) {
            System.out.println("\n=== WEEK " + i + " ===");

            if (justLoaded) {
                System.out.println("Endurance:  " + fortyNiner.getEndurance() + "%");
                System.out.println("Money:      $" + fortyNiner.getMoney());
                System.out.println("--- Tools ---");
                for (Tool t : fortyNiner.getTools()) {
                    if (t instanceof Sluice) {
                        System.out.println("Sluice     durability: " + t.getDurability() + "%");
                    }
                    if (t instanceof Cradle) {
                        System.out.println("Cradle     durability: " + t.getDurability() + "%");
                    }
                }
                justLoaded = false;
            } else {
                fortyNiner.useTools();
                fortyNiner.buyFood();
                fortyNiner.loseEndurance();
            }

            System.out.println("Do you want to save and exit? (y/n)");
            String ans = sc.next();

            if (ans.equalsIgnoreCase("y")) {
                saveGame(i);
                return;
            }

            fortyNiner.itIsSundayAgain(sc);
            fortyNiner.buyCradles(sc);

            currentWeek++;
        }

        System.out.println("Game finished! Money: $" + fortyNiner.getMoney());
    }

    public void loadGame() {
        if (!savedGame.exists()) return;

        try (Scanner sc = new Scanner(savedGame)) {

            int week = Integer.parseInt(sc.nextLine().split(" ")[2]);
            int endurance = Integer.parseInt(sc.nextLine().split(": ")[1].replace("%", ""));
            int money = Integer.parseInt(sc.nextLine().split("\\$")[1]);

            fortyNiner = new FortyNiner(endurance, money);

            currentWeek = week;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.startsWith("Sluice")) {
                    int d = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                    for (Tool t : fortyNiner.getTools()) {
                        if (t instanceof Sluice) {
                            t.setDurability(d);
                        }
                    }
                }

                if (line.startsWith("Cradle")) {
                    int d = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                    Cradle c = new Cradle();
                    c.setDurability(d);
                    fortyNiner.getTools().add(c);
                }
            }

            justLoaded = true;
            System.out.println("Game loaded!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveGame(int week) {
        try (PrintWriter pw = new PrintWriter(savedGame)) {

            pw.println("Week no. " + week);
            pw.println("49er endurance: " + fortyNiner.getEndurance() + "%");
            pw.println("49er money: $" + fortyNiner.getMoney());

            for (Tool t : fortyNiner.getTools()) {
                if (t instanceof Sluice) {
                    pw.println("Sluice durability: " + t.getDurability() + "%");
                }
                if (t instanceof Cradle) {
                    pw.println("Cradle durability: " + t.getDurability() + "%");
                }
            }

            System.out.println("Game saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}