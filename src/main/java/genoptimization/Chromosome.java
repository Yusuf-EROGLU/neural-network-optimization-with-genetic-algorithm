package genoptimization;

import java.util.LinkedList;
import java.util.Random;

public class Chromosome {

    private int numOfHiddenLayers, numOfHiddenNeuron, miniBatchSize, numberOfEpoch;
    private double learningRate;
    private double accuracy;
    private static double mutationRate;

    private static LinkedList<Integer> hiddenLayers ;
    private static LinkedList<Integer> hiddenNeurons ;
    private static LinkedList<Integer> miniBatchs ;
    private static LinkedList<Integer> epochs ;
    private static LinkedList<Double> learningRates ;

    public Chromosome() {

        numOfHiddenLayers = randomSelect(hiddenLayers);
        numOfHiddenNeuron = randomSelect(hiddenNeurons);
        miniBatchSize = randomSelect(miniBatchs);
        numberOfEpoch = randomSelect(epochs);
        learningRate = randomSelect(learningRates);

    }

    public Chromosome(int numOfHiddenLayers, int numOfHiddenNeuron, int miniBatchSize, int numberOfEpoch, float learningRate) {
        this.numOfHiddenLayers = numOfHiddenLayers;
        this.numOfHiddenNeuron = numOfHiddenNeuron;
        this.miniBatchSize = miniBatchSize;
        this.numberOfEpoch = numberOfEpoch;
        this.learningRate = learningRate;
    }

    public void Mutate() {
        Random rand = new Random();
        double randomForMutation;
        int randomForSelection;

        randomForMutation = rand.nextDouble();

        if (randomForMutation <= this.mutationRate) {
            randomForSelection = rand.nextInt(5);
            switch (randomForSelection) {
                case 0:
                    numOfHiddenLayers = randomSelect(hiddenLayers);
                    break;
                case 1:
                    numOfHiddenNeuron = randomSelect(hiddenNeurons);
                    break;
                case 2:
                    miniBatchSize = randomSelect(miniBatchs);
                    break;
                case 3:
                    numberOfEpoch = randomSelect(epochs);
                    break;
                case 4:
                    learningRate = randomSelect(learningRates);
                    break;
            }
        }
    }

    public void Evaluate() {
        String trainLabel = "src//main//resources//datasets//train-labels.idx1-ubyte";
        String trainImage = "src//main//resources//datasets//train-images.idx3-ubyte";

        String testLabel = "src//main//resources//datasets//t10k-labels.idx1-ubyte";
        String testImage = "src//main//resources//datasets//t10k-images.idx3-ubyte";

        MNISTReader training = new MNISTReader(trainLabel, trainImage);
        MNISTReader test = new MNISTReader(testLabel, testImage);

        MLNNetwork neuralNet = new MLNNetwork(this.numOfHiddenLayers - 1,
                this.numOfHiddenNeuron, 784, 10,
                this.learningRate,
                this.numberOfEpoch,
                this.miniBatchSize, test, training);
        neuralNet.setupNetwork();
        neuralNet.train();
        neuralNet.test();
        this.accuracy = neuralNet.getAccuracy();
        System.out.println("Accuracy:" + this.accuracy);
    }

    private <E> E randomSelect(LinkedList<E> list) {
        Random rand = new Random();
        int size = list.size();
        int selection = rand.nextInt(size);
        E newGene = list.get(selection);
        return newGene;
    }

    public static LinkedList<Integer> getHiddenLayers() {
        return hiddenLayers;
    }

    public static LinkedList<Integer> getHiddenNeurons() {
        return hiddenNeurons;
    }

    public static LinkedList<Integer> getMiniBatchs() {
        return miniBatchs;
    }

    public static LinkedList<Integer> getEpochs() {
        return epochs;
    }

    public static LinkedList<Double> getLearningRates() {
        return learningRates;
    }

    public static double getMutationRate() {
        return mutationRate;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getNumOfHiddenLayers() {
        return numOfHiddenLayers;
    }

    public int getNumOfHiddenNeuron() {
        return numOfHiddenNeuron;
    }

    public int getMiniBatchSize() {
        return miniBatchSize;
    }

    public int getNumberOfEpoch() {
        return numberOfEpoch;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setNumOfHiddenLayers(int numOfHiddenLayers) {
        this.numOfHiddenLayers = numOfHiddenLayers;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public void setNumOfHiddenNeuron(int numOfHiddenNeuron) {
        this.numOfHiddenNeuron = numOfHiddenNeuron;
    }

    public void setMiniBatchSize(int miniBatchSize) {
        this.miniBatchSize = miniBatchSize;
    }

    public void setNumberOfEpoch(int numberOfEpoch) {
        this.numberOfEpoch = numberOfEpoch;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public static void setMutationRate(double mutationRate) {
        Chromosome.mutationRate = mutationRate;
    }

    public static void setHiddenLayers(LinkedList<Integer> hiddenLayers) {
        Chromosome.hiddenLayers = hiddenLayers;
    }

    public static void setHiddenNeurons(LinkedList<Integer> hiddenNeurons) {
        Chromosome.hiddenNeurons = hiddenNeurons;
    }

    public static void setMiniBatchs(LinkedList<Integer> miniBatchs) {
        Chromosome.miniBatchs = miniBatchs;
    }

    public static void setEpochs(LinkedList<Integer> epochs) {
        Chromosome.epochs = epochs;
    }

    public static void setLearningRates(LinkedList<Double> learningRates) {
        Chromosome.learningRates = learningRates;
    }
}
