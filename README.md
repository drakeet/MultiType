# MultiType
An Android library to retrofit multiple item view types

Previously, when we need to develop a complex RecyclerView/ListView, it is a boring and troublesome thing. 
We should override the `getItemViewType` of `RecyclerView.Adapter` and add some types, 
then we create some `ViewHolder` to relate the type, all of the process it is a very bad experience.
**And once we need to add a new type, we have to go to the original Adapter and modify some old codes**, so sad. 

Today, I create a new way to easily develop the complex RecyclerView/ListView, with my MultiType library, 
no matter how complex and how frequently changing list, we could insert a new type without changing the old codes.
