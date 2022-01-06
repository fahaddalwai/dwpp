package com.twentyFourSeven.android.apb.commons

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController

/**
 * This function is used to 'safely' navigate to a destination, making sure that navigation actions are not triggered if -
 * i. [actionId] does not exist in the current destination AND
 * ii. current destination is not same as [destinationId]
 */
fun Fragment.navigateToDestination(
    destinationId: Int,
    actionId: Int,
    bundle: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    if (isAdded)
        if (this.findNavController().currentDestination?.id != destinationId &&
            this.findNavController().currentDestination?.getAction(actionId) != null
        )
            this.findNavController().navigate(
                actionId,
                bundle,
                navOptions,
                navigatorExtras
            )
}