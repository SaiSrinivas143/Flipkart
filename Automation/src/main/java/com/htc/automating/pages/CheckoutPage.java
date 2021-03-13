package com.htc.automating.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.htc.automating.util.Utitlity;

public class CheckoutPage extends BasePage{
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	private Reporter reporter;

	@FindBy(xpath="//ul[@class=\"checkout-types top\"]")
	private WebElement proceedtocheckoutbutton;
	
	@FindBy(xpath="//select[@name=\"billing_address_id\"]")
	private WebElement billingaddress;
	
	@FindBy(xpath="//select[@name=\"billing[country_id]\"]")
	private WebElement countryid;
	
	@FindBy(xpath="//input[@title=\"Zip/Postal Code\"][1]")
	private WebElement zipcode;
	
	@FindBy(xpath="//input[@name=\"billing[city]\"]")
	private WebElement city;
	
	@FindBy(xpath="//input[@title=\"Street Address\"][@name=\"billing[street][]\"]")
	private WebElement street;
	
	@FindBy(xpath="//input[@name=\"billing[telephone]\"]")
	private WebElement phonenumber;
	
	@FindBy(xpath="//button[@onclick=\"billing.save()\"]")
	private WebElement billing;
	
	@FindBy(xpath="//input[@value=\"freeshipping_freeshipping\"]")
	private WebElement freeshippingbox;
	
	@FindBy(xpath="//button[@onclick=\"shippingMethod.save()\"]")
	private WebElement freeshingcontinuebutton;
	
	@FindBy(xpath="//button[@onclick=\"payment.save()\"]")
	private WebElement paymentbutton;
	
	@FindBy(xpath="//button[@onclick=\"review.save();\"]")
	private WebElement reviewbutton;
	
	@FindBy(xpath="//h1[contains(text(),\"received\")]")
	private WebElement successmsg;
	
	public boolean isSuccessmsgDisplayed() {
		return successmsg.isDisplayed();
	}
	
	private void setCountry(String id) {
		Select cid=new Select(countryid);
		cid.selectByValue(id);
	}
	
	public void doCheckOut() {
		waitUtilThePageLoad();
		waitUntillTheElementToBeClickable(proceedtocheckoutbutton);
		this.proceedtocheckoutbutton.click();
		reporter.log("Checkout button clicked");
		try {
			waitUntillTheElementToBeClickable(billingaddress);
			Select nadd=new Select(billingaddress);
			nadd.selectByVisibleText("New Address");
		}
		finally {
			this.setCountry("IN");
			this.zipcode.sendKeys("65468");
			this.city.clear();
			this.city.sendKeys("nellore");
			this.street.sendKeys("lwuhf654");
			this.phonenumber.clear();
			this.phonenumber.sendKeys("6468484654");
			this.billing.click();
		}
		waitUntillTheElementToBeClickable(freeshippingbox);
		this.freeshippingbox.click();
		reporter.log("Got address for shipping the product");
		waitUntillTheElementToBeClickable( freeshingcontinuebutton);
		this.freeshingcontinuebutton.click();
		waitUntillTheElementToBeClickable( paymentbutton);
		this.paymentbutton.click();
		reporter.log("payment is successful");
		waitUntillTheElementToBeClickable(reviewbutton);
		this.reviewbutton.click();	
	}
}