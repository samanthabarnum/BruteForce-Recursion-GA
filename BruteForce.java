//Samantha Barnum
//CS 1181L-07
//Project 4
//4/7/22

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BruteForce {

    int maxFitness = 0;

    public static ArrayList<Item> getOptimalSet(ArrayList<Item> items) throws InvalidArgumentException {
        if (items.size() >= 10) {
            throw new InvalidArgumentException("Error: dataset too big. Please use 10 or below items.");
        }
        if (items.size() == 1) {
            return items;
        }
        int maxFitness = 0;
        ArrayList<Item> optimalSet = new ArrayList<Item>();
        for (int i = 0; i < items.size(); i++) {

            ArrayList<Item> itemsCopy = (ArrayList<Item>) items.clone();
            itemsCopy.remove(i);

            itemsCopy = getOptimalSet(itemsCopy);
            int currentFitness = getFitness(itemsCopy);

            if (currentFitness > maxFitness) {
                maxFitness = currentFitness;
                optimalSet = itemsCopy;
            }
        }
        if (maxFitness > getFitness(items)) {
            return optimalSet;
        } else {
            return items;
        }
    }

    public static int getFitness(ArrayList<Item> items) {
        int valueTotal = 0;
        double weightTotal = 0;
        int fitness = 0;
        for (int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            int currentValue = currentItem.getValue();
            double currentWeight = currentItem.getWeight();
            weightTotal = weightTotal + currentWeight;
            valueTotal = valueTotal + currentValue;
        }
        if (weightTotal > 10) {
            fitness = 0;
        } else {
            fitness = valueTotal;
        }
        return fitness;
    }

    public static void main(String[] args) throws FileNotFoundException, InvalidArgumentException {

        ArrayList<Item> arrayList = GeneticAlgorithm.readData("src/items.txt");

        System.out.println("The optimal set I've brute forced with recursion is: ");
        System.out.println("");

        ArrayList<Item> output = (getOptimalSet(arrayList));

        // following line from stack overflow because the output wasn't being pretty
        String listString = output.stream().map(Object::toString).collect(Collectors.joining());

        System.out.println(listString);
        System.out.println("Total value of this set is is: $" + getFitness(getOptimalSet(arrayList)) + ".");
    }
}