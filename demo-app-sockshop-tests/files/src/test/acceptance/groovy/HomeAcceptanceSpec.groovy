import geb.Module
import geb.Page
import geb.spock.GebReportingSpec
import spock.lang.Stepwise

class HomePage extends Page {
    static url = "/index.html"
    static at = { title == "WeaveSocks" }
    static content = {
    	loginLink (required: true, wait: true) { $("#login > a") }
    	loggedUser (required: false, cache: false) { $("#howdy > a") }
    	itemsInCart (required: true, wait: true, cache: false) { $("#numItemsInCart") }
    	offerOfTheDay { $("#top > div.container > div.col-md-6.offer > a:nth-child(2)") }

        loginForm { module LoginFormModule }
    }
}

class LoginFormModule extends Module {

    static content = {
        username { $("#username-modal") }
	    password { $("#password-modal") }
	    loginButton(to: HomePage) { $("#login-modal > div > div > div.modal-body > form > p > button") }
    }

    void fillInLoginDetails() {
        username.value("Eve_Berger")
        password.value("duis")
    }

    void doLogin(){
        loginButton.click()
    }
}

/**
 * Based on: https://github.com/microservices-demo/front-end/blob/master/test/e2e/homepage_test.js
 */
@Stepwise
class HomeAcceptanceSpec extends GebReportingSpec {

    def "Home page looks sexy"() {
        expect:
        given: "The user goes to home page"
        to HomePage

        and: "The info is displayed"
        assert loginLink
        assert !loggedUser
        assert itemsInCart.text().equals("0 items in cart")
        assert offerOfTheDay.text().equals("Buy 1000 socks, get a shoe for free!")
    }
}
