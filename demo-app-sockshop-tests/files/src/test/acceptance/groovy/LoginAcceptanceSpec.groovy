import geb.Page
import geb.spock.GebReportingSpec
import spock.lang.Stepwise

/**
 * Based on: https://github.com/microservices-demo/front-end/blob/master/test/e2e/login_test.js
 */
@Stepwise
class LoginAcceptanceSpec extends GebReportingSpec {

    def "EDPTB143_User logs in"() {
        expect:
        given: "The user goes to home page"
        to HomePage
        assert !loggedUser
         
        and: "Fill the login data"
        loginLink.click()
        waitFor { loginForm.loginButton.displayed }
        SpecHelper.printEvidenceForPageElements(this, 1, HomePage,
            [ 
              ['fragment' : $("#username-modal"), 'description' : 'username empty'], 
              ['fragment' : $("#password-modal"), 'description' : 'password empty']
            ]
        )
        loginForm.fillInLoginDetails()
      
        SpecHelper.printEvidenceForPageElements(this, 2, HomePage,
            [ 
              ['fragment' : $("#username-modal"), 'description' : 'username filled'], 
              ['fragment' : $("#password-modal"), 'description' : 'password filled']
            ]
        )

        true
        /*

        when: "Do the login"
        loginForm.doLogin()
        sleep(20000)
        
        then: "Is logged in home"
        at HomePage
        // Logged user selector
        $("#howdy > a").text() == "Logged in as Eve Berger"
        */
    }
}
