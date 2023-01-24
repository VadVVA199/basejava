package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;

/**
 * Test for your com.urise.webapp.storage.SortedArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    private static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {

        int[] numericPartResumeNumbers = {8, 1, 9, 5, 4, 3, 2};

        for (int numericPartResumeNumber : numericPartResumeNumbers) {
            Resume resume = new Resume("uuid" + numericPartResumeNumber);
            ARRAY_STORAGE.save(resume);
        }

        printLineArray();

        Resume r1 = new Resume("uuid1");
        Resume r4 = new Resume("uuid2");
        Resume r5 = new Resume("uuid8");

        ARRAY_STORAGE.save(r1);

        ARRAY_STORAGE.update(r4);

        ARRAY_STORAGE.update(r5);
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

//        for (int i = 0; i < SortedArrayStorage.STORAGE_LIMIT + 1; i++) {
//            Resume r = new Resume();
//            r.setUuid("uuid" + i);
//            ARRAY_STORAGE.save(r);
//        }
//        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }

    static void printLineArray() {
        System.out.println();
        for (Resume r: ARRAY_STORAGE.getAll()) {
            System.out.print(r + ",");
        }
        System.out.println("\n");
    }
}
