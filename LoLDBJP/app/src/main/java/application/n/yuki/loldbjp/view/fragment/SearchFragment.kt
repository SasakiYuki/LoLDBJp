package application.n.yuki.loldbjp.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.*
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.Views
import application.n.yuki.loldbjp.type.ChampionParttypeType
import application.n.yuki.loldbjp.type.ChampionType
import application.n.yuki.loldbjp.view.base.BaseFragment

/**
 * Created by yuki.n on 2017/04/18.
 */
class SearchFragment : BaseFragment() {
    val typeSpinner: Spinner by Views.bind(this, R.id.fragment_search_type_spinner)
    val partypeSpinner: Spinner by Views.bind(this, R.id.fragment_search_partype_spinner)
    val editText: EditText by Views.bind(this, R.id.fragment_search_edit)
    val button: Button by Views.bind(this, R.id.fragment_search_button)
    var searchedType: String = ""
    var searchedPartyp: String = ""

    override fun setContentLayout() = R.layout.fragment_search

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    fun setUpViews() {
        setUpTypeSpinner()
        setUpPartypeSpinner()
        button.setOnClickListener {
            targetFragment?.let {
                val intent = Intent()
                intent.putExtra(ChampionListFragment.ARG_SEARCH_KEY, searchedType)
                intent.putExtra(ChampionListFragment.ARG_SEARCH_PARTYPE, searchedPartyp)
                intent.putExtra(ChampionListFragment.ARG_SEARCH_NAME, editText.text.toString())
                targetFragment.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            }
            popBackStack()
        }
    }

    fun setUpTypeSpinner() {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val array = ChampionType.values()
        for (item in array) {
            adapter.add(item.japaneseName)
        }
        typeSpinner.adapter = adapter
        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                searchedType = array[position].string
            }
        }
    }

    fun setUpPartypeSpinner() {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val array = ChampionParttypeType.values()
        for (item in array) {
            adapter.add(item.string)
        }
        partypeSpinner.adapter = adapter
        partypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                searchedPartyp = array[position].string
            }
        }
    }

    companion object {
        fun newInstance(targetFragment: Fragment): SearchFragment {
            val fragment = SearchFragment()
            fragment.setTargetFragment(targetFragment, requestCode)
            val args = Bundle()
            args.putInt(ARG_CONTENTS_NAME_ID, R.string.fragment_search_title)
            fragment.arguments = args
            return fragment
        }

        val requestCode = 100
    }
}