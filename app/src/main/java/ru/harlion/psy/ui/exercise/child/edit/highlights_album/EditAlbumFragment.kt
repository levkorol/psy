package ru.harlion.psy.ui.exercise.child.edit.highlights_album


import android.os.Bundle
import android.view.View
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditAlbumBinding


class EditAlbumFragment : BindingFragment<FragmentEditAlbumBinding>(FragmentEditAlbumBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.questionOne.title = getString(R.string.event)
        binding.questionTwo.title = getString(R.string.ex_album_title_two)
    }
}