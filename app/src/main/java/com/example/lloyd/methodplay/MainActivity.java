package com.example.lloyd.methodplay;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        int price = calculatePrice();
        String priceMessage = createOrderSumary(price);
        displayMessage(priceMessage);

    }

      /*
    * calculate the price of the order
    * quantity is the number of cups of coffee order
     */

    private int calculatePrice()
    {
        return quantity * 5;

    }

    /*
    *order summary method is like a slip
     */
    private String createOrderSumary(int price)
    {
        String priceMessage = "Name: Lloyd Kagiso";
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

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /*
 * when the decrement button is clicked then this method is been displayed
  */
    public void decrement(View view)
    {

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
