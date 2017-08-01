package com.std28.lib.utils;

import android.os.AsyncTask;


public class GenericTask
        extends AsyncTask<Void, Void, Void>
{

    private Commands mCommands;

    public interface Commands {
        void onPreExecute ();
        void doInBackground();
        void onPostExecute();
    }

    private GenericTask() {
        super();
    }

    public GenericTask(Commands commands) {
        this();
        mCommands = commands;
    }

    @Override
    protected void onPreExecute () {
        mCommands.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mCommands.doInBackground();
        return null;
    }

    protected void onPostExecute (Void result) {
        mCommands.onPostExecute();
    }


}
