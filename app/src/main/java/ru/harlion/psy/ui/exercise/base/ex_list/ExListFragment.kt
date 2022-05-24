package ru.harlion.psy.ui.exercise.base.ex_list


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.FragmentExListBinding
import ru.harlion.psy.databinding.ItemExListVpBinding
import ru.harlion.psy.models.Exercise

import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.ui.exercise.child.edit.free_writing.EditFreeWritingFragment
import ru.harlion.psy.ui.exercise.child.edit.highlights_album.EditAlbumFragment
import ru.harlion.psy.ui.exercise.base.edit.text_recycler.EditExTextRecyclerFragment
import ru.harlion.psy.ui.exercise.base.edit.edit_text.EditTextViewsFragment
import ru.harlion.psy.ui.exercise.child.edit.wish_diary.EditWishFragment
import ru.harlion.psy.ui.exercise.parent.edit.EditBeliefFragment
import ru.harlion.psy.utils.replaceFragment


class ExListFragment : BindingFragment<FragmentExListBinding>(FragmentExListBinding::inflate) {

    private val viewModel: ExViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeEx = requireArguments().getSerializable("ENUM")
        val tabs = requireArguments().getIntArray("TABS_TEXT_IDS")
        val title = requireArguments().getInt("TITLE")!!

        viewModel.getEx(typeEx = typeEx as TypeEx, true)
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = ListAdapter(viewModel.exercises, {
            when (typeEx) {
                TypeEx.GRATITUDE_DIARY -> checkEnumAndReplaceFragment(TypeEx.GRATITUDE_DIARY, it)
                TypeEx.FAIL_DIARY -> checkEnumAndReplaceFragment(TypeEx.FAIL_DIARY, it)
                TypeEx.ACTS_SELF_LOVE -> checkEnumAndReplaceFragment(TypeEx.ACTS_SELF_LOVE, it)
                TypeEx.MY_AMBULANCE -> checkEnumAndReplaceFragment(TypeEx.MY_AMBULANCE, it)
                TypeEx.PERFECT_LIFE -> checkEnumAndReplaceFragment(TypeEx.PERFECT_LIFE, it)
                TypeEx.SUCCESS_DIARY -> checkEnumAndReplaceFragment(TypeEx.SUCCESS_DIARY, it)
                TypeEx.LIFE_RULES -> checkEnumAndReplaceFragment(TypeEx.LIFE_RULES, it)
                TypeEx.POSITIVE_BELIEFS -> checkEnumAndReplaceFragment(TypeEx.POSITIVE_BELIEFS, it)
                TypeEx.IDEAS_DIARY -> checkEnumAndReplaceFragment(TypeEx.IDEAS_DIARY, it)
                TypeEx.SELF_ESTEEM -> checkEnumAndReplaceFragment(TypeEx.SELF_ESTEEM, it)
                TypeEx.WISH_DIARY -> checkEnumAndReplaceFragment(TypeEx.WISH_DIARY, it)
                TypeEx.WORK_WITH_BELIEFS -> checkEnumAndReplaceFragment(
                    TypeEx.WORK_WITH_BELIEFS,
                    it
                )
                TypeEx.FREE_WRITING -> checkEnumAndReplaceFragment(TypeEx.FREE_WRITING, it)
                TypeEx.HIGHLIGHTS_ALBUM -> checkEnumAndReplaceFragment(TypeEx.HIGHLIGHTS_ALBUM, it)
            }
        }, {
            infoFragmentAndSetText(typeEx)
        })

        if (tabs != null) {
            binding.tab.visibility = View.VISIBLE
            TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
                tab.text = getString(tabs[position])
            }.attach()
            binding.viewPager.isUserInputEnabled = true
        }

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.titleToolbar.text = resources.getText(title)

        binding.addBtnMain.setOnClickListener {
            checkEnumAndReplaceFragment(typeEx, 0)
        }

        binding.info.setOnClickListener {
            infoFragmentAndSetText(typeEx)
        }

    }

    private fun infoFragmentAndSetText(typeEx: TypeEx) {
        when (typeEx) {
            TypeEx.HIGHLIGHTS_ALBUM -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_album_one,
                    R.string.ex_info_album_two,
                    R.string.ex_info_album_three,
                    R.string.ex_info_album_for,
                    R.string.info
                ), true
            )
            TypeEx.IDEAS_DIARY -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_idea_one,
                    R.string.ex_info_idea_two,
                    R.string.ex_info_idea_three,
                    R.string.ex_info_idea_for,
                    R.string.info
                ), true
            )
            TypeEx.WISH_DIARY -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_wish_one,
                    R.string.ex_info_wish_two,
                    R.string.ex_info_wish_three,
                    R.string.ex_info_wish_for,
                    R.string.info
                ), true
            )
            TypeEx.FREE_WRITING -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_free_writing_one,
                    R.string.ex_info_free_writing_two,
                    R.string.ex_info_free_writing_three,
                    R.string.ex_info_free_writing_for,
                    R.string.info
                ), true
            )
            TypeEx.GRATITUDE_DIARY -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_gratitude_one,
                    R.string.ex_info_gratitude_two,
                    R.string.ex_info_gratitude_three,
                    R.string.ex_info_gratitude_for,
                    toolbar = R.string.info
                ), true
            )
            TypeEx.SELF_ESTEEM -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_self_one,
                    R.string.ex_info_self_two,
                    R.string.ex_info_self_three,
                    R.string.ex_info_self_for,
                    R.string.info
                ), true
            )
            TypeEx.FAIL_DIARY -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    oneTitle = R.string.ex_info_fail_one,
                    twoTitle = R.string.ex_info_fail_two,
                    forTitle = R.string.ex_info_fail_for,
                    toolbar = R.string.info
                ), true
            )
            TypeEx.ACTS_SELF_LOVE -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    oneTitle = R.string.ex_info_acts_one,
                    twoTitle = R.string.ex_info_acts_two,
                    forTitle = R.string.ex_info_acts_for,
                    toolbar = R.string.info
                ), true
            )
            TypeEx.MY_AMBULANCE -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_ambulance_one,
                    R.string.ex_info_ambulance_two,
                    R.string.ex_info_ambulance_three,
                    R.string.ex_info_ambulance_for,
                    toolbar = R.string.info
                ), true
            )
            TypeEx.PERFECT_LIFE -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_perfect_life_one,
                    R.string.ex_info_perfect_life_two,
                    R.string.ex_info_perfect_life_three,
                    R.string.ex_info_perfect_life_for,
                    toolbar = R.string.info
                ), true
            )
            TypeEx.SUCCESS_DIARY -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_success_one,
                    R.string.ex_info_success_two,
                    R.string.ex_info_success_three,
                    R.string.ex_info_success_for,
                    toolbar = R.string.info
                ), true
            )
            TypeEx.WORK_WITH_BELIEFS -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_work_beliefs_one,
                    R.string.ex_info_work_beliefs_two,
                    R.string.ex_info_work_beliefs_three,
                    R.string.ex_info_work_beliefs_for,
                    toolbar = R.string.info
                ), true
            )
            TypeEx.LIFE_RULES -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_work_rules_one,
                    R.string.ex_info_work_rules_two,
                    R.string.ex_info_work_rules_three,
                    R.string.ex_info_work_rules_for,
                    toolbar = R.string.info
                ), true
            )
            TypeEx.POSITIVE_BELIEFS -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_info_work_positive_one,
                    R.string.ex_info_work_positive_two,
                    R.string.ex_info_work_positive_three,
                    R.string.ex_info_work_positive_for,
                    toolbar = R.string.info
                ), true
            )
            TypeEx.NOTHING -> replaceFragment(
                ExInstructionsFragment.newInstance(
                    R.string.ex_album_title_two,
                    R.string.ex_album_title_two,
                    R.string.ex_album_title_two,
                    R.string.ex_album_title_two,
                    toolbar = R.string.info
                ), true
            )
        }
    }

    private fun checkEnumAndReplaceFragment(typeEx: TypeEx, id: Long) {
        when (typeEx) {
            TypeEx.LIFE_RULES -> {
                replaceFragment(
                    EditExTextRecyclerFragment.newInstance(
                        id,
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
                        id,
                        R.string.sphere_life,
                        R.string.ex_positive_hint,
                        R.string.new_belief,
                        TypeEx.POSITIVE_BELIEFS
                    ), true
                )
            }
            TypeEx.IDEAS_DIARY -> {
                replaceFragment(
                    EditExTextRecyclerFragment.newInstance(
                        id,
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
                        id,
                        R.string.theme_facts,
                        R.string.ex_self_hint,
                        R.string.fact_about_me,
                        TypeEx.SELF_ESTEEM
                    ), true
                )
            }
            TypeEx.WISH_DIARY -> replaceFragment(EditWishFragment.newInstance(id), true)
            TypeEx.FREE_WRITING -> replaceFragment(EditFreeWritingFragment.newInstance(id), true)
            TypeEx.HIGHLIGHTS_ALBUM -> replaceFragment(EditAlbumFragment.newInstance(id), true)
            TypeEx.WORK_WITH_BELIEFS -> replaceFragment(EditBeliefFragment.newInstance(id), true)
            TypeEx.GRATITUDE_DIARY -> {
                replaceFragment(
                    EditTextViewsFragment.newInstance(
                        titleOne = R.string.gratitude_question_one,
                        titleTwo = R.string.gratitude_question_two,
                        titleThree = R.string.gratitude_question_three,
                        typeEx = TypeEx.GRATITUDE_DIARY,
                        id = id
                    ), true
                )
            }
            TypeEx.FAIL_DIARY -> {
                replaceFragment(
                    EditTextViewsFragment.newInstance(
                        titleOne = R.string.fail_diary_question_1,
                        hintOne = R.string.gratitude_question_one,
                        infoOne = R.string.gratitude_question_one,
                        titleTwo = R.string.fail_diary_question_2,
                        hintTwo = R.string.gratitude_question_one,
                        infoTwo = R.string.gratitude_question_one,
                        titleThree = R.string.fail_diary_question_3,
                        hintThree = R.string.gratitude_question_one,
                        infoThree = R.string.gratitude_question_one,
                        typeEx = TypeEx.FAIL_DIARY,
                        id = id
                    ), true
                )
            }
            TypeEx.ACTS_SELF_LOVE -> {
                replaceFragment(
                    EditTextViewsFragment.newInstance(
                        titleOne = R.string.love_self_question_one,
                        hintOne = R.string.gratitude_question_one,
                        infoOne = R.string.gratitude_question_one,
                        titleTwo = R.string.love_self_question_two,
                        hintTwo = R.string.gratitude_question_one,
                        infoTwo = R.string.gratitude_question_one,
                        titleThree = R.string.love_self_question_three,
                        hintThree = R.string.gratitude_question_one,
                        infoThree = R.string.gratitude_question_one,
                        typeEx = TypeEx.ACTS_SELF_LOVE,
                        id = id
                    ), true
                )
            }
            TypeEx.MY_AMBULANCE -> {
                replaceFragment(
                    EditTextViewsFragment.newInstance(
                        titleOne = R.string.my_ambulance_question_one,
                        hintOne = R.string.gratitude_question_one,
                        infoOne = R.string.gratitude_question_one,
                        titleTwo = R.string.my_ambulance_question_two,
                        hintTwo = R.string.gratitude_question_one,
                        infoTwo = R.string.gratitude_question_one,
                        titleThree = R.string.my_ambulance_question_three,
                        hintThree = R.string.gratitude_question_one,
                        infoThree = R.string.gratitude_question_one,
                        typeEx = TypeEx.MY_AMBULANCE,
                        id = id
                    ), true
                )
            }
            TypeEx.PERFECT_LIFE -> {
                replaceFragment(
                    EditTextViewsFragment.newInstance(
                        titleOne = R.string.perfect_life_question_one,
                        infoOne = R.string.gratitude_question_one,
                        titleTwo = R.string.perfect_life_question_two,
                        infoTwo = R.string.gratitude_question_one,
                        typeEx = TypeEx.PERFECT_LIFE,
                        id = id
                    ), true
                )
            }
            TypeEx.SUCCESS_DIARY -> {
                replaceFragment(
                    EditTextViewsFragment.newInstance(
                        titleOne = R.string.success_diary_question_one,
                        hintOne = R.string.gratitude_question_one,
                        infoOne = R.string.gratitude_question_one,
                        titleTwo = R.string.success_diary_question_two,
                        hintTwo = R.string.gratitude_question_one,
                        infoTwo = R.string.gratitude_question_one,
                        titleThree = R.string.success_diary_question_three,
                        hintThree = R.string.gratitude_question_one,
                        infoThree = R.string.gratitude_question_one,
                        typeEx = TypeEx.SUCCESS_DIARY,
                        id = id
                    ), true
                )
            }
//            TypeEx.NOTHING -> null
        }
    }

    override fun onDestroyView() {
        binding.viewPager.adapter = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(
            title: Int,
            typeEx: TypeEx,
            active: Int = 0,
            archive: Int = 0
        ) =
            ExListFragment().apply {
                arguments = Bundle().apply {
                    putInt("TITLE", title)
                    putSerializable("ENUM", typeEx)
                    if (active != 0 && archive != 0) {
                        putIntArray("TABS_TEXT_IDS", intArrayOf(active, archive))
                    }
                }
            }
    }

    class ListAdapter(
        val items: List<LiveData<List<Exercise>>>,
        val clickEdit: (Long) -> Unit,
        val clickInfo: () -> Unit
    ) :
        RecyclerView.Adapter<Holder>() {

        override fun getItemCount(): Int = items.size

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): Holder {
            return Holder(parent).apply {
                binding.recyclerEx.apply {
                    layoutManager = LinearLayoutManager(parent.context)
                    adapter = AdapterEx(clickEdit)
                }
            }
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.elements?.removeObserver(holder)
            holder.elements = items[position]
            items[position].observeForever(holder)
            holder.binding.btnBigInstruction.setOnClickListener { clickInfo.invoke() }
        }

        override fun onViewRecycled(holder: Holder) {
            super.onViewRecycled(holder)
            holder.elements?.removeObserver(holder)
            holder.elements = null
        }
    }
}

class Holder(parent: ViewGroup) :
    BindingHolder<ItemExListVpBinding>(ItemExListVpBinding::inflate, parent),
    Observer<List<Exercise>> {

    var elements: LiveData<List<Exercise>>? = null

    override fun onChanged(it: List<Exercise>) {
        binding.apply {

            if (it.isNotEmpty()) {
                recyclerEx.visibility = View.VISIBLE
                emptyList.visibility = View.GONE
                (recyclerEx.adapter as AdapterEx).items = it
            } else {
                recyclerEx.visibility = View.GONE
                emptyList.visibility = View.VISIBLE
            }
        }
    }
}