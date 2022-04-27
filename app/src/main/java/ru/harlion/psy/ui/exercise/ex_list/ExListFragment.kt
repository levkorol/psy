package ru.harlion.psy.ui.exercise.ex_list


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentExListBinding

import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.child.edit.EditFreeWritingFragment
import ru.harlion.psy.ui.exercise.child.edit.highlights_album.EditAlbumFragment
import ru.harlion.psy.ui.exercise.base.edit.text_recycler.EditExTextRecyclerFragment
import ru.harlion.psy.ui.exercise.base.edit.EditTextViewsFragment
import ru.harlion.psy.ui.exercise.child.edit.wish_diary.EditWishFragment
import ru.harlion.psy.ui.exercise.parent.edit.EditBeliefFragment
import ru.harlion.psy.utils.replaceFragment


class ExListFragment : BindingFragment<FragmentExListBinding>(FragmentExListBinding::inflate) {

    private val viewModel: ExViewModel by viewModels()
    private lateinit var adapterEx: AdapterEx

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeEx = requireArguments().getSerializable("ENUM")
        val tabs = requireArguments().getIntArray("TABS_TEXT_IDS")
        val (title, textInfo) = requireArguments().getIntArray("TEXTS_IDS_LIST_FG")!!

        viewModel.getEx( typeEx = typeEx as TypeEx)

        adapterEx = AdapterEx()
        binding.recyclerEx.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEx
        }

        observe()

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }


        binding.titleToolbar.text = resources.getText(title)

        binding.addBtnMain.setOnClickListener {
            when (typeEx) {
                TypeEx.LIFE_RULES -> {
                    replaceFragment(
                        EditExTextRecyclerFragment.newInstance(
                            R.string.sphere_life,
                            R.string.ex_rules_hint,
                            R.string.new_rule,
                            TypeEx.LIFE_RULES
                        ), true
                    )
                }
                TypeEx.POSITIVE_BELIEFS -> {
                    replaceFragment(
                        EditExTextRecyclerFragment.newInstance(
                            R.string.sphere_life,
                            R.string.ex_idea_hint,
                            R.string.new_belief,
                            TypeEx.POSITIVE_BELIEFS
                        ), true
                    )
                }
                TypeEx.IDEAS_DIARY -> {
                    replaceFragment(
                        EditExTextRecyclerFragment.newInstance(
                            R.string.theme_ideas,
                            R.string.ex_idea_hint,
                            R.string.new_idea,
                            TypeEx.IDEAS_DIARY
                        ), true
                    )
                }
                TypeEx.SELF_ESTEEM -> {
                    replaceFragment(
                        EditExTextRecyclerFragment.newInstance(
                            R.string.theme_facts,
                            R.string.ex_self_hint,
                            R.string.fact_about_me,
                            TypeEx.SELF_ESTEEM
                        ), true
                    )
                }
                TypeEx.WISH_DIARY -> replaceFragment(EditWishFragment(), true)
                TypeEx.FREE_WRITING -> replaceFragment(EditFreeWritingFragment(), true)
                TypeEx.HIGHLIGHTS_ALBUM -> replaceFragment(EditAlbumFragment(), true)
                TypeEx.WORK_WITH_BELIEFS -> replaceFragment(EditBeliefFragment(), true)
                TypeEx.GRATITUDE_DIARY -> {
                    replaceFragment(
                        EditTextViewsFragment.newInstance(
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_two,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_three,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            TypeEx.GRATITUDE_DIARY
                        ), true
                    )
                }
                TypeEx.FAIL_DIARY -> {
                    replaceFragment(
                        EditTextViewsFragment.newInstance(
                            R.string.fail_diary_question_1,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.fail_diary_question_2,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.fail_diary_question_3,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            TypeEx.FAIL_DIARY
                        ), true
                    )
                }
                TypeEx.ACTS_SELF_LOVE -> {
                    replaceFragment(
                        EditTextViewsFragment.newInstance(
                            R.string.love_self_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.love_self_question_two,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.love_self_question_three,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            TypeEx.ACTS_SELF_LOVE
                        ), true
                    )
                }
                TypeEx.MY_AMBULANCE -> {
                    replaceFragment(
                        EditTextViewsFragment.newInstance(
                            R.string.my_ambulance_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.my_ambulance_question_two,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.my_ambulance_question_three,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            TypeEx.MY_AMBULANCE
                        ), true
                    )
                }
                TypeEx.PERFECT_LIFE -> {
                    replaceFragment(
                        EditTextViewsFragment.newInstance(
                            R.string.my_ambulance_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.my_ambulance_question_two,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            TypeEx.PERFECT_LIFE
                        ), true
                    )
                }
                TypeEx.SUCCESS_DIARY -> {
                    replaceFragment(
                        EditTextViewsFragment.newInstance(
                            R.string.success_diary_question_one,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.success_diary_question_two,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            R.string.success_diary_question_three,
                            R.string.gratitude_question_one,
                            R.string.gratitude_question_one,
                            TypeEx.SUCCESS_DIARY
                        ), true
                    )
                }
            }
        }
    }

    private fun observe() {
        viewModel.exercises.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                binding.recyclerEx.visibility = View.VISIBLE
                binding.emptyList.visibility = View.GONE
                adapterEx.items = it
            } else {
                binding.recyclerEx.visibility = View.GONE
                binding.emptyList.visibility = View.VISIBLE
            }
        })
    }

    companion object {
        fun newInstance(title: Int, textInfo: Int, typeEx: TypeEx, active: Int = 0, archive: Int = 0) =
            ExListFragment().apply {
                arguments = Bundle().apply {
                    putIntArray("TEXTS_IDS_LIST_FG", intArrayOf(title, textInfo))
                    putSerializable("ENUM", typeEx)
                    if(active != 0 && archive != 0) {
                        putIntArray("TABS_TEXT_IDS", intArrayOf(active, archive))
                    }
                }
            }
    }
}