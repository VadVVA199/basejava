package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {

    protected final Storage storage;
    public static final Resume RESUME_1 = new Resume("uuid1");
    public static final Resume RESUME_2 = new Resume("uuid2");
    public static final Resume RESUME_3 = new Resume("uuid3");
    public static final Resume RESUME_4 = new Resume("uuid4");
    public static final Resume UUID_NOT_EXIST = new Resume("out");
    public static final Resume UPDATED_RESUME = new Resume("uuid1");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assert.assertEquals(0,storage.getAll().length);
    }

    @Test
    public void update() {
        storage.update(UPDATED_RESUME);
        Assert.assertSame(UPDATED_RESUME, storage.get(UPDATED_RESUME.getUuid()));
        assertSize(3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        storage.get(RESUME_1.getUuid());
    }

    @Test
    public void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(expected, storage.getAll());
        assertSize(storage.size());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(UUID_NOT_EXIST);
    }

    private void assertSize(int number) {
        Assert.assertEquals(number, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertSame(resume, storage.get(resume.getUuid()));
    }
}