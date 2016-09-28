package com.example.lloyd.methodplay;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    * when the order button is clicked then this method is been displayed
     */
    public void submitOrder(View view)
    {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream =  whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.hasChocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSumary(name, price, hasWhippedCream);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
        displayMessage(priceMessage);


    }

      /*
    * calculate the price of the order
    * quantity is the number of cups of coffee order
     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate)
    {
        int basePrice = 5;

        if(addWhippedCream)
        {
            basePrice = basePrice + 1;
        }

        if(addChocolate)
        {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;

    }

    /*
    *order summary method is like a slip
     */
    private String createOrderSumary(String name, int price, boolean addWhippedCream)
    {
        String priceMessage = "Name: " + name;
        priceMessage = priceMessage + "\nAdd whipped cream " + addWhippedCream;
        priceMessage = priceMessage + "\nQuantity" + quantity;
        priceMessage = priceMessage + "\nTotal: $ "  + price;
        priceMessage = priceMessage + "\n Thank you! ";
        return priceMessage;
    }

    /*
   * when the increment button is clicked then this method is been displayed
    */
    public void increment(View view)
    {
        if (quantity == 100)
        {
            Toast.makeText(this,"You cannot have more than 100 coffee", Toast.LENGTH_LONG ).show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /*
 * when the decrement button is clicked then this method is been displayed
  */
    public void decrement(View view)
    {
        if (quantity == 1)
        {
            Toast.makeText(this,"You cannot have less than 1 coffee", Toast.LENGTH_LONG ).show();
            return;
        }
        quantity = quantity -1;
        displayQuantity(quantity);
    }


    private void displayQuantity(int numberOfCoffee)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffee);

    }



    /*
    * this method displays the given text on screen
     */
    private void displayMessage(String message)
    {
        TextView orderTextView = (TextView) findViewById(R.id.order_text_view);
        orderTextView.setText(message);
    }


}
