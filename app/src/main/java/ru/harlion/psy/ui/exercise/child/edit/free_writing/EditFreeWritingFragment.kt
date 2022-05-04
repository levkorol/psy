package ru.harlion.psy.ui.exercise.child.edit.free_writing


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditFreeWritingBinding
import ru.harlion.psy.ui.exercise.child.edit.highlights_album.EditAlbumFragment
import ru.harlion.psy.ui.exercise.child.edit.highlights_album.EditAlbumViewModel


class EditFreeWritingFragment :
    BindingFragment<FragmentEditFreeWritingBinding>(FragmentEditFreeWritingBinding::inflate) {

    private val viewModel: EditAlbumViewModel by viewModels()
    private var id = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getLong("ID")
        viewModel.getExById(id)

        binding.fieldOne.title = resources.getText(R.string.ex_free_writing_qwestion_one)

        binding.btnBeginEx.setOnClickListener {
            binding.stepOne.visibility = View.GONE
            binding.stepTwo.visibility = View.VISIBLE

        }

        observe()
        initClicks()

    }


    private fun observe() {
        viewModel.exercise.observe(viewLifecycleOwner, {
            if(id > 0) {
                binding.fieldOne.setText(it.fieldOne)
                binding.fieldTwo.text = it.fieldOne
            }
        })
    }

    private fun initClicks() {
        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }
//        binding.save.setOnClickListener {
//            if (id > 0) {
//                viewModel.update(
//                    binding.questionOne.text.toString(),
//                    binding.questionTwo.text.toString(),
//                    "",
//                    0,
//                )
//            } else {
//                viewModel.add(
//                    binding.questionOne.text.toString(),
//                    binding.questionTwo.text.toString(),
//                    "",
//                    0,
//                )
//            }
//            parentFragmentManager.popBackStack()
//        }
    }

    companion object {
        fun newInstance(id: Long) = EditFreeWritingFragment().apply {
            arguments = Bundle().apply {
                putLong("ID", id)
            }
        }
    }

}