package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Group;

public class GroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //@@author grantcm
    @Test
    public void equals() {
        //Test same equality
        GroupCommand groupCommand = new GroupCommand(Arrays.asList("Group", "Trip", "Alice"));
        assertTrue(groupCommand.equals(groupCommand));

        //Test value equality
        GroupCommand copy = new GroupCommand(Arrays.asList("Group", "Trip", "Alice"));
        assertTrue(groupCommand.equals(copy));

        //Test unequal values
        assertFalse(groupCommand.equals(1));
        assertFalse(groupCommand.equals("False"));
    }

    @Test
    public void executeGroupCommandAddSuccessful() throws Exception {
        GroupCommand validCommand = new GroupCommand(Arrays.asList("Group", "Trip", "Alice"));
        validCommand.setData(model, new CommandHistory(), new UndoRedoStack());

        CommandResult result = validCommand.executeUndoableCommand();

        assertEquals(result.feedbackToUser, GroupCommand.MESSAGE_SUCCESS);
    }

    @Test
    public void executeGroupCommandDeleteSuccesful() throws Exception {
        GroupCommand validCommand = new GroupCommand(Arrays.asList("Trip"));
        validCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        Group trip = new Group("Trip");
        model.addGroup(trip);

        CommandResult result = validCommand.executeUndoableCommand();

        assertEquals(result.feedbackToUser, GroupCommand.MESSAGE_SUCCESS);
        assertFalse(model.groupExists(trip));
    }
    //@@author
}
