package com.example.transportezaragozaapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class CargadorDialog {

    private final Activity activity;
    private AlertDialog dialog;


    public CargadorDialog(Activity myActivity) {
        activity = myActivity;
    }


    public void iniciarCargadorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.cargador_dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }


    public void cancelarCargadorDialog() {
        dialog.dismiss();
    }
}
