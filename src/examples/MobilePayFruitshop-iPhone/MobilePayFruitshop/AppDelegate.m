//
//  AppDelegate.m
//  MobilePayFruitshop
//
//  Created by Troels Richter on 18/09/14.
//  Copyright (c) 2014 DanskeBank A/S. All rights reserved.
//

#import "AppDelegate.h"
#import "ViewHelper.h"
#import "MobilePayManager.h"

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    [[MobilePayManager sharedInstance] setupWithMerchantId:@"APPDK0000000000" merchantUrlScheme:@"fruitshop"];
    return YES;
}

- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

-(BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url {
    
    [[MobilePayManager sharedInstance] handleMobilePayCallbacksWithUrl:url success:^(NSString *orderId, NSString *transactionId, NSString *signature) {
        NSLog(@"MobilePay purchase succeeded: Your have now payed for order with id '%@' and MobilePay transaction id '%@'", orderId, transactionId);
        [ViewHelper showAlertWithTitle:@"MobilePay Succeeded" message:[NSString stringWithFormat:@"You have now payed with MobilePay. Your MobilePay transactionId is '%@'", transactionId]];
    } error:^(NSString *orderId, int errorCode, NSString *errorMessage) {
        NSLog(@"MobilePay purchase failed:  Error code '%i' and message '%@'",errorCode,errorMessage);
        [ViewHelper showAlertWithTitle:[NSString stringWithFormat:@"MobilePay Error %i",errorCode] message:errorMessage];
    } cancel:^(NSString *orderId) {
        NSLog(@"MobilePay purchase with order id '%@' canceled by user", orderId);
        [ViewHelper showAlertWithTitle:@"MobilePay Canceled" message:@"You canceled the payment flow from MobilePay, please pick a fruit and try again"];
    }];
    return YES;
}

@end
