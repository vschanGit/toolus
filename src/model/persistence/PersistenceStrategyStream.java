package model.persistence;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<UserStory> implements PersistenceStrategy<UserStory> {

    //PersistenceStrategyStream attributes
    private ObjectInputStream objectInputStream;

    ////persistently stores list in file
    @Override
    public void save(List<UserStory> userStory) throws PersistenceException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(getFile())); //open connection
            objectOutputStream.writeObject(userStory);                                                       //saving process
            objectOutputStream.close();                                                                      //close connection
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.SaveFailure, "IOException in PersistenceStrategyStream.save");
        }
    }

    //loads last saved list from file
    @Override
    public List<UserStory> load() throws PersistenceException {
        try {
            File file = getFile();
            objectInputStream = new ObjectInputStream(new FileInputStream(file)); //open connection
            Object input = objectInputStream.readObject();                        //loading process
            objectInputStream.close();                                            // close connection
            if (input instanceof List<?>) {
                return (List<UserStory>) input;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "IOException in PersistenceStrategyStream.load");
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "ClassNotFoundException in PersistenceStrategyStream.load");
        }

        return null;
    }

    private File getFile() {
        return new File("container.dat");
    }
}
