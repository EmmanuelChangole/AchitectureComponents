package echangole.com.achitecturecomponents;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

public class NoteRepository
{
    private NoteDao noteDao;
    private LiveData<List<Note>> allNote;

    public NoteRepository(Application application)
    {
        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
        noteDao=noteDatabase.noteDao();
        allNote=noteDao.getAllNote();
    }
public void insert(Note note)
{
 new InsertNoteAsyncTask(noteDao).execute(note);
}

public void update(Note note)
{
new UpdateNoteAsyncTask(noteDao).execute(note);
}
public void delete(Note note)
{
    new DeleteNoteAsyncTask(noteDao).execute(note);

}

public void deleteAllNode()
{
new DeleteAllAsyncTask(noteDao).execute();
}
public LiveData<List<Note>> getAllNote()
{
    return allNote;
}

     private static class InsertNoteAsyncTask extends AsyncTask<Note,Void ,Void>
       {
    private NoteDao noteDao;
    private InsertNoteAsyncTask(NoteDao noteDao)
    {
        this.noteDao=noteDao;
    }


    @Override
    protected Void doInBackground(Note... notes)
    {
        noteDao.insert(notes[0]);
        return null;
    }
}
     private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void ,Void>
       {
        private NoteDao noteDao;
        private UpdateNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }


        @Override
        protected Void doInBackground(Note... notes)
        {
            noteDao.update(notes[0]);
            return null;
        }
    }
     private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void ,Void>
       {
        private NoteDao noteDao;
        private DeleteNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }


        @Override
        protected Void doInBackground(Note... notes)
        {
            noteDao.delete(notes[0]);
            return null;
        }
    }
     private static class DeleteAllAsyncTask extends AsyncTask<Void,Void ,Void>
       {
        private NoteDao noteDao;
        private DeleteAllAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNode();
            return null;
        }
    }
}
