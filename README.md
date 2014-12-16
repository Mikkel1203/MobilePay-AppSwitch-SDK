## MobilePay AppSwitch
The MobilePay SDK enables your app to receive payments through the MobilePay app.
![][1]

**How it works**  
1. You initiate the payment from your own app through the SDK.

2. It automatically switches to the MobilePay App where the user is asked to sign in.

3. The user confirms the payment.

4. The receipt is shown and the user can either tap "videre" or wait for five seconds.

5. MobilePay switches back to your own app together with a MobilePay transactionId.


## Requirements
* You have a MobilePay business agreement with Danske Bank. Read more at [MobilePay Business](https://www.danskebank.dk/da-dk/Erhverv/pages/mobilepaybusiness.aspx).
* You have received a MerchantId from Danske Bank that you must use when communicating through the SDK.
* You have downloaded the latest SDK for the wanted platform: [AppSwitch SDK](https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/tree/master/sdk).

  [1]: https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/blob/master/doc/wiki/images/mobilepay_appswitch_purchase_flow.png "MobilePay AppSwitch purchase flow"

## MerchantID for test purposes
**APPDK0000000000** can be used for test purposes. When the test merchantId is used you are able to complete the payment flow without transferring any money.

## Documentation
 * [Getting Started on iPhone](https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/wiki/Getting-Started-on-iPhone)
 * [Getting started on Android](https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/wiki/Getting-started-on-Android)
 * [Getting Started on Windows Phone](https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/wiki/Getting-Started-on-Windows-Phone)
 * [Error Handling](https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/wiki/Error-handling)
 * [Parameter Specification](https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/wiki/Parameter-Specification)
 * [Security](https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/wiki/Security)
 * [F.A.Q.](https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/wiki/F.A.Q.)
