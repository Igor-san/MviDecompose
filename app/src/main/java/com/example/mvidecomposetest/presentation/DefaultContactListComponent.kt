package com.example.mvidecomposetest.presentation

import com.arkivanov.decompose.ComponentContext
import com.example.mvidecomposetest.core.componentScope
import com.example.mvidecomposetest.domain.Contact

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.extensions.coroutines.labels //без этго будет ошибка в store.labels.collect Cannot access class 'com.arkivanov.mvikotlin.rx.Disposable'. Check your module classpath for missing or conflicting dependencies
import kotlinx.coroutines.ExperimentalCoroutinesApi


class DefaultContactListComponent(
    componentContext: ComponentContext,
    val onEditingContactRequested: (Contact) -> Unit,
    val onAddContactRequested: () -> Unit,
) : ContactListComponent, ComponentContext by componentContext  {

   private lateinit var store: ContactListStore

   init{
       componentScope().launch {
           store.labels.collect{
               when(it){
                   ContactListStore.Label.AddContact -> onAddContactRequested()
                   is ContactListStore.Label.EditContact -> onEditingContactRequested(it.contact)
               }
           }
       }


   }
    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<ContactListStore.State>
        get() = store.stateFlow //Type mismatch.
/*

Property type is StateFlow<com.example.mvidecomposetest.presentation.ContactListStore.State>, which is not a subtype type of overridden public abstract val model: StateFlow<com.example.mvidecomposetest.presentation.EditContactStore.State> defined in com.example.mvidecomposetest.presentation.ContactListComponent
 */

    override fun onContactClicked(contact: Contact) {
        store.accept(ContactListStore.Intent.SelectContact(contact))
    }

    override fun onAddContactClicked() {
        store.accept(ContactListStore.Intent.AddContact)
    }
}