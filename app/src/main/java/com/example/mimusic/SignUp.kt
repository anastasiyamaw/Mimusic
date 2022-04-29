package com.example.mimusic

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.sign_up.*
import kotlinx.android.synthetic.main.sign_up.view.*

class SignUp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sign_up, container, false)

        view.reg_button.setOnClickListener {

            val login = login.text.toString()
            val email = email.text.toString()
            val pass = password.text.toString()
            val pass2 = password2.text.toString()
            val prefs = requireContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE)

            if (TextUtils.isEmpty(login.trim()) ||
                TextUtils.isEmpty(email.trim()) ||
                TextUtils.isEmpty(pass.trim()) ||
                TextUtils.isEmpty(pass2.trim())
            )
                Toast.makeText(context, "Заполните пустые области", Toast.LENGTH_LONG).show()
            else if (login.length < 6)
                Toast.makeText(context, "Логин должен быть не менее 6 символов", Toast.LENGTH_LONG)
                    .show()
            else if (pass != pass2)
                Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            else if (password.length() < 8)
                Toast.makeText(
                    context,
                    "Пароль должен содержать не менее 8 символов",
                    Toast.LENGTH_SHORT
                ).show()
            else if (!switch_btn.isChecked)
                Toast.makeText(
                    context,
                    "Вы не согласились с правилами использования.",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                prefs.edit().putString("login", login).apply()
                prefs.edit().putString("email", email).apply()
                prefs.edit().putString("pass", pass).apply()
                prefs.edit().putString("pass2", pass2).apply()
                Toast.makeText(context, "Вы успешно зарегистрировались", Toast.LENGTH_LONG).show()
                view.findNavController().navigate(R.id.action_signUp_to_signIn)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.signInButton.setOnClickListener{
            findNavController().navigate(R.id.action_signUp_to_signIn)
        }
    }
}