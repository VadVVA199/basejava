package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveResume(Resume r) {
        storage[countResume] = r;
        countResume++;
        sort(storage);
    }

    @Override
    protected void deleteResume(int index) {
        storage[index] = storage[countResume - 1];
        storage[countResume - 1] = null;
        countResume--;
        sort(storage);
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
