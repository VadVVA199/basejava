import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int countResume;

    void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
        System.out.println("There is no resume in the database");
    }

    void save(Resume r) {
        if (countResume < 10000) {
            storage[countResume] = r;
            countResume++;
            System.out.println("Resume saved to database");
        } else {
            System.out.println("There are no places to store a resume");
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, countResume - (i + 1));
                countResume--;
                System.out.println("This resume has been deleted");
                return;
            }
        }
        System.out.println("This resume is not in the database");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    int size() {
        return countResume;
    }
}
