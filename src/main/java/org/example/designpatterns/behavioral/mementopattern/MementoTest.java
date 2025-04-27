package org.example.designpatterns.behavioral.mementopattern;

public class MementoTest {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        TextEditorMementoManager manager = new TextEditorMementoManager();

        manager.save(editor);

        editor.type("Hello ");
        manager.save(editor);  // Save after typing Hello

        editor.type("World!");
        manager.save(editor); // save after typing Hello World!


        manager.undo(editor);  // Undo World!, back to Hello
        System.out.println("OUTPUT: "+ editor.getContent());

        editor.type(" Happy");
        manager.save(editor); //save after Hello Happy

        editor.type(" Weekend");
        manager.save(editor); //save after Hello Happy Weekend

        manager.undo(editor);  // Undo Weekend, back to "Hello Happy Hello"
        System.out.println("OUTPUT: "+ editor.getContent());


    }
}
