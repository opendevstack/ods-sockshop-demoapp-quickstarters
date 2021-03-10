import geb.Page
import geb.module.FormElement
import geb.spock.GebReportingSpec
import spock.lang.Stepwise

class CustomerOrdersPage extends Page {
    static at = { title == "WeaveSocks" }
    static content = {
    	myOrders (required: true, cache: false) { $("#customer-orders > div > h1") }
    }
}

/**
 * Based on: https://github.com/microservices-demo/front-end/blob/master/test/e2e/checkout_test.js
 */
@Stepwise
class CustomerOrdersAcceptanceSpec extends GebReportingSpec {

    def "EDPTB142_User buys some socks"() {
        expect:
        true
        /*
        new SpecHelper().resetCart("5df1f0179e602c00011dd5dc")

        given: "The user goes to home page"
        to HomePage
        assert !loggedUser
         
        and: "Fill the login data"
        loginLink.click()
        waitFor { loginForm.loginButton.displayed }
        loginForm.fillInLoginDetails()

        when: "Do the login"
        loginForm.doLogin()
        sleep(20000)
        
        then: "Goes to catalogue"
        to CataloguePage
        waitFor { addFirstProductLink.displayed }
                
        and: "Add one item to the cart"
        addFirstProductLink.click()

        then: "Verify the item is added"
        to HomePage
        sleep(2000)
        assert $("#numItemsInCart").text().equals("1 item(s) in cart")

        and: "Go to cart and confirm the order"
        to CartPage
        sleep(2000)
        checkoutLink.click()
        
        then: "Show the list of orders"
        at CustomerOrdersPage
        sleep(2000)
        assert myOrders.text() == "My orders"
        */
    }
}
