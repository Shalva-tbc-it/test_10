package com.example.testteen.presentation.screen.home

import com.example.testteen.databinding.FragmentHomeBinding
import com.example.testteen.presentation.common.BaseFragment
import com.example.testteen.presentation.screen.home.bottom_sheet.account_number.AccountNumberFragment
import com.example.testteen.presentation.screen.home.bottom_sheet.cards.CardsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), CardsFragment.PlaceSearchListener {

    private var cardsSheet: CardsFragment = CardsFragment()
    private var accountSheet: AccountNumberFragment = AccountNumberFragment()

    override fun bind() {

    }

    override fun bindViewActionListener() {
        listener()
    }

    override fun bindObserves() {

    }

    private fun listener() {
        binding.imgFrom.setOnClickListener {
            cardsSheet.setSearchListener(this)
            cardsSheet.show(parentFragmentManager, cardsSheet.tag)
        }

        binding.imgTo.setOnClickListener {
            accountSheet.show(parentFragmentManager, cardsSheet.tag)
        }
    }

    override fun cards(latLng: String) {

    }

}