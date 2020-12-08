package model;

import control.InputDialog;
import model.container.Container;
import model.container.ContainerException;
import model.persistence.PersistenceException;
import view.UserStoryView;

public class Main {

    public static void main(String[] args) throws ContainerException, PersistenceException {

        //initiate dialog
        InputDialog.startDialog();

        //code for manual testing
//        Container container = Container.getInstance();
//        UserStory myUserStory = new UserStory("Story1.", "Acceptance test.", 1, 1, 1, 1);
//        UserStory myUserStory2 = new UserStory("Story2.", "Acceptance test.", 2, 2, 4, 2);
//        UserStory myUserStory3 = new UserStory("Story3.", "Acceptance test.", 5, 2, 1, 2);
//        UserStory myUserStory4 = new UserStory("Story4.", "Acceptance test.", 4, 2, 1, 2);
//        UserStory myUserStory5 = new UserStory("Story5.", "Acceptance test.", 1, 2, 1, 5);
//        Container.getInstance().addUserStory(myUserStory);
//        Container.getInstance().addUserStory(myUserStory2);
//        Container.getInstance().addUserStory(myUserStory3);
//        Container.getInstance().addUserStory(myUserStory4);
//        Container.getInstance().addUserStory(myUserStory5);
//        Container.getInstance().store();
//        Container.getInstance().loadForce();
//        Container.getInstance().loadMerge();
//        UserStoryView.dumpEffort(Container.getInstance().getCurrentList(), 0);
//        Container.getInstance().store();
    }
}
