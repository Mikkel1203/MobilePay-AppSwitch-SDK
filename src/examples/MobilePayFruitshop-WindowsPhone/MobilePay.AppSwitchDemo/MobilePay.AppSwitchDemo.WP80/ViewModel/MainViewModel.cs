using GalaSoft.MvvmLight;
using GalaSoft.MvvmLight.Command;
using System;
using System.IO;
using System.Text;
using System.Collections.Generic;
using Windows.System;
using MobilePay.Models;
using MobilePay.Extensions;
using MobilePay;
using System.Windows;
using System.Threading.Tasks;
using MobilePay.AppSwitchDemo.Services;
using MobilePay.AppSwitchDemo.Models;

namespace MobilePay.AppSwitchDemo.ViewModel
{
    public class MainViewModel : BaseViewModel
    {
        private readonly IProductService productService;
        private readonly IMobilePayService mobilePayService;
        private readonly IPaymentResultService paymentResultService;
        private readonly IPaymentResultHandler paymentResultHandler;

        public List<Product> Products { get; set; }

        public RelayCommand<Product> BuyProductCommand { get; set; }


        public MainViewModel(IProductService productService, IMobilePayService mobilePayService, IPaymentResultService paymentResultService, IPaymentResultHandler paymentResultHandler)
        {
            this.productService = productService;
            this.mobilePayService = mobilePayService;
            this.paymentResultService = paymentResultService;
            this.paymentResultHandler = paymentResultHandler;

            BuyProductCommand = new RelayCommand<Product>(Buy);
            Products = new List<Product>(productService.GetAllProducts());
        }

        public async void Buy(Product product)
        {

            var payment = new Payment
            {
                OrderId = Guid.NewGuid().ToString(),
                ProductPrice = product.Price,
                ReceiptMessage = "Tak fordi du handlede hos os, nyd din frugt!",
                ProductName = product.Title
            };

            var uri = mobilePayService.CreatePaymentLaunchUri(payment);

            await Launcher.LaunchUriAsync(uri);
        }

        public async void LoadFromQueryString(IDictionary<string, string> queryString)
        {
            await Task.Delay(300);
            await paymentResultService.HandlePaymentResultFromQueryStringAsync(queryString, paymentResultHandler.Success, paymentResultHandler.Failure);
        }
    }
}