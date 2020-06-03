package genoptimization;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Population {


    private static int populationSize; // parent sizeda pop size oluyor olmaya bilir.
    private static double pCrossover;
    private Chromosome best;
    LinkedList<Chromosome> chromosomes;

    public Population() {
        chromosomes = new LinkedList<Chromosome>();
    }


    public static int getPopulationSize() {
        return populationSize;
    }

    private void initializePopulation(int populationSize) {
        chromosomes = new LinkedList<Chromosome>();
        for (int i = 0; i < populationSize; i++) {
            chromosomes.add(new Chromosome());
        }

    }

    public Population(int populationSize) {
        initializePopulation(populationSize);
    }

    public double sumAccuracy() {
        double sum = 0;

        Iterator<Chromosome> iterate = this.chromosomes.iterator();
        Chromosome current = new Chromosome();

        while (iterate.hasNext()) {
            current = iterate.next();

            sum += current.getAccuracy();
        }
        return sum;
    }

    public static void setPopulationSize(int populationSize) {
        Population.populationSize = populationSize;
    }

    public static void setpCrossover(double pCrossover) {
        Population.pCrossover = pCrossover;
    }


    Chromosome setBestSolution() {
        Iterator<Chromosome> iterate = this.chromosomes.iterator();
        Chromosome current = new Chromosome();

        while (iterate.hasNext()) {
            current = iterate.next();

            if (current.getAccuracy() >= this.best.getAccuracy()) {
                this.best = current;
            }
        }
        return current;
    }

    static Population parentSelection(Population population) {
        //Parent seçimini rulet yöntemine göre yaptým
        //Fakat fittest fucntion da büyük farklar olacaðý için (0,0001 vs 0,7 gibi)
        //Mutasyon deðerini yüksek tutarak çeþitlilik saðlalýyorum

        Population parents = new Population();
        Chromosome current;
        Iterator<Chromosome> iterate;
        Random rulet = new Random();
        double sum = population.sumAccuracy();
        double point;

        for (int i = 0; i < population.chromosomes.size(); i++) {
            iterate = population.chromosomes.iterator();
            point = rulet.nextDouble() * sum;
            double temp = 0;

            while (iterate.hasNext()) {
                current = iterate.next();

                /// Burada obje kopyalanýyor mu yoksa pointer atamasý mý yapýlýyor Problem yaratabilir !!!!
                temp += current.getAccuracy();
                if (temp >= point) {
                    parents.chromosomes.add(current);
                    break;
                }
            }
        }
        return parents;
    }

    static void crossoverOnePoint(Chromosome firstParent, Chromosome secParent) {
        Random rand = new Random();
        if (Population.pCrossover >= rand.nextDouble()) {
            int point = rand.nextInt(5);
            int tempf;
            int temps;

            double tempf2;
            double temps2;

            switch (point) {
                case 0:
                    tempf = firstParent.getNumOfHiddenLayers();
                    temps = secParent.getNumOfHiddenLayers();
                    firstParent.setNumOfHiddenLayers(temps);
                    secParent.setNumOfHiddenLayers(tempf);
                case 1:
                    tempf = firstParent.getNumOfHiddenNeuron();
                    temps = secParent.getNumOfHiddenNeuron();
                    firstParent.setNumOfHiddenNeuron(temps);
                    secParent.setNumOfHiddenNeuron(tempf);
                case 2:
                    tempf = firstParent.getMiniBatchSize();
                    temps = secParent.getMiniBatchSize();
                    firstParent.setMiniBatchSize(temps);
                    secParent.setMiniBatchSize(tempf);
                case 3:
                    tempf = firstParent.getNumberOfEpoch();
                    temps = secParent.getNumberOfEpoch();
                    firstParent.setNumberOfEpoch(temps);
                    secParent.setNumberOfEpoch(tempf);
                case 4:
                    tempf2 = firstParent.getLearningRate();
                    temps2 = secParent.getLearningRate();
                    firstParent.setLearningRate(temps2);
                    secParent.setLearningRate(tempf2);
            }

        }
    }

    static void crossoverTwoPoint(Chromosome firstParent, Chromosome secParent) {
        Random rand = new Random();

        if (Population.pCrossover >= rand.nextDouble()) {
            int point = rand.nextInt(5);
            int size = rand.nextInt(5 - point);
            int counter = 0;

            int tempf;
            int temps;
            double tempf2;
            double temps2;

            switch (point) {
                case 0:
                    tempf = firstParent.getNumOfHiddenLayers();
                    temps = secParent.getNumOfHiddenLayers();
                    firstParent.setNumOfHiddenLayers(temps);
                    secParent.setNumOfHiddenLayers(tempf);
                    counter++;
                    if (counter >= size) break;
                case 1:
                    tempf = firstParent.getNumOfHiddenNeuron();
                    temps = secParent.getNumOfHiddenNeuron();
                    firstParent.setNumOfHiddenNeuron(temps);
                    secParent.setNumOfHiddenNeuron(tempf);
                    if (counter >= size) break;
                case 2:
                    tempf = firstParent.getMiniBatchSize();
                    temps = secParent.getMiniBatchSize();
                    firstParent.setMiniBatchSize(temps);
                    secParent.setMiniBatchSize(tempf);
                    counter++;
                    if (counter >= size) break;
                case 3:
                    tempf = firstParent.getNumberOfEpoch();
                    temps = secParent.getNumberOfEpoch();
                    firstParent.setNumberOfEpoch(temps);
                    secParent.setNumberOfEpoch(tempf);
                    counter++;
                    if (counter >= size) break;
                case 4:
                    tempf2 = firstParent.getLearningRate();
                    temps2 = secParent.getLearningRate();
                    firstParent.setLearningRate(temps2);
                    secParent.setLearningRate(tempf2);
                    counter++;
                    if (counter >= size) break;
            }
        }
    }

    public Chromosome getBest() {
        Iterator<Chromosome> iterate = this.chromosomes.iterator();
        Chromosome current = null;
        double bestAccuracy = 0;
        double currentAccuracy;

        while (iterate.hasNext()) {
            current = iterate.next();
            currentAccuracy = current.getAccuracy();

            if (currentAccuracy >= bestAccuracy) {
                bestAccuracy = currentAccuracy;
                this.best = current;
            }

        }
        return best;
    }

    public void evaluatePopulation() {
        Iterator<Chromosome> iterate = this.chromosomes.iterator();
        Chromosome current = null;


        while (iterate.hasNext()) {
            current = iterate.next();
            current.Evaluate();

        }

        getBest();
    }

}

