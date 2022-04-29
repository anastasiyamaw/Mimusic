package com.example.mimusic

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.sign_in.view.*
import kotlinx.android.synthetic.main.sign_up.*


class SignIn : Fragment() {

    var remember = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sign_in, container, false)
        val prefs = requireContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        remember = prefs.getBoolean("CHECK", false)

        check()

        view.textView.setOnClickListener {
            val savedlog = prefs.getString("login", "")
            val savedpass = prefs.getString("pass", "")
            val log = login?.text.toString()
            val pass = password?.text.toString()

            if ((checkLogin(log) == checkPassword(pass))  &&
                    (log == savedlog) &&
                    (pass == savedpass)) {
                        val editor: SharedPreferences.Editor = prefs.edit()
                        editor.putBoolean("CHECK", true).apply()
                        Toast.makeText(context, "Вы успешно вошли", Toast.LENGTH_SHORT).show()
                        view.findNavController().navigate(R.id.action_signIn_to_secondFragment)
            }
            else {
                Toast.makeText(context, "Неверные введенные данные", Toast.LENGTH_SHORT).show()
                }
            }

        view.sighUpButton.setOnClickListener{
            findNavController().navigate(R.id.action_signIn_to_signUp)
        }
        return view
    }

    private fun check(){
        if (remember) findNavController().navigate(R.id.action_signIn_to_secondFragment)
    }

    private fun checkLogin(log:String): Boolean {
        return if (log.isBlank()) {
            Toast.makeText(context, "Вы не ввели логин", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun checkPassword(pass: String): Boolean{
        if (pass.isBlank()) {
            Toast.makeText(context, "Вы не ввели пароль", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }
}