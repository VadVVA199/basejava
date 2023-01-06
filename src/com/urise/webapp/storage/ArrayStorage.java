package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int countResume;

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
        System.out.println("There is no resume in the database");
    }

     public void update(Resume r) {
        int result = lookResumeRepository(r.getUuid());
        if (result == -1) {
            printResumeMissing(r.getUuid());
            return;
        }
        storage[result] = r;
        System.out.println(r.getUuid() + " this resume has been update");
    }

    public void save(Resume r) {
        if (countResume == storage.length) {
            System.out.println(r.getUuid() + " resume cannot be saved there is no place in the database");
            return;
        }
        switch (lookResumeRepository(r.getUuid())) {
            case -1 -> {
                storage[countResume] = r;
                countResume++;
                System.out.println(r.getUuid() + " resume saved to database");
            }
            default -> System.out.println(r.getUuid() + " is already in the database");
        }
    }

    public Resume get(String uuid) {
        int result = lookResumeRepository(uuid);
        if (result != -1) {
            System.out.println(uuid + " resume sent");
            return storage[result];
        }
        System.out.println(uuid + " is no resume in the database");
        return null;
    }

    public void delete(String uuid) {
        int result = lookResumeRepository(uuid);
        if (result != -1) {
            storage[result] = storage[countResume - 1];
            storage[countResume - 1] = null;
            countResume--;
            System.out.println(uuid + " resume has been deleted");
            return;
        }
        System.out.println(uuid + " resume is not in the database");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public int size() {
        return countResume;
    }

    private int lookResumeRepository(String uuid) {
        int result = -1;
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return result;
    }

    private void printResumeMissing(String uuid) {
        System.out.println(uuid + " there is no resume in the database");
    }
}
