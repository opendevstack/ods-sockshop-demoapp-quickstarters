(function (){
  'use strict';

  var util = require('util');

  var domain = "-" + process.env.OPENSHIFT_BUILD_NAMESPACE + "." + process.env.OPENSHIFT_HOST;
  if (process.env.USE_SERVICE === 'true') {
     domain = ':8080'
  }
  process.argv.forEach(function (val, index, array) {
    var arg = val.split("=");
    if (arg.length > 1) {
      if (arg[0] == "--domain") {
        domain = "." + arg[1];
        console.log("Setting domain to:", domain);
      }
    }
  });

  module.exports = {
    catalogueUrl:  util.format("http://demo-app-catalogue%s", domain),
    tagsUrl:       util.format("http://demo-app-catalogue%s/tags", domain),
    cartsUrl:      util.format("http://demo-app-carts%s/carts", domain),
    ordersUrl:     util.format("http://demo-app-orders%s", domain),
    customersUrl:  util.format("http://demo-app-user%s/customers", domain),
    addressUrl:    util.format("http://demo-app-user%s/addresses", domain),
    cardsUrl:      util.format("http://demo-app-user%s/cards", domain),
    loginUrl:      util.format("http://demo-app-user%s/login", domain),
    registerUrl:   util.format("http://demo-app-user%s/register", domain),
  };
}());
