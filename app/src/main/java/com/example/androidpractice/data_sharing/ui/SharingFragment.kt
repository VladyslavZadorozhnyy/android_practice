package com.example.androidpractice.data_sharing.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentSharingBinding


class SharingFragment : Fragment() {
    private lateinit var binding: FragmentSharingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSharingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        binding.passTo.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Some data to be shared")
            shareIntent.type = "text/plain"
            startActivity(shareIntent)
        }

        binding.readFrom.setOnClickListener {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS), 0)

            val contactsUri = ContactsContract.Contacts.CONTENT_URI
            val cursor = context?.contentResolver?.query(contactsUri, null, null, null, null)

            binding.textLabel.text = "Cursor count: ${cursor?.count}"
            binding.textLabel.visibility = View.VISIBLE
            cursor?.close()
        }
    }
}