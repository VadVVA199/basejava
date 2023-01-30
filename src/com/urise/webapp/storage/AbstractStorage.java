package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void clear() {
        clearStorage();
        System.out.println("There is no resume in the database");
    }

    @Override
    public void update(Resume r) {
        updateResume(r);
        System.out.println(r.getUuid() + " this resume has been update");
    }

    @Override
    public void save(Resume r) {
        saveResumeStorage(r);
        System.out.println(r.getUuid() + " resume saved to database");
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = getResume(uuid);
        System.out.println(uuid + " resume sent");
        return resume;
    }

    @Override
    public void delete(String uuid) {
        deleteResume(uuid);
        System.out.println(uuid + " resume has been deleted");
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    @Override
    public int size() {
        return sizeStorage();
    }

    protected abstract void clearStorage();
    protected abstract void updateResume(Resume resume);
    protected abstract void saveResumeStorage(Resume resume);
    protected abstract Resume getResume(String uuid);
    protected abstract void deleteResume(String uuid);
    protected abstract Resume[] getAllResume();
    protected abstract int sizeStorage();
}
