package JavaSE02;

public class Task6Notebook {
    private Object[] notes = new Object[10];
    private int arrayElementsCounter = 0;

    public static void main(String[] args) {

    Task6Notebook notebook = new Task6Notebook();

        for (int i = 0; i < 105; i++){
            notebook.addNote("note" + i);
        }

    notebook.showAllNotes();
    System.out.println();
    notebook.editNoteByPage("note3", 1);
    notebook.deleteNoteByPageNumber(10);
    notebook.showAllNotes();
    }

    public void addNote(String note){
        Task6Note newNote = new Task6Note();
        newNote.setNote(note);

        if(arrayElementsCounter == notes.length - 1){
            notes = increaseArrayCapacity(notes);
        }
            notes[arrayElementsCounter] = newNote;
            arrayElementsCounter++;
    }

    public void deleteNoteByPageNumber(int pageNumber){
        notes[pageNumber] = null;
    }

    public void editNoteByPage(String note, int page){
        Task6Note editedNote = new Task6Note();
        editedNote.setNote(note);
        notes[page] = editedNote;
    }

    public void showAllNotes(){
        for(int i = 0; i<notes.length; i++) {
            Task6Note note;
            if (notes[i] != null) {
                note = (Task6Note) notes[i];
                System.out.print(note.getNote() + " ");
            }
        }
    }

    private Object[] increaseArrayCapacity(Object[] notes){
            Object[] newNotes = new Object[notes.length * 2];
            System.arraycopy(notes, 0, newNotes, 0, notes.length);
            return newNotes;
    }
}