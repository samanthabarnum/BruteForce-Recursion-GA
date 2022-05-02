//Samantha Barnum
//CS 1181L-07
//Project 4
//4/7/22

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GeneticAlgorithm {

    public static final int POP_SIZE = 100;
    public static final int NUM_EPOCHS = 1000;
    public static final int NUM_THREADS = 5;

    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        ArrayList<Item> itemObjects = new ArrayList<Item>();
        File file = new File(filename);
        Scanner input = new Scanner(file);
        String fileLine = "";

        while (input.hasNextLine()) {
            fileLine = input.nextLine();
            Scanner splitString = new Scanner(fileLine);
            splitString.useDelimiter(", ");
            String name = splitString.next();
            Double weight = splitString.nextDouble();
            int value = splitString.nextInt();
            splitString.close();
            Item currentItem = new Item(name, weight, value);
            itemObjects.add(currentItem);
        }
        input.close();
        return itemObjects;

    }

    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        ArrayList<Chromosome> populationSizeChromosome = new ArrayList<Chromosome>();
        for (int i = 0; i < populationSize; i++) {
            Chromosome currentChromosome = new Chromosome(items);
            populationSizeChromosome.add(currentChromosome);
        }
        return populationSizeChromosome;
    }

}
