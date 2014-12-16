## What is the MobilePay AppSwitch SDK?
The MobilePay SDK enables your app to receive payments through the MobilePay app.
![][1]

**What happens is**  
1. You initiate the payment from your own app through the SDK  
2. It automatically switches to the MobilePay App where you can sign  
3. You confirm the payment  
4. Your receipt id shown and you can either tap "videre" or wait for five seconds  
5. It switches back to your own app together with a MobilePay transactionId  

## Requirements
* You have a MobilePay business agreement with Danske Bank. Read more https://www.danskebank.dk/da-dk/Erhverv/pages/mobilepaybusiness.aspx
* You have received a MerchantId from Danske Bank that you must use when communicating through the SDK
* You have downloaded the latest SDK for the wanted platform. https://github.com/DanskeBank/MobilePay-AppSwitch-SDK/tree/master/sdk

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
