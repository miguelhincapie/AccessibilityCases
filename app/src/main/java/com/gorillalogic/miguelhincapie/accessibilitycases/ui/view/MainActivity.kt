package com.gorillalogic.miguelhincapie.accessibilitycases.ui.view

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.domain.entities.CarouselElement
import com.gorillalogic.miguelhincapie.domain.entities.GridElement
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.RecyclerViewType
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel.CarouselAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel.CarouselElementDelegateAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel.CarouselElementViewType
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid.GridAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid.GridElementDelegateAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid.GridElementViewType
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel.TalkBackViewModel
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel.TalkBackViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(),
    GridElementDelegateAdapter.OnGridElementListener,
    CarouselElementDelegateAdapter.OnCarouselElementListener {

    private lateinit var gridAdapter: GridAdapter
    private lateinit var carouselAdapter: CarouselAdapter

    @Inject
    internal lateinit var talkBackViewModelFactory: TalkBackViewModelFactory
    private val talkBackViewModel: TalkBackViewModel by viewModels { talkBackViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridAdapter =
            GridAdapter(
                this
            )
        gridRV.apply {
            layoutManager = object : GridLayoutManager(applicationContext, NUMBER_OF_COLUMNS) {
                override fun canScrollHorizontally() = false
            }
            adapter = gridAdapter
        }
        gridAdapter.setElements(populateDummyGridData())

        carouselAdapter =
            CarouselAdapter(
                this
            )
        carouselRV.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = carouselAdapter
        }
        carouselAdapter.setElements(populateDummyCarouselData())

        talkBackViewModel.talkBackStateLiveData().observe(this) { onTalkBackStateChanged(it) }
    }

    private fun populateDummyGridData() = mutableListOf<RecyclerViewType>().apply {
        for (index in 0..AMOUNT_OF_DUMMY_DATA) {
            add(
                GridElementViewType(
                    GridElement(
                        index,
                        getString(R.string.dummy_data_prefix, index + 1)
                    )
                )
            )
        }
    }

    private fun populateDummyCarouselData() = mutableListOf<RecyclerViewType>().apply {
        for (index in 0..AMOUNT_OF_DUMMY_DATA) {
            add(
                CarouselElementViewType(
                    CarouselElement(
                        index,
                        getString(R.string.dummy_data_prefix, index + 1)
                    )
                )
            )
        }
    }

    private fun onTalkBackStateChanged(isOn: Boolean) {
        if (isOn) accessibility_state.text = getString(R.string.accessibility_state_on)
        else accessibility_state.text = getString(R.string.accessibility_state_off)
    }

    override fun onGridElementClicked(gridElement: GridElement) {
        carouselAdapter.scrollToGridSelectedItem(carouselRV, gridElement.id)
    }

    override fun onCarouselElementClicked(carouselElement: CarouselElement) {
        Toast.makeText(
            applicationContext,
            getString(R.string.carousel_element_pressed_suffix, carouselElement.value),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return event?.let {
            currentFocus?.let { focusView ->
                talkBackViewModel.dispatchKeyEvent(it, WeakReference(focusView))
            }
        } ?: super.dispatchKeyEvent(event)
    }

    companion object {
        const val NUMBER_OF_COLUMNS = 3
        const val AMOUNT_OF_DUMMY_DATA = 10
    }
}
