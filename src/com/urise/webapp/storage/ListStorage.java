package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void clearStorage() {
        storage.clear();
    }

    @Override
    public void updateResume(Resume r) {
        ListIterator<Resume> listIterator = storage.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().getUuid().equals(r.getUuid())) {
                listIterator.set(r);
                return;
            }
        }
        throw new NotExistStorageException(r.getUuid());
    }

    @Override
    public void saveResumeStorage(Resume r) {
        storage.forEach(resume -> {
            if (resume.equals(r)) {
                throw new ExistStorageException(r.getUuid());
            }
        });
        storage.add(r);
    }

    @Override
    public Resume getResume(String uuid) {
        for (Resume resume: storage) {
            if (resume.getUuid().equals(uuid)) {
                return resume;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void deleteResume(String uuid) {
        if (!storage.removeIf(resume -> resume.getUuid().equals(uuid))) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAllResume() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int sizeStorage() {
        return storage.size();
    }
}
