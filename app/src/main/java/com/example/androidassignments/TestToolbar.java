package com.example.androidassignments;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidassignments.databinding.ActivityTestToolbarBinding;
import com.google.android.material.snackbar.Snackbar;

public class TestToolbar extends AppCompatActivity {

    private final ToolbarMessageState messageState =
            new ToolbarMessageState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityTestToolbarBinding binding =
                ActivityTestToolbarBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(view ->
                Snackbar.make(view,
                        "You clicked the floating action button",
                        Snackbar.LENGTH_LONG).show()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.choice1) {

            Snackbar.make(findViewById(R.id.main),
                    messageState.getMessage(),
                    Snackbar.LENGTH_LONG).show();

            return true;

        } else if (id == R.id.choice2) {

            new AlertDialog.Builder(this)
                    .setTitle(R.string.go_back_title)
                    .setPositiveButton(R.string.yes,
                            (dialog, which) -> finish())
                    .setNegativeButton(R.string.no,
                            (dialog, which) -> dialog.dismiss())
                    .show();

            return true;

        } else if (id == R.id.choice3) {

            View dialogView = getLayoutInflater()
                    .inflate(R.layout.dialog_new_message, null);

            EditText messageInput =
                    dialogView.findViewById(R.id.edit_new_message);

            new AlertDialog.Builder(this)
                    .setTitle("New Message")
                    .setIcon(R.drawable.ic_info)
                    .setView(dialogView)
                    .setPositiveButton("OK", (dialog, which) ->
                            messageState.setMessage(
                                    messageInput.getText().toString()))
                    .setNegativeButton("Cancel",
                            (dialog, which) -> dialog.dismiss())
                    .show();

            return true;

        } else if (id == R.id.about) {

            Toast.makeText(this,
                    "Version 1.0, by Saud Poonawala",
                    Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
