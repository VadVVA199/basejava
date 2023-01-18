package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveResume(Resume r, int index) {
        int insertIndex = index * -1;
        countResume++;
        System.arraycopy(storage, insertIndex - 1, storage, insertIndex, countResume - insertIndex);
        storage[insertIndex - 1] = r;
    }

    @Override
    protected void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, countResume - (index + 1));
        storage[countResume - 1] = null;
        countResume--;
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
