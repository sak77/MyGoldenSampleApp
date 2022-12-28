package com.saket.data.cache.di

import dagger.Subcomponent

/*
I am not using sub-component here..but this is just for
reference.
 */
@Subcomponent(modules = [RoomModule::class])
interface DataSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DataSubComponent
    }

    /*
    @Subcomponent.Builder
     */
    interface Builder {
        //Need to figure out how builder works..
    }
}