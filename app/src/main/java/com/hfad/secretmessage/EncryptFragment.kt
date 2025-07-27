package com.hfad.secretmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.File

class EncryptFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_encrypt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cryptoManager = CryptoManager()

        val message = EncryptFragmentArgs.fromBundle(requireArguments()).message
        val encryptedView = view.findViewById<TextView>(R.id.encrypted_message)

        val file = File(requireContext().filesDir, "secret.txt")

        if (message.isNotBlank()) {
            try {
                if (!file.exists()) {
                    file.createNewFile()
                }
                val encrypted = cryptoManager.encrypt(
                    bytes = message.toByteArray(Charsets.UTF_8)
                )
                encryptedView.text = encrypted.decodeToString()
            } catch (e: Exception) {
                encryptedView.text = "Encryption failed"
            }
        }
    }
}