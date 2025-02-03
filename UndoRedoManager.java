/**
 * Implememt an application that support undo/redo functionality. Use a linked list to maintain a sequence of states.\
 * Each state change is stored as a node in the list, allowoing for easy navigation
 * 1<>2<>3<>4<>5
 */


public class UndoRedoManager<T> {
    private class StateNode {
        T state;
        StateNode prev;
        StateNode next;

        StateNode(T state) {
            this.state = state;
        }
    }

    private StateNode current;

    public UndoRedoManager() {
        this.current = null;
    }

    // Undo operation
    public T undo() {
        if (current == null || current.prev == null) {
            return null;
        }
        current = current.prev;
        return current.state;
    }

    // Redo operation
    public T redo() {
        if (current == null || current.next == null) {
            return null;
        }
        current = current.next;
        return current.state;
    }

    // Add new state
    public void addState(T newState) {
        StateNode newNode = new StateNode(newState);
        if (current == null) {
            current = newNode;
        } else {
            // Remove all future nodes if we're not at the latest state
            current.next = null;

            newNode.prev = current;
            current.next = newNode;
            current = newNode;
        }
    }

    // Get current state
    public T getCurrentState() {
        return (current != null) ? current.state : null;
    }

    public static void main(String[] args) {
        UndoRedoManager<String> undoRedoManager = new UndoRedoManager<>();
        undoRedoManager.addState("1");
        undoRedoManager.addState("2");
        undoRedoManager.addState("3");
        undoRedoManager.addState("4");

        System.out.println(undoRedoManager.undo()); // Should print "3"
        System.out.println(undoRedoManager.undo()); // Should print "2"
        System.out.println(undoRedoManager.undo()); // Should print "1"
        System.out.println(undoRedoManager.redo()); // Should print "2"
        System.out.println(undoRedoManager.redo()); // Should print "3"
        System.out.println();

        System.out.println(undoRedoManager.getCurrentState()); // Should print "3"
    }
}

