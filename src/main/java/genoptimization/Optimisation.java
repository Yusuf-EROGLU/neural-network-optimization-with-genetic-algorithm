package genoptimization;

import java.util.LinkedList;
import java.util.Scanner;

public class Optimisation {
    public static void main(String[] args) {
        int generationSequence = 0;
        Chromosome best;
        sethypers();
        setGenes();
        Population currentGeneration = new Population(Population.getPopulationSize());
        pritntGenerration(currentGeneration,0);
        currentGeneration.evaluatePopulation();
        best = currentGeneration.getBest();

        //stop condition metodu yazýlacak
        while(true){
            generationSequence++;
            pritntGenerration(currentGeneration, generationSequence);
            printChromosome(best);
            Population parents = Population.parentSelection(currentGeneration);
            Population children = new Population();

            for (int i = 0; i+1 < Population.getPopulationSize(); i += 2) {

                Chromosome child1 = parents.chromosomes.get(i);
                Chromosome child2 = parents.chromosomes.get(i+1);

                Population.crossoverOnePoint(child1, child2);
                child1.Mutate();
                child2.Mutate();
                children.chromosomes.add(child1);
                children.chromosomes.add(child2);
            }
            children.evaluatePopulation();
            if(best.getAccuracy() < children.getBest().getAccuracy())best = children.getBest();
            currentGeneration = children;
        }

    }

    static void sethypers() {
        Scanner input = new Scanner(System.in);

        System.out.println("Population Size:");
        Population.setPopulationSize(input.nextInt());

        System.out.println("Crossover Probability(..%)");
        Population.setpCrossover(input.nextDouble());

        System.out.println("Mutation Probability(..%)");
        Chromosome.setMutationRate(input.nextDouble());
    }

    static void setGenes(){ // bu iþlemlerin bir kýsmýný class içinde yapýlmasý lazým
        LinkedList<Integer> numsHiddenLayer = new LinkedList<Integer>();
        LinkedList<Integer> numsHiddenNeurons = new LinkedList<Integer>();
        LinkedList<Integer> numsEpochs = new LinkedList<Integer>();
        LinkedList<Integer> numsBatchSize = new LinkedList<Integer>();
        LinkedList<Double> learningRates = new LinkedList<Double>();

        numsHiddenLayer.add(2);
        numsHiddenLayer.add(3);
        numsHiddenLayer.add(4);
        /*numsHiddenLayer.add(5);*/

        numsHiddenNeurons.add(15);
        numsHiddenNeurons.add(20);
        numsHiddenNeurons.add(25);
        numsHiddenNeurons.add(30);
        /*numsHiddenNeurons.add(35);
        numsHiddenNeurons.add(40);
        numsHiddenNeurons.add(45);
        numsHiddenNeurons.add(50);*/

        numsEpochs.add(60000);
        numsEpochs.add(120000);
        numsEpochs.add(250000);
        /*numsEpochs.add(500000);
        numsEpochs.add(1000000);
        numsEpochs.add(2000000);
        numsEpochs.add(4000000);
        numsEpochs.add(6000000);*/


        numsBatchSize.add(1);
        numsBatchSize.add(2);
        numsBatchSize.add(5);
        /*numsBatchSize.add(10);
        numsBatchSize.add(50);*/

        /*learningRates.add(0.001);
        learningRates.add(0.01);
        learningRates.add(0.05);*/
        learningRates.add(0.1);
        learningRates.add(0.5);
        learningRates.add(1.0);
        learningRates.add(1.5);
        learningRates.add(2.0);

        Chromosome.setHiddenLayers(numsHiddenLayer);
        Chromosome.setHiddenNeurons(numsHiddenNeurons);
        Chromosome.setEpochs(numsEpochs);
        Chromosome.setMiniBatchs(numsBatchSize);
        Chromosome.setLearningRates(learningRates);
    }

    static void pritntGenerration(Population generation, int genNumber){
        LinkedList<Chromosome> genes = generation.chromosomes;
        Chromosome temp;
        int size = genes.size();

        int numHidden, numneuron, numBatch, numEpoch;
        double learningRate, accuracy;

        System.out.println(genNumber + " . Generasyon");
        System.out.println("GizliKatman__GizliNöron__BatchBoyutu__EpochSayýsý__ÖðrenmeOraný__Accuracy");
        for (int i = 0; i < size ; i++) {
            temp = genes.get(i);
            numHidden = temp.getNumOfHiddenLayers();
            numneuron = temp.getNumOfHiddenNeuron();
            numBatch = temp.getMiniBatchSize();
            numEpoch = temp.getNumberOfEpoch();
            learningRate = temp.getLearningRate();
            accuracy = temp.getAccuracy();

            System.out.print( numHidden + "__" );
            System.out.print( numneuron + "__" );
            System.out.print( numBatch + "__" );
            System.out.print( numEpoch + "__");
            System.out.print(learningRate + "__");
            System.out.println(accuracy);

        }

    }

    static void printChromosome(Chromosome chro){
        int numHidden = chro.getNumOfHiddenLayers();
        int numneuron = chro.getNumOfHiddenNeuron();
        int numBatch = chro.getMiniBatchSize();
        int numEpoch = chro.getNumberOfEpoch();
        double learningRate = chro.getLearningRate();
        double accuracy = chro.getAccuracy();

        System.out.println("---------------Best--------------");
        System.out.print( numHidden + "__" );
        System.out.print( numneuron + "__" );
        System.out.print( numBatch + "__" );
        System.out.print( numEpoch + "__");
        System.out.println(learningRate);
        System.out.println("--------------Accuracy:"+accuracy+"-------------------");
    }
}
