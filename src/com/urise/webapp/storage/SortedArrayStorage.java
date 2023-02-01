package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume r, int index) {
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
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, countResume, searchKey);
    }

    @Override
    protected boolean isExist(String uuid) {
        int index = (int) getSearchKey(uuid);
        return index >= 0;
    }
}
