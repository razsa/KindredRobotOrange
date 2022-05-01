public class MonteCarlo {

    public static void main(String[] args) {
        int trials = 0;
        int days = 1;
        int totalDays = 0;
        int totalInfected = 0;

        while (trials < 10000) {
            int infected = 1;

            int n = 20; // number of computers in the network
            int r = 5; // max number of computers cleaned daily
            double p = 0.1; // probability of spreading the virus

            // Initially computer #0 is infected (set to 1),
            // computers #1 through #19 are clean (set to 0).
            int[] comps = new int[n];
            comps[0] = 1;
            for (int i = 1; i < n; i++) {
                comps[i] = 0;
            }

            // For each wire "infected -> non-infected":
            // perform a Bernoulli trial with probability p=0.1;
            // if success, computer #j becomes newly_infected (set to 2).

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((comps[i] == 1) && (comps[j] == 0)) {
                        int x = MyBernoulli(p);
                        if (x == 1) {
                            comps[j] = 2;

                        }
                    }
                }
            }

            // After that, mark all newly infected as simply infected.
            for (int i = 0; i < n; i++) {
                if (comps[i] == 2) {
                    comps[i] = 1;
                    infected++;
                }
            }
            totalInfected += infected;

            //while loop to count days until virus removed from system
            while (infected != 0) {
                //clean - remove 5 infected
                int count = 1;
                for (int i = 0; i < n; i++) {
                    if (count <= r) {
                        if (comps[i] == 1) {
                            comps[i] = 0;
                            count++;
                            infected--;
                        }
                    }
                }

                int temp = 0;
                for (int j = 0; j < n; j++) {
                    if (comps[j] == 1) {
                        temp++;
                    }
                }
                if (temp > 0) {
                    days++;
                }

            }
            totalDays += days;
            trials++;
        }
        double avgInfected = (double) totalInfected / (trials);
        double probInfected = (double) totalInfected / (20*trials);
        System.out.println("Average days to remove from system: " + (totalDays/trials));
        System.out.println("Probability that each computer gets infected: " + probInfected);
        System.out.println("Expected number of computers that get infected: " + avgInfected);
    }


    public static int MyBernoulli(double p) {
        double U = Math.random();
        int X;
        if (U < p) {
            X = 1;
        }
        else {
            X = 0;
        }
        return X;
    }


}

