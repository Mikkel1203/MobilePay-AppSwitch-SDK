package dk.danskebank.mobilepayfruitshop;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import dk.danskebank.mobilepay.sdk.MobilePay;
import dk.danskebank.mobilepay.sdk.ResultCallback;
import dk.danskebank.mobilepay.sdk.model.FailureResult;
import dk.danskebank.mobilepay.sdk.model.Payment;
import dk.danskebank.mobilepay.sdk.model.SuccessResult;

public class MainActivity extends ListActivity {
    private static final int MOBILEPAY_PAYMENT_REQUEST_CODE = 1337;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the IntentHelper with your own Merchant ID
        MobilePay.getInstance().init(getString(R.string.merchant_id_generic));

        // Create some dummy items and setup the list adapter
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product(getString(R.string.product_name_orange), new BigDecimal(10), R.drawable.orange));
        products.add(new Product(getString(R.string.product_name_kiwi), new BigDecimal(0.56), R.drawable.kiwi));
        products.add(new Product(getString(R.string.product_name_strawberry), new BigDecimal(4.43), R.drawable.strawberry));
        products.add(new Product(getString(R.string.product_name_basket), new BigDecimal(1501.52), R.drawable.fruit_basket));

        adapter = new ProductAdapter(this, products);

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Product product = adapter.getItem(position);

        // Create a new payment object
        Payment payment = new Payment();

        // Set the product price
        payment.setProductPrice(product.getPrice());

        // Set the message shown at the bottom of the receipt in MobilePay
        payment.setReceiptMessage(getString(R.string.payment_receipt_message));

        // Set the order ID. This is your reference and should match your business case
        payment.setOrderId(UUID.randomUUID().toString());

        // Create an Intent with the Payment specified
        Intent paymentIntent = MobilePay.getInstance().createPaymentIntent(payment);

        // Query the SDK to see if MobilePay is present
        boolean isMobilePayInstalled = MobilePay.getInstance().isMobilePayInstalled(this);

        if (isMobilePayInstalled) {
            // Start a new activity with the Intent and a specific request code
            startActivityForResult(paymentIntent, MOBILEPAY_PAYMENT_REQUEST_CODE);
        } else {
            // Error dialog, with possible download
            downloadMobilePayApp();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MOBILEPAY_PAYMENT_REQUEST_CODE) {
            // We received a payment response matching our request code
            MobilePay.getInstance().handleResult(resultCode, data, new ResultCallback() {
                @Override
                public void onSuccess(SuccessResult result) {
                    // The payment succeeded. SuccessResult holds further information.
                    showPaymentResultDialog(getString(R.string.payment_result_dialog_success_title), getString(R.string.payment_result_dialog_success_message, result.getTransactionId()));
                }

                @Override
                public void onFailure(FailureResult result) {
                    // The payment failed. FailureResult holds further information.
                    showPaymentResultDialog(getString(R.string.payment_result_dialog_error_title, String.valueOf(result.getErrorCode())), result.getErrorMessage());
                }

                @Override
                public void onCancel() {
                    // The payment was cancelled
                    showPaymentResultDialog(getString(R.string.payment_result_dialog_cancelled_title), getString(R.string.payment_result_dialog_cancelled_message));
                }
            });
        }
    }

    private void showPaymentResultDialog(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(content)
                .setPositiveButton(getString(R.string.payment_result_dialog_positive), null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void downloadMobilePayApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.install_mobilepay_dialog_title))
                .setMessage(getString(R.string.install_mobilepay_dialog_message))
                .setPositiveButton(getString(R.string.install_mobilepay_dialog_positive_text), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Create a MobilePay download Intent
                        Intent intent = MobilePay.getInstance().createDownloadMobilePayIntent(getBaseContext());
                        startActivity(intent);
                    }
                })
                .setNegativeButton(getString(R.string.install_mobilepay_dialog_negative_text), null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}