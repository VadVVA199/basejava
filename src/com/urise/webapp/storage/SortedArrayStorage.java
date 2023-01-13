package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (countResume == storage.length) {
            System.out.println(r.getUuid() + " resume cannot be saved there is no place in the database");
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println(r.getUuid() + " is already in the database");
        } else {
            storage[countResume] = r;
            countResume++;
            sort(storage);
            System.out.println(r.getUuid() + " resume saved to database");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[countResume - 1];
            storage[countResume - 1] = null;
            countResume--;
            sort(storage);
            System.out.println(uuid + " resume has been deleted");
            return;
        }
        printResumeMissing(uuid);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, countResume, searchKey);
    }

    public void sort(Resume[] arrayResumes) {
        for (int i = 0; i < countResume; i++) {
            Resume current = arrayResumes[i];
            int j = Math.abs(Arrays.binarySearch(arrayResumes, 0, i, current) + 1);
            System.arraycopy(arrayResumes, j, arrayResumes, j + 1, i - j);
            arrayResumes[j] = current;
        }
    }
}
