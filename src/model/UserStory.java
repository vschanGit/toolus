package model;

import control.InputDialog;

import java.io.Serializable;
import java.util.UUID;

public class UserStory implements Serializable, Comparable<UserStory> {

    //UserStory attributes
    private final UUID uuid;
    private String description;
    private String acceptanceTest;
    private int relativeValue;
    private int relativeLoss;
    private int relativeRisk;
    private int relativeEffort;
    private double priority;

    //non-parameter constructor
    public UserStory() {
        this.uuid = UUID.randomUUID();
    }

    //parameter constructor
    public UserStory(String description, String acceptanceTest, int relativeValue, int relativeLoss, int relativeRisk, int relativeEffort) {
        this.uuid = UUID.randomUUID();
        this.description = description;
        this.acceptanceTest = acceptanceTest;
        this.relativeValue = relativeValue;
        this.relativeLoss = relativeLoss;
        this.relativeRisk = relativeRisk;
        this.relativeEffort = relativeEffort;
        this.calculatePriority();
    }

    //getter + setter for all attributes (except setter for priority and uuid)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcceptanceTest() {
        return acceptanceTest;
    }

    public void setAcceptanceTest(String acceptanceTest) {
        this.acceptanceTest = acceptanceTest;
    }

    public int getRelativeValue() {
        return relativeValue;
    }

    public void setRelativeValue(int relativeValue) {
        //checks if entered integer is in allowed range [1, 5]
        if (1 <= relativeValue & relativeValue <= 5) {
            this.relativeValue = relativeValue;
            this.calculatePriority();
        } else {
            System.out.print("The entered relative value: [" + relativeValue + "] is invalid. \nPlease enter a correct value (1-5): ");
            setRelativeValue(InputDialog.enterInt());
        }
    }

    public int getRelativeLoss() {
        return relativeLoss;
    }

    public void setRelativeLoss(int relativeLoss) {
        //checks if entered integer is in allowed range [1, 5]
        if (1 <= relativeLoss & relativeLoss <= 5) {
            this.relativeLoss = relativeLoss;
            this.calculatePriority();
        } else {
            System.out.print("The entered relative loss: [" + relativeLoss + "] is invalid. \nPlease enter a correct value (1-5): ");
            setRelativeLoss(InputDialog.enterInt());
        }
    }

    public int getRelativeRisk() {
        return relativeRisk;
    }

    public void setRelativeRisk(int relativeRisk) {
        //checks if entered integer is in allowed range [1, 5]
        if (1 <= relativeRisk & relativeRisk <= 5) {
            this.relativeRisk = relativeRisk;
            this.calculatePriority();
        } else {
            System.out.print("The entered relative risk: [" + relativeRisk + "] is invalid. \nPlease enter a correct value (1-5): ");
            setRelativeRisk(InputDialog.enterInt());
        }
    }

    public int getRelativeEffort() {
        return relativeEffort;
    }

    public void setRelativeEffort(int relativeEffort) {
        //checks if entered integer is in allowed range [1, 5]
        if (1 <= relativeEffort & relativeEffort <= 5) {
            this.relativeEffort = relativeEffort;
            this.calculatePriority();
        } else {
            System.out.print("The entered relative effort: [" + relativeEffort + "] is invalid. \nPlease enter a correct value (1-5): ");
            setRelativeEffort(InputDialog.enterInt());
        }
    }

    public double getPriority() {
        return priority;
    }

    public UUID getUuid() {
        return uuid;
    }

    //calculates the priority based on attributes and formula
    public void calculatePriority() {
        Double rv = (double) this.relativeValue;
        Double rl = (double) this.relativeLoss;
        Double rr = (double) this.relativeRisk;
        Double re = (double) this.relativeEffort;
        this.priority = (rv + rl) / (rr + re); //formula for priority
        this.priority = Math.round(this.priority * 100.0) / 100.0; //rounds the result to two decimals
    }

    //tabular display of UserStory
    @Override
    public String toString() {
        return "[Priority: " + priority
                + " | Description: " + description
                + " | Acceptance test: " + acceptanceTest
                + " | Relative value: " + relativeValue
                + " | Relative loss: " + relativeLoss
                + " | Relative risk: " + relativeRisk
                + " | Relative effort: " + relativeEffort
                + " | ID: " + uuid + "]";
    }

    //priority as primary comparison key
    @Override
    public int compareTo(UserStory userStory) {
        if (this.priority < userStory.priority) {
            return -1;
        }
        if (this.priority > userStory.priority) {
            return 1;
        }
        if (this.priority == userStory.priority) {
            return 0;
        }
        return this.compareTo(userStory);
    }
}
