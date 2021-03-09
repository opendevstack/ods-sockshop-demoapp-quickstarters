import geb.Page
import geb.spock.GebReportingSpec
import spock.lang.Stepwise

class CartPage extends Page {
    static url = "/basket.html"
    static at = { title == "WeaveSocks" }
    static content = {
    	checkoutLink(to: CustomerOrdersPage, wait: true, cache: false) { $("#orderButton") }
    }
}

/**
 * Based on: https://github.com/microservices-demo/front-end/blob/master/test/e2e/cart_test.js
 */
@Stepwise
class CartAcceptanceSpec extends GebReportingSpec {

    def "EDPTB140_Users can put items into the shopping cart"() {
        new SpecHelper().resetCart("5df1f0179e602c00011dd5dc")

        // Test inputs
        // n/a

        given: "The user goes to home page"
        to HomePage
        assert !loggedUser
         
        and: "Fill the login data"
        loginLink.click()
        waitFor { loginForm.loginButton.displayed }
        loginForm.fillInLoginDetails()

        when: "Do the login"
        loginForm.doLogin()
        sleep(2000)
        assert $("#numItemsInCart").text().equals("0 items in cart")
        // Provide evidence for test step 1
        SpecHelper.printEvidenceForPageElement(this, 1, HomePage, $("#numItemsInCart"), "cart is empty")
        
        then: "Goes to catalogue"
        to CataloguePage
        waitFor { addFirstProductLink.displayed }
                
        and: "Add one item to the cart"
        addFirstProductLink.click()

        then: "Verify the item is added"
        to HomePage
        sleep(20000)
        assert $("#numItemsInCart").text().equals("1 item(s) in cart")
        // Provide evidence for test step 2
        SpecHelper.printEvidenceForPageElement(this, 2, HomePage, $("#numItemsInCart"), "1 item is in the cart")
    }
}
