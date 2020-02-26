package com.gorillalogic.miguelhincapie.accessibilitycases.ui.view

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.TalkBackFacade
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.entities.CarouselElement
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.entities.GridElement
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.*
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel.CarouselAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel.CarouselElementDelegateAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel.CarouselElementViewType
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid.GridAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid.GridElementDelegateAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid.GridElementViewType
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

const val NUMBER_OF_COLUMNS = 3

class MainActivity : AppCompatActivity(),
    GridElementDelegateAdapter.OnGridElementListener,
    CarouselElementDelegateAdapter.OnCarouselElementListener {

    private lateinit var gridAdapter: GridAdapter
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDependencies()

        gridAdapter =
            GridAdapter(
                this
            )
        gridRV.apply {
            layoutManager = object : GridLayoutManager(applicationContext,
                NUMBER_OF_COLUMNS
            ) {
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
    }

    private fun initDependencies() {
        TalkBackFacade.contextWeakReference = WeakReference(applicationContext)
    }

    private fun populateDummyGridData() = mutableListOf<RecyclerViewType>().apply {
        for (index in 0..10) {
            add(
                GridElementViewType(
                    GridElement(
                        index,
                        "Element ${index + 1}"
                    )
                )
            )
        }
    }

    private fun populateDummyCarouselData() = mutableListOf<RecyclerViewType>().apply {
        for (index in 0..10) {
            add(
                CarouselElementViewType(
                    CarouselElement(
                        index,
                        "Element ${index + 1}"
                    )
                )
            )
        }
    }

    override fun onGridElementClicked(gridElement: GridElement) {
        carouselAdapter.scrollToGridSelectedItem(carouselRV, gridElement.id)
    }

    override fun onCarouselElementClicked(carouselElement: CarouselElement) {
        Toast.makeText(applicationContext, "${carouselElement.value} pressed", Toast.LENGTH_SHORT)
            .show()
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return super.dispatchKeyEvent(event)
    }
}
