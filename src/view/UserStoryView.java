package view;

import model.UserStory;

import java.util.List;

public class UserStoryView {

    //tabular display of currently active list
    public static void dump(List<UserStory> userStoryList) {
        userStoryList.stream().sorted() //Map
                .forEachOrdered(System.out::println); //Map
    }


    //same as dump + shows only UserStories with x <= effort
    public static void dumpEffort(List<UserStory> userStoryList, int effort) {
        if(1 <= effort & effort <=5) {
            userStoryList.stream().filter(userStory -> userStory.getRelativeEffort() >= effort) //Filter
                    .sorted() //Map
                    .forEachOrdered(System.out::println); //Map
        }
        else {
            System.out.println("Try again. (Incorrect input: an integer is required from scale [1, 5])");
        }
    }
}
