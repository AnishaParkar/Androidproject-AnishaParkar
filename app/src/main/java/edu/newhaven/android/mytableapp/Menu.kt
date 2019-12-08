package edu.newhaven.android.mytableappv1


import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class Menu : GenericMethods() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this@Menu, R.layout.activity_menu)

        //Item 1
        menu_add1.onClick {
            var number = menu_number1.text.toString().toInt()
            number += 1
            menu_number1.text = number.toString()
        }

        menu_sub1.onClick {
            var number = menu_number1.text.toString().toInt()
            if (number >= 1)
                number -= 1
            menu_number1.text = number.toString()
        }

        //Item 2
        menu_add2.onClick {
            var number = menu_number2.text.toString().toInt()
            number += 1
            menu_number2.text = number.toString()
        }

        menu_sub2.onClick {
            var number = menu_number2.text.toString().toInt()
            if (number >= 1)
                number -= 1
            menu_number2.text = number.toString()
        }

        //Item 3
        menu_add3.onClick {
            var number = menu_number3.text.toString().toInt()
            number += 1
            menu_number3.text = number.toString()
        }

        menu_sub3.onClick {
            var number = menu_number3.text.toString().toInt()
            if (number >= 1)
                number -= 1
            menu_number3.text = number.toString()
        }

        //Item 4
        menu_add4.onClick {
            var number = menu_number4.text.toString().toInt()
            number += 1
            menu_number4.text = number.toString()
        }

        menu_sub4.onClick {
            var number = menu_number4.text.toString().toInt()
            if (number >= 1)
                number -= 1
            menu_number4.text = number.toString()
        }

        val ref = database.reference.child("Restaurant").child("Olive Garden").child("Menu").child("Dinner").child("Antipasti")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                // Item 1
                val item1 = p0.children.elementAt(0)

                menu_itemName1.text = item1.key
                menu_description1.text = item1.child("Description").value.toString()
                menu_cost1.text = item1.child("Cost").value.toString()
                menu_calories1.text = item1.child("Calories").value.toString()

                // Item 2
                val item2 = p0.children.elementAt(1)

                menu_itemName2.text = item2.key
                menu_description2.text = item2.child("Description").value.toString()
                menu_cost2.text = item2.child("Cost").value.toString()
                menu_calories2.text = item2.child("Calories").value.toString()

                // Item 3
                val item3 = p0.children.elementAt(2)

                menu_itemName3.text = item3.key
                menu_description3.text = item3.child("Description").value.toString()
                menu_cost3.text = item3.child("Cost").value.toString()
                menu_calories3.text = item3.child("Calories").value.toString()

                // Item 4
                val item4 = p0.children.elementAt(3)

                menu_itemName4.text = item4.key
                menu_description4.text = item4.child("Description").value.toString()
                menu_cost4.text = item4.child("Cost").value.toString()
                menu_calories4.text = item4.child("Calories").value.toString()
            }
        })


    }
}


