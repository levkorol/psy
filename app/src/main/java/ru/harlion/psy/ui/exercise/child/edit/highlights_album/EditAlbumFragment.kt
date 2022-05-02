package ru.harlion.psy.ui.exercise.child.edit.highlights_album


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditAlbumBinding
import ru.harlion.psy.ui.exercise.child.edit.wish_diary.EditWishFragment


class EditAlbumFragment : BindingFragment<FragmentEditAlbumBinding>(FragmentEditAlbumBinding::inflate) {

    private val viewModel : EditAlbumViewModel by viewModels()
    private var id = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getLong("ID")
        viewModel.getExById(id)

        binding.questionOne.title = getString(R.string.event)
        binding.questionTwo.title = getString(R.string.ex_album_title_two)

        observe()
        initClicks()
    }


    private fun observe() {
        viewModel.exercise.observe(viewLifecycleOwner, {
            if(id > 0) {
                binding.questionOne.setText(it.fieldOne)
                binding.questionTwo.setText(it.fieldOne)
            }
        })
    }

    private fun initClicks() {
        binding.save.setOnClickListener {
            if (id > 0) {
                viewModel.update(
                    binding.questionOne.text.toString(),
                    binding.questionTwo.text.toString(),
                    "",
                    0,
                )
            } else {
                viewModel.add(
                    binding.questionOne.text.toString(),
                    binding.questionTwo.text.toString(),
                    "",
                    0,
                )
            }
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        fun newInstance(id: Long) = EditAlbumFragment().apply {
            arguments = Bundle().apply {
                putLong("ID", id)
            }
        }
    }
}