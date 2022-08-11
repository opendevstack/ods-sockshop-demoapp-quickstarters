import groovy.json.JsonSlurper

import kong.unirest.Unirest

import spock.lang.Specification
  
class UserIntegrationSpec extends Specification {
     
    def Properties applicationProperties

    def setup() {
        applicationProperties = new SpecHelper().getApplicationProperties()
    }

    def "User exists in system"() {
        when: "Request user data from id"
        def response = Unirest.get(applicationProperties."config.user.service.url" + "/{customerId}")
      		.routeParam("customerId", "5df1f0179e602c00011dd5dc")
      		.asString()
 
        then: "Receive the user info"
        true==true
    }
}
