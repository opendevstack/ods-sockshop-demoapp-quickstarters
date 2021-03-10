(function (){
  'use strict';

  var expect    = require("chai").expect
    , endpoints = require("../../api/endpoints")

  describe("endpoints", function() {
    describe("catalogueUrl", function() {
      it("points to the proper endpoint", function() {
        expect(endpoints.catalogueUrl).to.equal("http://demo-app-catalogue.test");
      });
    });

    describe("tagsUrl", function() {
      it("points to the proper endpoint", function() {
        expect(endpoints.tagsUrl).to.equal("http://demo-app-catalogue.test/tags");
      });
    });

    describe("cartsUrl", function() {
      it("points to the proper endpoint", function() {
        expect(endpoints.cartsUrl).to.equal("http://demo-app-carts.test/carts");
      });
    });

    describe("ordersUrl", function() {
      it("points to the proper endpoint", function() {
        expect(endpoints.ordersUrl).to.equal("http://demo-app-orders.test");
      });
    });

    describe("customersUrl", function() {
      it("points to the proper endpoint", function() {
        expect(endpoints.customersUrl).to.equal("http://demo-app-user.test/customers");
      });
    });

    describe("addressUrl", function() {
      it("points to the proper endpoint", function() {
        expect(endpoints.addressUrl).to.equal("http://demo-app-user.test/addresses");
      });
    });

    describe("cardsUrl", function() {
      it("points to the proper endpoint", function() {
        expect(endpoints.cardsUrl).to.equal("http://demo-app-user.test/cards");
      });
    });

    describe("loginUrl", function() {
      it("points to the proper endpoint", function() {
        expect(endpoints.loginUrl).to.equal("http://demo-app-user.test/login");
      });
    });

    describe("registerUrl", function() {
      it("points to the proper endpoint", function() {
        expect(endpoints.registerUrl).to.equal("http://demo-app-user.test/register");
      });
    });
  });
}());
