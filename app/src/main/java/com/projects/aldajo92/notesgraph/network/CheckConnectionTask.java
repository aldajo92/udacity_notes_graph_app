package com.projects.aldajo92.notesgraph.network;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CheckConnectionTask extends AsyncTask<Void, Void, Boolean> {

    private CheckConnectionListener checkConnectionListener;

    public CheckConnectionTask(CheckConnectionListener checkConnectionListener) {
        this.checkConnectionListener = checkConnectionListener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            Socket sock = new Socket();
            sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
            sock.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean isConnected) {
        checkConnectionListener.connectionEvent(isConnected);
    }

}