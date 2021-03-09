import groovy.json.JsonSlurper

import kong.unirest.HttpResponse
import kong.unirest.Unirest

import spock.lang.Specification
  
class InstallationSpec extends Specification {
    
    def Properties applicationProperties

    def setup() {
        applicationProperties = new SpecHelper().getApplicationProperties()
    }

    def "The product catalogue database is correctly installed"() {
        expect:
   	    when:
        HttpResponse response = Unirest.get(applicationProperties."config.catalogue.service.url" + "/health")
      		.header("accept", "application/json")
      		.asString();
        
        def result = new JsonSlurper().parseText(response.body)

        then:
        result.health.each { service ->
            // check the status of database through service health associated in microservice
            if (service.service.equals("catalogue-db")) {
                service.status == "OK"
            }
        }
    }

    def "The frontend is correctly installed"() {
   	    when:
        HttpResponse response = Unirest.get(applicationProperties."config.application.url")
      		.header("accept", "application/json")
      		.asString();
        
        then:
        response.status == 200
    }

    def "The payment service is correctly installed"() {
   	    when:
        HttpResponse response = Unirest.get(applicationProperties."config.payment.service.url" + "/health")
      		.header("accept", "application/json")
      		.asString();
        
        def result = new JsonSlurper().parseText(response.body)

        then:
        result.health.each { service ->
            service.status == "OK"
        }
    }

    def "The orders service is correctly installed"() {
   	    when:
        HttpResponse response = Unirest.get(applicationProperties."config.order.service.url" + "/health")
      		.header("accept", "application/json")
      		.asString();
        
        def result = new JsonSlurper().parseText(response.body)

        then:
        result.health.each { service ->
            service.status == "OK"
        }
    }

    def "The shipping service is correctly installed"() {
    	when:
        HttpResponse response = Unirest.get(applicationProperties."config.shipping.service.url" + "/health")
      		.header("accept", "application/json")
      		.asString();
        
        def result = new JsonSlurper().parseText(response.body)

        then:
        result.health.each { service ->
            service.status == "OK"
        }
    }
}

