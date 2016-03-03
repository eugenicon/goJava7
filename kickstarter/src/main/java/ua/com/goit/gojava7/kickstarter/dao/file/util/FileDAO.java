package ua.com.goit.gojava7.kickstarter.dao.file.util;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.dao.DataSource;

public class FileDAO<T> implements DataSource<T> {

    private String pathToFile;
    private Class<T> persistentClass;
    CsvParser parser = new CsvParser();
    List<T> cachedElements;
    long lastAccess;

    public FileDAO(Class<T> persistentClass) {
        this(persistentClass, "./kicks-files/storage/files/%name%.CSV".replace("%name%", persistentClass.getSimpleName()));
    }

    public FileDAO(Class<T> persistentClass, String pathToFile) {
        this.pathToFile = pathToFile;
        this.persistentClass = persistentClass;
    }

    @Override
    public List<T> getAll() {
        
        if (isAccessTimeOut()) {
            CsvParser parser = getParser();
            cachedElements = parser.read(pathToFile, persistentClass);
            updateAccessTime();
        }

        return cachedElements;
    }

    private boolean isAccessTimeOut() {
        return System.currentTimeMillis() > lastAccess + 10000;
    }
    
    private void updateAccessTime() {
        lastAccess = System.currentTimeMillis();
    }
    
    private void resetAccessTime() {
        lastAccess = 0;
    }

    @Override
    public T get(int index) {

        T element = null;

        List<T> all = getAll();

        if (index < all.size()) {
            element = all.get(index);
        }

        return element;
    }

    @Override
    public void add(T element) {
        resetAccessTime();
        List<T> all = getAll();
        all.add(element);
        getParser().write(all, pathToFile);

    }

    public CsvParser getParser() {
        return parser;
    }

    public void clear() {
        getParser().clear(pathToFile);
    }

    @Override
    public void addAll(List<T> elements) {
        resetAccessTime();
        List<T> all = getAll();
        all.addAll(elements);
        CsvParser parser = getParser();
        parser.write(all, pathToFile);
    }
   
}
