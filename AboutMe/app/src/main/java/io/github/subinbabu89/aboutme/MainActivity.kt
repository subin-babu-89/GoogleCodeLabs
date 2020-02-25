package io.github.subinbabu89.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import io.github.subinbabu89.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val myName: MyName = MyName("Subin D")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.myName = myName
        binding.doneButton.setOnClickListener{
            addNickname(it)
            it.visibility = View.GONE
        }

        binding.nicknameText.setOnClickListener {
            updateNickName(it)
        }
    }

    private fun addNickname(view: View){
        val editText = binding.nicknameEdit
        val nicknameTextView = binding.nicknameText

        binding.apply {
            myName.nickname = nicknameEdit.text.toString()
            invalidateAll()
        }
        myName.nickname = editText.text.toString()
        editText.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE

        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }

    private fun updateNickName(view: View){
        val editText = binding.nicknameEdit
        val doneButton = binding.doneButton
        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        editText.requestFocus()

        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText,0)
    }
}
