package com.example.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    private int counter = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        view.setBackgroundResource(R.color.red);

        Button button = view.findViewById(R.id.buttonShowMenu);
        button.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), v);
            MenuInflater inflater1 = popupMenu.getMenuInflater();
            inflater1.inflate(R.menu.popup_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.counter) {
                    counter++;
                    Toast.makeText(getActivity(), "Счётчик: " + counter, Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });

            popupMenu.show();
        });

        return view;
    }
}
