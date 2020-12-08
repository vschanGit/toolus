package control;

import model.UserStory;
import model.container.Container;
import model.container.ContainerException;
import model.persistence.PersistenceException;
import view.UserStoryView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputDialog {

    //InputDialog attributes
    private final Scanner scanner;

    //myInputDialog implemented as singleton
    private static final InputDialog myInputDialog = new InputDialog();

    //private non-parameter constructor
    private InputDialog() {
        this.scanner = new Scanner(System.in);
    }

    //closes the scanner of myInputDialog
    private void closeScanner() {
        myInputDialog.scanner.close();
    }

    //reads a line from the console
    public static String enterString() {
        return myInputDialog.scanner.nextLine();
    }

    //reads an integer from the console
    public static int enterInt() {
        while(myInputDialog.scanner.hasNext()) {
            try {
                return myInputDialog.scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Try again. (Incorrect input: an integer is required)");
                myInputDialog.scanner.nextLine(); //clears scanner 'buffer' of bad input
            }
        }
        return enterInt();
    }

    //creates new UserStory by user input in console and adds it to container
    public static void createUserStory() throws ContainerException {
        UserStory userStory = new UserStory();
        System.out.print("enter description: ");
        userStory.setDescription(enterString());
        System.out.print("enter acceptance test: ");
        userStory.setAcceptanceTest(enterString());
        System.out.print("enter relative Value: ");
        userStory.setRelativeValue(enterInt());
        System.out.print("enter relative loss: ");
        userStory.setRelativeLoss(enterInt());
        System.out.print("enter relative risk: ");
        userStory.setRelativeRisk(enterInt());
        System.out.print("enter relative effort: ");
        userStory.setRelativeEffort(enterInt());
        userStory.calculatePriority();
        Container.addUserStory(userStory);
    }

    //starts the dialog which enables the user to use commands in the console
    public static void startDialog() throws ContainerException, PersistenceException {

        System.out.println("UserStory-Tool"); //heading

        while (true) {
            System.out.print("> ");

            String strInput = enterString();

            String[] strings = strInput.split(" ");

            switch (strings[0]) {

                case "enter": //create new UserStory
                    createUserStory();
                    myInputDialog.scanner.nextLine();
                    break;

                case "store": //store current list of UserStories persistently
                    Container.store();
                    break;

                case "load": //loads last saved list with different options
                    switch (strings[1]) {
                        case "[merge]" -> Container.loadMerge(); //loads last saved list and merges with currently active list
                        case "[force]" -> Container.loadForce(); //loads last saved list and overrides currently active list
                        default -> System.out.println("Invalid command. For list of all commands type: 'help'.");
                    }
                    break;

                case "dump": //tabular display of currently active list sorted by priority
                    if (strings.length == 1) {
                        UserStoryView.dump(Container.getCurrentList());
                        break;
                    }

                    switch (strings[1]) {
                        case "[effort]": //same as dump + shows only UserStories with x <= effort
                            try {
                                UserStoryView.dumpEffort(Container.getCurrentList(), Integer.parseInt(strings[2]));
                                break;
                            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                                System.out.println("Try again. (Incorrect input: an integer is required)");
                                break;
                            }
                        default:
                            System.out.println("Invalid command. For list of all commands type: 'help'");
                            break;
                    }
                    break;

                case "exit": //closes the program
                    myInputDialog.closeScanner();
                    System.exit(0);

                case "help": //displays list of all available commands
                    System.out.println("list of commands:\n" +
                            "help - shows list of all available commands\n" +
                            "enter - create new user story\n" +
                            "store - saves currently active list of user stories\n" +
                            "load \n" +
                            "     [merge] - loads the last saved list of user stories and merges it with the currently active list\n" +
                            "     [force] - loads the last saved list of user stories and overrides the currently active list\n" +
                            "dump          - shows the currently active list of user stories (full list)\n" +
                            "     [effort] x - shows the currently active list of user stories (except user stories with an effort lower than x)(x has to be a number)\n" +
                            "exit - closes the program");
                    break;

                default:
                    System.out.println("Invalid command. For list of all commands type: 'help'.");
                    break;
            }
        }
    }
}
