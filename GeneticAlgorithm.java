//Samantha Barnum
//CS 1181L-07
//Project 4
//4/7/22

//both of these groups of averages are based on 3 runs with each thread/file combo, divided by 3.

// small file                 this file gave the fitness of $3400 every time
// 1 thread 1443
// 2 threads 841
// 3 threads 608
// 4 threads 508
// 5 threads 472
// 6 threads 447
// 7 threads 438
// 8 threads 448
// 9 threads 447
// 10 threads 436

// big file                   this file gave the fitness of $7600 every time except for 6 times across all tests
// 1 thread 8840
// 2 threads 4794
// 3 threads 3432
// 4 threads 2720
// 5 threads 2305
// 6 threads 1971
// 7 threads 1767
// 8 threads 1763
// 9 threads 1758
// 10 threads 1788

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