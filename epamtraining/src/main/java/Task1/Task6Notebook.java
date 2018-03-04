package Task1;

public class Task6Notebook {
    private Object[] notes = new Object[10];
    public static void main(String[] args) {
    Task6Notebook notebook = new Task6Notebook();
    notebook.addNote("note1");
    notebook.addNote("note2");
    notebook.showAllNotes();
        System.out.println();
    notebook.editNoteByPage("note3", 1);
    notebook.showAllNotes();

    }
    public void addNote(String note){
        Task6Note newNote = new Task6Note();
        newNote.setNote(note);
        for(int i = 0; i<notes.length - 1; i++){
                if(notes[i] == null){
                    notes[i] = newNote;
                    break;
                }
        }
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
        for(int i = 0; notes[i]!= null; i++){
            Task6Note note = new Task6Note();
            note = (Task6Note) notes[i];
            System.out.print(note.getNote() + " ");
        }
    }
}
