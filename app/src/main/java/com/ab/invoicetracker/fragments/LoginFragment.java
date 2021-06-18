package com.ab.invoicetracker.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.invoicetracker.BaseFragment;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.activities.HomeActivity;
import com.ab.invoicetracker.databinding.FragmentLoginBinding;
import com.ab.invoicetracker.handler.UserLoginClickHandler;
import com.ab.invoicetracker.network.NetworkCallController;
import com.ab.invoicetracker.network.NetworkResponseListner;
import com.ab.invoicetracker.network.model.login.LoginData;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragment implements UserLoginClickHandler {
    FragmentLoginBinding mFragmentLoginBinding;

    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return mFragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentLoginBinding.setHandler(this);
    }

    @Override
    public void onLoginClicked(View view) {
        try {
            if (getValidated()) {
                NetworkCallController controller = new NetworkCallController(this);
                String username = mFragmentLoginBinding.edtUsername.getText().toString();
                String password = mFragmentLoginBinding.edtPassword.getText().toString();
                controller.setListner(new NetworkResponseListner<LoginData>() {
                    @Override
                    public void onResponse(LoginData response) {
                        getRealm().beginTransaction();
                        getRealm().deleteAll();
                        getRealm().commitTransaction();
                        // add new record
                        getRealm().beginTransaction();
                        getRealm().copyToRealmOrUpdate(response);
                        getRealm().commitTransaction();
                        startActivity(new Intent(getActivity(), HomeActivity.class));
                        Objects.requireNonNull(getActivity()).finishAffinity();
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                controller.login(username, password);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean getValidated() {
        if (mFragmentLoginBinding.edtUsername.getText().toString().length() == 0) {
            mFragmentLoginBinding.edtUsername.setError("Please enter username");
            return false;
        } else if (mFragmentLoginBinding.edtPassword.getText().toString().length() == 0) {
            mFragmentLoginBinding.edtPassword.setError("Please enter password");
            return false;
        } else {
            return true;
        }
    }
}