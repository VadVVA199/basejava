package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super(uuid + " there is no resume in the database", uuid);
    }
}
