package model.container;

import model.UserStory;
import model.persistence.PersistenceException;
import model.persistence.PersistenceStrategy;
import model.persistence.PersistenceStrategyStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Container implements Serializable {

    //Container attributes
    private List<UserStory> userStoryList;
    private PersistenceStrategy<UserStory> persistenceStrategy;

    //myContainer implemented as singleton
    private static Container myContainer = new Container();

    //private non-parameter constructor
    private Container() {
        userStoryList = new ArrayList<>();
        persistenceStrategy = new PersistenceStrategyStream<>();
    }

    //return reference to myContainer
    public static Container getInstance() {
        return myContainer;
    }

    //getter for userStoryList
    public static List<UserStory> getCurrentList() {
        return myContainer.userStoryList;
    }

    //setter for PersistenceStrategy
    public static void setPersistenceStrategy(PersistenceStrategy<UserStory> persistenceStrategy) {
        myContainer.persistenceStrategy = persistenceStrategy;
    }

    //clears the container (deletes all information in it!)
    public static void refresh() {
        myContainer = new Container();
    }

    //add UserStory to container
    public static void addUserStory(UserStory userStory) throws ContainerException {
        if (myContainer.userStoryList.contains(userStory)) //checks if container already contains this UserStory
            throw new ContainerException("The UserStory with the ID [" + userStory.getUuid() + "] is already existing!");
        myContainer.userStoryList.add(userStory);
    }

    //deletes UserStory from container
    public static void deleteUserStory(UserStory userStory) {
        myContainer.userStoryList.removeIf(i -> i.equals(userStory));
    }

    //persistently stores currently active list
    public static void store() throws PersistenceException, ContainerException {
        if (myContainer.persistenceStrategy == null) {
            throw new ContainerException("PersistenceStrategy not set");
        }

        myContainer.persistenceStrategy.save(myContainer.userStoryList);
    }

    //loads last saved list from file and overrides currently active list
    public static void loadForce() throws PersistenceException, ContainerException {
        if (myContainer.persistenceStrategy == null) {
            throw new ContainerException("PersistenceStrategy not set");
        }
        myContainer.userStoryList = myContainer.persistenceStrategy.load();
    }

    //loads last saved list from file and merges with currently active list
    public static void loadMerge() throws PersistenceException, ContainerException {
        if (myContainer.persistenceStrategy == null) {
            throw new ContainerException("PersistenceStrategy not set");
        }
        myContainer.userStoryList = Stream.concat(myContainer.userStoryList.stream(), myContainer.persistenceStrategy.load().stream()).collect(Collectors.toList());
    }
}